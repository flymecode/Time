<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap-theme.min.css">
</head>
<body>
<!--导航条-->
<%@include file="header.jsp" %>
<!--列表-->
<div class="container" style="margin-top: 100px">
    <div class="row">

        <div class="row">
            <c:forEach var="sk" items="${list}">
            <div class="col-sm-6 col-md-3">
                <div class="thumbnail text-center">
                    <img src="${pageContext.request.contextPath}/img/book/timg.jpg">
                    <div class="caption">
                        <h4>${sk.name}</h4>
                        <p>${sk.number}</p>
                        <p><fmt:formatDate value="${sk.startTime}" pattern="yyyy-MM-dd HH:mm:ss"/></p>
                        <p><fmt:formatDate value="${sk.endTime}" pattern="yyyy-MM-dd HH:mm:ss"/></p>
                        <p><fmt:formatDate value="${sk.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/></p>
                    </div>
                    <p><a class="btn btn-info" href="${pageContext.request.contextPath}/seckill?method=detail&seckillId=${sk.seckillId}" target="_blank">查看详情</a></p>
                </div>
            </div>
            </c:forEach>
        </div>

    </div>
</div>
<!--分页-->
<div class="container" align="center">
    <nav aria-label="Page navigation">
        <ul class="pagination">
            <li>
                <a href="#" aria-label="Previous">
                    <span aria-hidden="true">&laquo;</span>
                </a>
            </li>
            <li><a href="#">1</a></li>
            <li><a href="#">2</a></li>
            <li><a href="#">3</a></li>
            <li><a href="#">4</a></li>
            <li><a href="#">5</a></li>
            <li>
                <a href="#" aria-label="Next">
                    <span aria-hidden="true">&raquo;</span>
                </a>
            </li>
        </ul>
    </nav>
</div>
<!--页脚-->
<%@include file="footer.jsp" %>
<script src="https://cdn.bootcss.com/jquery/3.3.1/jquery.js"></script>
<script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</body>
</html>

