/**
 * 
 */
package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * @author Admin
 *
 */
public class Hospital {

	public Connection connect() {
		Connection con = null;

		try 
		{
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/hospitaldb?serverTimezone=UTC", "root", "");
			
			// For testing
			System.out.print("Successfully connected");
		} catch (Exception e) 
		{
			e.printStackTrace();
		}

		return con;
	}
	
	//For Insert Hospital Details
	public String insertHospital(String name, String type, String description, String phoneNo, String email, String code, String city, String state, String hospitalFee) {
		
		String output = "";
		
		try
		{
			Connection con= connect();
			
			if(con == null)
			{
				return "Error while connecting to the database";
			}
			
			//create a prepared statement
			String query = " insert into hospital (hospitalID,name,type,description,phoneNo,email,code,city,state,hospitalFee)"
							+ "values(?,?,?,?,?,?,?,?,?,?)";
			
			PreparedStatement preparedStmt =  con.prepareStatement(query);
			
			//binding values
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, name);
			preparedStmt.setString(3, type);
			preparedStmt.setString(4,description);
			preparedStmt.setString(5,phoneNo);
			preparedStmt.setString(6,email);
			preparedStmt.setString(7,code);
			preparedStmt.setString(8,city);
			preparedStmt.setString(9, state);
			preparedStmt.setDouble(10,Double.parseDouble(hospitalFee));
			
			//execute the statement
			preparedStmt.execute();
			con.close();
			
			output = "Inserted successfully";
		}catch (Exception e) {
			output="Error while inserting";
			System.out.println(e.getMessage());
		}
		
