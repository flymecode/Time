
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title></title>
    <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>

<%@include file="header.jsp" %>

<div class="container">
    <div class="form row">
        <div class="form-horizontal col-md-offset-3" id="login_form">
            <h3 class="form-title">Regist</h3>
            <div style="color:red;">${msg }</div>
            <div class="col-md-9">
                <form action="${pageContext.request.contextPath}/userServlet?method=userRegist" method="post"
                      onsubmit="return checkregist()">
                    <div class="form-group">
                        <i class="fa fa-user fa-lg"></i>
                        <input class="form-control required" type="text" placeholder="Username" id="username"
                               name="userName" autofocus="autofocus" maxlength="20"/>
                        <span id="usernamespan"></span>
                    </div>
                    <div class="form-group">
                        <i class="fa fa-lock fa-lg"></i>
                        <input class="form-control required" type="password" placeholder="Password" id="password"
                               name="passWord" maxlength="8"/>
                        <span id="passwordspan"></span>
                    </div>
                    <div class="form-group">
                        <i class="fa fa-lock fa-lg"></i>
                        <input class="form-control required" type="password" placeholder="Re_Password" id="re_password"
                               name="re_password" maxlength="8"/>
                        <span id="re_passwordspan"></span>
                    </div>
                    <div class="form-group">
                        <i class="fa fa-lock fa-lg"></i>
                        <input class="form-control required" type="email" placeholder="Email" id="email" name="email"
                               maxlength="30"/>
                        <span id="emailspan"></span>
                    </div>

                    <div class="form-group col-md-offset-9">
                        <button type="submit" class="btn btn-success pull-right" name="submit">注册</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
<script src="https://cdn.bootcss.com/jquery/3.3.1/jquery.js"></script>
<script src="${pageContext.request.contextPath }/js/regist.js"></script>
<script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</body>
</html>
