package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import database.dbconnect;
/**
 * @author Sachini
 *
 */
public class PatientDAO {

	dbconnect obj = new dbconnect();

	public String readPatients(String username) {
		String output = "";

		try {
			Connection con = obj.connect();

			if (con == null) {
				return "Error while connecting to the database for reading.";
			}

			// Prepare the html table to be displayed
			output = "<table border=\"1\"><tr>" + "<th>FirstName</th>" + "<th>LastName</th>" + "<th>Age</th>"
					+ "<th>Gender</th>" + "<th>Address</th>" + "<th>ContactNo</th>" + "<th>NIC</th>" + "<th>Email</th>"
					+ "<th>Username</th>" + "<th>Password</th>" + "<th>Update</th>" + "<th>Remove</th></tr>";

			String query = "select * from patient where pUsername='" + username + "'";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);

			// iterate through the rows in the result set
			while (rs.next()) {
				String pID = Integer.toString(rs.getInt("pID"));
				String pFname = rs.getString("pFname");
				String pLname = rs.getString("pLname");
				String pAge = Integer.toString(rs.getInt("pAge"));
				String pGender = rs.getString("pGender");
				String pAddress = rs.getString("pAddress");
				String pContactNo = rs.getString("pContactNo");
				String pNIC = rs.getString("pNIC");
				String pEmail = rs.getString("pEmail");
				String pUsername = rs.getString("pUsername");
				String pPassword = rs.getString("pPassword");

				// Add into the html table
				output += "<tr><td>" + pFname + "</td>";
				output += "<td>" + pLname + "</td>";
				output += "<td>" + pAge + "</td>";
				output += "<td>" + pGender + "</td>";
				output += "<td>" + pAddress + "</td>";
				output += "<td>" + pContactNo + "</td>";
				output += "<td>" + pNIC + "</td>";
				output += "<td>" + pEmail + "</td>";
				output += "<td>" + pUsername + "</td>";
				output += "<td>" + pPassword + "</td>";

				// buttons
				output += "<td><input name=\"btnUpdate\" type=\"button\"  "
						+ "      value=\"Update\" class=\"btn btn-secondary\"></td>"
						+ "<td><form method=\"post\" action=\"patients.jsp\">"
						+ "<input name=\"btnRemove\" type=\"submit\" value=\"Remove\"      class=\"btn btn-danger\">"
						+ "<input name=\"pID\" type=\"hidden\" value=\"" + pID + "\">" + "</form></td></tr>";
			}

			con.close();

			// Complete the html table
			output += "</table>";
		} catch (Exception e) {
			output = "Error while reading the patients details.";
			System.err.println(e.getMessage());
		}

