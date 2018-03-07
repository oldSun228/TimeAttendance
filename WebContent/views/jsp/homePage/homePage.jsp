<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <meta charset="utf-8"/>
    <title>Ace Admin</title>
    <meta name="description" content="404 Error Page"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resource/bootstrap/assets/css/bootstrap.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resource/bootstrap/assets/css/font-awesome.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resource/bootstrap/assets/css/ace-fonts.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resource/bootstrap/assets/css/ace.css" class="ace-main-stylesheet" id="main-ace-style"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resource/bootstrap/assets/css/ace-part2.css" class="ace-main-stylesheet"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resource/bootstrap/assets/css/ace-ie.css"/>
    <script src="${pageContext.request.contextPath}/resource/bootstrap/assets/js/ace-extra.js"></script>
    <script src="${pageContext.request.contextPath}/resource/bootstrap/assets/js/html5shiv.js"></script>
    <script src="${pageContext.request.contextPath}/resource/bootstrap/assets/js/respond.js"></script>
    <style>
        body{margin:0; padding:0;}
    </style>
</head>

<body class="no-skin">
<input type="hidden" name="contextPath" value="${pageContext.request.contextPath}">
<!-- 头部信息 开始  navbar-fixed-top-->
<div id="navbar" class="navbar navbar-default navbar-fixed-top">
    <script type="text/javascript">
        try {
            ace.settings.check('navbar', 'fixed')
        } catch (e) {
        }
    </script>

    <div class="navbar-container" id="navbar-container">
        <!-- #section:basics/sidebar.mobile.toggle -->
        <button type="button" class="navbar-toggle menu-toggler pull-left" id="menu-toggler" data-target="#sidebar">
            <span class="sr-only">Toggle sidebar</span>

            <span class="icon-bar"></span>

            <span class="icon-bar"></span>

            <span class="icon-bar"></span>
        </button>

        <!-- /section:basics/sidebar.mobile.toggle -->
        <div class="navbar-header pull-left">
            <!-- #section:basics/navbar.layout.brand -->
            <a href="#" class="navbar-brand">
                <small>
                    <i class="fa fa-leaf"></i>
                    南京**有限公司
                </small>
            </a>

            <!-- /section:basics/navbar.layout.brand -->

            <!-- #section:basics/navbar.toggle -->

            <!-- /section:basics/navbar.toggle -->
        </div>

        <!-- #section:basics/navbar.dropdown -->
        <div class="navbar-buttons navbar-header pull-right" role="navigation">
            <ul class="nav ace-nav">
                <li class="grey">
                    <a data-toggle="dropdown" class="dropdown-toggle" href="#">
                        <i class="ace-icon fa fa-tasks"></i>
                        <span class="badge badge-grey">4</span>
                    </a>

                    <ul class="dropdown-menu-right dropdown-navbar dropdown-menu dropdown-caret dropdown-close">
                        <li class="dropdown-header">
                            <i class="ace-icon fa fa-check"></i>
                            4 Tasks to complete
                        </li>

                        <li class="dropdown-content">
                            <ul class="dropdown-menu dropdown-navbar">
                                <li>
                                    <a href="#">
                                        <div class="clearfix">
                                            <span class="pull-left">Software Update</span>
                                            <span class="pull-right">65%</span>
                                        </div>

                                        <div class="progress progress-mini">
                                            <div style="width:65%" class="progress-bar"></div>
                                        </div>
                                    </a>
                                </li>

                                <li>
                                    <a href="#">
                                        <div class="clearfix">
                                            <span class="pull-left">Hardware Upgrade</span>
                                            <span class="pull-right">35%</span>
                                        </div>

                                        <div class="progress progress-mini">
                                            <div style="width:35%" class="progress-bar progress-bar-danger"></div>
                                        </div>
                                    </a>
                                </li>

                                <li>
                                    <a href="#">
                                        <div class="clearfix">
                                            <span class="pull-left">Unit Testing</span>
                                            <span class="pull-right">15%</span>
                                        </div>

                                        <div class="progress progress-mini">
                                            <div style="width:15%" class="progress-bar progress-bar-warning"></div>
                                        </div>
                                    </a>
                                </li>

                                <li>
                                    <a href="#">
                                        <div class="clearfix">
                                            <span class="pull-left">Bug Fixes</span>
                                            <span class="pull-right">90%</span>
                                        </div>

                                        <div class="progress progress-mini progress-striped active">
                                            <div style="width:90%" class="progress-bar progress-bar-success"></div>
                                        </div>
                                    </a>
                                </li>
                            </ul>
                        </li>

                        <li class="dropdown-footer">
                            <a href="#">
                                See tasks with details
                                <i class="ace-icon fa fa-arrow-right"></i>
                            </a>
                        </li>
                    </ul>
                </li>

                <li class="purple">
                    <a data-toggle="dropdown" class="dropdown-toggle" href="#">
                        <i class="ace-icon fa fa-bell icon-animated-bell"></i>
                        <span class="badge badge-important">8</span>
                    </a>

                    <ul class="dropdown-menu-right dropdown-navbar navbar-pink dropdown-menu dropdown-caret dropdown-close">
                        <li class="dropdown-header">
                            <i class="ace-icon fa fa-exclamation-triangle"></i>
                            8 Notifications
                        </li>

                        <li class="dropdown-content">
                            <ul class="dropdown-menu dropdown-navbar navbar-pink">
                                <li>
                                    <a href="#">
                                        <div class="clearfix">
													<span class="pull-left">
														<i class="btn btn-xs no-hover btn-pink fa fa-comment"></i>
														New Comments
													</span>
                                            <span class="pull-right badge badge-info">+12</span>
                                        </div>
                                    </a>
                                </li>

                                <li>
                                    <a href="#">
                                        <i class="btn btn-xs btn-primary fa fa-user"></i>
                                        Bob just signed up as an editor ...
                                    </a>
                                </li>

                                <li>
                                    <a href="#">
                                        <div class="clearfix">
													<span class="pull-left">
														<i class="btn btn-xs no-hover btn-success fa fa-shopping-cart"></i>
														New Orders
													</span>
                                            <span class="pull-right badge badge-success">+8</span>
                                        </div>
                                    </a>
                                </li>

                                <li>
                                    <a href="#">
                                        <div class="clearfix">
													<span class="pull-left">
														<i class="btn btn-xs no-hover btn-info fa fa-twitter"></i>
														Followers
													</span>
                                            <span class="pull-right badge badge-info">+11</span>
                                        </div>
                                    </a>
                                </li>
                            </ul>
                        </li>

                        <li class="dropdown-footer">
                            <a href="#">
                                See all notifications
                                <i class="ace-icon fa fa-arrow-right"></i>
                            </a>
                        </li>
                    </ul>
                </li>

                <li class="green">
                    <a data-toggle="dropdown" class="dropdown-toggle" href="#">
                        <i class="ace-icon fa fa-envelope icon-animated-vertical"></i>
                        <span class="badge badge-success">5</span>
                    </a>

                    <ul class="dropdown-menu-right dropdown-navbar dropdown-menu dropdown-caret dropdown-close">
                        <li class="dropdown-header">
                            <i class="ace-icon fa fa-envelope-o"></i>
                            5 Messages
                        </li>

                        <li class="dropdown-content">
                            <ul class="dropdown-menu dropdown-navbar">
                                <li>
                                    <a href="#" class="clearfix">
                                        <img src="${pageContext.request.contextPath}/resource/bootstrap/assets/avatars/avatar.png" class="msg-photo" alt="Alex's Avatar"/>
												<span class="msg-body">
													<span class="msg-title">
														<span class="blue">Alex:</span>
														Ciao sociis natoque penatibus et auctor ...
													</span>

													<span class="msg-time">
														<i class="ace-icon fa fa-clock-o"></i>
														<span>a moment ago</span>
													</span>
												</span>
                                    </a>
                                </li>

                                <li>
                                    <a href="#" class="clearfix">
                                        <img src="${pageContext.request.contextPath}/resource/bootstrap/assets/avatars/avatar3.png" class="msg-photo" alt="Susan's Avatar"/>
                                        <span class="msg-body">
                                            <span class="msg-title">
                                                <span class="blue">Susan:</span>
                                                Vestibulum id ligula porta felis euismod ...
                                            </span>

                                            <span class="msg-time">
                                                <i class="ace-icon fa fa-clock-o"></i>
                                                <span>20 minutes ago</span>
                                            </span>
                                        </span>
                                    </a>
                                </li>

                                <li>
                                    <a href="#" class="clearfix">
                                        <img src="${pageContext.request.contextPath}/resource/bootstrap/assets/avatars/avatar4.png" class="msg-photo" alt="Bob's Avatar"/>
                                        <span class="msg-body">
                                            <span class="msg-title">
                                                <span class="blue">Bob:</span>
                                                Nullam quis risus eget urna mollis ornare ...
                                            </span>

                                            <span class="msg-time">
                                                <i class="ace-icon fa fa-clock-o"></i>
                                                <span>3:15 pm</span>
                                            </span>
                                        </span>
                                    </a>
                                </li>

                                <li>
                                    <a href="#" class="clearfix">
                                        <img src="${pageContext.request.contextPath}/resource/bootstrap/assets/avatars/avatar2.png" class="msg-photo" alt="Kate's Avatar"/>
                                        <span class="msg-body">
                                            <span class="msg-title">
                                                <span class="blue">Kate:</span>
                                                Ciao sociis natoque eget urna mollis ornare ...
                                            </span>

                                            <span class="msg-time">
                                                <i class="ace-icon fa fa-clock-o"></i>
                                                <span>1:33 pm</span>
                                            </span>
                                        </span>
                                    </a>
                                </li>

                                <li>
                                    <a href="#" class="clearfix">
                                        <img src="${pageContext.request.contextPath}/resource/bootstrap/assets/avatars/avatar5.png" class="msg-photo" alt="Fred's Avatar"/>
                                        <span class="msg-body">
                                            <span class="msg-title">
                                                <span class="blue">Fred:</span>
                                                Vestibulum id penatibus et auctor  ...
                                            </span>

                                            <span class="msg-time">
                                                <i class="ace-icon fa fa-clock-o"></i>
                                                <span>10:09 am</span>
                                            </span>
                                        </span>
                                    </a>
                                </li>
                            </ul>
                        </li>

                        <li class="dropdown-footer">
                            <a href="inbox.html">
                                See all messages
                                <i class="ace-icon fa fa-arrow-right"></i>
                            </a>
                        </li>
                    </ul>
                </li>

                <li class="light-blue">
                    <a data-toggle="dropdown" href="#" class="dropdown-toggle">
                        <img class="nav-user-photo" src="${pageContext.request.contextPath}/resource/bootstrap/assets/avatars/user.jpg" alt="Jason's Photo"/>
                        <span class="user-info">
                            <small>Welcome,</small>
                             ${sessionScope.ADMIN_USER_BEAN.userName}
                        </span>

                        <i class="ace-icon fa fa-caret-down"></i>
                    </a>

                    <ul class="user-menu dropdown-menu-right dropdown-menu dropdown-yellow dropdown-caret dropdown-close">
                        <li>
                            <a href="#">
                                <i class="ace-icon fa fa-cog"></i>
                                设置
                            </a>
                        </li>

                        <li>
                            <a href="profile.html">
                                <i class="ace-icon fa fa-user"></i>
                                个人中心
                            </a>
                        </li>

                        <li class="divider"></li>

                        <li>
                            <a data-action="loginOut">
                                <i class="ace-icon fa fa-power-off"></i>
                                退出
                            </a>
                        </li>
                    </ul>
                </li>
            </ul>
        </div>
    </div>
