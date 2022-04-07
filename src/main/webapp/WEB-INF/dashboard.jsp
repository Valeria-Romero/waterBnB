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

	<form action="/logout" method="GET" >
		<button type="submit">
			Logout
		</button>
	</form>

	<h1>Hello ${currentUser} this are your current listings</h1>
	
	<form action="/add/listing">
		<label for="Address">Address</label>
		<input type="text" name="address">
		
		<label for="description">Description</label>
		<textarea rows="10" cols="30" name="description"></textarea>
		
		<label for="cost">Cost</label>
		<input type="number" name="cost">
		
		<select name="poolsize">
			<option value="small">Small</option>
			<option value="medium">Medium</option>
			<option value="large">Large</option>
		</select>
		
		<button type="submit">Add listing</button>
	</form>
</body>
</html>