/**
 * 弹出层
 * @type {{openIframe: Function}}
 */
var Pop_up_layer = {
    openIframe: function (param) {
        $.post(param.url,param.data,function (str) {
            layer.open({
                type: 1,
                title: '标题',
                skin: 'demo-class',//样式
                area: [param.width, param.height],
                //offset: 'cb',//坐标
                closeBtn: 1,//两种风格的关闭按钮，可通过配置1和2来展示，如果不显示，则closeBtn: 0
                shade: [0.8, '#393D49'],//如果你不想显示遮罩，可以shade: 0
                shadeClose: false,// 是否点击遮罩关闭
                //time: 5000,//自动关闭所需毫秒
                shift: 0,//0-6弹出的风格
                maxmin: true,// 最大最小化。
                fix: false,// 固定  为true下面的页面可以动
                scrollbar: true,//是否允许浏览器出现滚动条
                content: str, //注意，如果str是object，那么需要字符拼接。
                btn: ['确认', '取消']
                ,yes: function(index, layero){
                    (param.fnCallback)(layero);
                    layer.close(index);
                },
                btn2: function(index, layero){
                    layer.close(index);
                }
            });
        });
    }
};