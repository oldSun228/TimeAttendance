$(function () {
    $('body').attr('class', 'login-layout light-login');
    $('#id-text2').attr('class', 'grey');
    $('#id-company-text').attr('class', 'blue');

    //validate 初始化
    LoginTreatmentApplication.toValidate($('#LoginForm'));

    //enter键 事件
    document.onkeydown = function (event) {
        var e = event || window.event || arguments.callee.caller.arguments[0];
        if (e && e.keyCode == 13) { // enter 键
            var btn = $('#loading-btn');
            btn.button('loading');
            $('#LoginForm').submit();
            setTimeout(function(){
                btn.button('reset');
            },1000);
        }
    };

    //点击 登录 按钮 事件
    $('#loading-btn').on('click', function () {
        var btn = $('#loading-btn');
        btn.button('loading');
        $('#LoginForm').submit();
        setTimeout(function(){
            btn.button('reset');
        },1000);
    });

    //忘记密码
    $('[data-action="forgetPassword"]').on('click', function () {
        layer.msg("请联系管理员!", {shift: 6});
    });

    //用来 管理session过期 跳到登录页面
    var flag = $('[name="flag"]').val();
    if (flag == 'interceptor') {
        window.parent.location.href = "../loginManage/loginOut";
    }

});

