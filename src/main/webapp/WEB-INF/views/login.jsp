<%@ page import="org.springframework.security.core.context.SecurityContextHolder" %>
<%@ page import="org.springframework.security.core.Authentication" %>
<%@ taglib prefix="sec"
           uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Святослав
  Date: 25.11.2019
  Time: 16:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% Authentication auth = SecurityContextHolder.getContext().getAuthentication();%>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%= auth.getName()%>
<sec:authorize access="isAuthenticated()">
    <a href="<c:url value="/logout"/>">Logout</a>
</sec:authorize>
<form method="post" action="/login">
    <input name="name"/>
    <input name="password"/>
    <input type="submit"/>
</form>

</body>
</html>
