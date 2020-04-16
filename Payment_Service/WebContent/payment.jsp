<%@ page import="model.Payment"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%
	//Insert payment----------------------------------
if (request.getParameter("amount") != null) {
	Payment paymentObj = new Payment();
	String stsMsg = paymentObj.insertPayment(request.getParameter("appointmentId"), request.getParameter("paymentType"));
	session.setAttribute("statusMsg", stsMsg);
}

//Delete item----------------------------------
if (request.getParameter("paymentNo") != null) {
	Payment paymentObj = new Payment();
	String stsMsg = paymentObj.deletePayment(request.getParameter("paymentNo"));
	session.setAttribute("statusMsg", stsMsg);
}
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="Views/bootstrap.min.css">
<title>Payment Service</title>
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col">
				<h1>Make Payment</h1>
				<form method="post" action="payment.jsp">
				
					Appointment No: <input name="appointmentId" type="text" class="form-control"><br>

					Payment Type:<br>
					<div class="form-check form-check-inline">
						<input class="form-check-input" type="radio"
							name="paymentType" id="inlineRadio1" value="Credit Card">
						<label class="form-check-label" for="inlineRadio1">Credit Card</label>
					</div>
					<div class="form-check form-check-inline">
						<input class="form-check-input" type="radio"
							name="paymentType" id="inlineRadio2" value="Debit Card">
						<label class="form-check-label" for="inlineRadio2">Debit Card</label>
					</div>
					<br></br>

					<br> <input name="btnSubmit" type="submit" value="Proceed Payment"
						class="btn btn-primary">
						
				</form>
				<br>
				<div class="alert alert-success">
					<%
						out.print(session.getAttribute("statusMsg"));
					%>
				</div>
				<br>
				<%
					Payment paymentObj = new Payment();
				out.print(paymentObj.readPayment());
				%>
			</div>
		</div>
	</div>
</body>
</html>