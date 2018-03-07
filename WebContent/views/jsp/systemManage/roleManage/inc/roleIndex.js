$(function () {
    var contextPath = $('[name="contextPath"]').val();

    //角色分页列表
     var rolePageListTable = $("#roleTable").dataTable({
            "processing": true,
            "serverSide": true,
            "bAutoWidth": true, //是否自适应宽度
            //"bScrollInfinite" : false, //是否启动初始化滚动条
            "bScrollCollapse": true, //是否开启DataTables的高度自适应，当数据条数不够分页数据条数的时候，插件高度是否随数据条数而改变
            "bPaginate": true, //是否显示（应用）分页器
            "bInfo": false, //是否显示页脚信息，DataTables插件左下角显示记录数
            "paginationType": "full_numbers",
            "bLengthChange": false,//选择一夜显示条数
            "aLengthMenu": [20, 40, 60], //更改显示记录数选项
            "bFilter": false,//查询条件
            "bSort": false,//排序
            "iDisplayLength": 10,//一页显示的条数
            "iDisplayStart": 0,//开始
            "columns": [

                {
                    "data": "roleId",
                    "bVisible": false,
                    "bSortable": false
                },
                {
                    "data": "roleName"
                },
                {
                    "data": "status",
                    render: function (data, type, row) {
                        if(data=='1'){
                            return "启用";
                        }else{
                            return "停用";
                        }
                    }
                },
                {
                    "data": "remark",
                    "bVisible": false
                },
                {
                    "data": "createBy"
                },
                {
                    "data": "createTime",
                    render:function(data, type, row){

                        return  new Date(data).Format("yyyy-MM-dd hh:mm:ss");
                    }
                },
                {
                    render: function (data, type, row) {
                        var html = "";
                        html += '<div class="hidden-sm hidden-xs action-buttons">';
                        /*查看详情 按钮*/
                        html += '<a class="blue" href="#" operateType="detail">';
                        html += '<i class="ace-icon fa fa-eye bigger-130"></i>';
                        html += '</a>';
                        /*数据编辑 按钮*/
                        html += '<a class="green" href="#" operateType="update">';
                        html += '<i class="ace-icon fa fa-pencil bigger-130"></i>';
                        html += '</a>';
                        /*删除数据 按钮*/
                        html += '<a class="red" href="#" operateType="delete">';
                        html += '<i class="ace-icon fa fa-trash-o bigger-130"></i>';
                        html += '</a>';
                        /*赋权限 按钮*/
                        html += '<a class="orange" href="#" operateType="power">';
                        html += '<i class="ace-icon fa fa-lock bigger-130"></i>';
                        html += '</a>';
                        html += '</div>';
                        return html;
                    }
                }
            ],
            "ajax": {
                "url": contextPath + "/roleManage/getRolePageList",
                "type": "POST"
            },
            aaSorting: [[2, "desc"]],
            "fnServerParams": function (d) {
                var params = {};
                params.roleName = $('.searchArea').find('[name="roleName"]').val();
                params.status = $('.searchArea').find('[name="status"]').val();
                d = $.extend(d, params);
            },
            "rowCallback": function (row, data) {
                $(row).on('click', function (e) {
                    var event = e.target;
                    //查看详情操作标识
                    var detailFlag = $($(event).parent()).attr('operateType') == 'detail';
                    //修改操作标识
                    var updateFlag = $($(event).parent()).attr('operateType') == 'update';
                    //删除操作标识
                    var deleteFlag = $($(event).parent()).attr('operateType') == 'delete';
                    //权限操作标识
                    var powerFlag = $($(event).parent()).attr('operateType') == 'power';
                    if (detailFlag || updateFlag) {
                        AjaxSubmit.post({
                            url: contextPath + "/roleManage/detailForRole",
                            params: {
                                roleId: data.roleId,//提交参数 角色id
                            },
                            fnCallback: function (result) {
                                if (result.isSuccess) {
                                    var formAttr = $('#RoleForm').find('input,select,textarea');
                                    $.each(formAttr, function (i, o) {
                                        var v = result.result[o.name];
                                        $(o).val(v);
                                        if (detailFlag) {
                                            $('#RoleForm').find('[name="' + o.name + '"]').attr('disabled', true);
                                        } else {
                                            $('#RoleForm').find('[name="' + o.name + '"]').removeAttr('disabled');
                                        }
                                        if (o.tagName == 'SELECT') {
                                            $(o).trigger('chosen:updated');
                                        }
                                    });
                                    $('[data-action="openAdd"]').click();
                                }
                            }
                        });
                        if (detailFlag) {
                            $('.btn-operate').find("a").hide();
                        } else {
                            $('.btn-operate').find("a").show();
                        }
                    } else if (deleteFlag) {
                        layer.confirm('确定删除此信息？', {
                            time: 0,//不自动关闭
                            btn: ['确定', '取消'],
                            yes: function (index) {
                                layer.close(index);
                                AjaxSubmit.post({
                                    url: contextPath + "/roleManage/delForRole",
                                    params: {
                                        roleId: data.roleId,//提交参数 角色id
                                    },
                                    fnCallback: function (result) {
                                        if (result.isSuccess) {
                                            rolePageListTable.fnDraw(false);//false 为不自动跳到第一页
                                            //提示信息
                                            Common_function.promotInfo(result.msg);
                                        }
                                    }
                                });
                            }
                        });
                    }else if(powerFlag){
                        $('.initArea').slideToggle("slow");
                        $('.powerArea').slideToggle("slow");
                        $('[name="roleId"]').val(data.roleId);
                        $('[name="identy"]').val(data.roleIdenty);
                        queryMenuRole(data.roleIdenty);
                    }
                });
            }
        }
    );

    /**
     * 点击 放大镜按钮 切换显示页面事件
     */
    $('[data-action="openSearchArea"]').on('click', function () {
        $('.searchArea').slideToggle("slow");
        //初始化 select 样式
        //Common_function.queryDicListHtml("status","status");
        Common_function.indexSelect();
    });

    /**
     * 点击 查询按钮 事件
     */
    $('[data-action="searchDate"]').on('click', function () {
        rolePageListTable.fnDraw(true);
    });

    /**
     * 点击 重置按钮 事件
     */
    $('[data-action="reset"]').on('click', function () {
        $('.searchArea').find('[name="roleName"]').val("");
        $('.searchArea').find('[name="status"]').val("").trigger('chosen:updated');
    });

    /**
     * 点击 增加按钮 事件  加 表单验证
     */
    $('[data-action="openAdd"]').on('click', function () {
        $('.initArea').slideToggle("slow");
        $('.editArea').slideToggle("slow");
        //初始化下拉框
        Common_function.queryDicListHtml("status","status");
        //开启验证
        var jsonRemote={
            type: "POST",
            url: contextPath + '/roleManage/checkRoleName',             //servlet
            cache: false,
            data: {
                roleId: function () {
                    return $('#RoleForm').find('[name="roleId"]').val();
                },
                roleName: function () {
                    return $('#RoleForm').find('[name="roleName"]').val();
                }
            }
        };
        //获取form表单里的 所有填值空间
        var resultData = Do_Form_Validate.getFormValidateData($('#RoleForm'),jsonRemote);
        $('#RoleForm').validate({
            focusInvalid: true, //当为false时，验证无效时，没有焦点响应
            onkeyup: false,
            ignore: "",
            submitHandler: function (form) {   //表单提交带回调函数
                var btn = $('[data-action="save"]');
                btn.button('loading');
                var resultFormateData = Do_Form_Validate.dataFormate(form);
                AjaxSubmit.ajax({
                    url: contextPath + '/roleManage/saveOrUpdateRole',
                    async: true,
                    params: resultFormateData,
                    fnCallback: function (data) {
                        if (data.isSuccess) {
                            $('[data-action="cancel"]').click();
                            rolePageListTable.fnDraw(false);
                            btn.button('reset');
                            //提示信息
                            Common_function.promotInfo(data.msg);
                        }
                        //清楚 需要romote字段的缓存
                        clearRemote();
                    }
                });
            },
            rules: resultData.rules,
            messages: resultData.messages,
            errorPlacement: function (error, element) {
                if (element.is('input[type=checkbox]') || element.is('input[type=radio]')) {
                    var controls = element.closest('div[class*="col-"]');
                    if (controls.find(':checkbox,:radio').length > 1) controls.append(error);
                    else error.insertAfter(element.nextAll('.lbl:eq(0)').eq(0));
                } else {
                    if (element[0].tagName == 'SELECT') {
                        error.insertAfter(element.next());
                    } else {
                        error.insertAfter(element);
                    }
                }
            }
        });
        //初始化 select 样式
        Common_function.indexSelect();
    });

    /**
     * 清楚 需要romote字段的缓存
     */
    function clearRemote() {
        $('[name="roleName"]').removeData("previousValue");
    }

    /**
     * 点击 取消按钮 事件
     */
    $('[data-action="cancel"]').on('click', function () {
        $('.initArea').slideToggle("slow");
        $('.editArea').slideToggle("slow");
        //清空验证信息
        Common_function.hiddenError();
        //清空表单数据
        Common_function.clearFormData($('#RoleForm'));
        //清楚 需要romote字段的缓存
        clearRemote();
    });

    /**
     * 点击 数据保存按钮 事件
     */
    $('[data-action="save"]').on('click', function () {
        $('#RoleForm').submit();
    });

    /**
     * 页面刷新功能
     */
    $('[data-action="refresh"]').on('click', function () {
        rolePageListTable.fnDraw(true);
    });

    /**
     * 点击 权限编辑页面 取消按钮 事件
     */
    $('[data-action="powerCancel"]').on('click', function () {
        $('.initArea').slideToggle("slow");
        $('.powerArea').slideToggle("slow");
        $('[name="roleId"]').val("");
    });


    /**
     * 获取 菜单 角色  关系
     */
    var zTree;
    function queryMenuRole(obj){
        var setting = {
            check: {
                enable: true,
                chkStyle: "checkbox",
                chkboxType: {"Y": "ps", "N": "ps"}
            },
            //获取json数据
            async: {
                enable: true,
                url: contextPath + "/menuManage/getMenusList", // Ajax 获取数据的 URL 地址
                autoParam: ["id", "name"], //ajax提交的时候，传的是id值
            },
            view: {
                showTitle: false,// 是否显示节点的 title
                selectedMulti: false,//设置是否允许同时选中多个节点。
                nameIsHTML: false,//设置 name 属性是否支持 HTML 脚本
                showIcon: true,
            },
            edit: {
                enable: false, //单独设置为true时，可加载修改、删除图标
                editNameSelectAll: false,
                showRemoveBtn: false,
                showRenameBtn: false
            },
            data: { // 必须使用data
                key: {
                    checked: "checked",
                    children: "subChildren",
                    name: "name",
                    title: "",
                },
                simpleData: {
                    enable: false,//是否采用简单数据模式
                    idKey: "id", // id编号命名
                    pIdKey: "pId", // 父id编号命名
                    rootId: null
                }
            },
            // 回调函数
            callback: {
                //捕获异步加载出现异常错误的事件回调函数 和 成功的回调函数
                onAsyncSuccess: function (event, treeId, treeNode, msg) {

                },
                onCheck: onCheck,
            }
        };

        zTree = $.fn.zTree.init($("#treeDiv"), setting);

        PowerMenu.getRoleMenuById(obj);

    }

    //check 事件
    function onCheck(e, treeId, treeNode) {
        return;
        var zTree = $.fn.zTree.getZTreeObj("treeDiv"),
            nodes = zTree.getCheckedNodes(true),
            v = "";
        var ids = "";
        for (var i = 0, l = nodes.length; i < l; i++) {
            v += nodes[i].name + ",";
            ids += nodes[i].id + ",";
        }

        if (ids.length > 0) ids = ids.substring(0, ids.length - 1);
        alert(ids);
        alert(v);
    }

    /**
     * 保持 权限菜单
     */
    $('[data-action="powerSave"]').on('click',function(){
        var nodes = zTree.getCheckedNodes(true);
        var roleId=$('[name="roleId"]').val();
        var identy=$('[name="identy"]').val();
        var nodesArray=[];
        if(nodes.length > 0){
            for(var i=0;i<nodes.length;i++){
                var obj={};
                obj.roleId=roleId;
                obj.menuId=nodes[i].id;
                obj.status=1;
                nodesArray.push(obj);
            }
        }

        var params={};
        params.nodesArray=JSON.stringify(nodesArray);
        params.roleId=roleId;
        params.identy=identy;
        AjaxSubmit.ajax({
            url: contextPath + '/menuManage/saveOrUpdatePowerMenu',
            async: true,
            params: params,
            fnCallback: function (data) {
                if (data.isSuccess) {
                    $('[name="roleId"]').val("");
                    $('[data-action="powerCancel"]').click();
                }
                //清楚 需要romote字段的缓存
                clearRemote();
            }
        });

    });

});