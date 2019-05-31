<%--
  Created by IntelliJ IDEA.
  User: Asia
  Date: 26.05.2019
  Time: 15:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<%@include file="head.jspf"%>
<body>
<div class=" "container col-6">
<h1>Register</h1>
<form action ="user?action=add" method = "post">
    <div class="form-group">
        <input class="form-control mb-3" type="email"  name="email" placeholder="your email" />
        <input class="form-control mb-3" type="text" name="nick" placeholder="your nick" />
        <input class="form-control mb-3" type="password" name="password" placeholder="your password"/>
        <input class="form-control mb-3" type="password" name="repeatedPassword" placeholder=" repeat your password"/>

        <button class="form-control mb-3" type = "submit">Register</button>
    </div>
</form>
</div>
<div class="container col-6">
    <a href="article?action=main">Main</a>
</div>
</body>
</html>