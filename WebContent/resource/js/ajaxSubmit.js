/**
 * ajax 提交
 * @type {{ajax: Function, post: Function}}
 */
var AjaxSubmit;
AjaxSubmit = {
    //ajax 提交方式
    ajax: function (param) {
        $.ajax({
            //提交数据的类型 POST GET
            type: "POST",
            //提交的网址
            url: param.url,
            async: param.async,//true 时，ajax请求是异步的
            //提交的数据
            data: param.params,
            //返回数据的格式
            datatype: "json",//"xml", "html", "script", "json", "jsonp", "text".
            //在请求之前调用的函数
            beforeSend: function () {

            },
            //成功返回之后调用的函数
            success: function (data) {
                var da=$.parseJSON(data);
                //var da = eval("(" + data + ")");
                (param.fnCallback)(da);
            },
            //调用执行后调用的函数
            complete: function (XMLHttpRequest, textStatus) {

            },
            //调用出错执行的函数
            error: function (XMLHttpRequest, textStatus, errorThrown) {
                $.gritter.add({
                    title: '错误信息',
                    text: errorThrown,
                    sticky: false,
                    time: 8000,
                    class_name: ('gritter-error')
                });
            }
        });
    },
    //post 提交方式
    post: function (param) {
        $.post(param.url, param.params, function (data) {
            //var da = eval("(" + data + ")");
            var da=$.parseJSON(data);
            (param.fnCallback)(da);
        });
    }
};