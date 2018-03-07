$(function(){
    var contextPath =$('[name="contextPath"]').val();
    //获取 该用户的 角色列表
    AjaxSubmit.post({
        url: contextPath + '/roleManage/getRoleListByAccount',
        async: true,//true：为异步提交 false：为同步提交
        fnCallback: function (data) {
            if (data.isSuccess) {
                var htmlTable="";
                if(data.roleList.length == 1){
                    for(var i=0;i<data.roleList.length;i++){
                        htmlTable+='<tr style="text-align: left">';
                        htmlTable+='<td class="">';
                        htmlTable+='<label>';
                        htmlTable+='<input onclick="clickEven();" name="identy" type="radio" value="'+data.roleList[i].roleIdenty+'" class="ace">';
                        htmlTable+='<span class="lbl">'+data.roleList[i].roleName+'</span>';
                        htmlTable+='</label>';
                        htmlTable+='</td>';
                        htmlTable+='</tr>';
                    }
                    $('.table').html(htmlTable);
                    $('[name="identy"]').attr('checked',true);
                    var searchForm=document.createElement("form");
                    var SortListDoc =document.createElement("input");
                    SortListDoc.setAttribute('name',"roleIdenty");
                    SortListDoc.setAttribute('type',"text");
                    SortListDoc.value=$('input[name="identy"]:checked').val();
                    searchForm.appendChild(SortListDoc);

                    //执行方法调用
                    searchForm.action = contextPath + "/loginManage/toHomepage";
                    searchForm.method = "POST";
                    $(document.body).append(searchForm);
                    searchForm.submit();
                }else{
                    for(var i=0;i<data.roleList.length;i++){
                        htmlTable+='<tr style="text-align: left">';
                        htmlTable+='<td class="">';
                        htmlTable+='<label>';
                        htmlTable+='<input onclick="clickEven();" name="identy" type="radio" value="'+data.roleList[i].roleIdenty+'" class="ace">';
                        htmlTable+='<span class="lbl">'+data.roleList[i].roleName+'</span>';
                        htmlTable+='</label>';
                        htmlTable+='</td>';
                        htmlTable+='</tr>';
                    }
                    $('.table').html(htmlTable);
                }
            }else{
                //提示信息
                Common_function.promotInfo(data.msg);
            }
        }
    });

});
/**
 * 选中radio
 */
function clickEven(){
    $('[name="roleIdenty"]').val($('input[name="identy"]:checked').val());
}