<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="model.Appointment"%>
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
	<%=request.getAttribute("pID")%>
	<div style="text-align: right">
	</div>
		
	<form method="post" action="search.jsp">
		<input name="btnSubmit" type="submit" value="Add Appointment">
	</form>

	<form method="post"
		action="http://localhost:8080/Patient_Service/PatientService/Patients?displayProfile?pID<%=request.getAttribute("pID")%>">
		<input name="btnSubmit" type="submit" value="My Profile">
	</form>
	<a href="http://localhost:8080/Patient_Service/AppointmentService/Appointments/update">Cancel</a></td>
	<a href="http://localhost:8080/Patient_Service/AppointmentService/Appointments/displayAppointments?pID=<%=request.getAttribute("pID")%>&status=1">View Booked Appointments</a>
	<a href="http://localhost:8080/Patient_Service/AppointmentService/Appointments/displayAppointments?pID=<%=request.getAttribute("pID")%>&status=0">View Cancelled Appointments</a>

		
</body>
</html>