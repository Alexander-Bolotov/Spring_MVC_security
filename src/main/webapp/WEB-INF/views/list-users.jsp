<%@ page import="org.springframework.security.core.Authentication" %>
<%@ page import="org.springframework.security.core.context.SecurityContextHolder" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec"
           uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<% Authentication auth = SecurityContextHolder.getContext().getAuthentication();%>
<!DOCTYPE html>
<html>
<head>
    <%@ page isELIgnored="false" %>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title></title>
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

            <input type="button" value="Add User"
                   onclick="window.location.href='showForm'; return false;"
                   class="btn btn-primary"/>
            <br/><br/>
            <div class="panel panel-info">
                <div class="panel-heading">
                    <div class="panel-title">User List</div>
                </div>
                <div class="panel-body">
                    <table class="table table-striped table-bordered">
                        <tr>
                            <th>ID</th>
                            <th>Name</th>
                            <th>Password</th>
                            <th>Role</th>
                            <th>Action</th>
                        </tr>

                        <!-- loop over and print our customers -->
                        <c:forEach var="tempUser" items="${users}">

                        <!-- construct an "update" link with customer id -->
<%--                        <c:url var="updateLink" value="/updateForm">--%>
<%--                            <c:param name="userId" value="${tempUser.id}"/>--%>
<%--                        </c:url>--%>

                        <!-- construct an "delete" link with customer id -->
                        <c:url var="deleteLink" value="/delete">
                            <c:param name="userId" value="${tempUser.id}"/>
                        </c:url>
                        <!-- construct an "delete" link with customer id -->
                        <c:url var="userdataLink" value="/userdata">
                            <c:param name="userName" value="${tempUser.name}"/>
                        </c:url>

                        <tr>
                            <td>${tempUser.id}</td>
                            <td>${tempUser.name}</td>
                            <td>${tempUser.password}</td>
                            <td>${tempUser.roles}</td>


                            <td>
<%--                                <!-- display the update link --> <a href="${updateLink}">Update</a>--%>
                                | <a href="${deleteLink}"
                                     onclick="if (!(confirm('Are you sure you want to delete this user?'))) return false">Delete</a>
                            </td>

                        </tr>

                        </c:forEach>

                </div>
                <table class="table table-striped table-bordered">
                    <tr>
                        <th>ROLES</th>
                    </tr>
                    <tr>
                        <td>${rolesList}</td>
                    </tr>
                    <tr>
                    <th>

                    </th>
                    </tr>

            </div>
    </div>

</div>
</body>
</html>