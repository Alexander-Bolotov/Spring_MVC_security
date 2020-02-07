<%@ page import="org.springframework.security.core.context.SecurityContextHolder" %>
<%@ page import="org.springframework.security.core.Authentication" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec"
           uri="http://www.springframework.org/security/tags"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<% Authentication auth = SecurityContextHolder.getContext().getAuthentication();%>
<html>
<head>
    <%@ page isELIgnored="false" %>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>User Page</title>
    <link href="<c:url value="/resources/css/bootstrap.min.css" />"
          rel="stylesheet">
    <script src="<c:url value="/resources/js/jquery-1.11.1.min.js" />"></script>
    <script src="<c:url value="/resources/js/bootstrap.min.js" />"></script>
</head>
<body>

<div class="container">
    <div class="col-md-offset-1 col-md-10">
        <b><%= auth.getName()%>
            <sec:authorize access="isAuthenticated()">
            <a href="<c:url value="/logout"/>">Logout</a>
            </sec:authorize>
            <hr/>
    <h1><%=request.getAttribute("messages")%></h1>
        </b>
    </div>
</div>

</body>
</html>