package com;

import model.Payment;

//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;

@Path("/Payments")
public class paymentService {
	Payment paymentObj = new Payment();

	//for payments..............
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readPayment() {
		return paymentObj.readPayment();
	}

	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertPayment(  @FormParam("patientId") String patientId ,@FormParam("appointmentId") String appointmentId , @FormParam("paymentType") String paymentType) {
		String output = paymentObj.insertPayment(patientId,appointmentId,paymentType);
		return output;
	}

	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deletePayment(String paymentData) {
		// Convert the input string to an XML document
		Document doc = Jsoup.parse(paymentData, "", Parser.xmlParser());
		// Read the value from the element <itemID>
		String paymentNo = doc.select("paymentNo").text();
		String output = paymentObj.deletePayment(paymentNo);
		return output;
	}

	
}
