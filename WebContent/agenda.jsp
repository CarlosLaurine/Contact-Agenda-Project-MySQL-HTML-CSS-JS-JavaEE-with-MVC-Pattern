<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<%@ page import="model.JavaBeans"%>
<%@ page import="java.util.ArrayList"%>


<!-- CREATING DYNAMIC ARRAY TO RECEIVE AND PROCESS ATTRIBUTE "contacts"-->
<%
@SuppressWarnings(value = "unchecked")
ArrayList<JavaBeans> contactsList = (ArrayList<JavaBeans>) request.getAttribute("contacts");

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
	
	<br/>
	<h1 style="text-align: center" style="margin-top: 2em; ">Contact Agenda</h1>

	<div style="padding-left: 27.5%; margin: 3em auto 5em auto;">
	<table id="table" style="min-width: 50%">

		<thead>
			<tr>
				<th>(ID)</th>
				<th>(NAME)</th>
				<th>(PHONE)</th>
				<th>(E-MAIL)</th>
				<!-- Adding Options Header -->
				<th>(OPTIONS)</th>
			</tr>
		</thead>

		<tbody>

			<%
			for (JavaBeans contact : contactsList) {
			%>

			<!-- Generating Dynamic HTML Table with contactsList Content -->

			<tr>
				<td class="id"><%=contact.getConId()%></td>
				<td><%=contact.getConName()%></td>
				<td><%=contact.getConPhone()%></td>
				<td><%=contact.getConEmail()%></td>

				<!-- Adding Edit/Delete Buttons to Each Contact Row -->
				<td><a href="select?conId=<%=contact.getConId()%>"
					class="button1"> Edit </a> <a
					href="javascript: confirmContact(<%=contact.getConId()%>)"
					class="button2"> Delete </a></td>
			</tr>

			<%
			}
			%>

		</tbody>

	</table>
	
	<br/><br/>
	
	<a href="new.html" class="button1" style="margin-left: 26%; " >New Contact</a>
	<br/><br/>
	<a href="report" class="button2" style="margin-left: 24.67%; ">Generate Report</a>
	
	</div>

	<script src="scripts/confirmator.js"></script>

</body>
</html>