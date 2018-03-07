<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <meta charset="utf-8"/>
    <title>角色选择页</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resource/bootstrap/assets/css/bootstrap.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resource/bootstrap/assets/css/ace.css" />
    <%--共通js--%>
    <script type="text/javascript" src='${pageContext.request.contextPath}/resource/js/common.js'></script>
    <%--提交共通js--%>
    <script type="text/javascript" src='${pageContext.request.contextPath}/resource/js/ajaxSubmit.js'></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/views/jsp/login/inc/roleIndexPage.js"></script>

</head>
<body>
<input type="hidden" name="contextPath" value="${pageContext.request.contextPath}" />
<input type="hidden" name="roleIdenty" />
<%--初始化显示的页面--%>
<div class="col-sm-12" style="margin: 0; padding: 0;">
    <table class="table table-striped table-bordered table-hover">

    </table>
</div>
</body>
</html>
