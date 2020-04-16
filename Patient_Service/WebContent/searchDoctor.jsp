<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Search Doctor</title>
</head>
<body>

	<form method="get"
		action="http://localhost:8080/HospitalMSystem/AppointmentService/Appointments/search?dname=<%=request.getParameter("dname")%>&specialization=<%=request.getParameter("specialization")%>">
		Doctor Name : <input name="dname" id="dname" type="text"><br>
		Doctor Specialty : <input name="specialization" id="specialization"><br>

		<input name="btnSubmit" type="submit" value="Search">
	</form>
</body>
</html>