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
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Редактировать</title>
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

<script src="https://code.jquery.com/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>

<body>

<nav class="navbar navbar-inverse">
    <div class="container-fluid">
        <div class="navbar-header">
            <a href="#" class="navbar-brand">Netcracker</a>
        </div>
        <div>
            <ul class="nav navbar-nav nav-tabs">
                <li class="active"><a href="/">Home</a></li>
            </ul>
        </div>
    </div>
</nav>

<c:set var="editRoot" scope="page" value="/saveEditRoot"></c:set>
<c:set var="editCust" scope="page" value="/saveEditCust"></c:set>
<c:set var="editPhone" scope="page" value="/saveEditPhone"></c:set>
<c:forEach var="list" items="${list}" varStatus="status">
    <c:if test="${list.objectTypeId == 2}">
        <c:set var="edit" scope="page" value="${editRoot}"></c:set>
    </c:if>
    <c:if test="${list.objectTypeId == 3}">
        <c:set var="edit" scope="page" value="${editCust}"></c:set>
    </c:if>
    <c:if test="${list.objectTypeId == 4}">
        <c:set var="edit" scope="page" value="${editPhone}"></c:set>
    </c:if>
</c:forEach>
<form class="form col-md-4" action="${edit}" method="post" modelAttribute="entityById">
    <h3>Edit entity</h3>
    <div class="form-group">
        <c:forEach var="obj" items="${list}" varStatus="status">
            <input class="form-control" type="hidden" name="objectId" value="${obj.objectId}">
            <input class="form-control" type="hidden" name="parentId" value="${obj.parentId}">
            <input class="form-control" type="hidden" name="objectTypeId" value="${obj.objectTypeId}">
            <input class="form-control" type="hidden" name="name" value="${obj.name}">
            <c:if test="${obj.objectTypeId == 2}">
                <div class="form-group">
                    <input class="form-control" type="text" name="nameRoot"
                           placeholder="nameRoot">
                </div>
                <div class="form-group">
                    <input class="form-control" type="text" name="cost"
                           placeholder="cost">
                </div>
            </c:if>
            <c:if test="${obj.objectTypeId == 3}">
                <div class="form-group">
                    <input class="form-control" type="text" name="nameCust"
                           placeholder="nameCust">
                </div>
                <div class="form-group">
                    <input class="form-control" type="text" name="address"
                           placeholder="address">
                </div>
                <div class="form-group">
                    <input class="form-control" type="text" name="telephone"
                           placeholder="mobile phone">
                </div>
            </c:if>
            <c:if test="${obj.objectTypeId == 4}">
                <div class="form-group">
                    <input class="form-control" type="text" name="mark"
                           placeholder="mark">
                </div>
                <div class="form-group">
                    <input class="form-control" type="text" name="price"
                           placeholder="price">
                </div>
            </c:if>
        </c:forEach>
    </div>
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
