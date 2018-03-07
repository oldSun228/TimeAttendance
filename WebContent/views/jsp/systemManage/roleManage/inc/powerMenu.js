var allZtree="";
var PowerMenu={
    getRoleMenuById:function(roleId){
        setTimeout(function(){
            allZtree = $.fn.zTree.getZTreeObj("treeDiv");
            var params={};
            params.inWays= roleId;
            AjaxSubmit.ajax({
                url: contextPath + '/leftTree/getMenusList',
                async: true,
                params: params,
                fnCallback: function (result) {
                    if(result.length > 0){
                        PowerMenu.setCheckedNodes(result);
                    }
                }
            })
        },500);

    },
    setCheckedNodes:function(result){
        for(var i=0;i<result.length;i++){
            if(result[i].functionMenusList == null){
                 var node = allZtree.getNodeByParam("id", result[i].menuId, null);
                allZtree.checkNode(node,true);
            }else{
                PowerMenu.setCheckedNodes(result[i].functionMenusList);
            }

        }
    }
};