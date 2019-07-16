<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
    <title>Новый сотрудник</title>
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
                <li class="active"><a href="/">Главная</a></li>
                <li class="dropdown">
                    <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                        Добавить<b class="caret"></b>
                    </a>
                    <ul class="dropdown-menu">
                        <li><a href="#">Телефон</a></li>
                        <li><a href="#">Машина</a></li>
                    </ul>
            </ul>
        </div>
    </div>
</nav>
<form class="form col-md-4" action="saveCustomer" method="post">
    <h2>Новый Customer</h2>
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
    <td><input type="submit" class="btn btn-default" value="Сохранить">
    <td>
        <button class="btn btn-default" type="submit" onclick="history.back()">Назад</button>

</form>

</body>
</html>