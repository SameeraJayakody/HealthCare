/**
 * 
 */
package com;

import model.Hospital;

//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
//For JSON
import com.google.gson.*;
//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;

/**
 * @author Admin
 *
 */

@Path("/Hospitals")
public class HospitalService {

	Hospital hospObj = new Hospital();
	
	//View Hospital Details
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readItems() {
		return hospObj.readItems();
	}
	
	//Insert Hospital Details
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertHospital( @FormParam("name") String name, @FormParam("type") String type, @FormParam("description") String description, @FormParam("phoneNo") String phoneNo, @FormParam("email") String email, @FormParam("code") String code,@FormParam("city") String city,@FormParam("state") String state,@FormParam("hospitalFee") String hospitalFee) {
		String output = hospObj.insertHospital(name, type, description, phoneNo, email, code, city, state, hospitalFee);
		return output;
	}
	
	//Update Hospital Details
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateItem(String itemData) {

		// Convert the input string to a JSON object
		JsonObject hospitalObject = new JsonParser().parse(itemData).getAsJsonObject();

		// Read the values from the JSON object
		String hospitalID = hospitalObject.get("hospitalID").getAsString();
		String name = hospitalObject.get("name").getAsString();
		String type = hospitalObject.get("type").getAsString();
		String description = hospitalObject.get("description").getAsString();
		String phoneNo = hospitalObject.get("phoneNo").getAsString();
		String email = hospitalObject.get("email").getAsString();
		String code = hospitalObject.get("code").getAsString();
		String city = hospitalObject.get("city").getAsString();
		String state = hospitalObject.get("state").getAsString();
		String hospitalFee = hospitalObject.get("hospitalFee").getAsString();

		String output = hospObj.updateItem(hospitalID, name, type, description, phoneNo,email,code,city,state,hospitalFee);
		return output;
	}
	
	//Delete Hospital Details
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteHospital(String itemData) {
		// Convert the input string to an XML document
		Document doc = Jsoup.parse(itemData, "", Parser.xmlParser());

		// Read the value from the element <itemID>
		String hospitalID = doc.select("hospitalID").text();
		String output = hospObj.deleteHospital(hospitalID);
		return output;
	}
	
}
