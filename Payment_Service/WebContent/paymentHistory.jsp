<%@ page import="model.Payment"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="Views/bootstrap.min.css">
<title>Payment History</title>
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col">
				<h1>Payment History</h1>
				
				<%
					Payment paymentObj = new Payment();
				out.print(paymentObj.readPayment());
				%>
			</div>
		</div>
	</div>
</body>
</html>