var zTree;
var rMenu;//右键菜单元素
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
            //otherParam: ["contactid", function () {
            //    return window.opener.document.getElementById("contactid").value;
            //}]
        },
        view: {
            //addHoverDom: null,
            //removeHoverDom: null,
            //fontCss: beforeClick, //个性化样式
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
            onRightClick: OnRightClick,//右键事件
            //捕获异步加载出现异常错误的事件回调函数 和 成功的回调函数
            onAsyncSuccess: function (event, treeId, treeNode, msg) {

            },
            //beforeClick: function (treeId, treeNode) {
            //    var zTree = $.fn.zTree.getZTreeObj("treeDiv");
            //    if (treeNode.isParent) {
            //        zTree.expandNode(treeNode);
            //        return true;
            //    }
            //},
            onCheck: onCheck,
            //onRemove: onRemove, //移除事件
            //onRename: onRename //修改事件
            beforeExpand: zTreeBeforeExpand,
            beforeCollapse: zTreeBeforeCollapse,
        }
    };

    zTree = $.fn.zTree.init($("#treeDiv"), setting);
    rMenu = $("#rMenu");
});

//树枝 点击事件
function onClick(event, treeId, treeNode, clickFlag) {
    //获取父节点
    var parentTreeNode=treeNode.getParentNode();
    if($.isEmptyObject(parentTreeNode)){
        $('[name="parentMenuId"]').val("0");
        $('[name="parentMenuName"]').val("");
    }else{
        $('[name="parentMenuId"]').val(parentTreeNode.id);
        $('[name="parentMenuName"]').val(parentTreeNode.name);
    }

    var formAttr = $('#MenuForm').find('input,select,textarea');
    $.each(formAttr, function (i, o) {
        if(o.name == 'menuId'){
            $('[name="menuId"]').val(treeNode['id']);
        }else if(o.name == 'menuName'){
            $('[name="menuName"]').val(treeNode['name']);
        }else{
            var v = treeNode[o.name];
            if(o.name != "parentMenuId" && o.name != "parentMenuName"){
                $(o).val(v);
            }
            if(o.name == "menuPic"){
                $('#picContent').html(v);
            }
            $(o).trigger('chosen:updated');
        }
    });

}

//check 事件
function onCheck(e, treeId, treeNode) {
    var zTree = $.fn.zTree.getZTreeObj("treeDiv"),
        nodes = zTree.getCheckedNodes(true),
        v = "";
    var ids = "";
    for (var i = 0, l = nodes.length; i < l; i++) {
        v += nodes[i].name + ",";
        ids += nodes[i].id + ",";
    }

    if (ids.length > 0) ids = ids.substring(0, ids.length - 1);
    alert(ids);
}

//展开事件
function zTreeBeforeExpand(treeId, treeNode) {

};
//折叠事件
function zTreeBeforeCollapse(treeId, treeNode) {

};



// 在ztree上的右击事件
function OnRightClick(event, treeId, treeNode) {
    if (treeNode) {
        showRMenu("node", event.clientX, event.clientY);
    }
}
//显示右键菜单
function showRMenu(type, x, y) {
    $("#rMenu ul").show();
    rMenu.css({"top": y + "px", "left": x + "px", "visibility": "visible"}); //设置右键菜单的位置、可见
    $("body").bind("mousedown", onBodyMouseDown);
}
//隐藏右键菜单
function hideRMenu() {
    if (rMenu) {
        rMenu.css({"visibility": "hidden"}); //设置右键菜单不可见
    }
    $("body").unbind("mousedown", onBodyMouseDown);
}
//鼠标按下事件
function onBodyMouseDown(event) {
    if (!(event.target.id == "rMenu" || $(event.target).parents("#rMenu").length > 0)) {
        rMenu.css({"visibility": "hidden"});
    }
}

//全部展开
function expandAll() {
    hideRMenu();
    zTree.expandAll(true);
}
//全部折叠
function collapseAll() {
    hideRMenu();
    zTree.expandAll(false);
}