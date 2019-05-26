<%--
  Created by IntelliJ IDEA.
  User: Asia
  Date: 26.05.2019
  Time: 11:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<%@include file="head.jspf"%>
<body>
<div class=" "container col-6">
        <form action ="/project_jsp_blog_war/article?action=updateTitle&id=${requestScope.article.id}" method = "post">
        <div class = "form-group">
            <input class="form-control mb-3" type="text" name="title"  value="${requestScope.article.title}"/>
            <textarea disabled class="form-control mb-3" type="text" name="content"> ${requestScope.article.content}
            </textarea>
        <button class="btn btn-primary" type = "submit">Change the title</button>
    </div>
</form>
</div>
</body>
</html>