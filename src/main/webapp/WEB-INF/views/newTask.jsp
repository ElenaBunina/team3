<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">

<style>
    <%@include file="/resources/bootstrap.css"%>
    body {
        background: #ffffff
    }
</style>

<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Новый объект</title>
</head>

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

<!-- Latest compiled and minified JavaScript -->
<script src="https://code.jquery.com/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>

<body>
<ul class="nav nav-tabs">
    <li class="active"><a href="#">New task</a></li>
</ul>

<c:if test="${entity.previousTask == 0}">
<form class="form col-md-4" action="/saveTask" method="post">

    </c:if>
    <c:if test="${entity.previousTask != 0}">
    <form class="form col-md-4" action="/saveTask/${entity.previousTask}" method="post">
        </c:if>
        <h2>Новый Task</h2>


        <c:forEach var="entity" items="${listEntity}">
            <div class="checkbox">
                <label> <input type="checkbox" modelAttribute="task" name="previousTask"
                           value="${entity.objectId}" > ${entity.objectId}</label>
            </div>

        </c:forEach>

        <div class="form-group" modelAttribute="task">
            <input class="form-control" type="text" name="implementation"
                   placeholder="implementation">
        </div>

        <select class="form-control input-lg" type="text" modelAttribute="task" name="statusTask">
            <option>Выберите статус</option>
            <option>accepted</option>
            <option>completed</option>
            <option>canceled</option>
        </select>
        <div align="center">
            <input type="submit" class="btn btn-success" value="Save">
            <button class="btn btn-warning" type="submit"
                    onclick="history.back()"><span class="glyphicon glyphicon-chevron-left"></span>
                Back
            </button>
        </div>
    </form>
</body>
</html>
