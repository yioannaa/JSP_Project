<%--
  Created by IntelliJ IDEA.
  User: Asia
  Date: 25.05.2019
  Time: 13:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<%@include file="head.jspf"%>
<body>
<div class=" "container col-6">
    <h1>Add article to  the blog</h1>
            <form action ="/project_jsp_blog_war/article?action=add" method = "post">
                <div class="form-group">
                <input class="form-control mb-3" type="text"  name="title" placeholder="write the title" />
                <textarea class= "form-control mb-3" name ="content" placeholder="write the text"></textarea>
                <button class="form-control mb-3" type = "submit">Add</button>
                </div>
            </form>
        </div>
</body>
</html>
