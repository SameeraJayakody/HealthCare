<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Search Hospital</title>
</head>
<body>
	<form method="get"
		action="http://localhost:8080/HospitalManagement/HospitalService/Hospitals/search?name=<%=request.getParameter("name")%>&city=<%=request.getParameter("city")%>">
		Hospital Name : <input name="name" id="name" type="text"><br>
		City : <input name="city" id="city"><br>

		<input name="btnSubmit" type="submit" value="Search">
	</form>
</body>
</html>