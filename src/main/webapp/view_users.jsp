<%--
  Created by IntelliJ IDEA.
  User: Asia
  Date: 26.05.2019
  Time: 15:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"  %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<html>
<%@include file="head.jspf"%>
<body>
<div class="container col-3">

    <table>
        <tr class="table">
            <th>
                email
            </th>
            <th>
                nick
            </th>
            <th>
                registration date
            </th>
        </tr>
        <c:forEach var="user" items ="${requestScope.users}">
            <tr>
                <td>
                    <a href="user?action=view&id=${user.id}">${user.email}</a>
                </td>
                <td>
                    ${user.nick}
                </td>
                <td>
                    ${user.registered}
                </td>

            </tr>
        </c:forEach>
    </table>

</div>
<div class="container col-6">
    <a href="article?action=main">Main</a>
</div>

</body>
</html>
