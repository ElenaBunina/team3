<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<style>
    <%@include file="/resources/bootstrap.css"%>
    body {
        background: #f8fff6
    }
</style>

<html>
<head>
    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
          integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
    <!-- Optional theme -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css"
          integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">

    <!-- Latest compiled and minified JavaScript -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
            integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
            crossorigin="anonymous"></script>
    <!-- Latest compiled and minified JavaScript-->
    <script src="https://code.jquery.com/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Main page</title>

</head>
<body>
<nav class="navbar navbar-inverse">
    <div class="container-fluid">
        <div class="navbar-header">
            <a href="#" class="navbar-brand">Netcracker</a>
        </div>
        <div>
            <ul class="nav navbar-nav nav-tabs">
                <li><a href="/">Home</a></li>
                <li>
                    <a href="<c:url value="/logout"/>">Exit</a>
                </li>
            </ul>
        </div>
    </div>
</nav>

<div class="col-md-2">
    <ul class="nav nav-tabs nav-stacked">
        <li class="active"><a href="/">Welcome page <span class="glyphicon glyphicon-home"></span></a></li>
        <li>
            <a href="/viewObjectType?id=${1}">Admin Tools <span class="glyphicon glyphicon-cog"></span></a>
        </li>
        <li>
            <a href="/loadTemplate?id=${6}">Template<span class="glyphicon glyphicon-cog"></span></a>
        </li>
        <li class="dropdown">
            <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                Create<b class="caret"></b>
            </a>
            <ul class="dropdown-menu">
                <li><a href="newEntity">New Root</a></li>
                <li><a href="newCustomer">New Customer</a></li>
                <%--<li><a href="newTask">New Task</a></li>--%>
            </ul>
    </ul>
</div>

<div class="col-md-10">
    <div align="left">
        <div class="row">
            <div class="container-fluid">
                <h3>List of objects</h3>
                <table class="table table-responsive table-condensed table-hover">
                    <th>Object_Id</th>
                    <th>Name</th>
                    <c:forEach var="entity" items="${listEntity}" varStatus="status">
                        <tr>
                            <td style="color: #100508">${entity.objectId}</td>
                            <td><a style="color: #100508" href="object?objectId=${entity.objectId}">${entity.name}
                                <a href="delete?objectId=${entity.objectId}"><span class="glyphicon glyphicon-trash"></span></a></a></td>
                        </tr>
                    </c:forEach>
                </table>
            </div>
        </div>
    </div>
</div>

<div align="center">
    <ul class="pagination">
        <li><a href="/">1</a></li>
        <li><a href="/home">2</a></li>
        <li><a href="/login">3</a></li>
    </ul>
</div>

</body>
</html>