<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">

<style>
    <%@include file="/resources/bootstrap.css"%>
    body {
        background: #708C7F
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

<script src="https://code.jquery.com/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>

<body>
<ul class="nav nav-tabs">
    <li class="active"><a href="/newCustomerWithPhone">Новый сотрудник</a></li>
</ul>
<form class="form col-md-4" action="saveCustomerWithPhone" method="post">
    <h2>Новый сотрудник</h2>
    <div class="form-group" modelAttribute="customer">
        <input class="form-control" type="text" name="nameCust"
               placeholder="имя">
    </div>
    <div class="form-group" modelAttribute="customer">
        <input class="form-control" type="text" name="address"
               placeholder="адрес">
    </div>
    <div class="form-group" modelAttribute="customer">
        <input class="form-control" type="text" name="telephone"
               placeholder="телефон">
    </div>
    <div class="form-group" modelAttribute="phone">
        <input class="form-control" type="text" name="mark"
               placeholder="марка">
    </div>
    <div class="form-group" modelAttribute="phone">
        <input class="form-control" type="text" name="price"
               placeholder="цена">
    </div>
    <td><input type="submit" class="btn btn-default" value="Save">
    <td>
        <button class="btn btn-default" type="submit" onclick="history.back()">Назад</button>

</form>

</body>
</html>
