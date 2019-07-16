<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page session="true" %>
<%@page pageEncoding="UTF-8" %>
<%request.setCharacterEncoding("UTF-8");%>

<html>
<head>
    <style>
        <%@include file="/resources/bootstrap.css"%>
        body {
            background: #ffffff
        }
    </style>
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

<body onload='document.loginForm.username.focus();'>
<div id="login-box">
    <div class="container">
        <div class="row">
            <div class="col-md-3"></div>
            <div class="col-md-6">
                <form method="POST" action="${contextPath} login" class="form-signin">
                    <br><br><br><br><br><br><br><br>
                    <h2 class="form-heading" align="center">Авторизация</h2>
                    <div class="form-group ${error != null ? 'has-error' : ''}">
                        <span>${message}</span>
                        <input name="username" type="text" class="form-control" placeholder="Имя пользователя" autofocus="true"/>
                        <input name="password" type="password" class="form-control" placeholder="Пароль"/>
                        <span>${error}</span>
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                        <button class="btn btn-md btn-primary btn-block" type="submit">Войти</button>
                        <a href="${contextPath}#" class="btn btn-md btn-success btn-block">Создать аккаунт</a>
                    </div>
                </form>
            </div>
            <div class="col-md-3"></div>
        </div>
    </div>
</div>
</body>
</html>