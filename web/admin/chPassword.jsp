
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>login</title>
    <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" >
    <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" >
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/login.css">
</head>
<body>

<%@include file="header.jsp"%>
<div class="container" style="padding-top:50px;">
    <div class="form row">
        <div class="form-horizontal col-md-offset-3" id="login_form">
            <h3 class="form-title">CHANGE</h3>
            <div class="col-md-9">
                <form action="${pageContext.request.contextPath }/userServlet?method=changePassword" method="post" onsubmit="return checkLogin()">
                    <div class="form-group">
                        <i class="fa fa-user fa-lg"></i>
                        <input class="form-control required" type="password" placeholder="oldPassword" id="old_password" name="oldPassword" autofocus="autofocus" maxlength="12"/>
                        <span id="usernamespan"></span>
                    </div>
                    <div class="col-sm-5">
                        <label id="username_tip" class="control-label" style="color:red"></label>
                    </div>
                    <div class="form-group">
                        <i class="fa fa-lock fa-lg"></i>
                        <input class="form-control required" type="password" placeholder="newPassword" id="new_password" name="newPassword" maxlength="12"/>
                        <span id="passwordspan"></span>
                    </div>
                    <div class="form-group">
                        <a  href="/index.jsp" class="btn btn-success pull-left" name="submit">返回</a>
                        <button type="submit" class="btn btn-success pull-right" name="submit">确认修改</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
<script src="https://cdn.bootcss.com/jquery/3.3.1/jquery.js"></script>
<script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath }/js/login.js"></script>
</body>
</html>
