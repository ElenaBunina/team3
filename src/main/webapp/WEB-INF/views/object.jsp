<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<style>
    <%@include file="/resources/bootstrap.css"%>
    body {
        background: #ffffff
    }
</style>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Свойства</title>
</head>
<body>

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

<script src="https://code.jquery.com/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>

<nav class="navbar navbar-inverse">
    <div class="container-fluid">
        <div class="navbar-header">
            <a href="#" class="navbar-brand">Netcracker</a>
        </div>
        <div>
            <ul class="nav navbar-nav nav-tabs">
                <li><a href="/">Home</a></li>
            </ul>
        </div>
    </div>
</nav>
<div align="left">
    <div class="container-fluid">
        <div class="row">
            <div class="col-md-6">
                <div align="left">
                    <c:forEach var="list" items="${listParams}" varStatus="status">
                        <c:if test="${list.objectTypeId == 3}">
                            <div align="left">
                                <ol class="breadcrumb">
                                    <li><a href="#">Entity</a></li>
                                    <li class="active">Customer</li>
                                </ol>
                            </div>
                            <div align="left">
                                <h3>Action</h3>
                            </div>
                            <div>
                                <div>
                                    <ul class="nav nav-pills">
                                        <li class="dropdown">
                                            <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                                                Action list <b class="caret"></b></a>
                                            <ul class="dropdown-menu">
                                                <li><a href="/newPhone/${list.objectId}">Add mobile phone</a></li>
                                                <li class="divider"></li>
                                                <li><a href="/viewObjectType?id=${list.objectTypeId}">Type tree</a></li>
                                            </ul>
                                        </li>
                                    </ul>
                                </div>
                            </div>
                        </c:if>
                        <c:if test="${list.objectTypeId == 6}">
                            <div align="left">
                                <ol class="breadcrumb">
                                    <li><a href="#">Entity</a></li>
                                    <li class="active">Template</li>
                                </ol>
                            </div>
                            <div align="left">
                                <h3>Action</h3>
                            </div>
                            <div>
                                <div>
                                    <ul class="nav nav-pills">
                                        <li class="dropdown">
                                            <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                                                Action list <b class="caret"></b></a>
                                            <ul class="dropdown-menu">
                                                <li><a href="/newTask/${list.objectId}">Add Task</a></li>
                                                <li class="divider"></li>
                                                <li><a href="/viewObjectType?id=${list.objectTypeId}">Type tree</a></li>
                                            </ul>
                                        </li>
                                    </ul>
                                </div>
                            </div>
                        </c:if>
                        <c:if test="${list.objectTypeId == 4}">
                            <div align="left">
                                <ol class="breadcrumb">
                                    <li><a href="#">Entity</a></li>
                                    <li><a href="#">Customer</a></li>
                                    <li class="active">Phone</li>
                                </ol>
                            </div>
                            <div align="left">
                                <h3>Action</h3>
                            </div>
                            <div>
                                <div>
                                    <ul class="nav nav-pills">
                                        <li class="dropdown">
                                            <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                                                Action list <b class="caret"></b></a>
                                            <ul class="dropdown-menu">
                                                <li><a href="#">Nothing</a></li>
                                                <li class="divider"></li>
                                                <li><a href="/viewObjectType?id=${list.objectTypeId}">Type tree</a></li>
                                            </ul>
                                        </li>
                                    </ul>
                                </div>
                            </div>
                        </c:if>
                        <c:if test="${list.objectTypeId == 7}">
                            <div align="left">
                                <ol class="breadcrumb">
                                    <li><a href="#">Entity</a></li>
                                    <li><a href="#">Template</a></li>
                                    <li class="active">Task</li>
                                </ol>
                            </div>
                            <div align="left">
                                <h3>Action</h3>
                            </div>
                            <div>
                                <div>
                                    <ul class="nav nav-pills">
                                        <li class="dropdown">
                                            <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                                                Action list <b class="caret"></b></a>
                                            <ul class="dropdown-menu">
                                                <li><a href="/newTask/${list.objectId}">Add next Task</a></li>
                                                <li class="divider"></li>
                                                <li><a href="/viewObjectType?id=${list.objectTypeId}">Type tree</a></li>
                                            </ul>
                                        </li>
                                    </ul>
                                </div>
                            </div>
                        </c:if>
                        <c:if test="${list.objectTypeId == 2}">
                            <div align="left">
                                <ol class="breadcrumb">
                                    <li><a href="#">Entity</a></li>
                                    <li class="active">Root</li>
                                </ol>
                            </div>
                            <div align="left">
                                <h3>Action</h3>
                            </div>
                            <div>
                                <div>
                                    <ul class="nav nav-pills">
                                        <li class="dropdown">
                                            <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                                                Action list <b class="caret"></b></a>
                                            <ul class="dropdown-menu">
                                                <li><a href="#">Nothing</a></li>
                                                <li class="divider"></li>
                                                <li><a href="/viewObjectType?id=${list.objectTypeId}">Type tree</a></li>
                                            </ul>
                                        </li>
                                    </ul>
                                </div>
                            </div>
                        </c:if>
                    </c:forEach>
                    <br>
                </div>
                <div align="left">
                    <h3>Parameters</h3>
                </div>
                <table class="table table-hover table-condensed">
                    <tbody>
                    <c:forEach var="list" items="${listParams}" varStatus="status">
                        <h5><a class="glyphicon glyphicon-pencil" href=edit?objectId=${list.objectId}>Edit</a></h5>
                        <c:if test="${list.objectTypeId == 2}">
                            <tr>
                                <td>Cost</td>
                                <td>${list.cost}</td>
                            </tr>
                            <tr>
                                <td>Name</td>
                                <td>${list.nameRoot}</td>
                            </tr>
                        </c:if>
                        <c:if test="${list.objectTypeId == 3}">
                            <tr>
                                <td>Address</td>
                                <td>${list.address}</td>
                            </tr>
                            <tr>
                                <td>Telephone</td>
                                <td>${list.telephone}</td>
                            </tr>
                            <tr>
                                <td>Name</td>
                                <td>${list.nameCust}</td>
                            </tr>
                        </c:if>
                        <c:if test="${list.objectTypeId == 4}">
                            <tr>
                                <td>Mark</td>
                                <td>${list.mark}</td>
                            </tr>
                            <tr>
                                <td>Price</td>
                                <td>${list.price}</td>
                            </tr>
                        </c:if>
                        <c:if test="${list.objectTypeId == 7}">
                            <tr>
                                <td>Статус</td>
                                <td>${list.statusTask}</td>
                            </tr>
                            <tr>
                                <td>Implementation</td>
                                <td>${list.implementation}</td>
                            </tr>
                            <tr>
                            <c:forEach var="task" items="${list.previousTasks}" varStatus="status">
                                <tr>
                                    <td> previousTasks </td>
                                    <td><a href="object?objectId=${task}">previousTask</a></td>
                                </tr>
                            </c:forEach>
                            </tr>
                        </c:if>
                    </c:forEach>
                    </tbody>
                </table>
                <div align="left">
                    <button class="btn btn-warning" type="submit"
                            onclick="history.back()"><span class="glyphicon glyphicon-chevron-left"></span>
                        Back
                    </button>
                </div>
            </div>
            <div class="col-md-6">
                <br><br><br><br><br><br><br><br>
                <c:forEach var="list" items="${listParams}" varStatus="status">
                <div align="left">
                    <h3> dependency </h3>
                </div>
                <table class="table table-hover table-condensed">
                    <tbody>
                    <c:if test="${list.objectTypeId == 3}">
                        <c:forEach var="phone" items="${list.phones}" varStatus="status">
                            <tr>
                                <td>Mobile phone <a href="delete?objectId=${phone}"><span
                                        class="glyphicon glyphicon-trash"></span></a></td>
                                <td><a href="object?objectId=${phone}">Phone</a></td>
                            </tr>
                        </c:forEach>
                    </c:if>
                    <c:if test="${list.objectTypeId == 7 }">
                        <c:forEach var="task" items="${list.previousTasks}" varStatus="status">
                            <tr>
                                <td>Task<br><a href="delete?objectId=${task}">Delete</a></td>
                                <td><a href="object?objectId=${task}">Task</a></td>
                            </tr>
                        </c:forEach>
                    </c:if>

                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>
</body>
</html>
