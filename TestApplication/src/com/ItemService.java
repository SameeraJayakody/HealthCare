package com;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.parser.Parser;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import model.*;


@Path("/test")
public class ItemService {
	
	item itemObj = new item();// create item class objects
	
	RoomClassification itemObj1 = new RoomClassification(); // create RoomClassification class object
	
	
	// read schedule table details
	
	@GET
	@Path("/readItems")
	@Produces(MediaType.TEXT_HTML)
	public String readItems()
	{
	return itemObj.readItems();
	}
	
	
	// read doctors details .Display to the patients/doctors.
	
	@GET
	@Path("/DisplayDoctor")
	@Produces(MediaType.TEXT_HTML)
	public String readItem()
	{
	return itemObj.DisplayDoctor();
	}
	
	
	
	// view only schedule confirmed doctors details
	
	@GET
	@Path("/ViewTable")
	@Produces(MediaType.TEXT_HTML)
	public String ViewTable()
	{
	return itemObj.ViewTable();
	}
	
	
	// display only confirmed doctors details to the particular time period
	
	@GET
	@Path("/DisplayPatients")
	@Produces(MediaType.TEXT_HTML)
	public String DisplayPatients()
	{
	return itemObj.DisplayPatients();
	}
	
	
	
	// view doctors Messages
	
	@GET
	@Path("/readRequest")
	@Produces(MediaType.TEXT_HTML)
	public String readRequest()
	{
	return itemObj.readRequest();
	}
	
	
	
	@GET
	@Path("/RoomClassification/CheckRoom")
	@Produces(MediaType.TEXT_HTML)
	public String CheckRoom()
	{
	return itemObj1.CheckRoom();
	}
	
	
	
	//Search hospital using hospital
		@GET
		@Path("/{ID}")
		@Produces(MediaType.APPLICATION_JSON)
		public search getHospital(@PathParam("ID") int Hospital_id) {

				return itemObj.getHospital(Hospital_id);
			
		}
	
	
		
		
		
		
	
	  // View appointment by id
	  
	/*
	 * @GET
	 * 
	 * @Path("/{appointment_Id}") // @Consumes(MediaType.APPLICATION_JSON)
	 * 
	 * @Produces(MediaType.APPLICATION_JSON) public search
	 * ShowTypeById(@PathParam("appointment_Id") int id) { return
	 * itemObj.ShowTypeById(id);
	 * 
	 * }
	 */
	 
		
	
	
	// Start POST methods ------------------------------
	
		// Insert to schedule table according to doctors requests
		
		@POST
		@Path("/insertItem")
		@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
		@Produces(MediaType.TEXT_PLAIN)
		
		public String insertItem(
		@FormParam("schedule_id") String schedule_id,
		@FormParam("hospital_id") String hospital_id,
		@FormParam("hospital_name") String hospital_name,
		@FormParam("doctor_id") String doctor_id,
		@FormParam("doctor_name") String doctor_name,
		@FormParam("speciality") String speciality,
		@FormParam("date") String date,
	    @FormParam("startTime") String startTime,
	    @FormParam("endTime") String endTime,
	    @FormParam("roomNumber") String roomNumber,
		@FormParam("status") String status)
		
		{
		String output = itemObj.insertItem(schedule_id, hospital_id, hospital_name, 
				doctor_id,doctor_name,speciality,date,startTime,endTime,roomNumber,status);
		return output;
		}
	
	
	

		// insert only doctors confirmed schedule data to (docschedule table)
		
		@POST
		@Path("/insertConfirmSchedule")
		@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
		@Produces(MediaType.TEXT_PLAIN)
		
		public String insertConfirmSchedule(
		@FormParam("scheduleID") String scheduleID,
		@FormParam("hospitalName") String hospitalName,
		@FormParam("doctorName") String doctorName,
		@FormParam("speciality") String speciality,
		@FormParam("date") String date,
	    @FormParam("startTime") String startTime,
	    @FormParam("endTime") String endTime,
	    @FormParam("roomNumber") String roomNumber,
		@FormParam("Status") String Status)
		