		return output;
	}

	/*
	public ArrayList<String> readPatients1(String username) {
		String output = "";
		ArrayList<String> pati = new ArrayList<String>();
		try {
			Connection con = obj.connect();

			if (con == null) {
				//return "Error while connecting to the database for reading.";
			}


			String query = "select * from patient = '" + username + "'";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);

			// iterate through the rows in the result set
			while (rs.next()) {
				String pID = Integer.toString(rs.getInt("pID"));
				String pFname = rs.getString("pFname");
				String pLname = rs.getString("pLname");
				String pAge = Integer.toString(rs.getInt("pAge"));
				String pGender = rs.getString("pGender");
				String pAddress = rs.getString("pAddress");
				String pContactNo = rs.getString("pContactNo");
				String pNIC = rs.getString("pNIC");
				String pEmail = rs.getString("pEmail");
				String pUsername = rs.getString("pUsername");
				String pPassword = rs.getString("pPassword");

				pati.add(pID);
				pati.add(pFname);
				pati.add(pLname);
				pati.add(pAge);
				pati.add(pGender);
				pati.add(pAddress);
				pati.add(pContactNo);
				pati.add(pNIC);
				pati.add(pEmail);
				pati.add(pUsername);
				pati.add(pPassword);
				
				
		
				// buttons
				output += "<td><input name=\"btnUpdate\" type=\"button\"  "
						+ "      value=\"Update\" class=\"btn btn-secondary\"></td>"
						+ "<td><form method=\"post\" action=\"patients.jsp\">"
						+ "<input name=\"btnRemove\" type=\"submit\" value=\"Remove\"      class=\"btn btn-danger\">"
						+ "<input name=\"pID\" type=\"hidden\" value=\"" + pID + "\">" + "</form></td></tr>";
			}

			con.close();

			// Complete the html table
			output += "</table>";
		} catch (Exception e) {
			output = "Error while reading the patients details.";
			System.err.println(e.getMessage());
		}

		return pati;
	}
*/
	/*-----------------------------------------Insert patient-------------------------------------------*/
	public String insertPatient(String pFname, String pLname, String pAge, String pGender, String pAddress,
			String pContactNo, String pNIC, String pEmail, String pUsername, String pPassword) {
		String output = "";

		try {
			Connection con = obj.connect();

			if (con == null) {
				return "Error while connecting to the database for inserting.";
			}

			// create a prepared statement
			String query = " insert into patient          "
					+ " (`pID`,`pFname`,`pLname`,`pAge`,`pGender`,`pAddress`,`pContactNo`,`pNIC`,`pEmail`,`pUsername`,`pPassword`)"
					+ " values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

			PreparedStatement preparedStmt = con.prepareStatement(query);

			// binding values
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, pFname);
			preparedStmt.setString(3, pLname);
			preparedStmt.setInt(4, Integer.parseInt(pAge));
			preparedStmt.setString(5, pGender);
			preparedStmt.setString(6, pAddress);
			preparedStmt.setString(7, pContactNo);
			preparedStmt.setString(8, pNIC);
			preparedStmt.setString(9, pEmail);
			preparedStmt.setString(10, pUsername);
			preparedStmt.setString(11, pPassword);

			// execute the statement
			preparedStmt.execute();
			con.close();

			output = "Inserted successfully";
		} catch (Exception e) {
			output = "Error while inserting the patient.";
			System.err.println(e.getMessage());
		}

		return output;

	}

	/*-----------------------------------------Update patient-------------------------------------------*/
	public String updatePatient(String pID, String pFname, String pLname, String pAge, String pGender, String pAddress,
			String pContactNo, String pNIC, String pEmail, String pUsername, String pPassword) {
		String output = "";

		try {
			Connection con = obj.connect();

			if (con == null) {
				return "Error while connecting to the database for updating.";
			}

			// create a prepared statement
			String query = "UPDATE patient SET pFname=?,pLname=?,pAge=?,pGender=?,pAddress=?,pContactNo=?,pNIC=?,pEmail=?,pUsername=?,pPassword=?      "
					+ "WHERE pID=?";

			PreparedStatement preparedStmt = con.prepareStatement(query);

			// binding values
			preparedStmt.setString(1, pFname);
			preparedStmt.setString(2, pLname);
			preparedStmt.setInt(3, Integer.parseInt(pAge));
			preparedStmt.setString(4, pGender);
			preparedStmt.setString(5, pAddress);
			preparedStmt.setString(6, pContactNo);
			preparedStmt.setString(7, pNIC);
			preparedStmt.setString(8, pEmail);
			preparedStmt.setString(9, pUsername);
			preparedStmt.setString(10, pPassword);
			preparedStmt.setInt(11, Integer.parseInt(pID));

			// execute the statement
			preparedStmt.execute();
			con.close();

			output = "Updated successfully";
		} catch (Exception e) {
			output = "Error while updating patient details";
			System.err.println(e.getMessage());
		}

		return output;
	}

	/*-----------------------------------------Delete patient-------------------------------------------*/

	/**
	 */
	public String deletePatient(String pID) {
		String output = "";

		try {
			Connection con = obj.connect();

			if (con == null) {
				return "Error while connecting to the database for deleting.";
			}

			// create a prepared statement
			String query = "delete from patient where pID=?";

			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, Integer.parseInt(pID));

			// execute the statement
			preparedStmt.execute();
			con.close();

			output = "Deleted successfully";
		} catch (Exception e) {
			output = "Error while deleting patient.";
			System.err.println(e.getMessage());
		}

		return output;
	}

	/*----------------------------------------------------searchPatients-------------------------------------------------*/
	public String searchPatients(String name) {
		String output = "";

		try {
			Connection con = obj.connect();

			if (con == null) {
				return "Error while connecting to the database for reading.";
			}

			// Prepare the html table to be displayed
			output = "<table border=\"1\"><tr>" + "<th>FirstName</th>" + "<th>LastName</th>" + "<th>Age</th>"
					+ "<th>Gender</th>" + "<th>Address</th>" + "<th>ContactNo</th>" + "<th>NIC</th>" + "<th>Email</th>"
					+ "<th>Username</th>" + "<th>Password</th>" + "<th>Update</th>" + "<th>Remove</th></tr>";

			// String query = "select * from patient where pID="+pFname;
			String query = "SELECT * FROM patient WHERE pFname like '%" + name + "%'" + "OR pLname like '%" + name
					+ "%'";
			// PreparedStatement preparedStmt = con.prepareStatement(query);

			// binding values

			Statement stmt = con.createStatement();
			// preparedStmt.setInt(1, Integer.parseInt(pID));
			ResultSet rs = stmt.executeQuery(query);

			// iterate through the rows in the result set
			while (rs.next()) {
				String pID = Integer.toString(rs.getInt("pID"));
				String pFname = rs.getString("pFname");
				String pLname = rs.getString("pLname");
				String pAge = Integer.toString(rs.getInt("pAge"));
				String pGender = rs.getString("pGender");
				String pAddress = rs.getString("pAddress");
				String pContactNo = rs.getString("pContactNo");
				String pNIC = rs.getString("pNIC");
				String pEmail = rs.getString("pEmail");
				String pUsername = rs.getString("pUsername");
				String pPassword = rs.getString("pPassword");

				// Add into the html table
				output += "<tr><td>" + pFname + "</td>";
				output += "<td>" + pLname + "</td>";
				output += "<td>" + pAge + "</td>";
				output += "<td>" + pGender + "</td>";
				output += "<td>" + pAddress + "</td>";
				output += "<td>" + pContactNo + "</td>";
				output += "<td>" + pNIC + "</td>";
				output += "<td>" + pEmail + "</td>";
				output += "<td>" + pUsername + "</td>";
				output += "<td>" + pPassword + "</td>";

				// buttons
				output += "<td><input name=\"btnUpdate\" type=\"button\"  "
						+ "      value=\"Update\" class=\"btn btn-secondary\"></td>"
						+ "<td><form method=\"post\" action=\"patients.jsp\">"
						+ "<input name=\"btnRemove\" type=\"submit\" value=\"Remove\"      class=\"btn btn-danger\">"
						+ "<input name=\"pID\" type=\"hidden\" value=\"" + pID + "\">" + "</form></td></tr>";
			}

			con.close();

			// Complete the html table
			output += "</table>";
		} catch (Exception e) {
			output = "Error while reading the patients details.";
			System.err.println(e.getMessage());
		}

		return output;
	}

}
