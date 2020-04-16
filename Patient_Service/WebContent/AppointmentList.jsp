<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="model.AppointmentDAO"%>
<%@ page import="model.Appointment"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.List"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>My Appointment</title>
</head>
<%
	//In case if admin session is not set,redirect to Login Page
	if ((request.getSession(false).getAttribute("Patient") == null)) {
%>
<jsp:forward page="Login.jsp"></jsp:forward>
<%
	}
%>
<body>
	<center>
		<h2>Patient's Home</h2>
	</center>
	Welcome
	<%=request.getAttribute("userName")%>
	<div style="text-align: right">
	</div>
		
	<form method="post" action="registerForm.jsp">
		<input name="btnSubmit" type="submit" value="Add Appointment">
	</form>

	<form method="post"
		action="http://localhost:8080/HospitalMSystem/PatientService/Patients">
		<input name="btnSubmit" type="submit" value="My Profile">
	</form>
	<%
		//request.setAttribute("listStock", AppointmentDAO.testRead());
		AppointmentDAO a1 = new AppointmentDAO();

		List<Appointment> a = a1.testRead();
		request.setAttribute("a", a);
	%>
	<table border="1" cellpadding="5">
		<caption>
			<h2>List of Appointments</h2>
		</caption>
		<tr>
			<th>AppointmentNo</th>
			<th>SessionDate</th>
			<th>SessionTime</th>
			<th>status</th>
			<th>paymentStatus</th>


		</tr>
		<c:forEach var="appoint" items="${a}">
			<tr>

				<td><c:out value="${appoint.appointmentNo}" /></td>
				<td><c:out value="${appoint.sessionDate}" /></td>
				<td><c:out value="${appoint.sessionTime}" /></td>
				<td><c:out value="${appoint.status}" /></td>
				<td><c:out value="${appoint.paymentStatus}" /></td>
				<td><a
					href="http://localhost:8080/HospitalMSystem/AppointmentStatus/Appointments/update">Cancel</a></td>


			</tr>
		</c:forEach>
	</table>
</body>
</html>