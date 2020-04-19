package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import database.dbconnect;

public class Validate {
	
	dbconnect object = new dbconnect();

//validate card form inputs--------------------------------
	public String validateCardInput(String cardNo, String holderName, String expiryDate, String cvv, String paymentNo) {
		
		String output;
		
		if(cardNo.equals("")||holderName.equals("")||expiryDate.equals("")||cvv.equals("")||paymentNo.equals(""))
		{
			output="Please fill all fields";
		}
		else if(cardNo.length()!=16 && !cardNo.matches("[0-9]+")) {
			output="Invalid Card No";
		}
		else if(!holderName.matches("[a-zA-Z_]+"))
		{
			output="Invalid Cardholder Name";
		}
		else if(cvv.length()!=3 && !cvv.matches("[0-9]+")) 
		{
			output="Invalid cvv";
		}
		else if(!paymentNo.matches("[0-9]+"))
		{
			output="Invalid payment No";
		}
		else
		{
			output="Success";
		}
		return output;
		
	}
	
//validate payment form inputs------------------------------------------	
public String validatePaymentInput(String patientId, String appointmentId, String paymentType) {
		
		String output;
		
		if(patientId.equals("")||appointmentId.equals("")||paymentType.equals(""))
		{
			output="Please fill all fields";
		}
		else if(!patientId.matches("[0-9]+")) {
			output="Invalid Appointment id";
		}
		else if(!appointmentId.matches("[0-9]+")) {
			output="Invalid Appointment id";
		}
		else if(!paymentType.matches("[a-zA-Z_]+"))
		{
			output="Invalid Payment Type";
		}
		else
		{
			output="Success";
		}
		return output;
		
	}


//validate paymentNo in card form---------------------------------------------------------------
		public Boolean validatePaymentNo(String paymentNo) {
			Boolean output = null;

			try {
				Connection con = object.connect();
				if (con == null) {
					output=false;
				}
				
				// validate paymentNo
				PreparedStatement stmt = con.prepareStatement("SELECT p.paymentNo FROM payment p" + " WHERE p.paymentNo=? ");
				stmt.setInt(1, Integer.parseInt(paymentNo));
				ResultSet result1 = stmt.executeQuery();

				if (result1.next() != false) {

					output=true;
				}
				
			} catch (Exception e) {
				output=false;
				System.err.println(e.getMessage());
			}
			return output;
		}
	
// validate appointmentid in payment form---------------------------------------------------------------
		public Boolean validateAppointmentId(String appointmentId) {

			try {
				Connection con = object.connect();
				if (con == null) {
					return false;
				}

				// validate appointmentid
				PreparedStatement stmt1 = con.prepareStatement("SELECT aID FROM appointment a" + " WHERE a.aID=? ");
				stmt1.setInt(1, Integer.parseInt(appointmentId));
				ResultSet result1 = stmt1.executeQuery();

				if (result1.next() != false) {

					return true;
				}

			} catch (Exception e) {
				System.err.println(e.getMessage());
			}
			return true;

		}

// validate patientid in payment form-----------------------------------------------------------
		public Boolean validatePatientId(String patientId) {

			try {
				Connection con = object.connect();
				if (con == null) {
					return false;
				}

				// validate patientid
				PreparedStatement stmt2 = con.prepareStatement("SELECT pID FROM patient p" + " WHERE p.pID=? ");
				stmt2.setInt(1, Integer.parseInt(patientId));
				ResultSet result2 = stmt2.executeQuery();

				if (result2.next() != false) {

					return true;
				}

			} catch (Exception e) {
				System.err.println(e.getMessage());
			}
			return true;

		}
		
		
}
