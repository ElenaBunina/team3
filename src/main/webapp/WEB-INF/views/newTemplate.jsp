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

<form class="form col-md-4" action="saveTemplate" method="post">

    <div align="left">
        <ol class="breadcrumb">
            <li><a href="#">Entity</a></li>
            <li><a href="active">Template</a></li>
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
                        <li><a href="/newTask/">Add First Task</a></li>
                    </ul>
                </li>
            </ul>
        </div>
    </div>

    <div class="col-md-10">
        <div align="left">
            <div class="row">
                <div class="container-fluid">
                    <h3>List Task</h3>
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

        <div align="center">
            <button class="btn btn-warning" type="submit"
                    onclick="history.back()"><span class="glyphicon glyphicon-chevron-left"></span>
                Back
            </button>
        </div>

    </div>


</form>
</body>
</html>



