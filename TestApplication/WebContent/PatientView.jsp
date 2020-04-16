
<%@page import="model.item"%>
<%@page import="database.dbconnect"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<h1>Patients View</h1>


<%
 item itemObj1 = new item();
out.print(itemObj1.DisplayPatients());
%> 


</body>
</html>