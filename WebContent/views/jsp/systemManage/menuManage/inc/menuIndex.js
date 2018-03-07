var treeObj;
var contextPath="";
$(function(){
    treeObj=$.fn.zTree.getZTreeObj("treeDiv");
    contextPath=$('[name="contextPath"]').val();
    //开启验证
    var jsonRemote={
        type: "POST",
        url: contextPath + '/menuManage/checkMenuName',             //servlet
        cache: false,
        data: {
            menuId: function () {
                return $('#MenuForm').find('[name="menuId"]').val();
            },
            menuName: function () {
                return $('#MenuForm').find('[name="menuName"]').val();
            }
        }
    };
    //获取form表单里的 所有填值空间
    var resultData = Do_Form_Validate.getFormValidateData($('#MenuForm'),jsonRemote);
    $('#MenuForm').validate({
        focusInvalid: true, //当为false时，验证无效时，没有焦点响应
        onkeyup: false,
        ignore: "",
        submitHandler: function (form) {   //表单提交带回调函数
            var btn = $('[data-action="save"]');
            btn.button('loading');
            //表单数据格式化为json
            var resultFormateData = Do_Form_Validate.dataFormate(form);
            if($.isEmptyObject($('[name="menuPic"]').val())){
                resultFormateData['menuPic']=encodeURIComponent('<i class="icon-only ace-icon fa fa-align-justify"></i>');
            }else{
                resultFormateData['menuPic']=encodeURIComponent($('[name="menuPic"]').val());
            }
            AjaxSubmit.ajax({
                url: contextPath + '/menuManage/saveOrUpdateMenu',
                async: true,//1：为异步提交 0：为同步提交
                params: resultFormateData,
                fnCallback: function (data) {
                    if (data.isSuccess) {
                        btn.button('reset');
                        //提示信息
                        Common_function.promotInfo(data.msg);
                        treeObj.reAsyncChildNodes(null, "refresh");
                    }else{
                        //提示信息
                        Common_function.promotInfo(data.msg);
                    }
                    //清楚 需要romote字段的缓存
                    clearRemote();
                }
            })
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

    //初始化 select 下拉框
    Common_function.indexSelect();

    /**
     * 点击 数据保存按钮 事件
     */
    $('[data-action="save"]').on('click', function () {
        if($("#MenuForm").valid()){
            $('#MenuForm').submit();
        }
    });
});

/**
 * 清楚 需要romote字段的缓存
 */
function clearRemote() {
    $('[name="menuName"]').removeData("previousValue");
}



/**
 * 弹出层事件
 */
function queryTreeEven() {
    var curNodes = treeObj.getSelectedNodes();
    var contextPath = $('[name="contextPath"]').val();
    var requestUrl = contextPath + "/menuManage/toMenuAlertPage";
    var selectedId="";
    if(!$.isEmptyObject(curNodes)){
        selectedId=curNodes[0].id;
    }
    /**
     * 选择节点的弹出层
     */
    Pop_up_layer.openIframe({
        url: requestUrl,
        data:{selectedId:selectedId},
        width:'30%',
        height:'80%',
        fnCallback: function (data) {
            if($(data).find('[name="layerMenuId"]').val() == $('#MenuForm').find('[name="menuId"]').val()){
                Common_function.promotInfo('当前节点不可以和父节点相同');
                return;
            }
            $('[name="parentMenuId"]').val($(data).find('[name="layerMenuId"]').val());
            $('[name="parentMenuName"]').val($(data).find('[name="layerMenuName"]').val());
        }
    });
}



/**
 * 重置 操作
 */
function resetData(){
    var formAttr = $('#MenuForm').find('input,select,textarea');
    $.each(formAttr, function (i, o) {
        $(o).val("");
    });
    treeObj.reAsyncChildNodes(null, "refresh");
}


/**
 * 查询 图标 方法
 */
function queryMenuPic(){
    var contextPath = $('[name="contextPath"]').val();
    var requestUrl=contextPath + "/menuManage/toMenuPicIndexPage";
    /**
     * 选择图标的弹出层
     */
    Pop_up_layer.openIframe({
        url: requestUrl,
        data:{},
        width:'40%',
        height:'90%',
        fnCallback: function (data) {
            if(!$.isEmptyObject($(data).find('[name="result"]').val())){
                $('[name="menuPic"]').val($(data).find('[name="result"]').val());
                $('#picContent').html($(data).find('[name="result"]').val());
            }
        }
    });
}

