package model;
import database.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class RoomClassification {
	
	dbconnect obj = new dbconnect();
	
	public String CheckRoom() {
		String output = "";
		try {
			Connection con = obj.connect();
			if (con == null) {
				return "Error while connecting to the database for reading.";
			}
			// Prepare the html table to be displayed
			
			output = "Children Ward rooms"+"<table border=\"1\" align=\"center\"><tr><th>scheduleID</th>"
					+ "<th>HospitalID</th><th>HospitalName</th>" + "<th>DoctorID</th>" + "<th>DoctorName</th>"
					+ "<th>Speciality</th>" + "<th>Date</th>" + "<th>StartTime</th>" + "<th>EndTime</th>"
					+ "<th>RoomNumber</th>" + "<th>Status</th>" + "<th>Remove</th></tr>";

			String query = "select * from schedule where `RoomNumber` <"+25;
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			// iterate through the rows in the result set
			
			
			
			while (rs.next()) {
				String id = rs.getString("ID");
				String sid = rs.getString("scheduleID");
				String hid = rs.getString("HospitalID");
				String hn = rs.getString("HospitalName");
				String did = rs.getString("DoctorID");
				String dn = rs.getString("DoctorName");
				String sp = rs.getString("Speciality");
				String da = rs.getString("Date");
				String st = rs.getString("StartTime");
				String en = rs.getString("EndTime");
				String rm = rs.getString("RoomNumber");
				String sts = rs.getString("Status");
				// Add into the html table
				
				
				output += "<tr><td>" + sid + "</td>";
				output += "<td>" + hid + "</td>";
				output += "<td>" + hn + "</td>";
				output += "<td>" + did + "</td>";
				output += "<td>" + dn + "</td>";
				output += "<td>" + sp + "</td>";
				output += "<td>" + da + "</td>";
				output += "<td>" + st + "</td>";
				output += "<td>" + en + "</td>";
				output += "<td>" + rm + "</td>";
				output += "<td>" + sts + "</td>";
				// buttons
				output += "<td><form method=\"post\" action=\"TimeCollector.jsp\">" + "<input name=\"btnRemove\" "
						+ " type=\"submit\" value=\"Remove\" action=\"TimeCollector.jsp\">"
						+ "<input name=\"id\" type=\"hidden\" " + " value=\"" + id + "\">" + "</form></td></tr>";
				
				  	
					
			}
						
					
						
				con.close();
			// Complete the html table
			output += "</table>";
		
			}catch (Exception e)
		{
			output = "Error while reading the items.";
			System.err.println(e.getMessage());
		}
		return output;
	}

}
