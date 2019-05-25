<%--
  Created by IntelliJ IDEA.
  User: Asia
  Date: 25.05.2019
  Time: 22:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%@include file="head.jspf"%>
<body>
<h1>${requestScope.article.title}</h1>
<p>${requestScope.article.content}</p>
</body>
</html>