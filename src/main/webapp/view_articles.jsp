<%--
  Created by IntelliJ IDEA.
  User: Asia
  Date: 25.05.2019
  Time: 13:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"  %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<html>
<%@include file="head.jspf"%>
<body>
    <div class="container">
        <%--
        <sql:setDataSource var="base"
              driver="com.mysql.cj.jdbc.Driver"
              url="jdbc:mysql://localhost:3306/blog?serverTimezone=UTC"
              user="root"
              password = "kubistycznykot"/>

        <sql:query var="aricles" dataSource="${base}">
            SELECT * FROM ARTICLE
        </sql:query>sql
        --%>

        <table>
            <tr>
                <th>
                    Title
                </th>
            </tr>
            <c:forEach var="article" items ="${requestScope.articles}">
                <tr>
                    <td>
                <a href="/project_jsp_blog_war/article?action=view&id=${article.id}">${article.title}</a>
                    </td>
                    <td>
                        <a href="article?action=delete&id=${article.id}">Delete</a>
                    </td>
                    <td>
                        <a href="article?action=changeTitle&id=${article.id}">Change the title</a>
                    </td>

                </tr>
            </c:forEach>
        </table>

    </div>

</body>
</html>
