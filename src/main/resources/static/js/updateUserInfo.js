//jquery 闭包作用域
var pathName = document.location.pathname.substring(0,window.document.location.pathname.substr(1).indexOf('/')+1);
$(function(){
        $("#updateUserInfo").click(function() {
            update();
        })

        function  update() {
            var userName = $("#userName").val();
            var password = $("#password").val();
            var resetPassword = $("#resetPassword").val();
            var enterPassword = $("#enterPassword").val();
            if ("" == userName.trim() || userName == undefined) {
                alert("请输入用户名");
                return false;
            }
            if ("" == password.trim() || password == undefined) {
                alert("请输入原密码");
                return false;
            }
            if ("" == resetPassword.trim() || resetPassword == undefined) {
                alert("请输入新密码");
                return false;
            }
            if ("" == enterPassword.trim() || enterPassword == undefined) {
                alert("请输入重复密码");
                return false;
            }
            if (resetPassword != enterPassword) {
                alert("请输入新密码和重复新密码不一致，请重新输入！");
                return false;
            }

            console.log("ok" + pathName)
            $.ajax({
        type:"get",
        url:pathName+"/products",
        data:{
            userName:userName,
            password:password,
            resetPassword:resetPassword,
            enterPassword:enterPassword,
        },
        dataType:"json",
        success:function (data) {
            console.log(data);
            if(data.status){
                //window.location.href="getIndex";
            }
        },error:function (XMLRequestStatus) {
            console.log(XMLRequestStatus);
        }
    })


/*    $.post("products",
        {userName:userName,
            password:password,
            resetPassword:resetPassword,
            enterPassword:enterPassword,
        },function(data){
            console.log(data);
        },"json")*/

}

});

/*function test(){
    var data = sendHttpRequest("post","/products",false,null);
    console.log(data);
}*/


