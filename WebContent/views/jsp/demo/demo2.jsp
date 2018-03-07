<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <meta charset="utf-8" />
    <title>Login Page</title>
    <meta name="description" content="User login page"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resource/bootstrap/assets/css/bootstrap.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resource/bootstrap/assets/css/font-awesome.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resource/bootstrap/assets/css/ace-fonts.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resource/bootstrap/assets/css/ace.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resource/bootstrap/assets/css/ace-rtl.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resource/css/jQueryValidateForLogin.css"/>
    <script type="text/javascript" src='${pageContext.request.contextPath}/resource/bootstrap/assets/js/jquery.js'></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resource/bootstrap/assets/js/bootstrap.js"></script>
    <script type="text/javascript" src='${pageContext.request.contextPath}/resource/jQuery.validate/jquery.validate.js'></script>
    <script type="text/javascript" src='${pageContext.request.contextPath}/resource/jQuery.validate/additional-methods.js'></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resource/js/js.cookie.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resource/layer/layer.js"></script>
</head>

<body class="login-layout">
<input type="hidden" name="flag" value="${flag}"/>
<div class="main-container">
    <div class="main-content">
        <div class="row">
            <div class="col-sm-10 col-sm-offset-1">
                <button class="btn">上传附件</button>
            </div>
        </div>
    </div>
</div>
</body>
</html>
