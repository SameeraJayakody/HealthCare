<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="model.Hospital"%>

<%
	//insert hospital
	if (request.getParameter("name") != null) {
		Hospital hospObj = new Hospital();

		String stsMsg = hospObj.insertHospital(request.getParameter("name"), request.getParameter("type"),
				request.getParameter("description"), request.getParameter("phoneNo"),
				request.getParameter("email"), request.getParameter("code"), request.getParameter("city"),
				request.getParameter("state"), request.getParameter("hospitalFee"));

		session.setAttribute("statusMsg", stsMsg);
		//itemObj.connect();//For testing the connect method
		/*session.setAttribute("itemCode", request.getParameter("itemCode"));
		session.setAttribute("itemName", request.getParameter("itemName"));
		session.setAttribute("itemPrice", request.getParameter("itemPrice"));
		session.setAttribute("itemDesc", request.getParameter("itemDesc"));*/
	}

	//delete hospital
	if (request.getParameter("hospitalID") != null)
	{
		Hospital hospObj = new Hospital();
		String stsMsg = hospObj.deleteHospital(request.getParameter("hospitalID"));
		session.setAttribute("statusMsg", stsMsg);
	} 
	
	//update hospital
	
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Hospital Management System</title>
</head>
<body>
	<h1>Hospital Management</h1>
	<form method="post" action="hospitalReg.jsp">
		Hospital name: <input name="name" type="text"><br> 
		Hospital Type: <input name="type" type="text"><br> 
		Hospital description: <input name="description" type="text"><br> 
		Phone Number: <input name="phoneNo" type="text"><br> 
		Email: <input name="email" type="text"><br> 
		Address Code: <input name="code" type="text"><br> 
		City: <input name="city" type="text"><br> 
		State: <input name="state" type="text"><br> 
		Hospital Fee: <input name="hospitalFee" type="text"><br> 
		
		<input name="btnSubmit" type="submit" value="Save">
	</form>
	<%
		out.print(session.getAttribute("statusMsg"));
	%>
	<br>
	<%
		Hospital hospObj = new Hospital();
		out.print(hospObj.readItems());
	%>

</body>
</html>