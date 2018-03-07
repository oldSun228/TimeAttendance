<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <meta charset="utf-8"/>
    <title>角色管理</title>
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
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resource/bootstrap/assets/css/jquery.gritter.css" />

    <link rel="stylesheet" href="${pageContext.request.contextPath}/resource/css/jQueryValidateForForm.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resource/css/common.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resource/jqueryztree/css/zTreeStyle/metro.css">

    <script type="text/javascript" src='${pageContext.request.contextPath}/resource/jqueryztree/js/jquery-1.9.1.js'></script>
    <script type="text/javascript" src='${pageContext.request.contextPath}/resource/jQuery.validate/jquery.validate.js'></script>
    <script type="text/javascript" src='${pageContext.request.contextPath}/resource/dataTables/js/jquery.dataTables.js'></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resource/bootstrap/assets/js/bootstrap.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resource/dataTables/js/dataTables.bootstrap.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resource/jqueryztree/js/jquery.ztree.all-3.5.js"></script>
    <script src="${pageContext.request.contextPath}/resource/bootstrap/assets/js/chosen.jquery.js"></script>

    <script src="${pageContext.request.contextPath}/resource/bootstrap/assets/js/ace/ace.js"></script>
    <script src="${pageContext.request.contextPath}/resource/layer/layer.js"></script>
    <script src="${pageContext.request.contextPath}/resource/bootstrap/assets/js/jquery.gritter.js"></script>


    <script type="text/javascript" src='${pageContext.request.contextPath}/resource/js/ajaxSubmit.js'></script>

    <script type="text/javascript" src='${pageContext.request.contextPath}/resource/js/formToValidate.js'></script>
    <script type="text/javascript" src='${pageContext.request.contextPath}/resource/js/common.js'></script>
    <script type="text/javascript" src='${pageContext.request.contextPath}/resource/js/dataFormat.js'></script>
    <script type="text/javascript" src='${pageContext.request.contextPath}/views/jsp/systemManage/roleManage/inc/roleIndex.js'></script>
    <script type="text/javascript" src='${pageContext.request.contextPath}/views/jsp/systemManage/roleManage/inc/powerMenu.js'></script>

</head>
<body>
<input type="hidden" name="contextPath" value="${pageContext.request.contextPath}">

