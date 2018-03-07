var zTree;
//请求 路径
var contextPath="";
//当前选中的节点
var curTreeNode="";
//初始化 树 对象
var treeObj = "";

/**
 * 鼠标移到节点上 触发的事件
 * @param treeId
 * @param treeNode
 */
function addHoverDom(treeId,treeNode) {
    curTreeNode=treeNode;
    var sObj = $("#" + treeNode.tId + "_span");
    if ($("#removeBtn_"+treeNode.tId).length >0 || $("#addBtn_"+treeNode.tId).length>0){
        return;
    }
    var addStr="";
    if(treeNode.level == 0){
        addStr += "<span class='button add' id='addBtn_" + treeNode.tId + "'></span>";
    }else if(treeNode.level == 1){
        addStr += "<span class='button remove' id='removeBtn_" + treeNode.tId + "'></span>";
        addStr += "<span class='button add' id='addBtn_" + treeNode.tId + "'></span>";
        addStr += "<span class='button edit' id='editBtn_" + treeNode.tId + "'></span>";
    }else if(treeNode.level == 2){
        addStr += "<span class='button remove' id='removeBtn_" + treeNode.tId + "'></span>";
        addStr += "<span class='button edit' id='editBtn_" + treeNode.tId + "'></span>";
    }
    sObj.after(addStr);
    //删除
    var btnDel = $("#removeBtn_"+treeNode.tId);
    if (btnDel){
        btnDel.bind("click", function(){
            treeObj=$.fn.zTree.getZTreeObj("tree");
            $('#saveButton').hide();

            var params={};
            params.dicId=curTreeNode.id;

            AjaxSubmit.ajax({
                async:true,
                params:params,
                url:contextPath+"/dictionary/delDictionary",
                fnCallback:function(result){
                    if(result.isSuccess){
                        //提示信息
                        $.gritter.add({
                            title: '操作信息',
                            text: result.msg,
                            sticky: false,
                            time: 1000,
                            class_name: ('gritter-success')
                        });
                        treeObj.reAsyncChildNodes(null, "refresh");
                    }else{
                        //提示信息
                        $.gritter.add({
                            title: '操作信息',
                            text: result.msg,
                            sticky: false,
                            time: 1000,
                            class_name: ('gritter-success')
                        });
                    }
                }
            });

            // 全部置空
            var formAttr = $('#dicForm').find('input,select,textarea');
            $.each(formAttr, function (i, o) {
               $(o).val("");
            });
            return false;
        });
    }
    //增加
    var btnAdd = $("#addBtn_"+treeNode.tId);
    if (btnAdd){
        btnAdd.bind("click", function(){
            treeObj=$.fn.zTree.getZTreeObj("tree");
            //选中此节点
            treeObj.selectNode(curTreeNode);
            $('[name="parentId"]').val(curTreeNode.id);
            $('[name="parentName"]').val(curTreeNode.name);
            if(curTreeNode.level == 0){
                $('#dicIdenty').show();
                $('#dicCode').hide();
            }else if(curTreeNode.level == 1){
                $('#dicCode').show();
                $('#dicIdenty').hide();
            }
            setEmpty();
            $('#saveButton').show();
            return false;
        });
    }
    //修改
    var btnEdit = $("#editBtn_"+treeNode.tId);
    if (btnEdit){
        btnEdit.bind("click", function(){
            treeObj=$.fn.zTree.getZTreeObj("tree");
            treeObj.selectNode(curTreeNode);
            $('[name="dicId"]').val(curTreeNode.id);
            $('[name="parentId"]').val(curTreeNode.pId);
            $('[name="parentName"]').val(treeObj.getNodeByParam("id", curTreeNode.pId).name);
            if(curTreeNode.level == 1){
                $('#dicIdenty').show();
                $('#dicCode').hide();
            }else if(curTreeNode.level == 2){
                $('#dicCode').show();
                $('#dicIdenty').hide();
            }
            setValue(curTreeNode);
            $('#saveButton').show();
            return false;
        });
    }
}
//鼠标移出节点上 触发的事件
function removeHoverDom(treeId,treeNode) {
    $("#addBtn_"+treeNode.tId).unbind().remove();
    $("#removeBtn_"+treeNode.tId).unbind().remove();
    $("#editBtn_"+treeNode.tId).unbind().remove();
}



$(function () {
    contextPath = $('[name="contextPath"]').val();
    //初始化 select 下拉框
    Common_function.indexSelect();

    setInterval(function(){
        // 滚动条
        $(".content").mCustomScrollbar("update");
    },500);
    var setting = {
        check: {
            enable: false
        },
        //获取json数据
        async: {
            enable: true,
            url: contextPath + "/dictionary/getDictionaryList"// Ajax 获取数据的 URL 地址
        },
        view: {
            addHoverDom: addHoverDom,
            removeHoverDom: removeHoverDom,
            dblClickExpand: false,
            showLine: true,
            selectedMulti: false
        },
        data: {
            simpleData: {
                enable:true,
                idKey: "id",
                pIdKey: "pId",
                rootPId: ""
            }
        },
        callback: {
            beforeClick: function(treeId, treeNode) {
                var zTree = $.fn.zTree.getZTreeObj("tree");
                if (treeNode.isParent) {
                    zTree.expandNode(treeNode);
                    return true;
                }
            }
        }
    };
    $.fn.zTree.init($("#tree"), setting);
});

//赋值
function setValue(treeNode){
    var formAttr = $('#dicForm').find('input,select,textarea');
    $.each(formAttr, function (i, o) {
        var v = treeNode[o.name];
        if(o.name != "parentId" && o.name != "parentName" && o.name != "dicId"){
            $(o).val(v);
        }
        $(o).trigger('chosen:updated');
    });
}

//置空
function setEmpty(){
    var formAttr = $('#dicForm').find('input,select,textarea');
    $.each(formAttr, function (i, o) {
        if(o.name != "parentId" && o.name != "parentName"){
            $(o).val("");
        }
    });
}

//保存
function save(){
    //数据格式化
    var resultData = Do_Form_Validate.dataFormate($('#dicForm'));
    AjaxSubmit.ajax({
        url: contextPath + '/dictionary/saveOrUpdateDictionary',
        async: true,//true：为异步提交 false：为同步提交
        params:resultData,
        fnCallback: function (data) {
            if (data.isSuccess) {
                //提示信息
                $.gritter.add({
                    title: '操作信息',
                    text: data.msg,
                    sticky: false,
                    time: 1000,
                    class_name: ('gritter-success')
                });
                treeObj.reAsyncChildNodes(null, "refresh");
            }else{
                //提示信息
                $.gritter.add({
                    title: '操作信息',
                    text: data.msg,
                    sticky: false,
                    time: 1000,
                    class_name: ('gritter-success')
                });
            }
        }
    })
}