</div>
<!-- 头部信息 结束-->

<!-- 中间部分信息 开始-->
<div class="main-container" id="main-container">
    <script type="text/javascript">
        try {
            ace.settings.check('main-container', 'fixed')
        } catch (e) {
        }
    </script>

    <!-- #section:basics/sidebar  sidebar-fixed sidebar-scroll-->
    <div id="sidebar" class="sidebar responsive sidebar-fixed sidebar-scroll" data-sidebar="true" data-sidebar-scroll="true" data-sidebar-hover="true">
        <script type="text/javascript">
            try {
                ace.settings.check('sidebar', 'fixed')
            } catch (e) {
            }
        </script>
        <%--左侧图标隐藏--%>
        <div class="sidebar-shortcuts" id="sidebar-shortcuts" style="display: none">
            <div class="sidebar-shortcuts-large" id="sidebar-shortcuts-large">
                <button class="btn btn-success">
                    <i class="ace-icon fa fa-signal"></i>
                </button>

                <button class="btn btn-info">
                    <i class="ace-icon fa fa-pencil"></i>
                </button>

                <!-- #section:basics/sidebar.layout.shortcuts -->
                <button class="btn btn-warning">
                    <i class="ace-icon fa fa-users"></i>
                </button>

                <button class="btn btn-danger">
                    <i class="ace-icon fa fa-cogs"></i>
                </button>

                <!-- /section:basics/sidebar.layout.shortcuts -->
            </div>

            <div class="sidebar-shortcuts-mini" id="sidebar-shortcuts-mini">
                <span class="btn btn-success"></span>

                <span class="btn btn-info"></span>

                <span class="btn btn-warning"></span>

                <span class="btn btn-danger"></span>
            </div>
        </div>

        <%--树的位置--%>

        <%-- 展开 搜索 按钮--%>
        <div class="sidebar-toggle sidebar-collapse" id="sidebar-collapse">
            <i class="ace-icon fa fa-angle-double-left" data-icon1="ace-icon fa fa-angle-double-left" data-icon2="ace-icon fa fa-angle-double-right"></i>
        </div>


        <!-- /section:basics/sidebar.layout.minimize -->
        <script type="text/javascript">
            try {
                ace.settings.check('sidebar', 'collapsed')
            } catch (e) {
            }
        </script>
    </div>

    <!-- /section:basics/sidebar -->
    <div class="main-content">
        <iframe style="padding: 0px; width: 100%;background-color: #ffffff;" name="mainContent" id="mainContent" frameborder=0 scrolling="yes" noresize
                marginheight="0" marginwidth="0" src="${pageContext.request.contextPath}/homePage/toMiddleInitial" onload="changeFrameHeight()"></iframe>
    </div>
    <!-- /.main-content -->


    <a href="#" id="btn-scroll-up" class="btn-scroll-up btn btn-sm btn-inverse">
        <i class="ace-icon fa fa-angle-double-up icon-only bigger-110"></i>
    </a>
