<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
  <meta charset="utf-8"/>
  <title>菜单管理</title>
  <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0"/>
  <script type="text/javascript" src='${pageContext.request.contextPath}/views/jsp/systemManage/menuManage/inc/menuAlert.js'></script>
  <style type="text/css">

      #childTreeDiv{
          margin-left: auto;
          margin-right: auto;
      }
  </style>
</head>
<body>
  <input type="hidden" name="selectedId" value="${selectedId}"/>
  <input type="hidden" name="layerMenuId"/>
  <input type="hidden" name="layerMenuName"/>
  <%--初始化显示的页面--%>
  <div class="col-sm-12">
      <div class="col-sm-2"></div>
      <div class="col-sm-8">
          <div id="childTreeDiv" class="ztree"></div>
      </div>
      <div class="col-sm-2"></div>
  </div>
</body>
</html>
