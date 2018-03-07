<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <meta charset="utf-8"/>
    <title>菜单管理</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resource/bootstrap/assets/css/chosen.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resource/bootstrap/assets/css/bootstrap.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resource/dataTables/css/dataTables.bootstrap.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resource/bootstrap/assets/css/font-awesome.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resource/bootstrap/assets/css/ace-fonts.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resource/bootstrap/assets/css/ace.css" class="ace-main-stylesheet" id="main-ace-style"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resource/bootstrap/assets/css/ace-part2.css" class="ace-main-stylesheet"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resource/bootstrap/assets/css/ace-ie.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resource/bootstrap/assets/css/ace-part2.css" class="ace-main-stylesheet"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resource/bootstrap/assets/css/ace-ie.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resource/jqueryztree/css/zTreeStyle/zTreeStyle.css" type="text/css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resource/css/jQueryValidateForForm.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resource/css/common.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resource/bootstrap/assets/css/jquery.gritter.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resource/jqueryztree/css/zTreeStyle/metro.css">

    <script type="text/javascript" src='${pageContext.request.contextPath}/resource/jqueryztree/js/jquery-1.9.1.js'></script>

    <script type="text/javascript" src="${pageContext.request.contextPath}/resource/bootstrap/assets/js/bootstrap.js"></script>
    <script type="text/javascript" src='${pageContext.request.contextPath}/resource/jQuery.validate/jquery.validate.js'></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resource/layer/layer.js"></script>

    <%--弹出层 共通js--%>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resource/js/layerUtil.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resource/jqueryztree/js/jquery.ztree.all-3.5.js"></script>

    <%-- 下拉框 js--%>
    <script src="${pageContext.request.contextPath}/resource/bootstrap/assets/js/chosen.jquery.js"></script>
    <%--表单验证js--%>
    <script type="text/javascript" src='${pageContext.request.contextPath}/resource/js/formToValidate.js'></script>
    <%-- 成功后的提示信息--%>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resource/bootstrap/assets/js/jquery.gritter.js"></script>
    <%--共通js--%>
    <script type="text/javascript" src='${pageContext.request.contextPath}/resource/js/common.js'></script>
    <%--提交共通js--%>
    <script type="text/javascript" src='${pageContext.request.contextPath}/resource/js/ajaxSubmit.js'></script>
    <%--树结构共通js--%>
    <script type="text/javascript" src='${pageContext.request.contextPath}/views/jsp/systemManage/menuManage/inc/ztreeCommon.js'></script>
    <%--基本js--%>
    <script type="text/javascript" src='${pageContext.request.contextPath}/views/jsp/systemManage/menuManage/inc/menuIndex.js'></script>
    <style type="text/css">
        #treeDiv {
            width: 100%;
            padding-left: 20px;
            overflow: hidden;
            float: left;
        }

        .editArea {
            margin-top: 10px;
            width: 100%;
            float: left;
        }

        div#rMenu {
            position: absolute;
            visibility: hidden;
            top: 0;
            background-color: #555;
            text-align: left;
            padding: 2px;
        }

        div#rMenu a {
            cursor: pointer;
            list-style: none outside none;
        }
        .content{height:500px;}

    </style>
</head>
<body>
<input type="hidden" name="contextPath" value="${pageContext.request.contextPath}">