<div class="main-content-inner">
    <%--初始化显示的页面 开始--%>
    <div class="initArea">
        <%--当前页面头部信息--%>
        <div class="breadcrumbs" id="breadcrumbs">
            <ul class="breadcrumb">
                <li>
                    <i class="ace-icon fa fa-home home-icon"></i>
                    <a href="#">Home</a>
                </li>
            </ul>
            <div class="nav-search action-buttons" id="nav-search">
                <a class="btn-sm" data-action="openSearchArea" href="#">
                    <i class="ace-icon fa fa-search bigger-180"></i>
                </a>
                <a class="btn-sm orange" data-action="openAdd" href="#">
                    <i class="ace-icon fa fa-plus bigger-180"></i>
                </a>
                <%--删除图标--%>
                <%--
                 <a class="btn-sm red" data-action="batchDelete" href="#">
                     <i class="ace-icon fa fa-trash-o bigger-180"></i>
                 </a>
                 --%>
                <a class="btn-sm green" data-action="refresh" href="#">
                    <i class="ace-icon fa fa-refresh bigger-180"></i>
                </a>
            </div>
        </div>

        <!-- 主体部分 -->
        <div class="page-content">
            <span class="form-horizontal">
                <div class="row searchArea defaultHide">
                    <div class="col-xs-3">
                        <label class="col-xs-3 control-label no-padding-right">名称: </label>

                        <div class="col-xs-9">
                            <input type="text" placeholder="名称" class="col-xs-12" name="roleName">
                        </div>
                    </div>
                    <div class="col-xs-3">
                        <label class="col-xs-3 control-label no-padding-right">状态: </label>

                        <div class="col-xs-9">
                            <div class="col-sm-12" style="display: none"></div>
                            <select class="chosen-select form-control" name="status" data-placeholder="Choose a State..." placeholder="状态">
                                <option value="">-请选择-</option>
                                <option value="1">启用</option>
                                <option value="0">停用</option>
                            </select>
                        </div>
                    </div>
                    <div class="col-xs-3">

                    </div>
                    <div class="col-xs-3">
                        <div class="col-xs-6 center">
                            <button class="btn btn-sm btn-primary btn-round" data-action="searchDate">查询</button>
                        </div>
                        <div class="col-xs-6">
                            <button class="btn btn-sm btn-primary btn-round" data-action="reset">重置</button>
                        </div>
                    </div>
                </div>
            </span>

            <table id="roleTable" class="table table-striped table-bordered table-hover" style="width: 100%;">
                <thead>
                <tr>
                    <th>ID</th>
                    <th>名称</th>
                    <th>状态</th>
                    <th>备注</th>
                    <th>创建者</th>
                    <th>创建时间</th>
                    <th>操作</th>
                </tr>
                </thead>
                <tbody>
                </tbody>
            </table>
        </div>
    </div>
    <%--初始化显示的页面 结束--%>
    <%--增加，编辑，详情显示的页面 开始--%>
    <div class="editArea" style="padding-top:3px;display: none">
        <div class="col-sm-12">
            <div class="row">
                <div class="col-xs-12">
                    <span class="form-horizontal">
                        <form id="RoleForm">
                            <%-- 角色表id--%>
                            <input type="hidden" name="roleId"/>
                            <input type="hidden" name="identy"/>
                            <%--名称--%>
                            <div class="form-group">
                                <label class="col-sm-3 control-label no-padding-right">名称: </label>
                                <div class="col-sm-9">
                                    <input type="text" name="roleName" placeholder="名称" class="col-sm-5" dataMaxLength="10" dataExist="true"/>
                                </div>
                            </div>
                            <%--状态--%>
                            <div class="form-group">
                                <label class="col-sm-3 control-label no-padding-right">状态: </label>
                                <div class="col-sm-9">
                                    <div class="col-sm-5" style="display: none"></div>
                                    <select class="chosen-select form-control" name="status" data-placeholder="Choose a State..." placeholder="状态">
                                        <option value="">-请选择-</option>
                                        <option value="1">启用</option>
                                        <option value="0">停用</option>
                                    </select>
                                </div>
                            </div>
                            <%--备注--%>
                            <div class="form-group">
                                <label class="col-sm-3 control-label no-padding-right">备注: </label>
                                <div class="col-sm-9">
                                    <textarea class="col-sm-5" name="remark" style="margin: 0px; height: 71px;resize: none;" placeholder="备注"></textarea>
                                </div>
                            </div>

                            <div class="form-group">
                                <div class="col-sm-2 center"></div>
                                <div class="col-sm-3 center btn-operate">
                                    <a class="btn btn-primary btn-round" data-action="save" data-loading-text="加载中...">
                                        <i class="ace-icon fa fa-save align-top bigger-125"></i>
                                        保存
                                    </a>
                                </div>
                                <div class="col-sm-3">
                                    <a class="btn btn-primary btn-round" data-action="cancel">
                                        <i class="ace-icon fa fa-close align-top bigger-125"></i>
                                        取消
                                    </a>
                                </div>
                                <div class="col-sm-4 center"></div>
                            </div>
                        </form>
                    </span>
                </div>
            </div>
        </div>
    </div>
    <%--编辑显示的页面 结束--%>
    <%--权限的页面 开始--%>
    <div class="powerArea" style="display: none;">
        <div class="page-header">
            <h1>
                权限编辑
            </h1>
        </div>
        <div class="center">
            <div class="col-xs-5"></div>
            <div class="col-xs-3" style="overflow-y:auto; height:400px;">
                <div id="treeDiv" class="ztree" style="position: relative; top: 0px; transition-property: top; transition-duration: 0.15s;"></div>
            </div>
            <div class="col-xs-4"></div>
            <div class="col-xs-12" style="margin-top: 20px">
                    <a class="btn btn-primary btn-round" data-action="powerSave" data-loading-text="加载中...">
                        <i class="ace-icon fa fa-save align-top bigger-125"></i>
                        保存
                    </a>
                    <a class="btn btn-primary btn-round" data-action="powerCancel">
                        <i class="ace-icon fa fa-close align-top bigger-125"></i>
                        取消
                    </a>
            </div>
        </div>
    </div>
    <%--权限的页面 结束--%>
</div>
</body>
</html>