		return output;
	}
	
	//For Read Hospitals from Database
	public String readItems() 
	{
		String output= "";
		
		try {
			Connection con= connect();
			
			if(con==null)
			{
				return "Error while connecting to the database";
			}
			
			//prepare the html table to be displayed
			output = "<table border=\"1\"><tr><th>Hospital Name</th>"
					+ "<th>Hospital Type</th><th>Description</th>"
					+ "<th>Phone Number</th><th>Email</th>"
					+ "<th>Code</th><th>City</th>"
					+ "<th>State</th><th>Hoapital Fee</th>"
					+ "<th>Update</th><th>Remove</th></tr>";
			
			String query = "select * from hospital";
			Statement stmt= con.createStatement();
			ResultSet rs= stmt.executeQuery(query);
			
			//iterate through the rows in the result set
			while(rs.next())
			{
				String hospitalID = Integer.toString(rs.getInt("hospitalID"));
				String name = rs.getString("name");
				String type = rs.getString("type");
				String description = rs.getString("description");
				String phoneNo = rs.getString("phoneNo");
				String email = rs.getString("email");
				String code = rs.getString("code");
				String city = rs.getString("city");
				String state = rs.getString("state");
				String hospitalFee = Double.toString(rs.getDouble("hospitalFee"));
				
				
				//Add into the html table
				output += "<td>" + name + "</td>"; 
				output += "<td>" + type + "</td>";
				output += "<td>" + description + "</td>";
				output += "<td>" + phoneNo + "</td>";
				output += "<td>" + email + "</td>";
				output += "<td>" + code + "</td>";
				output += "<td>" + city + "</td>";
				output += "<td>" + state + "</td>";
				output += "<td>" + hospitalFee + "</td>";
				
				
				//buttons
				output += "<td><input name=\"btnUpdate\" "
						 + " type=\"button\" value=\"Update\"></td>"
						 + "<td><form method=\"post\" action=\"hospitalReg.jsp\">"
						 + "<input name=\"btnRemove\" "
						 + " type=\"submit\" value=\"Remove\" class=\"btn btn-danger\">"
						 + "<input name=\"hospitalID\" type=\"hidden\" "
						 + " value=\"" + hospitalID + "\">" + "</form></td></tr>";
						
			}
			con.close();
			
			//complete the html table
			output+= "</table>";
			
		}catch(Exception e) {
			output = "Error while reading the items.";
			System.err.println(e.getMessage());
		}
		return output;
	}
	
	//For Update Hospital Details
	public String updateItem(String hospitalID,String name, String type, String description, String phoneNo, String email, String code, String city, String state, String hospitalFee) {
		
		String output = "";
		
		try {
			
			Connection con = connect();
			
			if (con == null) {
				return "Error while connecting to the database for updating.";
			}
			
			// create a prepared statement
			String query = "UPDATE hospital SET name=?,type=?,description=?,phoneNo=?,email=?,code=?,city=?,state=?,hospitalFee=? WHERE hospitalID=?";
			
			PreparedStatement preparedStmt = con.prepareStatement(query);
			
			// binding values
			preparedStmt.setString(1, name);
			preparedStmt.setString(2, type);
			preparedStmt.setString(3, description);
			preparedStmt.setString(4, phoneNo);
			preparedStmt.setString(5, email);
			preparedStmt.setString(6, code);
			preparedStmt.setString(7, city);
			preparedStmt.setString(8, state);
			preparedStmt.setDouble(9, Double.parseDouble(hospitalFee));
		
			preparedStmt.setInt(10, Integer.parseInt(hospitalID));
			
			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Updated successfully";
		
		} catch (Exception e) {
			output = "Error while updating the item.";
			System.err.println(e.getMessage());
		}
		
		return output;
	}
	
	//For Delete Hospital Details
	public String deleteHospital(String hospitalID) {
		String output = "";
		
		try {
			Connection con= connect();
			if(con == null) {
				return "Error while connecting to the database for deleting.";
			}
			
			//create a prepared statement
			String query = "delete from hospital where hospitalID=?";
			
			PreparedStatement preparedStmt= con.prepareStatement(query);
			
			//binding values
			preparedStmt.setInt(1, Integer.parseInt(hospitalID));
			
			//execute the statement
			preparedStmt.execute();
			con.close();
			
			output = "Deleted Successfully";
			
		}catch(Exception e) {
			output = "Error while deleting the hospital.";
			System.err.println(e.getMessage());
		}
		return output;
	}
	
	//For Search Hospital Details
	public String searchHospitals(String name, String city) 
	{
		String output = "";
		
		try {
			Connection con= connect();
			if(con == null) {
				return "Error while connecting to the database for deleting.";
			}
	
			// Prepare the html table to be displayed
			output = "<table border=\"1\"><tr>" + "<th>name</th>" + "<th>type</th>"
					+ "<th>description</th>" + "<th>phoneNo</th>" + "<th>email</th>" + "<th>code</th>"
					+ "<th>city</th>"+ "<th>state</th>" + "<th>hospitalFee</th> </tr>";

			String query = "SELECT * FROM hospital WHERE name like '%" + name + "%' "
					+ "AND city= '" + city + "'";
			
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);

			// iterate through the rows in the result set
			while (rs.next()) {
				String hospitalID = Integer.toString(rs.getInt("hospitalID"));
				String hospname = rs.getString("name");
				String type = rs.getString("type");
				String description = rs.getString("description");
				String phoneNo = rs.getString("phoneNo");
				String email = rs.getString("email");
				String code = rs.getString("code");
				String city1 = rs.getString("city");
				String state = rs.getString("state");
				String hospitalFee = Double.toString(rs.getDouble("hospitalFee"));
				//Add into the html table
				output += "<td>" + hospname + "</td>"; 
				output += "<td>" + type + "</td>";
				output += "<td>" + description + "</td>";
				output += "<td>" + phoneNo + "</td>";
				output += "<td>" + email + "</td>";
				output += "<td>" + code + "</td>";
				output += "<td>" + city1 + "</td>";
				output += "<td>" + state + "</td>";
				output += "<td>" + hospitalFee + "</td>";
				

				// buttons
				output += "<td><a href=\"http://localhost:8080/HospitalManagement/searchHosp.jsp\">Back</a></td>";
			}
			con.close();

			// Complete the html table
			output += "</table>";
		} catch (Exception e) {
			output = "Error while reading the appointment details.";
			System.err.println(e.getMessage());
		}
		return output;
	}

}
