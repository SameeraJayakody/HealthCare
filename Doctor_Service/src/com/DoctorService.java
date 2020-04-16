package com;

import model.Doctor;

//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

//For JSON
import com.google.gson.*;

//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;

@Path("/Doctor")
public class DoctorService {

	Doctor docObj = new Doctor();
	
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readDoctors()
	{
		return docObj.readDoctors();
	}
	
	
	
	
    @POST
    @Path("/")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.TEXT_PLAIN)
    public String insertDoctors(@FormParam("hospitalID") String hospitalID,
		                    @FormParam("docName") String docName,
		                    @FormParam("docEmail") String docEmail,
		                    @FormParam("docAddress") String docAddress,
		                    @FormParam("specialization") String specialization,
		                    @FormParam("workingTime") String workingTime,
		                    @FormParam("workingDays") String workingDays,
		                    @FormParam("workingHospitals") String workingHospitals,
		                    @FormParam("docFee") String docFee,
		                    @FormParam("username") String username,
		                    @FormParam("password") String password)
    {
	  String output = docObj.insertDoctors(hospitalID, docName, docEmail, docAddress, specialization, workingTime, workingDays, workingHospitals, docFee, username, password);
	  return output;
    }
	

    
    @PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateDoctors(String docData)
	{
		//Convert the input string to a JSON object
		JsonObject docObject = new JsonParser().parse(docData).getAsJsonObject();
		
		//Read the values from the JSON object
		int docID = docObject.get("docID").getAsInt();
		String hospitalID = docObject.get("hospitalID").getAsString();
		String docName = docObject.get("docName").getAsString();
		String docEmail = docObject.get("docEmail").getAsString();
		String docAddress = docObject.get("docAddress").getAsString();
		String specialization = docObject.get("specialization").getAsString();
		String workingTime = docObject.get("workingTime").getAsString();
		String workingDays = docObject.get("workingDays").getAsString();
		String workingHospitals = docObject.get("workingHospitals").getAsString();
		String docFee = docObject.get("docFee").getAsString();
		String username = docObject.get("username").getAsString();
		String password = docObject.get("password").getAsString();
		
		String output = docObj.updateDoctors(docID, hospitalID, docName, docEmail, docAddress, specialization, workingTime, workingDays, workingHospitals, docFee, username, password);
		
		return output;
	}
	
	
    
    @DELETE
    @Path("")
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.TEXT_PLAIN)
    public String deleteDoctor(String docData)
    {
         //Convert the input string to an XML document
         Document doc = Jsoup.parse(docData, "", Parser.xmlParser());
    
         //Read the value from the element <itemID>
         String docID = doc.select("docID").text();
         
         String output = docObj.deleteDoctor(docID);
    
         return output;
    }
    
    
}
