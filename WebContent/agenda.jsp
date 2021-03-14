<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>

<%@ page import = "model.JavaBeans"    %>
<%@ page import = "java.util.ArrayList"%>


<!-- CREATING DYNAMIC ARRAY TO RECEIVE AND PROCESS ATTRIBUTE "contacts"-->
<%
	ArrayList <JavaBeans> contactsList = (ArrayList <JavaBeans>) request.getAttribute("contacts");
	
	/* Test for List Receipt Confirmation
	
	for(JavaBeans contact: contactsList){
		out.println(contact.getConId());
		out.println(contact.getConName());
		out.println(contact.getConPhone());
		out.println(contact.getConEmail());
	}
	*/

%>

<!DOCTYPE html>
<html>
<head>
<title>Contact Agenda</title>
<meta charset="utf-8">
<link rel="icon" href="Imgs/phone-icon.png">
<link rel="stylesheet" href="style.css">


</head>
<body>

	<h1>Contact Agenda</h1>
	<a href="new.html" class="button1">New Contact</a>
	
	<table id="table">
	
	<thead>
		<tr>
			<th>(ID)</th>
			<th>(NAME)</th>
			<th>(PHONE)</th>
			<th>(E-MAIL)</th>
			
		</tr>	
	</thead>
	
	<tbody>
	
		<%for(JavaBeans contact : contactsList) { %>
		
		<!-- Generating Dynamic HTML Table with contactsList Content -->
		
		<tr>
			<td class="id"><%= contact.getConId() %></td>	
			<td><%= contact.getConName() %></td>	
			<td><%= contact.getConPhone() %></td>	
			<td><%= contact.getConEmail() %></td>	
			
		</tr>
		
		<%} %>
	
	</tbody>
	
	
	
	
	</table>

</body>
</html>