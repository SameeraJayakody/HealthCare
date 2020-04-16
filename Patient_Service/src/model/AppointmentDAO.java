package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import database.dbconnect;

public class AppointmentDAO {

	dbconnect obj = new dbconnect();

	/*-----------------Insert appointment------------------------*/
	public String insertAppointment(String appointmentNo, String sessionDate, String sessionTime) {
		String output = "";

		try {
			Connection con = obj.connect();

			if (con == null) {
				return "Error while connecting to the database for inserting.";
			}
			int appointmentNo1 = Integer.parseInt(appointmentNo);
			LocalDate sessionDate1 = LocalDate.parse(sessionDate);
			LocalTime sessionTime1 = LocalTime.parse(sessionTime);

			// create a prepared statement
			String query = "INSERT INTO appointment (appointmentNo,sessionDate,sessionTime) VALUES('" + appointmentNo1
					+ "','" + sessionDate1 + "','" + sessionTime1 + "')";

			PreparedStatement preparedStmt = con.prepareStatement(query);

			// execute the statement
			preparedStmt.execute();
			con.close();

			output = "Inserted successfully";
		} catch (Exception e) {
			output = "Error while inserting the appointment";
			System.err.println(e.getMessage());
		}

		return output;

	}

	
	/*----------------display appointments based on the patientID---------------*/

	public String readAppointments(String patientID) {
		String output = "";

		try {
			Connection con = obj.connect();

			if (con == null) {
				return "Error while connecting to the database for reading.";
			}

			// Prepare the html table to be displayed
			output = "<table border=\"1\"><tr>" + "<th>appointmentNo</th>" + "<th>sessionDate</th>"
					+ "<th>sessionTime</th>" + "<th>status</th>" +"<th>paymentStatus</th>" + "<th>Cancel Appointment</th></tr>"
					+ "<a href=\"http://localhost:8080/HospitalMSystem/searchDoctor.jsp\">Add Appointment</a>"
					+ "<input name=\"btnUpdate\" type=\"button\"  "
					+ "      value=\"Add appointment\" onclick=\"registerForm.jsp\" >"
					+ "<input name=\"btnUpdate\" type=\"button\"  " + "      value=\"My Profile\" >";

			String query = "select * from appointment where patientID='" + patientID + "'";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);

			// iterate through the rows in the result set
			while (rs.next()) {
				String aID = Integer.toString(rs.getInt("aID"));
				String appointmentNo = Integer.toString(rs.getInt("appointmentNo"));
				String sessionDate = rs.getDate("sessionDate").toLocalDate().toString();
				String sessionTime = rs.getTime("sessionTime").toString();
				String status = rs.getString("status");
				String paymentStatus = rs.getString("paymentStatus");

				// Add into the html table
				output += "<tr><td>" + appointmentNo + "</td>";
				output += "<td>" + sessionDate + "</td>";
				output += "<td>" + sessionTime + "</td>";
				output += "<td>" + status + "</td>";
				output += "<td>" + paymentStatus + "</td>";

				System.out.print(aID);

				// buttons
				output += "<td><form method=\"post\"  action=\"Appointments\">"
						+ "Remove<input name=\"appID\" id=\"appID\" type=\"submit\" class=\"btn btn-danger\" value=\""
						+ aID + "\">" + "<input name=\"aID\" id=\"appID\" type=\"hidden\" value=\"" + aID + "\">"
						+ "</form></td></tr>";
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

	
	/*--------------------------search Doctor----------------------------*/
	public String searchDoctor(String docName, String specialization) {
		String output = "";

		try {
			Connection con = obj.connect();
			if (con == null) {
				return "Error while connecting to the database for reading.";
			}

			// Prepare the html table to be displayed
			output = "<table border=\"1\"><tr>" + "<th>docName</th>" + "<th>specialization</th>"
					+ "<th>WorkingHospitals</th>" + "<th>View Schedule</th></tr>";

			String query = "SELECT * FROM registereddoctors WHERE docName like '%" + docName + "%' "
					+ "AND specialization= '" + specialization + "'";
			
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);

			// iterate through the rows in the result set
			while (rs.next()) {
				String docID = Integer.toString(rs.getInt("docID"));
				String drname = rs.getString("docNname");
				String specialization1 = rs.getString("specialization");
				String workingHospital1 = rs.getString("workingHospital");

				// Add into the html table
				output += "<tr><td>" + drname + "</td>";
				output += "<td>" + specialization1 + "</td>";
				output += "<td>" + workingHospital1 + "</td>";

				// buttons
				output += "<td><a href=\"http://localhost:8080/HospitalMSystem/searchDoctor.jsp\">View Schedule</a></td>";
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

	/*------------------------Delete appointment--------------------------*/
	public String deleteAppointment(String id) {
		String output = "";

		try {
			Connection con = obj.connect();

			if (con == null) {
				return "Error while connecting to the database for deleting.";
			}

			// create a prepared statement
			String query = "delete from appointment where aID=?";

			PreparedStatement preparedStmt = con.prepareStatement(query);

			// binding values
			preparedStmt.setInt(1, Integer.parseInt(id));

			// execute the statement
			preparedStmt.execute();
			con.close();

			output = "Deleted successfully";
		} catch (Exception e) {
			output = "Error while deleting appointments.";
			System.err.println(e.getMessage());
		}

		return output;
	}

	
	public List<Appointment> testRead() {
		String output = "";
		List<Appointment> list = null;
		
		try {
			Connection con = obj.connect();

			String query = "select * from appointment";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);

			// iterate through the rows in the result set
			while (rs.next()) {
				int aID = rs.getInt("aID");
				int appointmentNo = rs.getInt("appointmentNo");
				LocalDate sessionDate = rs.getDate("sessionDate").toLocalDate();
				LocalTime sessionTime = rs.getTime("sessionTime").toLocalTime();
				boolean status = rs.getBoolean("status");
				boolean paymentStatus = rs.getBoolean("paymentStatus");

				list.add(new Appointment(aID, appointmentNo, sessionDate, sessionTime, status, paymentStatus));
			}
			con.close();
		} catch (Exception e) {
			output = "Error while reading the appointment details.";
			System.err.println(e.getMessage());
		}
		return list;
	}

	/*--------------------------cancel appointment by updating status ----------------------------*/
	public String updateAppointment(String aID) {
		String output = "";

		try {
			Connection con = obj.connect();

			if (con == null) {
				return "Error while connecting to the database for updating.";
			}

			// create a prepared statement
			String query = "UPDATE appointment SET status=0 WHERE aID=" + aID;

			PreparedStatement preparedStmt = con.prepareStatement(query);

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
}