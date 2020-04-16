<%@ page import="model.Card"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%
	//Insert card----------------------------------
if (request.getParameter("cardNo") != null) {
	Card cardObj = new Card();
	String stsMsg = cardObj.insertCard(request.getParameter("cardNo"), request.getParameter("holderName"),
	request.getParameter("expiryDate"), request.getParameter("cvv"), request.getParameter("paymentNo"));
	session.setAttribute("statusMsg", stsMsg);
}

//Delete card----------------------------------
//if (request.getParameter("cardNo") != null) {
	//Card cardObj = new Card();
	//String stsMsg = cardObj.deleteCard(request.getParameter("cardNo"));
	//session.setAttribute("statusMsg", stsMsg);
//}

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
				<form method="post" action="card.jsp">
				
					Payment No: <input name="paymentNo" type="text" class="form-control"><br> 

					Card No: <input name="cardNo" type="text" class="form-control"><br> 
					
					Card Holder Name: <input name="holderName" type="text" class="form-control"><br>
					
					Expire Date: <input name="expiryDate" type="date" class="form-control"><br> 
					
					CVV: <input name="cvv" type="number" class="form-control"><br> <br>
					 
					<input name="btnSubmit" type="submit" value="Save" class="btn btn-primary">

				</form>
				<div class="alert alert-success">
					<%
						out.print(session.getAttribute("statusMsg"));
					%>
				</div>
				<br>
				<%
					Card cardObj = new Card();
				out.print(cardObj.readCard());
				%>
			</div>
		</div>
	</div>
</body>
</html>