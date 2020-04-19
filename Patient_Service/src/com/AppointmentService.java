package com;



import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import model.Appointment;

/**AppointmentService class.
 * @author Sachini
 *
 */
@Path("/Appointments")
public class AppointmentService {

	//Appointment Object
	Appointment appointObj = new Appointment();

	/*----------------------insert appointment------------------*/
	@POST
	@Path("/insert")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_HTML)
	public String insertAppointment(@QueryParam("docID") String docID,
			@QueryParam("patientID") String patientID,
			@FormParam("date") String sessionDate,
			@FormParam("time") String sessionTime) {
		String output = appointObj.insertAppointment(docID, patientID, sessionDate, sessionTime);

		return output;
	}



	/*---------display appointments of logged in patient-----*/
	//"status" value passed as a query parameter decides which appointments to display(booked/cancelled)
	@GET
	@Path("/displayAppointments")
	@Produces(MediaType.TEXT_HTML)
	public String readAppointments(@QueryParam("patientID") String patientID, @QueryParam("status") String status) {
		return appointObj.readAppointments(patientID, status);
		// return appointObj.testRead();
	}



	/*-------------------search doctor by name & specialization-----------------------*/
	@GET
	@Path("/search")
	@Produces(MediaType.TEXT_HTML)
	public String getUsers(@QueryParam("dname") String docName, @QueryParam("specialization") String specialization) {
		return appointObj.searchDoctor(docName, specialization);

	}



	
	/*-----------update appointment(change status to cancelled)-------*/
	@PUT
	@Path("/updateStatus")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updatePatient(String appointData) {
		// Convert the input string to a JSON object
		JsonObject appObject = new JsonParser().parse(appointData).getAsJsonObject();

		// Read the values from the JSON object
		String aID = appObject.get("aID").getAsString();
		
		String output = appointObj.updateStatus(aID);
				
		return output;
	}

}
