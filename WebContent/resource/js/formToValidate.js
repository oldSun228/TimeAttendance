/**
 * 表单验证
 * @type {{toValidate: Function, getFormValidateData: Function, dataFormate: Function}}
 */
var Do_Form_Validate = {
    /**
     * 获取表单需要验证的数据
     * @param form
     * @param jsonRemote
     */
    getFormValidateData: function (form, jsonRemote) {
        var formData = $(form).find('input[type !="hidden"],select,textarea');
        var resultData = {};
        var rules = {};
        var messages = {};
        $.each(formData, function (i, o) {
            //rules
            var rulesChild = {};
            //messages
            var messagesChild = {};

            var name = $(o).attr('name');//获取name里的值
            var value = $(o).attr('placeholder');//获取placeholder里的值
            //判断是否是select
            if (o.tagName == 'SELECT' || o.tagName == 'TEXTAREA') {
                rulesChild.required = true;
                messagesChild.required = value + "不能为空!";
            } else {//其余的是input
                if ($(o).attr('type') == "checkbox" || $(o).attr('type') == "radio") {
                    rulesChild.required = true;
                    messagesChild.required = value + "不能为空!";
                } else {
                    //找到对象的name属性 和 placeholder属性 判断是否为空
                    if (!$.isEmptyObject(name) && !$.isEmptyObject(value)) {
                        rulesChild.required = true;
                        rulesChild.maxlength = [parseInt($(o).attr('dataMaxLength'))];
                        if ($(o).attr('dataExist') == "true") {
                            rulesChild.remote = jsonRemote;
                        }

                        messagesChild.required = value + "不能为空!";
                        messagesChild.maxlength = $.format(value + "的最大长度为:{0}个字符");
                        if ($(o).attr('dataExist') == "true") {
                            messagesChild.remote = $.format("此" + value + "已经被注册")
                        }
                    }
                }

            }
            rules[name] = rulesChild;
            messages[name] = messagesChild;
        });
        resultData.rules = rules;
        resultData.messages = messages;
        return resultData;
    },
    /**
     * form数据格式化为json
     * @type {{dataFormate: Function}}
     */
    dataFormate: function (obj) {
        var formData = $(obj).find('input,select,textarea');
        var jsonData = {};
        $.each(formData, function (i, o) {
            if (!$.isEmptyObject($(o).attr('name'))) {
                var name = $(o).attr('name');//获取name里的值
                jsonData[name] = $(obj).find('[name="' + name + '"]').val().trim();
            }
        });
        return jsonData;
    }
};
