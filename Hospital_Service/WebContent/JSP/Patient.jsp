<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<title>Patient Page</title>
</head>
<%
	//In case, if Admin session is not set, redirect to Login page
	if ((request.getSession(false).getAttribute("Patient") == null)) {
%>
<jsp:forward page="/JSP/Login.jsp"></jsp:forward>
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
		<a href="<%=request.getContextPath()%>/LogoutServlet">Logout</a>
	</div>
</body>
</html>