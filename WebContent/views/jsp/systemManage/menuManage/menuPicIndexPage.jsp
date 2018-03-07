<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <meta charset="utf-8"/>
    <title>菜单图标管理</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resource/iconpicker/bootstrap-3.2.0/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resource/iconpicker/bootstrap-3.2.0/css/pygments-manni.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resource/iconpicker/icon-fonts/elusive-icons-2.0.0/css/elusive-icons.min.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resource/iconpicker/icon-fonts/font-awesome-4.2.0/css/font-awesome.min.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resource/iconpicker/icon-fonts/ionicons-1.5.2/css/ionicons.min.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resource/iconpicker/icon-fonts/map-icons-2.1.0/css/map-icons.min.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resource/iconpicker/icon-fonts/material-design-1.1.1/css/material-design-iconic-font.min.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resource/iconpicker/icon-fonts/octicons-2.1.2/css/octicons.min.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resource/iconpicker/icon-fonts/typicons-2.0.6/css/typicons.min.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resource/iconpicker/icon-fonts/weather-icons-1.2.0/css/weather-icons.min.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resource/iconpicker/bootstrap-iconpicker/css/bootstrap-iconpicker.min.css"/>
    <style type="text/css">

    </style>
</head>
<body>
    <input type="hidden" name="contextPath" value="${pageContext.request.contextPath}">
    <input type="hidden" id="result" name="result"/>
    <div class="col-sm-12">
        <form class="form-horizontal" role="form">
            <div class="form-group">
                <div class="col-md-12">
                    <div id="button" class="" data-position="left" style="height: 50px; padding-top: 10px;">
                        <a href="#" class="btn btn-white"><i class="icon-only ace-icon fa fa-align-justify"></i></a>
                    </div>
                </div>
            </div>
            <div class="form-group">
                <div class="col-sm-12">
                    <div id="btn-colors" class="btn-group">
                        <button type="button" class="btn btn-default">black</button>
                        <input  type="radio" name="color" style="display: none" value="black"/>
                        <button type="button" class="btn btn-info">blue</button>
                        <input  type="radio" name="color" style="display: none" value="blue"/>
                        <button type="button" class="btn btn-success">green</button>
                        <input  type="radio" name="color" style="display: none" value="green"/>
                        <button type="button" class="btn btn-warning">orange</button>
                        <input  type="radio" name="color" style="display: none" value="orange"/>
                        <button type="button" class="btn btn-danger" >red</button>
                        <input  type="radio" name="color" style="display: none" value="red"/>
                    </div>
                </div>
            </div>
            <div class="form-group">
                <div class="col-sm-12">
                    <div id="btn-icon" class="btn btn-white" data-iconset="fontawesome" data-icon="fa-area-chart" ></div>
                </div>
            </div>
        </form>
    </div>

<%--滚动条 js--%>
<script type="text/javascript" src="${pageContext.request.contextPath}/resource/iconpicker/bootstrap-3.2.0/js/bootstrap.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resource/iconpicker/bootstrap-iconpicker/js/iconset/iconset-all.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resource/iconpicker/bootstrap-iconpicker/js/bootstrap-iconpicker.js"></script>
<!-- Button Builder Example -->
<script type="text/javascript">
    $(function(){
        $('[name="color"]:first').attr("checked", true);
        var show_result = function(){
            $('#result').val($('#button ').find('a').html().trim());
        };

        $('#btn-icon').iconpicker({
            rows: 5,
            cols: 10,
            align: 'left'
        });

        $('#btn-colors button').on('click', function(e) {
            $(this).next('[name="color"]').attr("checked", true);
            $('#button i').removeClass('gray blue green orange red').addClass($(this).next('[name="color"]').val());
            show_result();
        });

        $('#btn-icon').on('change', function(e) {
            $('#button a > i').attr('class', '').addClass('menu-icon fa ' + e.icon+' '+$('[name="color"]:checked').val());
            show_result();
        });
    });

</script>
</body>
</html>
