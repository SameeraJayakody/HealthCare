
<%@page import="model.schedule"%>
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
 schedule itemObj1 = new schedule();
out.print(itemObj1.DisplayPatients());
%> 


<%-- <%
 schedule itemObj2 = new schedule();
out.print(itemObj2.Test());
%>  --%>


</body>
</html>