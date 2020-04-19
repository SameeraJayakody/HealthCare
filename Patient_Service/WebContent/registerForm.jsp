<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Hospital Management  System</title>
</head>
<body>
<form method="post" action="PatientService/Patients/insert">   
	First name: <input name="pFname" id="pFname" type="text"><br> 
	Last name: <input name="pLname" id="pFname"><br> 
	Age:   <input name="pAge" id="pAge" type="text"><br>
	Gender:<input name="pGender" id="pGender" type="text"><br> 
	Address: <input name="pAddress" id="pAddress" type="text"><br> 
	ContactNo: <input name="pContactNo" id="pContactNo" type="text"><br> 
	NIC: <input name="pNIC" id="pNIC" type="text"><br> 
	Email:   <input name="pEmail" name="pEmail" type="text"><br>
	User name:<input name="pUsername" name="pUsername" type="text"><br> 
	Password:<input name="pPassword" name="pPassword" type="text"><br> 
	<input  name="btnSubmit" type="submit" value="REGISTER">  
</form> 
 


</body>
</html>