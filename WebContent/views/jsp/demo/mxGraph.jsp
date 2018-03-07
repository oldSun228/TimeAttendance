<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=UTF-8" />
<title>上海市交通委综合交通（规划）业务平台</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta content="width=device-width, initial-scale=1" name="viewport" />
	<meta content="" name="description" />
	<meta content="" name="author" />
	<script type="text/javascript">
		mxBasePath = '${pageContext.request.contextPath}/resource/mxGraph/src';
	</script>
	<script type="text/javascript" src='${pageContext.request.contextPath}/resource/bootstrap/assets/js/jquery.js'></script>
	<script src="${pageContext.request.contextPath}/resource/mxGraph/src/js/mxClient.js" type="text/javascript"></script>
</head>
<body class="page-container-bg-solid page-content-white" style="background: white;">

</body>
<script type="text/javascript">
	var container;
	var model;
	var graph;
	$(function(){
		// 检查浏览器支持
		if (!mxClient.isBrowserSupported()) {
			mxUtils.error('Browser is not supported!', 200, false);
		} else {
			//定义创建新连接的图标。这将自动禁用高亮显示的元素。
			mxConnectionHandler.prototype.connectImage = new mxImage("${pageContext.request.contextPath}/resource/mxGraph/examples/images/connector.gif",16, 16);

			// 创建带工具栏的容器
			var tbContainer = document.createElement('div');
			tbContainer.style.position = 'absolute';
			tbContainer.style.overflow = 'hidden';
			tbContainer.style.padding = '2px';
			tbContainer.style.left = '0px';
			tbContainer.style.top = '0px';
			tbContainer.style.width = '24px';
			tbContainer.style.bottom = '0px';
			document.body.appendChild(tbContainer);

			// 创建一个没有事件的工具栏
			var toolbar = new mxToolbar(tbContainer);
			toolbar.enabled = false;

			// 创建图形编辑器的容器
			var container = document.createElement('div');
			container.style.position = 'absolute';
			container.style.overflow = 'auto';
			container.style.left = '24px';
			container.style.top = '0px';
			container.style.right = '0px';
			container.style.bottom = '0px';
			container.style.background = 'url("${pageContext.request.contextPath}/resource/mxGraph/examples/editors/images/grid.gif")';
			document.body.appendChild(container);

			// 创建的模型和容器内的图形图，在浏览器上使用可用的最快渲染
			model = new mxGraphModel();
			graph = new mxGraph(container, model);


			// 开启可以拖拽建立关系
			graph.setConnectable(true);
			// 开启方块上的文字编辑功能
			graph.setCellsEditable(true);
			// 启用对齐线帮助定位
			mxGraphHandler.prototype.guidesEnabled = true;


			var addVertex = function(icon, w, h, style) {
				var vertex = new mxCell(null, new mxGeometry(0, 0, w, h), style);
				vertex.setVertex(true);

				var img = addToolbarItem(graph, toolbar, vertex, icon);
				img.enabled = true;

				graph.getSelectionModel().addListener(mxEvent.CHANGE,function() {
					var tmp = graph.isSelectionEmpty();
					mxUtils.setOpacity(img, (tmp) ? 100 : 20);
					img.enabled = tmp;
				});
			};

			addVertex('${pageContext.request.contextPath}/resource/mxGraph/examples/editors/images/rectangle.gif',100, 40, '');
			addVertex('${pageContext.request.contextPath}/resource/mxGraph/examples/editors/images/rounded.gif',100, 40, 'shape=rounded');
			addVertex('${pageContext.request.contextPath}/resource/mxGraph/examples/editors/images/ellipse.gif', 40, 40, 'shape=ellipse');
			addVertex('${pageContext.request.contextPath}/resource/mxGraph/examples/editors/images/rhombus.gif', 40, 40, 'shape=rhombus');
			addVertex('${pageContext.request.contextPath}/resource/mxGraph/examples/editors/images/triangle.gif', 40, 40, 'shape=triangle');
			addVertex('${pageContext.request.contextPath}/resource/mxGraph/examples/editors/images/cylinder.gif', 40, 40, 'shape=cylinder');
			addVertex('${pageContext.request.contextPath}/resource/mxGraph/examples/editors/images/actor.gif', 30, 40, 'shape=actor');

			loadTC();
		}
	});

	function loadTC(){
		//自动调整大小的容器
		graph.border = 100;
		graph.getView().translate = new mxPoint(graph.border / 2,graph.border / 2);
		//设置只适应大小
		graph.setResizeContainer(false);
		//是否可以跨域 默认可以
		graph.graphHandler.setRemoveCellsFromParent(true);


		// 改变默认样式
		var style = graph.getStylesheet().getDefaultVertexStyle();
		style[mxConstants.STYLE_SHAPE] = mxConstants.SHAPE_SWIMLANE;//形状
		style[mxConstants.STYLE_VERTICAL_ALIGN] = 'middle';
		style[mxConstants.STYLE_LABEL_BACKGROUNDCOLOR] = 'white';
		style[mxConstants.STYLE_FONTSIZE] = 11;//字体大小
		style[mxConstants.STYLE_STARTSIZE] = 33;
		style[mxConstants.STYLE_HORIZONTAL] = false;//横向
		style[mxConstants.STYLE_FONTCOLOR] = 'black';//字体颜色
		style[mxConstants.STYLE_STROKECOLOR] = 'black';//行程颜色
		delete style[mxConstants.STYLE_FILLCOLOR];//选框颜色（默认蓝色，删除则为白色）


		style = mxUtils.clone(style);
		style[mxConstants.STYLE_SHAPE] = mxConstants.SHAPE_RECTANGLE;//长方形
		style[mxConstants.STYLE_FONTSIZE] = 10;
		style[mxConstants.STYLE_ROUNDED] = true;
		style[mxConstants.STYLE_HORIZONTAL] = true;
		style[mxConstants.STYLE_VERTICAL_ALIGN] = 'middle';
		delete style[mxConstants.STYLE_STARTSIZE];
		style[mxConstants.STYLE_LABEL_BACKGROUNDCOLOR] = 'none';

		graph.getStylesheet().putCellStyle('process', style);



//		//开始
//		debugger;
//		style = mxUtils.clone(style);
//		style[mxConstants.STYLE_SHAPE] = mxConstants.SHAPE_ELLIPSE;//椭圆形
//		style[mxConstants.STYLE_PERIMETER] = mxPerimeter.EllipsePerimeter;//周长
//		delete style[mxConstants.STYLE_ROUNDED];
//		graph.getStylesheet().putCellStyle('state', style);
//
//		//菱形
//		style = mxUtils.clone(style);
//		style[mxConstants.STYLE_SHAPE] = mxConstants.SHAPE_RHOMBUS;//菱形
//		style[mxConstants.STYLE_PERIMETER] = mxPerimeter.RhombusPerimeter;
//		style[mxConstants.STYLE_VERTICAL_ALIGN] = 'top';
//		style[mxConstants.STYLE_SPACING_TOP] = 40;
//		style[mxConstants.STYLE_SPACING_RIGHT] = 64;
//		graph.getStylesheet().putCellStyle('condition', style);
//
//		//结束
//		style = mxUtils.clone(style);
//		style[mxConstants.STYLE_SHAPE] = mxConstants.SHAPE_DOUBLE_ELLIPSE;
//		style[mxConstants.STYLE_PERIMETER] = mxPerimeter.EllipsePerimeter;
//		style[mxConstants.STYLE_SPACING_TOP] = 28;
//		style[mxConstants.STYLE_FONTSIZE] = 14;
//		style[mxConstants.STYLE_FONTSTYLE] = 1;
//		delete style[mxConstants.STYLE_SPACING_RIGHT];
//		graph.getStylesheet().putCellStyle('end', style);

		//连接线的颜色
		style = graph.getStylesheet().getDefaultEdgeStyle();
		style[mxConstants.STYLE_EDGE] = mxEdgeStyle.ElbowConnector;
		style[mxConstants.STYLE_ENDARROW] = mxConstants.ARROW_BLOCK;
		style[mxConstants.STYLE_ROUNDED] = true;
		style[mxConstants.STYLE_FONTCOLOR] = 'black';
		style[mxConstants.STYLE_STROKECOLOR] = 'black';

		style = mxUtils.clone(style);
		style[mxConstants.STYLE_DASHED] = true;
		style[mxConstants.STYLE_ENDARROW] = mxConstants.ARROW_OPEN;
		style[mxConstants.STYLE_STARTARROW] = mxConstants.ARROW_OVAL;
		graph.getStylesheet().putCellStyle('crossover', style);




		// 创建默认窗体
		var parent = graph.getDefaultParent();

		// 开启更新事务
		model.beginUpdate();
		try {


		} finally {
			// 结束更新事务
			model.endUpdate();
		}
	}

	function addToolbarItem(graph, toolbar, prototype, image) {
		// 当工具栏图标被拖动后执行此方法。
		// 如果鼠标点击连接图标并移动到另外一个元素时，就建立两个图标的连接
		var funct = function(graph, evt, cell, x, y) {
			graph.stopEditing(false);
			var vertex = graph.getModel().cloneCell(prototype);
			vertex.geometry.x = x;
			vertex.geometry.y = y;
			graph.addCell(vertex);
			graph.setSelectionCell(vertex);
		}
		// 创建拖动时的预览图标
		var img = toolbar.addMode(null, image, function(evt, cell) {
			var pt = this.graph.getPointForEvent(evt);
			funct(graph, evt, cell, pt.x, pt.y);
		});
		// 总是在所有的浏览器中的任何其他侦听器之前，首先调用此侦听器。
		mxEvent.addListener(img, 'mousedown', function(evt) {
			if (img.enabled == false) {
				mxEvent.consume(evt);
			}
		});
		// 使图标可以拖动
		mxUtils.makeDraggable(img, graph, funct);
		return img;
	}

</script>
</html>

