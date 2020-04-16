package com;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.parser.Parser;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import model.Appointment;
import model.AppointmentDAO;

@Path("/Appointments")
public class AppointmentService {

	AppointmentDAO appointObj = new AppointmentDAO();

	/*-----------------------------insert appointment---------------------------------*/
	@POST
	@Path("/insert")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertAppointment(@FormParam("no") String appointmentNo, @FormParam("date") String sessionDate,
			@FormParam("time") String sessionTime) {
		String output = appointObj.insertAppointment(appointmentNo, sessionDate, sessionTime);

		return output;
	}
	

	/*-------------------display appointments of logged in patient------------------*/
	@GET
	@Path("/display")
	@Produces(MediaType.TEXT_HTML)
	public String readPatients() {
		return appointObj.readAppointments("1");
		// return appointObj.testRead();
	}


	@POST
	@Path("/search1")
	@Produces(MediaType.TEXT_HTML)
	public String searchDoctor(@FormParam("docName") String docName,

			@FormParam("specialization") String specialization) {
		return appointObj.searchDoctor(docName, specialization);
	}

	/*-------------------search doctor------------------*/
	@GET
	@Path("/search")
	@Produces(MediaType.TEXT_HTML)
	public String getUsers(@QueryParam("docName") String docName, @QueryParam("specialization") String specialization) {
		return appointObj.searchDoctor(docName, specialization);

	}

	/*-------------------update appointment(change status to cancelled)------------------*/
	@PUT
	@Path("/update")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateAppointment(String appointmentData) {
		// Convert the input string to a JSON object
		JsonObject apObject = new JsonParser().parse(appointmentData).getAsJsonObject();

		// Read the values from the JSON object
		String aID = apObject.get("aID").getAsString();

		String output = appointObj.updateAppointment(aID);

		return output;
	}

	/*-------------------delete appointment------------------*/
	@DELETE
	@Path("/delete")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteAppointment(String appointmentData) {
		// Convert the input string to an XML document
		Document doc = Jsoup.parse(appointmentData, "", Parser.xmlParser());
		// Read the value from the element <pID>
		String aID = doc.select("aID").text();

		String output = appointObj.deleteAppointment(aID);

		return output;
	}

}
