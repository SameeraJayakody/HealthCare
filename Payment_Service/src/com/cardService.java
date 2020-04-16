package com;

import model.Card;

//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

//For JSON
import com.google.gson.*;

//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;

@Path("/Cards")
public class cardService {
	Card cardObj = new Card();

	// for cards.........................
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readCard() {
		return cardObj.readCard();
	}

	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertCard(@FormParam("cardNo") String cardNo, @FormParam("holderName") String holderName,
			@FormParam("expiryDate") String expiryDate, @FormParam("cvv") String cvv,
			@FormParam("paymentNo") String paymentNo) {
		String output = cardObj.insertCard(cardNo, holderName, expiryDate, cvv, paymentNo);
		return output;
	}

	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateCard(String cardData) {
		// Convert the input string to a JSON object
		JsonObject cardObject = new JsonParser().parse(cardData).getAsJsonObject();
		// Read the values from the JSON object
		String cardNo = cardObject.get("cardNo").getAsString();
		String holderName = cardObject.get("cardHolderName").getAsString();
		String expiryDate = cardObject.get("expiryDate").getAsString();
		String cvv = cardObject.get("cvv").getAsString();
		String output = cardObj.updateCard(cardNo, holderName, expiryDate, cvv);
		return output;
	}

	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteCard(String cardData) {
		// Convert the input string to an XML document
		Document doc = Jsoup.parse(cardData, "", Parser.xmlParser());
		// Read the value from the element <itemID>
		String cardNo = doc.select("cardNo").text();
		String output = cardObj.deleteCard(cardNo);
		return output;
	}

}