		{
		String output = itemObj.insertConfirmSchedule(scheduleID,hospitalName,doctorName,speciality,date,startTime,
				endTime,roomNumber,Status);
		return output;
		}

		
		
		
		
	/*
	 * @POST
	 * 
	 * @Path("/insertMessage")
	 * 
	 * @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	 * 
	 * @Produces(MediaType.TEXT_PLAIN) public String insertMessage(
	 * 
	 * @FormParam("s") String sid,
	 * 
	 * @FormParam("dii") String did,
	 * 
	 * @FormParam("dnn") String dname,
	 * 
	 * @FormParam("da") String date,
	 * 
	 * @FormParam("stats") String Status) { String output =
	 * itemObj.insertMessage(sid,did,dname,date,Status); return output; }
	 */
		
		
		
		
		
		
		
		// beginning of the PUT method
		
		// update schedule table
		
		@PUT
		@Path("/updateItem")
		@Consumes(MediaType.APPLICATION_JSON)
		@Produces(MediaType.TEXT_PLAIN)
		public String updateItem(String itemData)
		{
		//Convert the input string to a JSON object
		JsonObject itemObject = new JsonParser().parse(itemData).getAsJsonObject();
		//Read the values from the JSON object
		
		int d = itemObject.get("id").getAsInt();
		String sii = itemObject.get("s").getAsString();
		String hidd = itemObject.get("hid").getAsString();
		String hnn = itemObject.get("hnnn").getAsString();
		String didd = itemObject.get("dii").getAsString();
		String dnn = itemObject.get("dnnn").getAsString();
		String spp = itemObject.get("spl").getAsString();
		String dated = itemObject.get("da").getAsString();
		String startd = itemObject.get("st").getAsString();
		String endd = itemObject.get("en").getAsString();
		String rmd = itemObject.get("rm").getAsString();
		String stat = itemObject.get("stat").getAsString();
		
		String output = itemObj.updateItem(d,sii,hidd,hnn,didd,dnn,spp,dated,startd,endd,rmd,stat);
		
		return output;
		
		}
		
		
		
		
		
		@PUT
		@Path("/updateTes")
		@Consumes(MediaType.APPLICATION_JSON)
		@Produces(MediaType.TEXT_PLAIN)
		public String updateTe(String itemData)
		{
		//Convert the input string to a JSON object
		JsonObject itemObject = new JsonParser().parse(itemData).getAsJsonObject();
		//Read the values from the JSON object
		
		String scheduleID = itemObject.get("scheduleID").getAsString();
		String status = itemObject.get("status").getAsString();
		
		String output = itemObj.updateTes(scheduleID,status);
		
		return output;
		
		}
		
		
		
		
		
		
		
		
		
		
		// beginning of the DELETE method	
		
		// Remove incorrect schedules from the schedule table
		
	    @DELETE
		@Path("/deleteItem")
		@Consumes(MediaType.APPLICATION_XML)
		@Produces(MediaType.TEXT_PLAIN)
		public String deleteItem(String itemData)
		{
		//Convert the input string to an XML document
		Document doc = Jsoup.parse(itemData, "", Parser.xmlParser());
		//Read the value from the element <itemID>
		String id = doc.select("ID").text();
		String output = itemObj.deleteItem(id);
		return output;
		}
		
		
		
	    
	    

		@DELETE
		@Path("/RemoveRecord")
		@Consumes(MediaType.APPLICATION_XML)
		@Produces(MediaType.TEXT_PLAIN)
		public String RemoveRecord(String itemData)
		{
		//Convert the input string to an XML document
		Document doc = Jsoup.parse(itemData, "", Parser.xmlParser());
		//Read the value from the element <itemID>
		String idd = doc.select("da").text();
		String output = itemObj.RemoveRecord(idd);
		return output;
		}
	    
		
		
		
		
		
}
