
<%@page import="model.schedule"%>
<%@page import="database.dbconnect"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>



 <%
if (request.getParameter("si") != null)
{
session.setAttribute("id", request.getParameter("id"));
session.setAttribute("stat", request.getParameter("stat"));
}
    
%> 

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>


<h1>Search by doctors</h1>



<form method="post" action="Search.jsp" align="center">
 Doctor ID:<input name="id" type="text" ><br> 
Doctor Name: <input name="stat" type="text" ><br> 
<input name="btnSubmit" type="submit" value="Save"> 
</form>






<% 


schedule itemObj3 = new schedule();
out.print(itemObj3.docSearch(
		request.getParameter("stat"),request.getParameter("id")));


%>


</body>
</html>