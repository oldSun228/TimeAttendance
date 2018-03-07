$(function () {
    var contextPath = $('[name="contextPath"]').val();

    //用户 分页列表
    var userPageListTable = $("#userTable").dataTable({
            "processing": true,
            "serverSide": true,
            "bAutoWidth": true, //是否自适应宽度
            //"bScrollInfinite" : false, //是否启动初始化滚动条
            "bScrollCollapse": true, //是否开启DataTables的高度自适应，当数据条数不够分页数据条数的时候，插件高度是否随数据条数而改变
            "bPaginate": true, //是否显示（应用）分页器
            "bInfo": false, //是否显示页脚信息，DataTables插件左下角显示记录数
            "paginationType": "full_numbers",
            "bLengthChange": false,//选择一夜显示条数
            "aLengthMenu": [20, 40, 60], //更改显示记录数选项
            "bFilter": false,//查询条件
            "bSort": false,//排序
            "iDisplayLength": 10,//一页显示的条数
            "iDisplayStart": 0,//开始
            "columns": [
                {
                    "data": "userId",
                    "bVisible": false,
                    "bSortable": false
                },
                {
                    "data": "userName"
                },
                {
                    "data": "sex",
                    render: function (data, type, row) {
                        if (data == '0') {
                            return "女";
                        } else if (data == '1') {
                            return "男";
                        }
                    }
                },
                {
                    "data": "telphone"
                }, {
                    "data": "account"
                },
                {
                    "data": "status",
                    render: function (data, type, row) {
                        if (data == '1') {
                            return "启用";
                        } else if (data == '2') {
                            return "停用";
                        }
                    }
                },
                {
                    "data": "entryTime",
                    render:function(data, type, row){

                        return  new Date(data).Format("yyyy-MM-dd hh:mm:ss");
                    }
                },
                {
                    "data": "leaveTime"
                },
                {
                    "data": "createBy"
                },
                {
                    "data": "createTime",
                    render:function(data, type, row){

                        return  new Date(data).Format("yyyy-MM-dd hh:mm:ss");
                    }
                },
                {
                    render: function (data, type, row) {
                        var html = "";
                        html += '<div class="hidden-sm hidden-xs action-buttons">';
                        /*查看详情 按钮*/
                        html += '<a class="blue" href="#" operateType="detail">';
                        html += '<i class="ace-icon fa fa-eye bigger-130"></i>';
                        html += '</a>';
                        /*数据编辑 按钮*/
                        html += '<a class="green" href="#" operateType="update">';
                        html += '<i class="ace-icon fa fa-pencil bigger-130"></i>';
                        html += '</a>';
                        /*删除数据 按钮*/
                        html += '<a class="red" href="#" operateType="delete">';
                        html += '<i class="ace-icon fa fa-trash-o bigger-130"></i>';
                        html += '</a>';
                        /*角色 按钮*/
                        html += '<a class="yellow" href="#" operateType="role">';
                        html += '<i class="ace-icon fa fa-user bigger-130"></i>';
                        html += '</a>';
                        html += '</div>';
                        return html;
                    }
                }
            ],
            "ajax": {
                "url": contextPath + "/userManage/getUserPageList",
                "type": "POST"
            },
            aaSorting: [[2, "desc"]],
            "fnServerParams": function (d) {
                var params = {};
                params.roleName = $('.searchArea').find('[name="roleName"]').val();
                params.status = $('.searchArea').find('[name="status"]').val();
                d = $.extend(d, params);
            },
            "rowCallback": function (row, data) {
                $(row).on('click', function (e) {
                    var event = e.target;
                    //查看详情操作标识
                    var detailFlag = $($(event).parent()).attr('operateType') == 'detail';
                    //修改操作标识
                    var updateFlag = $($(event).parent()).attr('operateType') == 'update';
                    //删除操作标识
                    var deleteFlag = $($(event).parent()).attr('operateType') == 'delete';
                    //删除操作标识
                    var roleFlag = $($(event).parent()).attr('operateType') == 'role';
                    if(detailFlag){
                        $('.initArea').slideToggle("slow");
                        $('.editArea').slideToggle("slow");
                    }else if (updateFlag) {
                        AjaxSubmit.post({
                            url: contextPath + "/roleManage/detailForRole",
                            params: {
                                roleId: data.roleId,//提交参数 角色id
                            },
                            fnCallback: function (result) {
                                if (result.isSuccess) {
                                    var formAttr = $('#RoleForm').find('input,select,textarea');
                                    $.each(formAttr, function (i, o) {
                                        var v = result.result[o.name];
                                        $(o).val(v);
                                        if (detailFlag) {
                                            $('#RoleForm').find('[name="' + o.name + '"]').attr('disabled', true);
                                        } else {
                                            $('#RoleForm').find('[name="' + o.name + '"]').removeAttr('disabled');
                                        }
                                        if (o.tagName == 'SELECT') {
                                            $(o).trigger('chosen:updated');
                                        }
                                    });
                                    $('[data-action="openAdd"]').click();
                                }
                            }
                        });
                        if (detailFlag) {
                            $('.btn-operate').find("a").hide();
                        } else {
                            $('.btn-operate').find("a").show();
                        }
                    } else if (deleteFlag) {
                        layer.confirm('确定删除此信息？', {
                            time: 0,//不自动关闭
                            //area: ['200px', '100px'],
                            btn: ['确定', '取消'],
                            yes: function (index) {
                                layer.close(index);
                                AjaxSubmit.post({
                                    url: contextPath + "/roleManage/delForRole",
                                    params: {
                                        roleId: data.roleId,//提交参数 角色id
                                    },
                                    fnCallback: function (result) {
                                        if (result.isSuccess) {
                                            rolePageListTable.fnDraw(false);//false 为不自动跳到第一页
                                            //提示信息
                                            Common_function.promotInfo(result.msg);
                                        }
                                    }
                                });
                            }
                        });
                    }else if(roleFlag){
                        $('.initArea').slideToggle("slow");
                        $('.editRole').slideToggle("slow");
                        //查询角色列表
                        queryRoleList(data.userId);
                    }
                });
            }
        }
    );


    /**
     * 点击 放大镜按钮 切换显示页面事件
     */
    $('[data-action="openSearchArea"]').on('click', function () {
        $('.searchArea').slideToggle("slow");
        //初始化 select 样式
        Common_function.indexSelect();
    });

    /**
     * 点击 查询按钮 事件
     */
    $('[data-action="searchDate"]').on('click', function () {
        userPageListTable.fnDraw(true);
    });

    /**
     * 点击 重置按钮 事件
     */
    $('[data-action="reset"]').on('click', function () {
        $('.searchArea').find('[name="userName"]').val("");
        $('.searchArea').find('[name="account"]').val("");
        $('.searchArea').find('[name="status"]').val("").trigger('chosen:updated');
    });

    /**
     * 点击 增加按钮 事件  加 表单验证
     */
    $('[data-action="openAdd"]').on('click', function () {
        $('.initArea').slideToggle("slow");
        $('.editUserArea').slideToggle("slow");
        //初始化 select 样式
        Common_function.indexDoubleRowSelect();
        //开启验证
        var jsonRemote={
            type: "POST",
            url: contextPath + '/userManage/checkUserAccount',//servlet
            cache: false,
            data: {
                account: function () {
                    return $('#userForm').find('[name="account"]').val();
                }
            }
        };
        //获取form表单里的 所有填值空间
        var resultData = Do_Form_Validate.getFormValidateData($('#userForm'),jsonRemote);
        $('#userForm').validate({
            debug: true, //调试模式取消submit的默认提交功能
            focusInvalid: true, //当为false时，验证无效时，没有焦点响应
            onkeyup: false,
            ignore: "",
            submitHandler: function (form) {   //表单提交带回调函数
                var btn = $('[data-action="save"]');
                btn.button('loading');
                var resultFormateData = Do_Form_Validate.dataFormate(form);
                AjaxSubmit.ajax({
                    url: contextPath + '/userManage/saveOrUpdateUser',
                    async: true,
                    params: resultFormateData,
                    fnCallback: function (data) {
                        if (data.isSuccess) {
                            //$('[data-action="cancel"]').click();
                            //rolePageListTable.fnDraw(false);
                            //btn.button('reset');
                            ////提示信息
                            //Common_function.promotInfo(data.msg);
                        }
                        //清楚 需要romote字段的缓存
                        clearRemote();
                    }
                });
            },
            rules: resultData.rules,
            messages: resultData.messages,
            errorPlacement: function (error, element) {
                if (element.is('input[type=checkbox]') || element.is('input[type=radio]')) {
                    var controls = element.closest('div[class*="col-"]');
                    if (controls.find(':checkbox,:radio').length > 1) controls.append(error);
                    else error.insertAfter(element.nextAll('.lbl:eq(0)').eq(0));
                } else {
                    if (element[0].tagName == 'SELECT') {
                        error.insertAfter(element.next());
                    } else {
                        error.insertAfter(element);
                    }
                }
            }
        });

    });

    /**
     * 清楚 需要romote字段的缓存
     */
    function clearRemote() {
        $('[name="account"]').removeData("previousValue");
    }

    /**
     * 账号新增
     */
    $('[data-action="save"]').on('click',function(){
        $('#userForm').submit();
    });

    /**
     * 点击左边边框里的内容
     */
    $('[name="noExistRole"]').on('click',function(e){
        $.each($('[name="noExistRole"]').find("option"),function(i,obj){
           if(obj.outerHTML==e.target.outerHTML){
               $('[name="noExistRole"]').find("option")[i].remove();
               $('[name="existRole"]').append(e.target.outerHTML);
           }
        });
    });

    /**
     *  点击右边边框里的内容
     */
    $('[name="existRole"]').on('click',function(e){
        $.each($('[name="existRole"]').find("option"),function(i,obj){
            if(obj.outerHTML==e.target.outerHTML){
                $('[name="existRole"]').find("option")[i].remove();
                $('[name="noExistRole"]').append(e.target.outerHTML);
            }
        });
    });

    $('[data-action="allPotionInExistRole"]').on('click',function(){
        $('[data-action="allPotionInExistRole"]').addClass('active');
        $('[data-action="allPotionInNoExistRole"]').removeClass('active');
        $.each($('[name="noExistRole"]').find("option"),function(i,obj){
            obj.remove();
            $('[name="existRole"]').append(obj.outerHTML);
        });
    });

    $('[data-action="allPotionInNoExistRole"]').on('click',function(){
        $('[data-action="allPotionInNoExistRole"]').addClass('active');
        $('[data-action="allPotionInExistRole"]').removeClass('active');
        $.each($('[name="existRole"]').find("option"),function(i,obj){
            obj.remove();
            $('[name="noExistRole"]').append(obj.outerHTML);
        });
    });

    /**
     * 角色取消
     */
    $('[data-action="roleCancel"]').on('click',function(){
        $('.initArea').slideToggle("slow");
        $('.editRole').slideToggle("slow");
    });
    /**
     * 账号取消
     */
    $('[data-action="userCancel"]').on('click',function(){
        $('.initArea').slideToggle("slow");
        $('.editUserArea').slideToggle("slow");
    });

    /**
     * 角色保存
     */
    $('[data-action="roleSave"]').on('click',function(){
        var roleIdArray=[];
        var userId=$('#roleUserId').val();
        $.each($('[name="existRole"]').find("option"),function(i,obj){
            var roleIdObject={};
            roleIdObject.roleId=$(obj).val();
            roleIdObject.userId=userId;
            roleIdObject.status="1";
            roleIdArray.push(roleIdObject);
        });
        var params={};
        params.roleIdArray=JSON.stringify(roleIdArray);
        params.userId=userId;
        AjaxSubmit.ajax({
            url: contextPath + '/roleManage/saveRoleUserRelation',
            async: true,
            params: params,
            fnCallback: function (data) {
                if (data.isSuccess) {
                    //提示信息
                    $.gritter.add({
                        title: '操作信息',
                        text: '保存成功',
                        sticky: false,
                        time: 1000,
                        class_name: ('gritter-success')
                    });

                }
            }
        });

    });

});

function queryRoleList(userId){
    $('#roleUserId').val(userId);
    var params={};
    params.userId=userId;
    AjaxSubmit.ajax({
        url: contextPath + '/roleManage/getRoleList',
        async: true,
        params: params,
        fnCallback: function (data) {
            if (data.isSuccess) {
                var existRoleList=data.existRoleList;
                var noExistRoleList=data.noExistRoleList;
                for(var i=0;i<existRoleList.length;i++){
                    $('[name="existRole"]').append(' <option value='+existRoleList[i].roleId+'>'+existRoleList[i].roleName+'</option>');
                }
                for(var i=0;i<noExistRoleList.length;i++){
                    $('[name="noExistRole"]').append(' <option value='+noExistRoleList[i].roleId+'>'+noExistRoleList[i].roleName+'</option>');
                }

            }
        }
    });

}