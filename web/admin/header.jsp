<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title></title>
</head>
<body>

<!--导航条-->
<div class="container-fluid  navbar-default navbar-fixed-top " style="background-color: #FCFCFC;">

    <nav class="navbar" role="navigation">
        <div class="container-fluid">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle collapsed" data-toggle="collapse"
                        data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>

                <a class="navbar-brand active" href="${pageContext.request.contextPath}/index.jsp"> <span
                        class="glyphicon glyphicon-home" aria-hidden="true"></span><span
                        class="sr-only">(current)</span><span style="font-size: 23px;">首页</span></a>
            </div>

            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul class="nav navbar-nav" id="my_UL" >
                    <li><a href="${pageContext.request.contextPath}/seckill?method=list"
                           style="font-size: 20px; font-family:bodoni mt black">抢购</a></li>
                </ul>
                <form class="navbar-form navbar-right" method="post"
                      action="${pageContext.request.contextPath}/bookServlet?method=search">
                    <div class="form-group">
                        <input type="text" class="form-control" placeholder="Search Book" name="search">
                    </div>
                    <button type="submit" class="btn btn-default">查询</button>


                    <div class="btn-group">

                        <button href="${pageContext.request.contextPath }/cartServlet?method=#" type="button"
                                class="btn btn-default"><span
                                class="glyphicon glyphicon-shopping-cart"></span>购物车&nbsp;<span class="badge"
                                                                                                id="cart_number"></span>
                        </button>

                        <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown"
                                aria-haspopup="true" aria-expanded="false">
                            <span class="caret"></span>
                            <span class="sr-only">Toggle Dropdown</span>
                        </button>

                        <div class="dropdown-menu" style="width: 400px;">
                            <div style="margin: 10px">
                                <span>我的购物车</span>

                                <span>已经加入0本图书</span>

                            </div>
                            <li role="separator" class="divider"></li>

                            <div href="#" style="background-color:#FCFCFC;" align="center">
                                <img style="width: 100%" src="${pageContext.request.contextPath}/img/cart.png">
                                <a href="${pageContext.request.contextPath}/cartServlet?method=toCart">去购物</a>
                            </div>

                            <li role="separator" class="divider"></li>
                            <div align="right">
                                <li>
                                    <a class="btn btn-danger " style="border-radius:15px;margin-right: 10px"
                                       href="${pageContext.request.contextPath }/cartServlet?method=toCart"><span
                                            class="glyphicon glyphicon-shopping-cart"></span>去购物车
                                    </a>
                                </li>
                            </div>

                        </div>
                    </div>


                    <button type="button" class="btn btn-info dropdown-toggle" data-toggle="dropdown"
                            aria-haspopup="true" aria-expanded="false">
                        <c:if test="${empty userName }">
                            请登陆
                        </c:if>
                        <c:if test="${not empty userName }">
                            Welcome
                        </c:if>
                    </button>
                    <ul class="dropdown-menu">
                        <c:if test="${empty userName }">
                            <li><a href="${pageContext.request.contextPath }/userServlet?method=userLoginUI"><span
                                    class="glyphicon glyphicon-user "></span>&nbsp;登陆</a></li>
                            <li><a href="${pageContext.request.contextPath }/userServlet?method=userRegistUI"><span
                                    class="glyphicon glyphicon-cog"></span>&nbsp;注册</a></li>
                        </c:if>
                        <c:if test="${not empty userName }">
                            <li><a href="${pageContext.request.contextPath }/userServlet?method=userQuit"><span
                                    class="glyphicon glyphicon-off"></span>&nbsp;退出</a></li>
                            <li><a data-toggle="modal" data-target="#myUserInfo"><span
                                    class="glyphicon glyphicon-user"></span>&nbsp;个人信息</a>
                            </li>
                        </c:if>
                    </ul>
                </form>
            </div>
        </div>
    </nav>
</div>

<!-- 个人信息 -->
<div class="modal fade" id="myUserInfo" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="exampleModalLabel">个人信息</h4>
            </div>
            <div class="modal-body">
                <div class="row">
                    <div class="col-md-8">
                        <form>
                            <div class="form-group">
                                <label class="control-label">用户名:</label>
                                &nbsp;&nbsp;&nbsp;
                                <b>${userName.userName}</b>
                            </div>
                            <div class="form-group">
                                <label class="control-label">姓名:</label>
                                &nbsp;&nbsp;&nbsp;
                                <b></b>
                            </div>
                            <div class="form-group">
                                <label class="control-label">邮箱:</label>
                                &nbsp;&nbsp;&nbsp;
                                <b>${userName.email}</b>
                            </div>
                            <div class="form-group">
                                <label class="control-label">性别:</label>
                                &nbsp;&nbsp;&nbsp;
                                <b></b>
                            </div>
                            <div class="form-group">
                                <label class="control-label">状态:</label>
                                &nbsp;&nbsp;&nbsp;
                                <b>
                                    <c:if test="${userName.state == 1}">
                                        良好
                                    </c:if>
                                    <c:if test="${userName.state != 1}">
                                        冻结
                                    </c:if>
                                </b>
                            </div>
                        </form>
                    </div>
                    <!-- 图片 -->
                    <div class="col-md-4">
                        <img src="${pageContext.request.contextPath}/img/book/timg.jpg" style="height:200px;"
                             class="img-thumbnail">
                        <button type="button" class="btn btn-default">更换图片</button>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <a type="button" class="btn btn-primary" href="${pageContext.request.contextPath}/admin/chPassword.jsp">修改密码</a>
                <a type="button" class="btn btn-primary" href="${pageContext.request.contextPath}/admin/changeInfo.jsp">修改信息</a>
            </div>
        </div>
    </div>
</div>
<script src="https://cdn.bootcss.com/jquery/3.3.1/jquery.js"></script>
<script type="text/javascript">
    $(function () {
        var url = "${pageContext.request.contextPath }/bookCategoryServlet";
        var params = {"method": "findCategory"}
        $.post(url, params, function (data) {
            $.each(data, function (i, obj) {
                var li = "<li><a style='font-size: 20px; font-family:bodoni mt black;' href='${pageContext.request.contextPath }/bookCategoryServlet?method=findBookByTypeId&typeId=" + obj.bookTypeId + "&num=0'>" + obj.bookTypeName + "</a></li>";
                $("#my_UL").append(li);
            });
        }, "json");
    });

    $(function () {
        var url = "${pageContext.request.contextPath}/cartServlet";
        var params = {"method": "getBookNumber"}
        $.post(url, params, function (data) {
            if (data > 0) {
                $("#cart_number").html(data);
            } else {
                $("#cart_number").html(data);
            }
        })
    })
</script>
</body>
