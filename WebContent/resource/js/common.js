//字典常量
var dicMap;
var contextPath;
$(function () {
    contextPath = $('[name="contextPath"]').val();
    /**
     * dataTables 选中parent->checkbox 默认选中下面的child->checkbox
     */
    //$('[name="parentCheckbox"]').on('click', function () {
    //    if ($(this).is(':checked')) {
    //        $(this).parent().parent().parent().parent().next('tbody').find('[type=checkbox]').prop('checked', true);
    //    } else {
    //        $(this).parent().parent().parent().parent().next('tbody').find('[type=checkbox]').prop('checked', false);
    //    }
    //})
    //if($.isEmptyObject(dicMap)){
    //    AjaxSubmit.post({
    //        url: contextPath + "/commonManage/getDicMap",
    //        fnCallback: function (result) {
    //            dicMap=result;
    //        }
    //    });
    //}
});

var Common_function={
    /**
     * 初始化 select 选择框
     */
    indexSelect:function(){
        $('.chosen-select').chosen({
            no_results_text: "没有找到",
            allow_single_de: true
        });

        $('.chosen-select').each(function () {
            var $this = $(this);
            $this.next().css({'width': $this.prev().width()+14});
        });
    },
    /**
     * 初始化 select 选择框
     */
    indexDoubleRowSelect:function(){
        $('.chosen-select').chosen({
            no_results_text: "没有找到",
            allow_single_de: true
        });

        $('.chosen-select').each(function () {
            var $this = $(this);
            $this.next().css({'width': $this.prev().width()});
        });
    },
    /**
     * 隐藏 error 提示信息
     */
    hiddenError:function(){
        $('label[class="error"]').remove();
        $('.error').removeClass('error');
    },
    /**
     * 清空表单input数据
     * @param form
     */
    clearFormData:function(form){
        var formAttr = form.find('input,select,textarea');
        $.each(formAttr, function (i, o) {
            $('#RoleForm').find('[name="' + o.name + '"]').removeAttr('disabled');
            if (o.tagName != 'SELECT') {
                $(o).val("");
            }else{
                $(o).trigger('chosen:updated');
            }
            $('.btn-operate').find("a").show();
        });
    },
    /**
     * 清空 jquery Validate romote 验证缓存
     */
    clearPreviousValue:function(){
        if($(".remote").data("previousValue")){
            $(".remote").data("previousValue").old = null;
        }
    },
    /**
     * 提示信息
     * @param msg
     */
    promotInfo:function(msg,time){
        $.gritter.add({
            title: '操作信息',
            text: msg,
            sticky: false,
            time: $.isEmptyObject(time)?1000:time,
            class_name: ('gritter-success')
        });
    },
    /**
     *  获取字典 list
     * @param identy
     */
    queryDicList:function(identy){
        AjaxSubmit.post({
            url: contextPath + "/commonManage/getDicList",
            params: {
                identy: identy,//提交参数 标识
            },
            fnCallback: function (result) {

                return result;
            }
        });
    },
    /**
     *  获取字典 list html
     * @param identy
     */
    queryDicListHtml:function(identy,area){
        AjaxSubmit.post({
            url: contextPath + "/commonManage/getDicList",
            params: {
                identy: identy,//提交参数 标识
            },
            fnCallback: function (result) {
                var areaHtml='<option value="">-请选择-</option>';
                for(var i=0;i<result.length;i++){
                    areaHtml +=' <option value="'+result[i].code+'">'+result[i].name+'</option>';
                }
                $('select[name="'+area+'"]').html(areaHtml);
                Common_function.indexSelect();
            }
        });
    },
    /**
     *  获取字典 名称
     * @param identy
     * @param val
     */
    queryDicName:function(identy,valu){
        AjaxSubmit.ajax({
            url: contextPath + "/commonManage/getDicName",
            params: {
                identy: identy,//提交参数 标识
                valu: valu,//提交参数 值
                async: false
            },
            fnCallback: function (result) {

                return result;
            }
        });
    },
};
