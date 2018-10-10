<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: maxu
  Date: 2018/10/5
  Time: 9:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<c:forEach var="item" items="${cart.items}">
   书名 ${item.book.title}
    书价格${item.book.price}
    数量${item.bookNumber}
</c:forEach>
书本总数${cart.bookNumber}
书本总价${cart.totalPrice}

</body>
</html>
