
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


<h1>Doctor Message</h1>
   <%
 item itemObj2 = new item();
out.print(itemObj2.readRequest());
%>

</body>
</html>