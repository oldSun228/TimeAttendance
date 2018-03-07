<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <meta charset="utf-8"/>
    <title>用户管理</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resource/bootstrap/assets/css/chosen.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resource/bootstrap/assets/css/bootstrap.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resource/dataTables/css/dataTables.bootstrap.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resource/bootstrap/assets/css/font-awesome.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resource/bootstrap/assets/css/ace-fonts.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resource/bootstrap/assets/css/ace.css" class="ace-main-stylesheet" id="main-ace-style"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resource/bootstrap/assets/css/ace-part2.css" class="ace-main-stylesheet"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resource/bootstrap/assets/css/ace-ie.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resource/bootstrap/assets/css/jquery.gritter.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resource/css/common.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resource/css/jQueryValidateForForm.css"/>

    <script type="text/javascript" src='${pageContext.request.contextPath}/resource/bootstrap/assets/js/jquery.js'></script>
    <script type="text/javascript" src='${pageContext.request.contextPath}/resource/jQuery.validate/jquery.validate.js'></script>
    <script type="text/javascript" src='${pageContext.request.contextPath}/resource/dataTables/js/jquery.dataTables.js'></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resource/bootstrap/assets/js/bootstrap.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resource/dataTables/js/dataTables.bootstrap.js"></script>
    <script src="${pageContext.request.contextPath}/resource/bootstrap/assets/js/chosen.jquery.js"></script>

    <script src="${pageContext.request.contextPath}/resource/bootstrap/assets/js/ace/ace.js"></script>
    <script src="${pageContext.request.contextPath}/resource/bootstrap/assets/js/jquery.gritter.js"></script>

    <script type="text/javascript" src='${pageContext.request.contextPath}/resource/js/ajaxSubmit.js'></script>
    <script type="text/javascript" src='${pageContext.request.contextPath}/resource/js/formToValidate.js'></script>
    <script type="text/javascript" src='${pageContext.request.contextPath}/resource/js/common.js'></script>
    <script type="text/javascript" src='${pageContext.request.contextPath}/resource/js/dataFormat.js'></script>
    <script type="text/javascript" src='${pageContext.request.contextPath}/views/jsp/systemManage/userManage/inc/userIndex.js'></script>
    <script src="${pageContext.request.contextPath}/resource/laydate/laydate.js"></script>
    <style type="text/css">
        .laydate_box, .laydate_box * {
            box-sizing:content-box;
        }

        input.laydate-icon,div.laydate-icon{
            display: block;
            width: 100%;
            height: 34px;
            padding: 6px 12px;
            font-size: 14px;
            line-height: 1.42857143;
            color: #555;
            border: 1px solid #ccc;
            /*border-radius: 4px;*/
            -webkit-box-shadow: inset 0 1px 1px rgba(0,0,0,.075);
            box-shadow: inset 0 1px 1px rgba(0,0,0,.075);
            -webkit-transition: border-color ease-in-out .15s,-webkit-box-shadow ease-in-out .15s;
            -o-transition: border-color ease-in-out .15s,box-shadow ease-in-out .15s;
            transition: border-color ease-in-out .15s,box-shadow ease-in-out .15s;
        }
        div.laydate-icon{
            text-align: left;
        }

    </style>
</head>
<body>
<input type="hidden" name="contextPath" value="${pageContext.request.contextPath}">

