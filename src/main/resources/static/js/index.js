//    document.write('IP地址:' + returnCitySN["cip"] + ', CID:' + returnCitySN["cid"] + ', 地区:' + returnCitySN["cname"]+",浏览器版本:"+getBrowserInfo());
console.log('IP地址:' + returnCitySN["cip"] + ', CID:' + returnCitySN["cid"] + ', 地区:' + returnCitySN["cname"]+",浏览器版本:"+getBrowserInfo());
function baiduApi(){

}
function saveIpInfo(){
    var arr = [];
    arr.push("recordIpUrl="+returnCitySN["cip"]);
    arr.push("recordIpAddress="+returnCitySN["cname"]);
    arr.push("recordIpBrower="+navigator.userAgent.toLowerCase());
    var url = "/firstDemo/index/getIndex";
//            var url = "/index/saveRecordIpInfo";
//        console.log(paramsFlush(arr));
    sendHttpRequest("post",url,true,paramsFlush(arr));
}
saveIpInfo();
//        setTimeout(saveIpInfo(), 1000);

function getBrowserInfo(){
    var agent = navigator.userAgent.toLowerCase();
    var regStr_ie = /msie [\d.]+;/gi ;
    var regStr_ff = /firefox\/[\d.]+/gi
    var regStr_chrome = /chrome\/[\d.]+/gi ;
    var regStr_saf = /safari\/[\d.]+/gi ;

//IE
    if(agent.indexOf("msie") > 0)
    {
        return agent.match(regStr_ie) ;
    }
//firefox
    if(agent.indexOf("firefox") > 0)
    {
        return agent.match(regStr_ff) ;
    }
//Chrome
    if(agent.indexOf("chrome") > 0)
    {
        return agent.match(regStr_chrome) ;
    }
//Safari
    if(agent.indexOf("safari") > 0 && agent.indexOf("chrome") < 0)
    {
        return agent.match(regStr_saf) ;
    }

}

