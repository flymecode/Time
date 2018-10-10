
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<div class="container-fluid">
    <form action="${pageContext.request.contextPath }/userServlet?method=editUserInfo" method="post">
        <div class="form row">
            <div class="form-horizontal col-md-offset-3" id="login_form">
                <h3 class="form-title">个人信息</h3>
                <div class="col-md-9">
                    <div class="form-group">
                        <i class="fa fa-lock fa-lg"></i>
                        <input class="form-control required" type="text" value="" placeholder="Name" id="user_name"
                               name="user_name" maxlength="8"/>
                    </div>
                    <div class="form-group">
                        <i class="fa fa-lock fa-lg"></i>
                        <input class="form-control required" type="text" value="" placeholder="Email" id="user_email"
                               name="user_name" maxlength="8"/>
                    </div>
                    <div class="form-group">
                        <i class="fa fa-lock fa-lg"></i>
                        <input class="form-control required" type="text" value="" placeholder="Phone" id="user_phone"
                               name="user_name" maxlength="8"/>
                    </div>
                    <div class="form-group">
                        <i class="fa fa-lock fa-lg"></i>
                        <select class="form-control required" name="userSex"  >
                            <option value="男">&nbsp;&nbsp;&nbsp;男</option>
                            <option value="女">&nbsp;&nbsp;&nbsp;女</option>
                        </select>
                    </div>

                    <div class="form-group col-md-offset-9">
                        <a href="" type="submit" class="btn btn-success pull-left" name="submit">取消</a>
                        <button type="submit" class="btn btn-success pull-right" name="submit">保存</button>

                    </div>
                </div>
            </div>
        </div>
    </form>
</div>
<script src="https://cdn.bootcss.com/jquery/3.3.1/jquery.js"></script>
<script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</body>
</html>

