/**
 * 设置验证数据
 * @type {{setRulesAndMessagesData: Function}}
 */
var SetValidateDate = {
    setRulesAndMessagesData: function (obj) {
        var formData = $(obj).find('input[type !="hidden"]');
        var data = {};
        var rules = {};
        var messages = {};
        $.each(formData, function (i, o) {
            if (!$.isEmptyObject($(o).attr('name')) && !$.isEmptyObject($(o).attr('placeholder'))) {
                var name = $(o).attr('name');//获取name里的值
                var rulesChild = {};
                //rules
                rulesChild.required = true;
                rulesChild.rangelength = [3, 20];
                rules[name] = rulesChild;

                //messages
                var value = $(o).attr('placeholder');//获取placeholder里的值
                var messagesChild = {};
                if (value == 'Username') {
                    messagesChild.required = "用户名不能为空!";
                    messagesChild.rangelength = $.format("用户名长度为:{0}-{1}个字符");
                } else if (value == 'Password') {
                    messagesChild.required = "密码不能为空!";
                    messagesChild.rangelength = $.format("密码长度为:{0}-{1}个字符");
                } else {
                    messagesChild.required = value + "不能为空!";
                    messagesChild.rangelength = $.format(value + "最小长度:{0}, 最大长度:{1}");
                }
                messages[name] = messagesChild;
            }
        });
        data.rules = rules;
        data.messages = messages;
        return data;
    }
};

/**
 * form数据格式化json
 * @type {{dataFormate: Function}}
 */
var GetFormDataForJson = {
    dataFormate: function (obj) {
        var formData = $(obj).find('input');
        var jsonData = {};
        $.each(formData, function (i, o) {
            if (!$.isEmptyObject($(o).attr('name'))) {
                var name = $(o).attr('name');//获取name里的值
                jsonData[name] = $('[name="' + name + '"]').val();
            }
        });
        return jsonData;
    }
};

/**
 * ajax 表单提交请求
 * @type {{doSubmit: Function}}
 */
var FormSubmit = {
    doSubmit: function (obj) {
        var resultData = GetFormDataForJson.dataFormate(obj);
        $.ajax({
            type: "POST",
            dataType: 'json',
            async: true,
            data: resultData,
            url: resultData.path + '/loginManage/doLogin',
            success: function (data) {
                if (data.flag) {
                    RememberMe.checkCookie(obj);
                    //选择登录用户的一个角色
                    var requestUrl=resultData.path + "/loginManage/toRoleIndexPage";
                    /**
                     * 选择角色弹出层
                     */
                    $.post(requestUrl,{},function (str) {
                        layer.open({
                            type: 1,
                            title: '请选择一个角色',
                            skin: 'demo-class',//样式
                            area: ['20%', '50%'],
                            //offset: 'cb',//坐标
                            closeBtn: 2,//两种风格的关闭按钮，可通过配置1和2来展示，如果不显示，则closeBtn: 0
                            shade: [0.8, '#393D49'],//如果你不想显示遮罩，可以shade: 0
                            shadeClose: false,// 是否点击遮罩关闭
                            //time: 5000,//自动关闭所需毫秒
                            shift: 0,//0-6弹出的风格
                            maxmin: false,// 最大最小化。
                            fix: false,// 固定  为true下面的页面可以动
                            scrollbar: true,//是否允许浏览器出现滚动条
                            content: str, //注意，如果str是object，那么需要字符拼接。
                            btn: ['确认', '取消']
                            ,yes: function(index, layero){
                                var roleIdenty=$(layero).find('[name="roleIdenty"]').val();
                                if($.isEmptyObject(roleIdenty)){
                                    layer.msg("请选择一个角色!");
                                }else{
                                    var searchForm=document.createElement("form");
                                    var SortListDoc =document.createElement("input");
                                    SortListDoc.setAttribute('name',"roleIdenty");
                                    SortListDoc.setAttribute('type',"text");
                                    SortListDoc.value=roleIdenty;
                                    searchForm.appendChild(SortListDoc);

                                    //执行方法调用
                                    searchForm.action = resultData.path + "/loginManage/toHomepage";
                                    searchForm.method = "POST";
                                    $(document.body).append(searchForm);
                                    searchForm.submit();
                                    layer.close(index);
                                }
                            },
                            btn2: function(index, layero){
                                layer.close(index);
                            }
                        });
                    });
                } else {
                    layer.msg(data.msg);
                }
            },
            error: function (jqXHR) {

            }
        });
    }
};

/**
 * 登录处理应用
 * @type {{toValidate: Function, reset: Function}}
 */
var LoginTreatmentApplication = {
    toValidate: function (obj) {
        //初始化 给页面赋cookie里的值
        RememberMe.cookieIndex(obj);
        //拼接 字段 提示信息
        var resultData = SetValidateDate.setRulesAndMessagesData(obj);
        obj.validate({
            focusInvalid: true, //当为false时，验证无效时，没有焦点响应
            onkeyup: false,
            submitHandler: function (form) {   //表单提交句柄,为一回调函数，带一个参数：form （点击submit 进入该方法）
                FormSubmit.doSubmit(form);
            },
            rules: resultData.rules,
            messages: resultData.messages
        });
    }
};

//记住我
var RememberMe = {
    /**
     * 设置cookie
     * @param cname
     * @param cvalue
     * @param exdays
     */
    setCookie: function (cname, cvalue, exdays) {
        var d = new Date();
        d.setTime(d.getTime() + (exdays * 24 * 60 * 60 * 1000));
        var expires = "expires=" + d.toUTCString();
        document.cookie = cname + "=" + cvalue + "; " + expires;
    },
    /**
     * 获取cookie
     * @param cname
     * @returns {*}
     */
    getCookie: function (cname) {
        var name = cname + "=";
        var ca = document.cookie.split(';');
        for (var i = 0; i < ca.length; i++) {
            var c = ca[i];
            while (c.charAt(0) == ' ') c = c.substring(1);
            if (c.indexOf(name) != -1) return c.substring(name.length, c.length);
        }
        return "";
    },
    /**
     * 清除cookie
     * @param name
     */
    clearCookie: function (name) {
        RememberMe.setCookie(name, "", -1);
    },
    /**
     * checkCookie
     * @param obj
     */
    checkCookie: function (obj) {
        var checkedFlag = $('[name="rememberMe"]')[0].checked;
        if (checkedFlag) {
            RememberMe.setCookie("username", $(obj).find('[name=account]').val(), 7);
            RememberMe.setCookie("password", $(obj).find('[name=password]').val(), 7);
        } else {
            RememberMe.clearCookie("username");
            RememberMe.clearCookie("password");
        }
    },
    /**
     * 初始化 给页面赋cookie里的值
     * @param obj
     */
    cookieIndex: function (obj) {
        if(!$.isEmptyObject(RememberMe.getCookie("username"))){
            $(obj).find('[name=account]').val(RememberMe.getCookie("username"));
            $(obj).find('[name=password]').val(RememberMe.getCookie("password"));

            $(obj).find('[name="rememberMe"]').prop('checked',true);
        }

    }
};


