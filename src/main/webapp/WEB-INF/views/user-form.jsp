<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <%@ page isELIgnored="false" %>
    <title>///</title>
    <link href="<c:url value="/resources/css/bootstrap.min.css" />"
          rel="stylesheet">
    <script src="<c:url value="/resources/js/jquery-1.11.1.min.js" />"></script>
    <script src="<c:url value="/resources/js/bootstrap.min.js" />"></script>

</head>
<body>
<div class="container">
    <div class="col-md-offset-2 col-md-7">
        <h2 class="text-center">Добавить пользователя
        </h2>
        <div class="panel panel-info">
            <div class="panel-heading">
                <div class="panel-title">Add User</div>
            </div>
            <div class="panel-body">
                <form:form action="saveUser" cssClass="form-horizontal"
                           method="post" modelAttribute="user">

                    <!-- need to associate this data with customer id -->
                    <form:hidden path="id" />

                    <div class="form-group">
                        <label for="name" class="col-md-3 control-label">Name</label>
                        <div class="col-md-9">
                            <form:input path="name" cssClass="form-control" />
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="password" class="col-md-3 control-label">Password</label>
                        <div class="col-md-9">
                            <form:input path="password" cssClass="form-control" />
                        </div>
                    </div>
                    <div class="form-group">
                       <label for="roles" class="col-md-3 control-label">ROLES</label>

                        <div class="col-md-9" cssClass="form-control">

                            <sf:select multiple="true" path="roles" id="role" size="3" >
                                <sf:options items="${rolesList}" itemValue="role" itemLabel="role"/>
                            </sf:select>
                        </div>
                    </div>


                    <div class="form-group">
                        <!-- Button -->
                        <div class="col-md-offset-3 col-md-9">
                            <form:button cssClass="btn btn-primary">Submit</form:button>
                        </div>
                    </div>

                </form:form>
            </div>
        </div>
    </div>
</div>
</body>
</html>