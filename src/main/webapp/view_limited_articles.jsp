<%@ page import="entity.ArticleEntity" %>
<%@ page import="repository.ArticleRepository" %>
<%@ page import="java.util.Scanner" %>
<%@ page import="dao.Dao" %>
<%@ page import="entity.Article" %><%--
  Created by IntelliJ IDEA.
  User: Asia
  Date: 29.05.2019
  Time: 15:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib  uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%
//    ArticleRepository repo = new ArticleRepository();
//    int size =5;
//    int start = 0;
//    String pageStr = request.getParameter("page");
//
//    Scanner inStr = new Scanner (pageStr);
//    if(inStr.hasNextInt()){
//        start = inStr.nextInt();
//    }else{
//        start = 0;
//    }
%>
<html>
<head>
    <title>Articles</title>
</head>
<body>

<table>


<%--    <%--%>

<%--        for( Article a : repo.getLimited(start, start+size)){--%>
<%--    %>--%>
    <c:forEach items="${requestScope.articles}" var="article" varStatus="status">
        <c:if test="${status.count % 2 == 0}">
            <tr bgcolor = "aqua">
        </c:if>
        <c:if test="${status.count % 2 !=0}">
            <tr bgcolor = "pink">

        </c:if>

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
<%--    <%--%>
<%--        }--%>
<%--    %>--%>

</table>
<footer>
    <a href="article?action=viewLimited=first">First</a>
    <a href="article?action=viewLimited=prev">Previous</a>
    ${requestScope.page}
    <a href="article?action=viewLimited=next">Next</a>
    <a href="article?action=viewLimited=last">Last</a>

</footer>
<div class="container col-6">
    <a href="article?action=main">Main</a>
</div>
</body>
</html>
