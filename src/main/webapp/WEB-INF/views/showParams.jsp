<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<style>
    <%@include file="/resources/bootstrap.css"%>
</style>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Свойства</title>
</head>
<body>
<div align="center">
    <h1>Свойства</h1>

    <table border="10" class="table table-bordered table-responsive table-condensed table-hover">

        <th>Название</th>
        <th>Значение</th>

        <c:forEach var="entity" items="${list}" varStatus="status">
            <tr>
                <td>${entity.name}</td>
                <td>${entity.value}</td>
            </tr>
        </c:forEach>
    </table>
    <button class="btn btn-default" type="submit" onclick="history.back()">Назад</button>
</div>
</body>
</html>