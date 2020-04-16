package model;

import database.dbconnect;
import java.sql.*;

public class Doctor {
	
	dbconnect object = new dbconnect();
	

	public String insertDoctors(String hospitalID,String docName, String docEmail, String docAddress, String specialization, String workingTime, String workingDays, String workingHospitals, String docFee, String username, String password)
	{
		String output = "";
		
		try
		{
			Connection con = object.connect();
			
			if (con == null)
			{return "Error while connecting to the database for inserting.";}
			
			//create a prepared statement
			String query = "insert into doctors(`docID`,`hospitalID`,`docName`,`docEmail`,`docAddress`,`specialization`,`workingTime`,`workingDays`,`workingHospitals`,`docFee`,`username`,`password`)"+"values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			
			PreparedStatement preparedStmt = con.prepareStatement(query);
			
			//binding values
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, hospitalID);
			preparedStmt.setString(3, docName);
			preparedStmt.setString(4, docEmail);
			preparedStmt.setString(5, docAddress);
			preparedStmt.setString(6, specialization);
			preparedStmt.setString(7, workingTime);
			preparedStmt.setString(8, workingDays);
			preparedStmt.setString(9, workingHospitals);
			preparedStmt.setDouble(10, Double.parseDouble(docFee));
			preparedStmt.setString(11, username);
			preparedStmt.setString(12, password);
			
			//execute the statement
			preparedStmt.execute();
			con.close();
			
			output = "Inserted successfully";
		}
		catch (Exception e)
		{
			output = "Error while inserting the doctor.";
			System.err.println(e.getMessage());
		}
		
		return output;
	}
	
	
	
	public String readDoctors()
	{
	      
		String output = "";
	
		try
	    {
	      Connection con = object.connect();
	
	      if (con == null)
	      {return "Error while connecting to the database for reading."; }
	
	      // Prepare the html table to be displayed
	      output = "<table border=\"1\" align=\"center\"><tr><th>Hospital ID</th><th>Full Name</th><th>Email</th><th>Address</th><th>Specialization</th><th>Working Time</th><th>Working Days</th><th>Working Hospital</th><th>Doctor Fee</th><th>Username</th><th>Password</th><th>Update</th><th>Delete</th></tr>";
	 
	      String query = "select * from doctors";
	      Statement stmt = con.createStatement();
	      ResultSet rs = stmt.executeQuery(query);
	
	      // iterate through the rows in the result set
	      while (rs.next())
	      {
	        String docID = Integer.toString(rs.getInt("docID"));
	        String hospitalID = rs.getString("hospitalID");
	        String docName = rs.getString("docName");
	        String docEmail = rs.getString("docEmail");
	        String docAddress = rs.getString("docAddress");
	        String specialization = rs.getString("specialization");
	        String workingTime = rs.getString("workingTime");
	        String workingDays = rs.getString("workingDays");
	        String workingHospitals = rs.getString("workingHospitals");
	        String docFee = Double.toString(rs.getDouble("docFee"));
	        String username = rs.getString("username");
	        String password = rs.getString("password");
	
	        // Add into the html table
	        output += "<td>" + hospitalID + "</td>";
	        output += "<td>" + docName + "</td>";
	        output += "<td>" + docEmail + "</td>";
	        output += "<td>" + docAddress + "</td>";
	        output += "<td>" + specialization + "</td>";
	        output += "<td>" + workingTime + "</td>";
	        output += "<td>" + workingDays + "</td>";
	        output += "<td>" + workingHospitals + "</td>";
	        output += "<td>" + docFee + "</td>";
	        output += "<td>" + username + "</td>";
	        output += "<td>" + password + "</td>";
	
	        // buttons
	        output += "<td><input name=\"update\" type=\"button\" value=\"update\" class=\"btn btn-secondary\"></td>"
	               + "<td><form method=\"post\" action=\"doctor.jsp\">"
	               + "<input name=\"Delete\" type=\"submit\" value=\"Delete\" class=\"btn btn-danger\">"
	               + "<input name=\"docID\" type=\"hidden\" value=\"" + docID+ "\">" + "</form></td></tr>";
	}
	con.close();
	// Complete the html table
	output += "</table>";
	}
	catch (Exception e)
	{
	  output = "Error while reading the items.";
	  System.err.println(e.getMessage());
	}
	return output;
	}



	public String updateDoctors(int docID,String hID,String dName, String dEmail, String dAddress, String dspecialization, String dworkingTime, String dworkingDays, String dworkingHospitals, String dFee, String dusername, String dpassword)
	{
		
	    String output = "";
	
	    try
	    {
	       Connection con = object.connect();
	
	       if (con == null)
	       {return "Error while connecting to the database for updating."; }
	
	       // create a prepared statement
	       String query = "UPDATE doctors SET hospitalID=?,docName=?,docEmail=?,docAddress=?,specialization=?,workingTime=?,workingDays=?,workingHospitals=?,docFee=?,username=?,password=?WHERE docID=?";
	  
	       PreparedStatement preparedStmt = con.prepareStatement(query);
	
	       // binding values
			preparedStmt.setString(1, hID);
			preparedStmt.setString(2, dName);
			preparedStmt.setString(3, dEmail);
			preparedStmt.setString(4, dAddress);
			preparedStmt.setString(5, dspecialization);
			preparedStmt.setString(6, dworkingTime);
			preparedStmt.setString(7, dworkingDays);
			preparedStmt.setString(8, dworkingHospitals);
			preparedStmt.setDouble(9, Double.parseDouble(dFee));
			preparedStmt.setString(10, dusername);
			preparedStmt.setString(11, dpassword);
			preparedStmt.setInt(12, docID);
	
	       // execute the statement
	       preparedStmt.execute();
	       con.close();
	
	       output = "Updated successfully";
	    } 
	    catch (Exception e)
	    {
	       output = "Error while updating the doctor.";
	       System.err.println(e.getMessage());
	    }
	
	    return output;
	}
	
	
	
	public String deleteDoctor(String dID)
	{
	   String output = "";
	
	   try
	   {
	      Connection con = object.connect();
	
	      if (con == null)
	      {return "Error while connecting to the database for deleting."; }
	
	      // create a prepared statement
	      String query = "delete from doctors where docID=?";
	
	      PreparedStatement preparedStmt = con.prepareStatement(query);
	 
	      // binding values
	      preparedStmt.setInt(1, Integer.parseInt(dID));
	 
	      // execute the statement
	      preparedStmt.execute();
	      con.close();
	
	      output = "Deleted successfully";
	  }     
	  catch (Exception e)
	  {
	       output = "Error while deleting the item.";
	       System.err.println(e.getMessage());
	  }
	
	   return output;
	}



	
	
}
