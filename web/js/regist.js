
var usernameflag = true;
var passwordflag = true;
var emailflag = true;
var repassword = true;

$(function() {

    $("#username").blur(function() {//对于用户名的判断
        var username = $("#username").val();
        var reg=/^[a-zA-Z0-9]{5,12}$/;

        if (!reg.test(username)) {
            $("#usernamespan").html("请输入5-12位大小写字母或数字").css("color", "red");
            $("#username").focus();
            usernameflag = false;
        } else {
            $.post("/userServlet?method=checkUserName", {"username" : username}, function(data) {
                if (!data) {
                    usernameflag = true;
                    $("#usernamespan").html("满足要求").css("color", "green");
                } else {
                    usernameflag = false;
                    $("#usernamespan").html("用户名已存在").css("color", "red");
                }
            }, "json");
        }
    });

    $("#username").focus(function() {
        $("#usernamespan").html("");
    })

    $("#password").blur(function() {
        var reg=/^[a-zA-Z0-9]{5,12}$/;
        var password = $("#password").val();
        if (reg.test(password)) {
            $("#passwordspan").html("密码满足要求").css("color", "green");
            passwordflag = true;
        } else {
            $("#passwordspan").html("请输入5-12位大小写字母或数字").css("color", "red");
            passwordflag = false;
        }
    });

    $("#re_password").blur(function() {
        var password = $("#password").val();
        var re_password = $("#re_password").val().trim();

        if (password == re_password && re_password != null && re_password != "") {
            $("#re_passwordspan").html("两次一致").css("color", "green");
        } else {
            $("#re_passwordspan").html("两次密码不一致").css("color", "red");
            repassword = false;
        }
    });

    $("#email").blur(function() {//邮箱校验
        var email = $("#email").val();
        var reg=/^([a-zA-Z0-9_-])+@[a-zA-Z0-9_-]+((\.[a-z]{2,3}){1,2})$/;
        if (reg.test(email)) {
            $("#emailspan").html("邮箱格式正确").css("color", "green");
            emailflag = true;
        } else {
            $("#emailspan").html("请输入正确格式Email").css("color", "red");
            emailflag = false;
        }

    });

});

$("#email").focus(function() {
    $("#emailspan").html("");
});

function checkregist() {

    if ( usernameflag==true && passwordflag==true && emailflag==true && repassword==true) {
        return true;
    } else {
        alert("输入格式有错误,请重新输入");
        return false;
    }

}

	