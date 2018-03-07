var browserVersion = window.navigator.userAgent.toUpperCase();
var isOpera = browserVersion.indexOf("OPERA") > -1;
var isFireFox = browserVersion.indexOf("FIREFOX") > -1;
var isChrome = browserVersion.indexOf("CHROME") > -1;
var isSafari = browserVersion.indexOf("SAFARI") > -1;
var isIE = (!!window.ActiveXObject || "ActiveXObject" in window);
var isIE9More = (!-[1,] == false);
function reinitIframe() {
    var iframeId="mainContent";
    var minHeight=document.documentElement.clientHeight-46;
    try {
        var iframe = document.getElementById(iframeId);
        var bHeight = 0;
        if (isChrome == false && isSafari == false){
            bHeight = iframe.contentWindow.document.body.scrollHeight;
        }

        var dHeight = 0;
        if (isFireFox == true){
            dHeight = iframe.contentWindow.document.documentElement.offsetHeight + 2;
        }else if (isIE == false && isOpera == false){
            dHeight = iframe.contentWindow.document.documentElement.scrollHeight;
        }else if (isIE == true && isIE9More) {//ie9+
            var heightDeviation = bHeight - eval("window.IE9MoreRealHeight" + iframeId);
            if (heightDeviation == 0) {
                bHeight += 3;
            } else if (heightDeviation != 3) {
                eval("window.IE9MoreRealHeight" + iframeId + "=" + bHeight);
                bHeight += 3;
            }
        }else{//ie[6-8]、OPERA
            bHeight += 3;
        }


        var height = Math.max(bHeight, dHeight);
        if (height < minHeight) height = minHeight;
        iframe.style.height = height + "px";
    } catch (ex) {
    }
}

function startInit(iframeId, minHeight) {
    eval("window.IE9MoreRealHeight" + iframeId + "=0");
    window.setInterval("reinitIframe('" + iframeId + "'," + minHeight + ")", 80);
}
/**
 * 初始化 页面高度
 */
//startInit('mainContent',document.body.clientHeight-46);

function changeFrameHeight(){
    reinitIframe();
}
window.onresize=function(){
    changeFrameHeight();
}