<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Water BnB</title>
</head>
<body>
	<div style="display:flex; justify-content:right">
		<a href="/guest/signin">SignIn/SignUp</a>
	</div>
	<div style="margin-left:30%; margin-right:30%; margin-top:80px">
		<h1>Find places to swim and sleep on Water BnB!</h1>
		<form method="POST" action="/search">
			<input type="text" id="searchvalue" name="searchvalue" />
			<button type="submit">
				Search
			</button>
		</form>
	</div>
</body>
</html>