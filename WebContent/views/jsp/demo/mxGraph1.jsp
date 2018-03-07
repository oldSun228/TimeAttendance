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
	$(function() {
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

			// 在图中，启用新的连接  
			graph.setConnectable(true);
			graph.setMultigraph(false);

			// 拖入控件或释放按键时工具栏停止编辑
			var keyHandler = new mxKeyHandler(graph);
			var rubberband = new mxRubberband(graph);

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
		}

		loadTC();
	});

	function loadTC() {
		//自动调整大小的容器  
		graph.border = 80;
		graph.getView().translate = new mxPoint(graph.border / 2,graph.border / 2);
		graph.setResizeContainer(true);
		graph.graphHandler.setRemoveCellsFromParent(false);

		// 改变默认样式  
		var style = graph.getStylesheet().getDefaultVertexStyle();
		style[mxConstants.STYLE_SHAPE] = mxConstants.SHAPE_SWIMLANE;//形状
		style[mxConstants.STYLE_VERTICAL_ALIGN] = 'middle';
		style[mxConstants.STYLE_LABEL_BACKGROUNDCOLOR] = 'white';
		style[mxConstants.STYLE_FONTSIZE] = 11;//字体大小
		style[mxConstants.STYLE_STARTSIZE] = 33;
		style[mxConstants.STYLE_HORIZONTAL] = false;//水平
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

		//开始
		debugger;
		style = mxUtils.clone(style);
		style[mxConstants.STYLE_SHAPE] = mxConstants.SHAPE_ELLIPSE;//椭圆形
		style[mxConstants.STYLE_PERIMETER] = mxPerimeter.EllipsePerimeter;//周长
//		delete style[mxConstants.STYLE_ROUNDED];
		graph.getStylesheet().putCellStyle('state', style);

		//菱形
		style = mxUtils.clone(style);
		style[mxConstants.STYLE_SHAPE] = mxConstants.SHAPE_RHOMBUS;//菱形
		style[mxConstants.STYLE_PERIMETER] = mxPerimeter.RhombusPerimeter;
		style[mxConstants.STYLE_VERTICAL_ALIGN] = 'top';
		style[mxConstants.STYLE_SPACING_TOP] = 40;
		style[mxConstants.STYLE_SPACING_RIGHT] = 64;
		graph.getStylesheet().putCellStyle('condition', style);

		//结束
		style = mxUtils.clone(style);
		style[mxConstants.STYLE_SHAPE] = mxConstants.SHAPE_DOUBLE_ELLIPSE;
		style[mxConstants.STYLE_PERIMETER] = mxPerimeter.EllipsePerimeter;
		style[mxConstants.STYLE_SPACING_TOP] = 28;
		style[mxConstants.STYLE_FONTSIZE] = 14;
		style[mxConstants.STYLE_FONTSTYLE] = 1;
		delete style[mxConstants.STYLE_SPACING_RIGHT];
		graph.getStylesheet().putCellStyle('end', style);

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

		// 双击时改变边框样式  
		graph.alternateEdgeStyle = 'elbow=vertical';

		// 启用了图形添加了自动布局和各种开关  
		if (graph.isEnabled()) {
			// Allows new connections but no dangling edges  
			graph.setConnectable(true);
			graph.setAllowDanglingEdges(false);

			// End-states are no valid sources  
			var previousIsValidSource = graph.isValidSource;

			graph.isValidSource = function(cell) {
				if (previousIsValidSource.apply(this, arguments)) {
					var style = this.getModel().getStyle(cell);

					return style == null || !(style == 'end' || style.indexOf('end') == 0);
				}

				return false;
			};

			// 判断状态有无合法目标，不执行调用父类的功能，因为这会调用isValidSource  
			graph.isValidTarget = function(cell) {
				var style = this.getModel().getStyle(cell);

				return !this.getModel().isEdge(cell) && !this.isSwimlane(cell) && (style == null || !(style == 'state' || style
								.indexOf('state') == 0));
			};

			// 可以将新元素转换为新泳道，新泳道为新泳道池  
			graph.setDropEnabled(true);
			graph.setSplitEnabled(false);

			// 有效的拖放操作，则返回true  
			graph.isValidDropTarget = function(target, cells, evt) {
				if (this.isSplitEnabled()
						&& this.isSplitTarget(target, cells, evt)) {
					return true;
				}

				var model = this.getModel();
				var lane = false;
				var pool = false;
				var cell = false;

				// 检查元素是否被选择  
				for ( var i = 0; i < cells.length; i++) {
					var tmp = model.getParent(cells[i]);
					lanelane = lane || this.isPool(tmp);
					poolpool = pool || this.isPool(cells[i]);

					cellcell = cell || !(lane || pool);
				}

				return !pool && cell != lane && ((lane && this.isPool(target)) || (cell && this.isPool(model.getParent(target))));
			};

			// 添加新的方法识别泳道池  
			graph.isPool = function(cell) {
				var model = this.getModel();
				var parent = model.getParent(cell);

				return parent != null && model.getParent(parent) == model.getRoot();
			};

			//删除元素时清楚泳道  
			graph.model.getStyle = function(cell) {
				var style = mxGraphModel.prototype.getStyle.apply(this,
						arguments);

				if (graph.isCellCollapsed(cell)) {
					if (style != null) {
						style += ';';
					} else {
						style = '';
					}

					style += 'horizontal=1;align=left;spacingLeft=14;';
				}

				return style;
			};

			// 收缩窗体时保持宽度  
			var foldingHandler = function(sender, evt) {
				var cells = evt.getProperty('cells');

				for ( var i = 0; i < cells.length; i++) {
					var geo = graph.model.getGeometry(cells[i]);

					if (geo.alternateBounds != null) {
						geogeo.width = geo.alternateBounds.width;
					}
				}
			};

			graph.addListener(mxEvent.FOLD_CELLS, foldingHandler);
		}

		// 父元素和同级元素间跳转大小  
		new mxSwimlaneManager(graph);

		// 创建一个堆栈布局  
		var layout = new mxStackLayout(graph, false);

		// 确保所有的子元素都适应父泳道  
		layout.resizeParent = true;

		// 子元素适用父元素的变化  
		layout.fill = true;

		// 仅仅更新泳道的大小  
		layout.isVertexIgnored = function(vertex) {
			return !graph.isSwimlane(vertex);
		}

		// 保持泳道池与泳道的层叠关系  
		var layoutMgr = new mxLayoutManager(graph);

		layoutMgr.getLayout = function(cell) {
			if (!model.isEdge(cell) && graph.getModel().getChildCount(cell) > 0 && (model.getParent(cell) == model.getRoot() || graph.isPool(cell))) {
				layout.fill = graph.isPool(cell);
				return layout;
			}
			return null;
		};

		// 创建默认窗体  
		var parent = graph.getDefaultParent();

		// 开启更新事务  
		model.beginUpdate();
		try {
			var lane1a = graph.insertVertex(parent, null, '项目储备和计划', 0, 0, 640,200);
			lane1a.setConnectable(false);

			var lane1b = graph.insertVertex(parent, null, '项建书审批阶段', 0, 0, 640,110);
			lane1b.setConnectable(false);

			var lane1c = graph.insertVertex(parent, null, '可研审批阶段', 0, 0, 640,140);
			lane1c.setConnectable(false);

			var lane1d = graph.insertVertex(parent, null, '开工准备阶段', 0, 0, 640,110);
			lane1d.setConnectable(false);


			var step1 = graph.insertVertex(lane1a, null, '综合交通发展规划\n(5年)',90, 30, 80, 50, 'process;strokeColor=red;fillColor=green');
			var step11 = graph.insertVertex(lane1a, null,'Complete\nAppropriate\nRequest', 190, 30, 80, 50,'process');
			var step111 = graph.insertVertex(lane1a, null,'Receive and\nAcknowledge', 385, 30, 80, 50, 'process');


			var step2 = graph.insertVertex(lane1b, null, 'Receive\nRequest',90, 30, 80, 50, 'process');
			var step22 = graph.insertVertex(lane1b, null,'Refer to Tap\nSystems\nCoordinator', 190, 30, 80, 50,'process');

			var step3 = graph.insertVertex(lane1c, null,'Request 1st-\nGate\nInformation', 190, 30, 80, 50,'process');
			var step33 = graph.insertVertex(lane1c, null,'Receive 1st-\nGate\nInformation', 290, 30, 80, 50,'process');

			var step4 = graph.insertVertex(lane1d, null,'Receive and\nAcknowledge', 290, 20, 80, 50, 'process');
			var step44 = graph.insertVertex(lane1d, null,'Contract\nConstraints?', 400, 20, 50, 50, 'process');
			var step444 = graph.insertVertex(lane1d, null,'Tap for gas\ndelivery?', 480, 20, 50, 50, 'process');


			var e = null;
			graph.insertEdge(lane1a, null, null, step1, step11);
			graph.insertEdge(lane1a, null, null, step11, step111);

			graph.insertEdge(lane1b, null, null, step2, step22);

			graph.insertEdge(lane1c, null, null, step3, step33);

			graph.insertEdge(lane1d, null, null, step4, step44);
			graph.insertEdge(lane1d, null, null, step44, step444);

			//crossover  虚线
			graph.insertEdge(parent, null, null, step11, step4,'crossover');

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