</div>
<!-- 中间部分信息 结束-->


<script type="text/javascript">
    window.jQuery || document.write("<script src='${pageContext.request.contextPath}/resource/bootstrap/assets/js/jquery.js'>" + "<" + "/script>");
</script>

<!--[if IE]>
<script type="text/javascript">
    window.jQuery || document.write("<script src='${pageContext.request.contextPath}/resource/bootstrap/assets/js/jquery1x.js'>" + "<" + "/script>");
</script>
<![endif]-->

<script type="text/javascript" src="${pageContext.request.contextPath}/resource/js/ajaxSubmit.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resource/js/initialLeftZtree.js"></script>
<script type="text/javascript" src='${pageContext.request.contextPath}/resource/js/initialHeightUtil.js'></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/views/jsp/homePage/inc/homePage.js"></script>

<script type="text/javascript">
    if ('ontouchstart' in document.documentElement) document.write("<script src='${pageContext.request.contextPath}/resource/bootstrap/assets/js/jquery.mobile.custom.js'>" + "<" + "/script>");
</script>
<script src="${pageContext.request.contextPath}/resource/bootstrap/assets/js/bootstrap.js"></script>
<script src="${pageContext.request.contextPath}/resource/bootstrap/assets/js/ace/elements.scroller.js"></script>
<script src="${pageContext.request.contextPath}/resource/bootstrap/assets/js/ace/elements.colorpicker.js"></script>
<script src="${pageContext.request.contextPath}/resource/bootstrap/assets/js/ace/elements.fileinput.js"></script>
<script src="${pageContext.request.contextPath}/resource/bootstrap/assets/js/ace/elements.typeahead.js"></script>
<script src="${pageContext.request.contextPath}/resource/bootstrap/assets/js/ace/elements.wysiwyg.js"></script>
<script src="${pageContext.request.contextPath}/resource/bootstrap/assets/js/ace/elements.spinner.js"></script>
<script src="${pageContext.request.contextPath}/resource/bootstrap/assets/js/ace/elements.treeview.js"></script>
<script src="${pageContext.request.contextPath}/resource/bootstrap/assets/js/ace/elements.wizard.js"></script>
<script src="${pageContext.request.contextPath}/resource/bootstrap/assets/js/ace/elements.aside.js"></script>
<script src="${pageContext.request.contextPath}/resource/bootstrap/assets/js/ace/ace.js"></script>
<script src="${pageContext.request.contextPath}/resource/bootstrap/assets/js/ace/ace.ajax-content.js"></script>
<script src="${pageContext.request.contextPath}/resource/bootstrap/assets/js/ace/ace.touch-drag.js"></script>
<script src="${pageContext.request.contextPath}/resource/bootstrap/assets/js/ace/ace.sidebar.js"></script>
<script src="${pageContext.request.contextPath}/resource/bootstrap/assets/js/ace/ace.sidebar-scroll-1.js"></script>
<script src="${pageContext.request.contextPath}/resource/bootstrap/assets/js/ace/ace.submenu-hover.js"></script>
<script src="${pageContext.request.contextPath}/resource/bootstrap/assets/js/ace/ace.widget-box.js"></script>
<script src="${pageContext.request.contextPath}/resource/bootstrap/assets/js/ace/ace.settings.js"></script>
<script src="${pageContext.request.contextPath}/resource/bootstrap/assets/js/ace/ace.settings-rtl.js"></script>
<script src="${pageContext.request.contextPath}/resource/bootstrap/assets/js/ace/ace.settings-skin.js"></script>
<script src="${pageContext.request.contextPath}/resource/bootstrap/assets/js/ace/ace.widget-on-reload.js"></script>
<script src="${pageContext.request.contextPath}/resource/bootstrap/assets/js/ace/ace.searchbox-autocomplete.js"></script>

<!-- inline scripts related to this page -->

<!-- the following scripts are used in demo only for onpage help and you don't need them -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/resource/bootstrap/assets/css/ace.onpage-help.css"/>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resource/bootstrap/docs/assets/js/themes/sunburst.css"/>

<script type="text/javascript"> ace.vars['base'] = '..'; </script>
<script src="${pageContext.request.contextPath}/resource/bootstrap/assets/js/ace/elements.onpage-help.js"></script>
<script src="${pageContext.request.contextPath}/resource/bootstrap/assets/js/ace/ace.onpage-help.js"></script>
<script src="${pageContext.request.contextPath}/resource/bootstrap/docs/assets/js/rainbow.js"></script>
<script src="${pageContext.request.contextPath}/resource/bootstrap/docs/assets/js/language/generic.js"></script>
<script src="${pageContext.request.contextPath}/resource/bootstrap/docs/assets/js/language/html.js"></script>
<script src="${pageContext.request.contextPath}/resource/bootstrap/docs/assets/js/language/css.js"></script>
<script src="${pageContext.request.contextPath}/resource/bootstrap/docs/assets/js/language/javascript.js"></script>
</body>
</html>
