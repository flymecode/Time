
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css">
</head>
<body>


<%@include file="header.jsp" %>
<!--轮播图-->
<div id="carousel-example-generic" class="carousel slide" data-ride="carousel" style="position: relative; top: 73px;">
    <!-- Indicators -->
    <ol class="carousel-indicators">
        <li data-target="#carousel-example-generic" data-slide-to="0" class="active"></li>
        <li data-target="#carousel-example-generic" data-slide-to="1"></li>
        <li data-target="#carousel-example-generic" data-slide-to="2"></li>
    </ol>

    <!-- Wrapper for slides -->
    <div class="carousel-inner" role="listbox">
        <div class="item active" style="height: 450px">
            <img src="${pageContext.request.contextPath}/img/head_book/L1.jpg" alt="...">
            <div class="carousel-caption">
                ...
            </div>
        </div>
        <div class="item" style="height: 450px">
            <img src="${pageContext.request.contextPath}/img/head_book/L3.jpg" alt="...">
            <div class="carousel-caption">
                ...
            </div>
        </div>
        <div class="item" style="height: 450px">
            <img src="${pageContext.request.contextPath}/img/head_book/L1.jpg" alt="...">
            <div class="carousel-caption">
                ...
            </div>
        </div>
    </div>

    <!-- Controls -->
    <a class="left carousel-control" href="#carousel-example-generic" role="button" data-slide="prev">
        <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
        <span class="sr-only">Previous</span>
    </a>
    <a class="right carousel-control" href="#carousel-example-generic" role="button" data-slide="next">
        <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
        <span class="sr-only">Next</span>
    </a>
</div>
<div class="page-header container" style="margin-top: 100px;">
    <h3>热门图书
        <small>Top10</small>
    </h3>
</div>

<div class="container" style="background-color: #F5F5F5;">

    <div class="row" style="padding: 20px;">
        <c:forEach items="${hotBook}" var="b">
            <div class="col-xs-6 col-md-3">
                <a href="${pageContext.request.contextPath}/bookServlet?method=findBookById&bookId=${b.bookId}"
                   class="thumbnail" style="background-color:#FCFCFC ;">
                    <img src="${pageContext.request.contextPath}/${b.image}">
                </a>
            </div>
        </c:forEach>
    </div>

</div>


<div class="page-header container" style="margin-top: 100px;">
    <h3>热销图书
        <small>Top10</small>
    </h3>
</div>

<div class="container" style="background-color: #F5F5F5;">

    <div class="row" style="padding: 20px;">
        <c:forEach items="${newBook}" var="n">
            <div class="col-xs-6 col-md-3">
                <a href="${pageContext.request.contextPath}/bookServlet?method=findBookById&bookId=${n.bookId}"
                   class="thumbnail" style="background-color:#FCFCFC ;">
                    <img src="${pageContext.request.contextPath}/${n.image}">
                </a>
            </div>
        </c:forEach>
    </div>
</div>
<!--页脚-->
<%@include file="footer.jsp" %>
<script src="https://cdn.bootcss.com/jquery/3.3.1/jquery.js"></script>
<script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</body>
</html>
