$(function () {
    //项目名 每个页面都应该有
    var contextPath = $('[name="contextPath"]').val();

    /**
     * 左侧 菜单树
     * @type {{getChildMenus: Function}}
     */
    var ChildMenus = {
        getChildMenus: function (data) {
            var treeChildHtml = '<ul class="submenu">';
            $.each(data, function (i, o) {
                treeChildHtml += '<li class="">';
                if (o.nextFlag == 'true') {//为true 说明此菜单不能有连接  因为有下一层
                    treeChildHtml += '<a href="#" data-hidden="' + o.menuId + '" class="dropdown-toggle">';
                } else {// false 无下级菜单  http开头的
                    if (!$.isEmptyObject(o.menuUrl) && o.menuUrl.indexOf("http") == -1) {//下级菜单 非 http开头的
                        treeChildHtml += '<a href="' + contextPath + o.menuUrl + '" target="mainContent">';
                    } else {
                        treeChildHtml += '<a href="' + o.menuUrl + '" target="mainContent">';
                    }
                }
                treeChildHtml += '<i class="menu-icon fa fa-caret-right"></i>';
                treeChildHtml += o.menuName;
                if (o.nextFlag == 'true') { //为true 说明此菜单不能有连接  因为有下一层
                    treeChildHtml += '<b class="arrow fa fa-angle-down"></b>';//图标
                }
                treeChildHtml += '</a>';
                treeChildHtml += '<b class="arrow"></b>';
                if (!$.isEmptyObject(o.functionMenusList)) {
                    treeChildHtml += ChildMenus.getChildMenus(o.functionMenusList);
                }
                treeChildHtml += '</li>';
            });
            treeChildHtml += '</ul>';
            return treeChildHtml;
        }
    };

    /**
     * 左侧 菜单树
     */
    AjaxSubmit.ajax({
        url: contextPath + "/leftTree/getMenusList",
        async: false,//true：为异步提交 false：为同步提交
        fnCallback: function (data) {
            var treeHtml = '<ul class="nav nav-list">';
            $.each(data, function (i, o) {
                treeHtml += '<li class="">';
                if (o.nextFlag == "true") {//为true 说明此菜单不能有连接  因为有下一层
                    treeHtml += '<a href="#" data-hidden="' + o.menuId + '" class="dropdown-toggle">';
                } else {//为 false（此菜单无下级菜单)
                    treeHtml += '<a href="' + contextPath + o.menuUrl + '" target="mainContent">';
                }
                treeHtml += o.menuPic;
                treeHtml += '<span class="menu-text">' + o.menuName + '</span>';
                if (o.nextFlag == "true") { //为0 说明此菜单不能有连接  因为有下一层
                    treeHtml += '<b class="arrow fa fa-angle-down"></b>';//图标
                }
                treeHtml += '</a>';
                treeHtml += '<b class="arrow"></b>';
                if (!$.isEmptyObject(o.functionMenusList)) {
                    treeHtml += ChildMenus.getChildMenus(o.functionMenusList);
                }
                treeHtml += '</li>';
            });
            treeHtml += '</ul>';
            $('#sidebar-shortcuts').after(treeHtml);
        }
    });
    $('#sidebar-shortcuts').next('ul').find('li:first > a').click();

});

