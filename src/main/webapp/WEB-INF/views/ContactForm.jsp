<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
	"http://www.w3.org/TR/html4/loose.dtd">
<link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
<title>New/Edit Contact</title>
</head>
<body>




		<c:if test="${type==1}">
			<h1>NEW ATTRIBUTE</h1>
			<form action="saveAttribute" method="GET" class="form-horizontal">
				<div class="form-group">
					<div class="col-xs-5" >
						<input type="text" class="form-control"  placeholder="Имя нового Атрибута" name="username">
					</div>
					<input type="hidden" name="ido" value="${id}">
					<input type="submit" class="btn btn-primary" value="Отправить">



				</div>
			</form>
		</c:if>
		<c:if test="${type==2}">
			<h1>NEW OBJECT_TYPE</h1>
		<form action="saveObjecType" method="GET" class="form-horizontal">
			<div class="form-group">
				<div class="col-xs-5" >
					<input type="text" class="form-control"  placeholder="Имя нового Типа" name="username">
				</div>
				<input type="hidden" name="objTypeId" value="${id}">
				<input type="submit" class="btn btn-primary" value="Отправить">

		</c:if>







</body>
</html>

