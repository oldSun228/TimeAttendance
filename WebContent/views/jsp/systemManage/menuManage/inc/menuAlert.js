$(function () {
    var contextPath = $('[name="contextPath"]').val();
    var setting = {
        check: {
            enable: false,
            chkStyle: "checkbox",
            chkboxType: {"Y": "ps", "N": "ps"}
        },
        //获取json数据
        async: {
            enable: true,
            url: contextPath + "/menuManage/getMenusList", // Ajax 获取数据的 URL 地址
            autoParam: ["id", "name"], //ajax提交的时候，传的是id值
        },
        view: {
            showTitle: false,// 是否显示节点的 title
            selectedMulti: false,//设置是否允许同时选中多个节点。
            nameIsHTML: false,//设置 name 属性是否支持 HTML 脚本
            showIcon: true,
        },
        edit: {
            enable: false, //单独设置为true时，可加载修改、删除图标
            editNameSelectAll: false,
            showRemoveBtn: false,
            showRenameBtn: false
        },
        data: { // 必须使用data
            key: {
                checked: "checked",
                children: "subChildren",
                name: "name",
                title: "",
            },
            simpleData: {
                enable: false,//是否采用简单数据模式
                idKey: "id", // id编号命名
                pIdKey: "pId", // 父id编号命名
                rootId: null
            }
        },
        // 回调函数
        callback: {
            onClick: onClick,
        }
    };
    $.fn.zTree.init($("#childTreeDiv"), setting);

    setTimeout(function(){
        var indexNum=$('[name="selectedId"]').val();
        var treeObj = $.fn.zTree.getZTreeObj("childTreeDiv");
        var node = treeObj.getNodeByParam("id",indexNum);
        treeObj.removeChildNodes(node);
    },180);

});


function onClick(event, treeId, treeNode, clickFlag) {
    $('[name="layerMenuId"]').val(treeNode.id);
    $('[name="layerMenuName"]').val(treeNode.name);
}



