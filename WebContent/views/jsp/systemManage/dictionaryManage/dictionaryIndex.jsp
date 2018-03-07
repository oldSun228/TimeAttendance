<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <meta charset="utf-8"/>
    <title>字典管理</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0"/>
    <%-- 下拉框 css--%>
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
    <%--成功后的 提示信息 css--%>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resource/bootstrap/assets/css/jquery.gritter.css"/>
    <%--ztree css--%>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resource/jqueryztree/css/zTreeStyle/zTreeStyle.css" type="text/css">
    <%--ztree css--%>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resource/jqueryztree/css/zTreeStyle/metro.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resource/css/jQueryValidateForForm.css"/>
    <%-- 共通 css--%>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resource/css/common.css"/>
    <%-- 滚动条 css--%>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resource/scroll/jquery.mCustomScrollbar.css"  />
    <script type="text/javascript" src='${pageContext.request.contextPath}/resource/jqueryztree/js/jquery-1.9.1.js'></script>
    <%--提交 共通 js--%>
    <script type="text/javascript" src='${pageContext.request.contextPath}/resource/js/ajaxSubmit.js'></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resource/jqueryztree/js/jquery.ztree.all-3.5.js"></script>
    <%-- 下拉框 js--%>
    <script src="${pageContext.request.contextPath}/resource/bootstrap/assets/js/chosen.jquery.js"></script>
    <%--数据验证  和 格式化 数据 js--%>
    <script type="text/javascript" src='${pageContext.request.contextPath}/resource/js/formToValidate.js'></script>
    <%--共通js--%>
    <script type="text/javascript" src='${pageContext.request.contextPath}/resource/js/common.js'></script>
    <%-- 基础js--%>
    <script type="text/javascript" src='${pageContext.request.contextPath}/views/jsp/systemManage/dictionaryManage/inc/dictionaryIndex.js'></script>
    <%--成功后的 提示信息 js--%>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resource/bootstrap/assets/js/jquery.gritter.js"></script>
    <style type="text/css">
        .content{float:left;}
        .content{height:500px;}
    </style>
</head>
<body>
<input type="hidden" name="contextPath" value="${pageContext.request.contextPath}">
    <div class="main-content-inner">
        <%--初始化显示的页面--%>
        <div class="initArea">
            <!-- 主体部分 begin-->
            <div class="page-content row">
                <%--左半边--%>
                <div class="content col-xs-3">
                    <ul id="tree" class="ztree"></ul>
                </div>
                <%--右半边--%>
                <div class="col-xs-9">
                    <%--编辑处--%>
                    <div class="editArea">
                        <div class="col-xs-12">
                            <span class="form-horizontal">
                                <form id="dicForm">
                                    <%--父节点--%>
                                    <div id="parentDiv" class="form-group">
                                        <label class="col-sm-3 control-label no-padding-right">父节点名称: </label>

                                        <div class="col-sm-9">
                                            <input type="hidden" name="dicId"/>
                                            <input type="hidden" name="parentId"/>
                                            <input type="text" name="parentName" placeholder="父节点名称" class="col-sm-6" disabled dataMaxLength="10" dataExist="true"/>
                                        </div>
                                    </div>
                                    <%--名称--%>
                                    <div class="form-group">
                                        <label class="col-sm-3 control-label no-padding-right">字典名称: </label>

                                        <div class="col-sm-9">
                                            <input type="text" name="name" placeholder="字典名称" class="col-sm-6" dataMaxLength="10" dataExist="true"/>
                                        </div>
                                    </div>
                                    <%--code--%>
                                    <div id="dicCode" class="form-group">
                                        <label class="col-sm-3 control-label no-padding-right">字典值: </label>

                                        <div class="col-sm-9">
                                            <input type="text" name="code" placeholder="字典值" class="col-sm-6" dataMaxLength="3" dataExist="true"/>
                                        </div>
                                    </div>
                                    <%--code--%>
                                    <div id="dicIdenty" class="form-group">
                                        <label class="col-sm-3 control-label no-padding-right">字典标识: </label>

                                        <div class="col-sm-9">
                                            <input type="text" name="identy" placeholder="字典标识" class="col-sm-6" dataMaxLength="10"/>
                                        </div>
                                    </div>
                                    <%--描述--%>
                                    <div class="form-group">
                                        <label class="col-sm-3 control-label no-padding-right">字典描述: </label>

                                        <div class="col-sm-9">
                                            <input type="text" name="description" placeholder="字典描述" class="col-sm-6" dataMaxLength="10" dataExist="true"/>
                                        </div>
                                    </div>
                                    <%--状态--%>
                                    <div class="form-group">
                                        <label class="col-sm-3 control-label no-padding-right">状态: </label>


                                        <div class="col-sm-9">
                                            <div class="col-sm-6" style="display: none"></div>
                                            <select class="chosen-select col-sm-6" name="status" data-placeholder="Choose a State..." placeholder="状态">
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

                                        <div id="saveButton" class="col-sm-5 center" style="display: none">
                                            <a class="btn btn-primary btn-round" data-action="save" onclick="save();" data-loading-text="加载中...">
                                                <i class="ace-icon fa fa-save align-top bigger-125"></i>
                                                保存
                                            </a>
                                        </div>
                                    </div>
                                </form>
                            </span>
                        </div>
                    </div>
                </div>
            </div>
            <%--主体部分 end--%>
        </div>
    </div>
    <%--滚动条 js--%>
    <script src="${pageContext.request.contextPath}/resource/scroll/jquery.mCustomScrollbar.concat.min.js"></script>
    <script>
        (function($){
            $(document).ready(function(){
                $(".content").mCustomScrollbar({
                    scrollInertia:150,
                    autoHideScrollbar:true,//是否 隐藏
                    scrollButtons:{ enable: false }//是否显示 上下按钮
                });
            });
        })(jQuery);
    </script>

</body>
</html>