<div class="main-content-inner">
    <%--初始化显示的页面--%>
    <div class="initArea">
        <%--当前页面头部信息--%>
        <div class="breadcrumbs" id="breadcrumbs">
            <ul class="breadcrumb">
                <li>
                    <i class="ace-icon fa fa-home home-icon"></i>
                    <a href="#">Home</a>
                </li>
            </ul>
        </div>
        <%--左半边 pre-scrollable --%>
        <div class="col-xs-3" style="overflow-y:auto; height:500px;">
            <div class="clearfix">
                <div class="pull-left alert alert-success no-margin">
                    <button type="button" class="close" data-dismiss="alert">
                        <i class="ace-icon fa fa-times"></i>
                    </button>

                    <i class="ace-icon fa fa-umbrella bigger-120 blue"></i>
                    可以右键编辑树节点...
                </div>
            </div>
            <%--ztree--%>
            <div id="treeDiv" class="ztree" style="position: relative; top: 0px; transition-property: top; transition-duration: 0.15s;"></div>
            <div id="rMenu">
                <a href="#" class="list-group-item" onclick="">增加子节点</a>
                <a href="#" class="list-group-item" onclick="">删除此节点</a>
                <a href="#" class="list-group-item" onclick="">编辑此节点</a>
                <a href="#" class="list-group-item" onclick="expandAll();">展开全部节点</a>
                <a href="#" class="list-group-item" onclick="collapseAll();">折叠全部节点</a>
            </div>
        </div>



        <%--右半边--%>
        <div class="col-xs-9">
            <%--编辑处--%>
            <div class="editArea">
                <div class="col-xs-12">
                 <span class="form-horizontal">
                    <form id="MenuForm">
                        <%--父菜单--%>
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right">父菜单名称: </label>

                            <div class="col-sm-9">
                                <span class="input-icon input-icon-right col-sm-6 hideDoublePadding" onclick="queryTreeEven();">
                                    <input type="hidden" name="parentMenuId"/>
                                    <input type="text" name="parentMenuName" placeholder="父菜单名称" class="col-sm-12" disabled dataMaxLength="10"/>
                                    <i class="ace-icon fa fa-list blue"></i>
                                </span>
                            </div>
                        </div>
                        <%--图标--%>
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right">菜单图标: </label>

                            <div class="col-sm-9">
                                <input type="hidden" name="menuPic" placeholder="菜单图标" class="col-sm-6" dataMaxLength="10" disabled/>
                                <a class="btn btn-white btn-lg" id="picContent" onclick="queryMenuPic()">
                                    <i class="icon-only ace-icon fa fa-align-justify"></i>
                                </a>
                            </div>
                        </div>
                        <%--名称--%>
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right">菜单名称: </label>

                            <div class="col-sm-9">
                                <input type="hidden" name="menuId"/>
                                <input type="text" name="menuName" placeholder="菜单名称" class="col-sm-6" dataMaxLength="10" dataExist="true"/>
                            </div>
                        </div>
                        <%--序号--%>
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right">菜单序号: </label>

                            <div class="col-sm-9">
                                <input type="text" name="menuSn" placeholder="菜单序号" class="col-sm-6" dataMaxLength="3"/>
                            </div>
                        </div>

                        <%--菜单url--%>
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right">菜单Url: </label>

                            <div class="col-sm-9">
                                <input type="text" name="menuUrl" placeholder="菜单Url" class="col-sm-6" dataMaxLength="100"/>
                            </div>
                        </div>
                        <%--状态--%>
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right">状态: </label>

                            <div class="col-sm-9">
                                <div class="col-sm-6" style="display: none"></div>
                                <select class="chosen-select col-sm-6" name="status" placeholder="状态">
                                    <option value="1">启用</option>
                                    <option value="2">停用</option>
                                </select>
                            </div>
                        </div>
                        <%--备注--%>
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right"> 备注: </label>

                            <div class="col-sm-9">
                                <textarea class="col-sm-6" name="remark" style="margin: 0px; height: 71px;resize: none;" placeholder="备注"></textarea>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right"></label>

                            <div class="col-sm-5 center">
                                <a class="btn btn-primary btn-round"  data-action="save"  id="loading-btn" data-loading-text="加载中...">
                                    <i class="ace-icon fa fa-save align-top bigger-125"></i>
                                    保存
                                </a>
                                <a class="btn btn-primary btn-round" onclick="resetData();" data-loading-text="加载中...">
                                    <i class="ace-icon fa fa-save align-top bigger-125"></i>
                                    重置
                                </a>
                            </div>
                        </div>
                    </form>
                </span>
                </div>
            </div>
        </div>
    </div>
</div>

</body>
</html>
