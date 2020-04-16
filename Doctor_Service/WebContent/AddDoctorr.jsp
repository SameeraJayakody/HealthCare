<%@page import="model.Doctor" %>
<%@page import="database.dbconnect" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    
<%
if (request.getParameter("ddhid") != null)
{
  session.setAttribute("ddhid", request.getParameter("ddhid"));
  session.setAttribute("ddname", request.getParameter("ddname"));
  session.setAttribute("ddemail", request.getParameter("ddemail"));
  session.setAttribute("ddaddress", request.getParameter("ddaddress"));
  session.setAttribute("ddspecial", request.getParameter("ddspecial"));
  session.setAttribute("ddwtime", request.getParameter("ddwtime"));
  session.setAttribute("ddwdays", request.getParameter("ddwdays"));
  session.setAttribute("ddwhos", request.getParameter("ddwhos"));
  session.setAttribute("ddfee", request.getParameter("ddfee"));
  session.setAttribute("ddun", request.getParameter("ddun"));
  session.setAttribute("ddpw", request.getParameter("ddpw"));
}
    
%>        
    
    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>


<h1>Add Doctors</h1>

<form method="post" action="AddDoctor.jsp" align="center">
hospital ID: <input name="ddhid" type="text" ><br>
Full Name: <input name="ddname" type="text" ><br>
Email: <input name="ddemail" type="text" ><br>
Address: <input name="ddaddress" type="text" ><br>
Specialization: <input name="ddspecial" type="text" ><br>
Working Time: <input name="ddwtime" type="text" ><br>
Working Days: <input name="ddwdays" type="text" ><br>
Working Hospital: <input name="ddwhos" type="text" ><br>
Doctor Fee: <input name="ddfee" type=text ><br>
Username: <input name="ddun" type="text" ><br>
Password: <input name="ddpw" type="text" ><br>
<br>
<input name="btnSubmit" type="submit" value="Add Doctor"> 
</form>


<br><br>

<table border="1" align="center">
<tr>
<th>hospital ID</th>
<th>Full Name</th>
<th>Email</th>
<th>Address</th>
<th>Specialization</th>
<th>Working Time</th>
<th>Working Days</th>
<th>Working Hospital</th>
<th>Doctor Fee</th>
<th>Username</th>
<th>Password</th>
<th>Update</th>
<th>Delete</th>
</tr>
<tr>
<td><%out.print(session.getAttribute("ddhid")); %></td>
<td><%out.print(session.getAttribute("ddname")); %></td>
<td><%out.print(session.getAttribute("ddemail")); %></td>
<td><%out.print(session.getAttribute("ddaddress")); %></td>
<td><%out.print(session.getAttribute("ddspecial")); %></td>
<td><%out.print(session.getAttribute("ddwtime")); %></td>
<td><%out.print(session.getAttribute("ddwdays")); %></td>
<td><%out.print(session.getAttribute("ddwhos")); %></td>
<td><%out.print(session.getAttribute("ddfee")); %></td>
<td><%out.print(session.getAttribute("ddun")); %></td>
<td><%out.print(session.getAttribute("ddpw")); %></td>
<td><input name="btnUpdate" type="button" value="Update"></td>
<td><input name="btnRemove" type="button" value="Delete"></td>
</tr>
</table>  


<%   
    if (request.getParameter("ddhid") != null)
    {
      dbconnect Object = new dbconnect();
      Object.connect(); //For testing the connect method
    }
%>



<% 
if (request.getParameter("ddhid") != null)
{
  Doctor docObj = new Doctor();
  String Msg = docObj.insertDoctors(
  request.getParameter("ddhid"),
  request.getParameter("ddname"),
  request.getParameter("ddemail"),
  request.getParameter("ddaddress"),
  request.getParameter("ddspecial"),
  request.getParameter("ddwtime"),
  request.getParameter("ddwdays"),
  request.getParameter("ddwhos"),
  request.getParameter("ddfee"),
  request.getParameter("ddun"),
  request.getParameter("ddpw"));
  session.setAttribute("statusMsg", Msg);
}
%> 



<%
   Doctor docObj1 = new Doctor();
   out.print(docObj1.readDoctors());
%>  



<% 
if (request.getParameter("docID") != null)
{
	Doctor docObj = new Doctor();
	String Msg = docObj.deleteDoctor(request.getParameter("docID"));
	session.setAttribute("Message", Msg);
}
%> 



</body>
</html>