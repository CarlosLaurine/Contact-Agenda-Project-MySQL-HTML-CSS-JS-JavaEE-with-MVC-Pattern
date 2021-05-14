<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
<title>Contact Agenda</title>
<meta charset="utf-8">
<link rel="icon" href="Imgs/phone-icon.png">
<link rel="stylesheet" href="style.css">
</head>
<body>
	
	<h1> Edit Contact </h1>
	
	<!--Setting action to be catch at Controller's urlPatterns-->
	
	<form name = "formContact" action="update" >
		<table>
		
		<!-- Using "readonly" inside id textbox tag in order to disable Id editing by the user (only read) -->
			<tr>
				<td><input type="text" name="id" id="box3" readonly value="<%out.print(request.getAttribute("id"));%>"></td>
			</tr>
			<tr>
				<td><input type="text" name="name" class="box1" value="<%out.print(request.getAttribute("name"));%>"></td>
			</tr>
			<tr>
				<td><input type="text" name="phone" class="box2" value="<%out.print(request.getAttribute("phone"));%>"></td>
			</tr>
			<tr>
				<td><input type="text" name="email" class="box1" value="<%out.print(request.getAttribute("email"));%>"></td>	
			</tr>
		
		</table>
	
		<input type="button" value="Save" class="button1" onclick="validate()">
		
	</form>


<script src="scripts/validator.js"></script>
</body>
</html>