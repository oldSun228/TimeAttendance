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
    <%--弹出层 共通js--%>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resource/js/layerUtil.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resource/js/loginTreatmentApplication.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/views/jsp/login/inc/login.js"></script>
</head>

<body class="login-layout">
<input type="hidden" name="flag" value="${flag}"/>
<div class="main-container">
    <div class="main-content">
        <div class="row">
            <div class="col-sm-10 col-sm-offset-1" style="margin-top: 12%;">
                <div class="login-container">
                    <div class="center">
                        <h1>
                            <i class="ace-icon fa fa-leaf green"></i>
                            <span class="white" id="id-text2">*****系统</span>
                        </h1>
                        <h4 class="blue" id="id-company-text">&copy; 南京***信息技术有限公司</h4>
                    </div>

                    <div class="space-6"></div>

                    <div class="position-relative">
                        <div class="space-6"></div>
                        <form id="LoginForm">
                            <input type="hidden" name="path" value="${pageContext.request.contextPath}"/>
                            <fieldset>
                                <label class="block clearfix">
                                    <span class="block input-icon input-icon-left">
                                        <input type="text" class="form-control" maxlength="20" placeholder="Username" name="account"/>
                                        <i class="ace-icon fa fa-user"></i>
									</span>
                                </label>
                                <label class="block clearfix">
                                    <span class="block input-icon input-icon-left">
                                        <input type="password" class="form-control" maxlength="20" placeholder="Password" name="password"/>
                                        <i class="ace-icon fa fa-lock"></i>
                                    </span>
                                </label>

                                <div class="space"></div>

                                <div class="clearfix">
                                    <label class="inline">
                                        <input type="checkbox" name="rememberMe" class="ace"/>
                                        <span class="lbl">记住我</span>
                                    </label>
                                    <label class="inline pull-right" data-action="forgetPassword" style="cursor: pointer;">
                                        <span class="lbl">忘记密码?</span>
                                    </label>
                                </div>

                                <div class="space"></div>

                                <div class="clearfix">
                                    <a id="loading-btn" class="col-xs-12 btn btn-sm btn-primary btn-round" data-loading-text="加载中...">
                                        <i class="ace-icon fa fa-key"></i>
                                        <span class="bigger-110">登录</span>
                                    </a>
                                </div>

                                <div class="space-4"></div>
                            </fieldset>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