<div class="main-content-inner">
    <%--初始化显示的页面--%>
    <div class="initArea">
        <%--当前页面头部信息 begin--%>
        <div class="breadcrumbs" id="breadcrumbs">
            <ul class="breadcrumb">
                <li>
                    <i class="ace-icon fa fa-home home-icon"></i>
                    <a href="#">home</a>
                </li>
            </ul>
            <div class="nav-search action-buttons" id="nav-search">
                <a class="btn-sm" data-action="openSearchArea" href="#">
                    <i class="ace-icon fa fa-search bigger-180"></i>
                </a>
                <a class="btn-sm orange" data-action="openAdd" href="#">
                    <i class="ace-icon fa fa-plus bigger-180"></i>
                </a>
                <a class="btn-sm green" data-action="refresh" href="#">
                    <i class="ace-icon fa fa-refresh bigger-180"></i>
                </a>
            </div>
        </div>
        <%--当前页面头部信息 end--%>
        <!-- 主体部分 -->
        <div class="page-content">
            <span class="form-horizontal">
                <%--查询条件--%>
                <div class="row searchArea defaultHide">
                    <div class="col-xs-3">
                        <label class="col-xs-3 control-label no-padding-right">用户名: </label>

                        <div class="col-xs-9">
                            <input type="text" placeholder="名称" class="col-xs-12" name="userName">
                        </div>
                    </div>
                    <div class="col-xs-3">
                        <label class="col-xs-3 control-label no-padding-right">账号: </label>

                        <div class="col-xs-9">
                            <input type="text" placeholder="账号" class="col-xs-12" name="account">
                        </div>
                    </div>
                    <div class="col-xs-3">
                        <label class="col-xs-3 control-label no-padding-right">状态: </label>

                        <div class="col-xs-9">
                            <div class="col-sm-12" style="display: none"></div>
                            <select class="chosen-select form-control" name="status" data-placeholder="Choose a State..." placeholder="状态">
                                <option value="">-请选择-</option>
                                <option value="1">启用</option>
                                <option value="2">停用</option>
                            </select>
                        </div>
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
            <%--列表--%>
            <table id="userTable" class="table table-striped table-bordered table-hover" style="width: 100%;">
                <thead>
                <tr>
                    <th>ID</th>
                    <th>用户名</th>
                    <th>性别</th>
                    <th>手机号</th>
                    <th>账号</th>
                    <th>账号状态</th>
                    <th>入职时间</th>
                    <th>离职时间</th>
                    <th>创建人</th>
                    <th>创建时间</th>
                    <th>操作</th>
                </tr>
                </thead>
                <tbody>
                </tbody>
            </table>
        </div>
    </div>
    <%-- 详情页--%>
    <div class="userDetailArea" style="padding-top:3px;display: none">
        <div class="col-sm-12">
            <div class="tabbable">
                <ul class="nav nav-tabs padding-12" id="myTab4">
                    <li class="active">
                        <a data-toggle="tab" href="#tab1" aria-expanded="true">基本信息</a>
                    </li>

                    <li class="">
                        <a data-toggle="tab" href="#tab2" aria-expanded="false">本月考勤</a>
                    </li>

                    <li class="">
                        <a data-toggle="tab" href="#tab3" aria-expanded="false">拥有假期</a>
                    </li>

                    <li class="">
                        <a data-toggle="tab" href="#tab4" aria-expanded="false">现职流水</a>
                    </li>

                    <li class="">
                        <a data-toggle="tab" href="#tab5" aria-expanded="false">公司财物</a>
                    </li>

                    <li class="">
                        <a data-toggle="tab" href="#tab6" aria-expanded="false">
                            <i class="blue ace-icon fa fa-key bigger-125"></i>
                            设置密码
                        </a>
                    </li>

                    <li class="">
                        <a data-toggle="tab" href="#tab7" aria-expanded="false">领导评语</a>
                    </li>

                    <li class="">
                        <a data-toggle="tab" href="#tab8" aria-expanded="false">敬请期待..</a>
                    </li>
                </ul>

                <div class="tab-content">
                    <%--第一个tab--%>
                    <div id="tab1" class="tab-pane active">
                        <%--内容开始--%>
                            <div class="row">
                                <div class="col-xs-12 col-sm-3 center">
                                    <div>
                                        <span class="profile-picture">
                                            <img id="avatar" class="editable img-responsive editable-click editable-empty" alt="Alex's Avatar" src="../upload/profile-pic.jpg"></img>
                                        </span>
                                        <div class="space-4"></div>
                                        <div class="width-80 label label-info label-xlg arrowed-in arrowed-in-right">
                                            <div class="inline position-relative">
                                                <a href="#" class="user-title-label dropdown-toggle" data-toggle="dropdown">
                                                    <i class="ace-icon fa fa-circle light-green"></i>
                                                    &nbsp;
                                                    <span class="white">Alex M. Doe</span>
                                                </a>
                                            </div>
                                        </div>
                                    </div>
                                    <%-- 小 间隔线 --%>
                                    <div class="space-6"></div>
                                    <%-- 大 间隔线 --%>
                                    <div class="hr hr16 dotted"></div>
                                </div>
                                <div class="col-xs-12 col-sm-3">
                                    <h4 class="blue"></h4>

                                    <div class="profile-user-info">
                                        <div class="profile-info-row">
                                            <div class="profile-info-name">姓名</div>

                                            <div class="profile-info-value">
                                                <span>范国顺</span>
                                            </div>
                                        </div>

                                        <div class="profile-info-row">
                                            <div class="profile-info-name">性别</div>

                                            <div class="profile-info-value">
                                                <i class="fa fa-map-marker light-orange bigger-110"></i>
                                                <span>男</span>
                                            </div>
                                        </div>

                                        <div class="profile-info-row">
                                            <div class="profile-info-name">年龄</div>

                                            <div class="profile-info-value">
                                                <span>38</span>
                                            </div>
                                        </div>

                                        <div class="profile-info-row">
                                            <div class="profile-info-name">入职时间</div>

                                            <div class="profile-info-value">
                                                <span>2010/06/20</span>
                                            </div>
                                        </div>

                                        <div class="profile-info-row">
                                            <div class="profile-info-name">工作年限</div>

                                            <div class="profile-info-value">
                                                <span>3 hours ago</span>
                                            </div>
                                        </div>

                                        <div class="profile-info-row">
                                            <div class="profile-info-name">账号</div>

                                            <div class="profile-info-value">
                                                <span>3 hours ago</span>
                                            </div>
                                        </div>

                                        <div class="profile-info-row">
                                            <div class="profile-info-name">
                                                手机号码
                                            </div>

                                            <div class="profile-info-value">
                                                <a href="#">Find me on Facebook</a>
                                            </div>
                                        </div>

                                        <div class="profile-info-row">
                                            <div class="profile-info-name">
                                                现居地
                                            </div>

                                            <div class="profile-info-value">
                                                <a href="#">Follow me on Twitter</a>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-xs-12 col-sm-6">
                                    <h4 class="blue"></h4>

                                    <div class="profile-user-info">
                                        <div class="profile-info-row">
                                            <div class="profile-info-name"> Username </div>

                                            <div class="profile-info-value">
                                                <span>alexdoe</span>
                                            </div>
                                        </div>

                                        <div class="profile-info-row">
                                            <div class="profile-info-name"> Location </div>

                                            <div class="profile-info-value">
                                                <i class="fa fa-map-marker light-orange bigger-110"></i>
                                                <span>Netherlands</span>
                                                <span>Amsterdam</span>
                                            </div>
                                        </div>

                                        <div class="profile-info-row">
                                            <div class="profile-info-name"> Age </div>

                                            <div class="profile-info-value">
                                                <span>38</span>
                                            </div>
                                        </div>

                                        <div class="profile-info-row">
                                            <div class="profile-info-name"> Joined </div>

                                            <div class="profile-info-value">
                                                <span>2010/06/20</span>
                                            </div>
                                        </div>

                                        <div class="profile-info-row">
                                            <div class="profile-info-name"> Last Online </div>

                                            <div class="profile-info-value">
                                                <span>3 hours ago</span>
                                            </div>
                                        </div>
                                    </div>

                                    <div class="hr hr-8 dotted"></div>

                                    <div class="profile-user-info">
                                        <div class="profile-info-row">
                                            <div class="profile-info-name"> Website </div>

                                            <div class="profile-info-value">
                                                <a href="#" target="_blank">www.alexdoe.com</a>
                                            </div>
                                        </div>

                                        <div class="profile-info-row">
                                            <div class="profile-info-name">
                                                <i class="middle ace-icon fa fa-facebook-square bigger-150 blue"></i>
                                            </div>

                                            <div class="profile-info-value">
                                                <a href="#">Find me on Facebook</a>
                                            </div>
                                        </div>

                                        <div class="profile-info-row">
                                            <div class="profile-info-name">
                                                <i class="middle ace-icon fa fa-twitter-square bigger-150 light-blue"></i>
                                            </div>

                                            <div class="profile-info-value">
                                                <a href="#">Follow me on Twitter</a>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        <%--内容结束--%>
                    </div>
                    <%--第二个tab--%>
                    <div id="tab2" class="tab-pane">
                        <form class="form-horizontal" role="form">
                            <div class="row">
                                <div class="col-xs-12 col-sm-12 center">
                                    <div class="form-group">
                                        <label class="col-sm-3 control-label no-padding-right" for="form-field-1"> Text Field </label>

                                        <div class="col-sm-9">
                                            <input type="text" id="form-field-1" placeholder="Username" class="col-xs-10 col-sm-5">
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <label class="col-sm-3 control-label no-padding-right" for="form-field-1"> Text Field </label>

                                        <div class="col-sm-9">
                                            <input type="text" id="form-field-2" placeholder="Username" class="col-xs-10 col-sm-5">
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </form>
                    </div>
                    <%--第三个tab--%>
                    <div id="tab3" class="tab-pane">
                        <p>Etsy mixtape wayfarers, ethical wes anderson tofu before they sold out mcsweeney's organic lomo retro fanny pack lo-fi farm-to-table readymade.</p>
                    </div>
                    <%--第四个tab--%>
                    <div id="tab4" class="tab-pane">
                            <p>搞个折线图</p>
                    </div>
                    <%--第五个tab--%>
                    <div id="tab5" class="tab-pane">
                        <p>搞个结构树</p>
                    </div>
                    <%--第六个tab--%>
                    <div id="tab6" class="tab-pane">
                        <p>搞个列表</p>
                    </div>
                    <%--第七个tab--%>
                    <div id="tab7" class="tab-pane">
                        <p>搞个列表</p>
                    </div>
                    <%--第八个tab--%>
                    <div id="tab8" class="tab-pane">
                            <p>搞个日历</p>
                        </div>
                </div>
            </div>
        </div>
    </div>

    <%--账号编辑 页--%>
    <div class="editUserArea" style="padding-top:3px;display: none">
        <div class="col-sm-12">
            <div class="row">
                <div class="col-xs-12">
                    <span class="form-horizontal">
                        <form id="userForm">
                            <%-- 用户id--%>
                            <input type="hidden" name="userId"/>
                            <div class="form-group">
                                <label class="col-sm-2 control-label no-padding-right">姓名: </label>
                                <div class="col-sm-3">
                                    <input type="text" name="userName" placeholder="姓名" class="col-sm-12" dataMaxLength="10"/>
                                </div>

                                <label class="col-sm-1 control-label no-padding-right">性别: </label>
                                <div class="col-sm-3">
                                    <div class="col-sm-12" style="display: none"></div>
                                    <select class="chosen-select form-control" name="sex" data-placeholder="Choose a State..." placeholder="性别">
                                        <option value="1">男</option>
                                        <option value="0">女</option>
                                    </select>
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-sm-2 control-label no-padding-right">手机号码: </label>
                                <div class="col-sm-3">
                                    <input type="text" name="telphone" placeholder="手机号码" class="col-sm-12" dataMaxLength="11" />
                                </div>

                                <label class="col-sm-1 control-label no-padding-right">身份证号: </label>
                                <div class="col-sm-3">
                                    <input type="text" name="idCard" placeholder="身份证号" class="col-sm-12" dataMaxLength="18" />
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-sm-2 control-label no-padding-right">账号: </label>
                                <div class="col-sm-3">
                                    <input type="text" name="account" placeholder="账号" class="col-sm-12" dataMaxLength="20" dataExist="true"/>
                                </div>

                                <label class="col-sm-1 control-label no-padding-right">入职时间: </label>
                                <div class="col-sm-3">
                                    <input placeholder="入职时间" class="laydate-icon" id="entryTime" name="entryTime" onclick="laydate({istime: true, format: 'YYYY-MM-DD hh:mm:ss'})" dataMaxLength="19" />
                                </div>

                            </div>

                            <div class="form-group">
                                <label class="col-sm-2 control-label no-padding-right">学历: </label>
                                <div class="col-sm-3">
                                    <input type="text" name="education" placeholder="学历" class="col-sm-12" dataMaxLength="20"/>
                                </div>

                                <label class="col-sm-1 control-label no-padding-right">毕业学校: </label>
                                <div class="col-sm-3">
                                    <input type="text" name="graShool" placeholder="毕业学校" class="col-sm-12" dataMaxLength="20"/>
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-sm-2 control-label no-padding-right">月薪: </label>
                                <div class="col-sm-3">
                                    <input type="text" name="wage" placeholder="月薪" class="col-sm-12" dataMaxLength="5"/>
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-sm-2 control-label no-padding-right">住址: </label>
                                <div class="col-sm-7">
                                    <textarea class="col-sm-12" name="address" style="margin: 0px; height: 71px;resize: none;" placeholder="住址" dataMaxLength="200"></textarea>
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-sm-2 control-label no-padding-right">备注: </label>
                                <div class="col-sm-7">
                                    <textarea class="col-sm-12" name="remark" style="margin: 0px; height: 71px;resize: none;" placeholder="备注" dataMaxLength="200"></textarea>
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
                                    <a class="btn btn-primary btn-round" data-action="userCancel">
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

    <%--角色编辑 页--%>
    <div class="editRole" style="padding-top:3px;display: none">
        <input type="hidden" name="roleUserId" id="roleUserId"/>
        <div class="col-sm-12">
            <div class="col-sm-3"></div>
            <div class="col-sm-6">
                <div class="bootstrap-duallistbox-container row moveonselect">
                    <div class="box1 col-md-6">
                        <div class="info-container center">
                            <span class="info">未有角色</span>
                        </div>
                        <div class="buttons">
                            <a href="#" data-action="allPotionInExistRole" class="btn btn-white btn-bold btn-info btn-block" data-toggle="button">
                                <i class="glyphicon glyphicon-arrow-right"></i>
                                <i class="glyphicon glyphicon-arrow-right"></i>
                            </a>
                        </div>
                        <select multiple="multiple" id="noExistRole" class="form-control" name="noExistRole" style="height: 270px;">

                        </select>
                    </div>
                    <div class="box2 col-md-6">
                         <div class="info-container center">
                            <span class="info">已有角色</span>
                        </div>
                        <div class="buttons">
                            <a href="#" data-action="allPotionInNoExistRole" class="btn btn-white btn-bold btn-info btn-block" data-toggle="button">
                                <i class="glyphicon glyphicon-arrow-left"></i>
                                <i class="glyphicon glyphicon-arrow-left"></i>
                            </a>
                        </div>
                        <select multiple="multiple" id="existRole" class="form-control" name="existRole" style="height: 270px;">
                        </select>
                    </div>
                </div>
                <div class="hr hr-16 hr-dotted"></div>
            </div>
            <div class="col-sm-3"></div>
        </div>
        <div class="col-sm-12">
            <div class="col-sm-4"></div>
            <div class="col-sm-4">
                <div class="col-sm-6 center">
                    <button type="button" class="btn btn-primary btn-round" data-action="roleSave">
                        <i class="ace-icon fa fa-save align-top bigger-125"></i>保存
                    </button>
                </div>
                <div class="col-sm-6 center">
                    <button type="button" class="btn btn-primary btn-round" data-action="roleCancel">
                        <i class="ace-icon fa fa-save align-top bigger-125"></i>取消
                    </button>
                </div>


            </div>
            <div class="col-sm-4"></div>
        </div>
    </div>
</div>
</body>
</html>
