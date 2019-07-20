var xhr;
function  createXmlHttpRequest(){
    if (window.ActiveXObject)
        xhr  = new ActiveXObject("Microsoft.XMLHTTP");
    else
        xhr  = new XMLHttpRequest();
}

  /**异步请求函数  */
  function  sendHttpRequest(method,url,async,params) {
      createXmlHttpRequest();
      xhr.onreadystatechange = function () {
          if (xhr.readyState == 4 & xhr.status == 200) {
              var messages = xhr.responseText;
              console.log(messages);
          }
      }
      xhr.open(method,url,async);
      xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
      xhr.send(params);
  }


  /**请求参数追加  */
  function paramsFlush(paramsArray){
      if(paramsArray!=null &&  paramsArray!=undefined && ""!=paramsArray && paramsArray.length>=1){
          var params = "";
          if(paramsArray.length==1){
              params =  paramsArray[0];
          }else{
              for(var i=0;i<paramsArray.length;i++){
                  if(i==0){
                      params += paramsArray[0];
                  }else{
                      params += "&"+paramsArray[i];
                  }
              }
          }
          return params;
      }
      return null;
  }
