
var usernameflag = true;
var passwordflag = true;


$(function() {

    $("#username").blur(function() {//对于用户名的判断
        var username = $("#username").val();
        var reg=/^[a-zA-Z0-9]{5,12}$/;

        if (!reg.test(username)) {
            $("#usernamespan").html("请输入5-12位大小写字母或数字").css("color", "red");
            usernameflag = false;
        } else {
            usernameflag = true;
        }
    });

    $("#username").focus(function() {
        $("#usernamespan").html("");
    })

    $("#password").blur(function() {
        var reg=/^[a-zA-Z0-9]{5,12}$/;
        var password = $("#password").val();
        if (!reg.test(password)) {
            $("#passwordspan").html("请输入5-12位大小写字母或数字").css("color", "red");
            passwordflag = false;
        } else {
            passwordflag = true;
        }
    });





});



function checkLogin() {

    if ( usernameflag==true && passwordflag==true) {
        return true;
    } else {
        alert("输入格式有错误,请重新输入");
        return false;
    }

}

