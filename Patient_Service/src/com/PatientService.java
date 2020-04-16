package com;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.parser.Parser;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import model.PatientDAO;

@Path("/Patients")
public class PatientService {

	PatientDAO patiObj = new PatientDAO();

	/*-------------------display profile of logged in user------------------*/
	@GET
	@Path("/displayProfile")
	@Produces(MediaType.TEXT_HTML)
	public String readPatients(@QueryParam("username") String username) {
		return patiObj.readPatients(username);
	}

	/*-----------------------------insert patient-------------------------*/
	@POST
	@Path("/insert")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertPatient(@FormParam("pFname") String pFname, @FormParam("pLname") String pLname,
			@FormParam("pAge") String pAge, @FormParam("pGender") String pGender,
			@FormParam("pAddress") String pAddress, @FormParam("pContactNo") String pContactNo,
			@FormParam("pNIC") String pNIC, @FormParam("pEmail") String pEmail,
			@FormParam("pUsername") String pUsername, @FormParam("pPassword") String pPassword) {
		String output = patiObj.insertPatient(pFname, pLname, pAge, pGender, pAddress, pContactNo, pNIC, pEmail,
				pUsername, pPassword);
		return output;
	}

	/*----------------update patient details in profile-----------------*/
	@PUT
	@Path("/update")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updatePatient(String patientData) {
		// Convert the input string to a JSON object
		JsonObject patientObject = new JsonParser().parse(patientData).getAsJsonObject();

		// Read the values from the JSON object
		String pID = patientObject.get("pID").getAsString();
		String pFname = patientObject.get("pFname").getAsString();
		String pLname = patientObject.get("pLname").getAsString();
		String pAge = patientObject.get("pAge").getAsString();
		String pGender = patientObject.get("pGender").getAsString();
		String pAddress = patientObject.get("pAddress").getAsString();
		String pContactNo = patientObject.get("pContactNo").getAsString();
		String pNIC = patientObject.get("pNIC").getAsString();
		String pEmail = patientObject.get("pEmail").getAsString();
		String pUsername = patientObject.get("pUsername").getAsString();
		String pPassword = patientObject.get("pPassword").getAsString();

		String output = patiObj.updatePatient(pID, pFname, pLname, pAge, pGender, pAddress, pContactNo, pNIC, pEmail,
				pUsername, pPassword);

		return output;
	}

	/*----------------------------delete patient----------------------*/
	@DELETE
	@Path("/delete")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deletePatient(String patientData) {
		// Convert the input string to an XML document
		Document doc = Jsoup.parse(patientData, "", Parser.xmlParser());
		// Read the value from the element <pID>
		String pID = doc.select("pID").text();

		String output = patiObj.deletePatient(pID);

		return output;
	}

}