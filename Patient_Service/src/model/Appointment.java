package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import database.dbconnect;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalTime;


/**Appointment class.
 * @author Sachini
 *
 */
public class Appointment {

	dbconnect obj = new dbconnect();


	/*-----------------Insert appointment------------------------*/
	public String insertAppointment(String docID, String patientID, String sessionDate, String sessionTime) {
		String output = "";

		try {
			Connection con = obj.connect();

			if (con == null) {
				return "Error while connecting to the database for inserting.";
			}
			int id = Integer.parseInt(docID);
			int pid = Integer.parseInt(patientID);
			final LocalDate sessionDate1 = LocalDate.parse(sessionDate);
			final LocalTime sessionTime1 = LocalTime.parse(sessionTime);

			// create a prepared statement
			String query = "INSERT INTO appointment (sessionDate,sessionTime, docID,patientID) VALUES('" + sessionDate1
					+ "','" + sessionTime1 + "','" + id + "','" + pid + "')";

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
	public String readAppointments(String patientID, String aStatus) {
		String output = "";

		try {
			Connection con = obj.connect();

			if (con == null) {
				return "Error while connecting to the database for reading.";
			}

			// Prepare the html table to be displayed
			output = "<table border=\"1\"><tr>" 
					+ "<th>appointmentNo</th>"
					+ "<th>DocName</th>" 
					+ "<th>Specialty</th>"
					+ "<th>sessionDate</th>"
					+ "<th>sessionTime</th>"
					+ "<th>DoctorFee</th>"
					+ "<th>Hospital</th>"
					+ "<th>status</th>"
					+ "<th>paymentStatus</th>"
					+ "<th>Cancel Appointment</th></tr>"
					+ "<a href=\"http://localhost:8080/Patient_Service/searchDoctor.jsp\">Add Appointment</a>"
					+ "<input name=\"btnUpdate\" type=\"button\"  " 
					+ "      value=\"My Profile\" >";


			String query = "select a.aID,d.docName,d.specialization,a.sessionDate,a.sessionTime,d.workingHospitals,d.docFee,a.status,a.paymentStatus from appointment a,registereddoctors d where a.docID=d.docID and a.status='"+ aStatus + "'"+"and a.patientID='"+ patientID + "'";

			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);

			// iterate through the rows in the result set
			while (rs.next()) {
				String aID = Integer.toString(rs.getInt("a.aID"));
				// String appointmentNo = Integer.toString(rs.getInt("appointmentNo"));
				String docName = rs.getString("docName");
				String specialization = rs.getString("specialization");
				String sessionDate = rs.getDate("sessionDate").toLocalDate().toString();
				String sessionTime = rs.getTime("sessionTime").toString();
				String docFee = Double.toString(rs.getDouble("docFee"));
				String workingHospitals = rs.getString("workingHospitals");
				String status = rs.getString("status");
				String paymentStatus = rs.getString("paymentStatus");

				// condition to check status of appointment booked/cancelled
				if (status.equalsIgnoreCase("1")) 
				{
					status = "Booked";
				}
				else
				{
					status = "Cancelled";
				}


				// condition to check status of payment(paid/unpaid)
				if (paymentStatus.equalsIgnoreCase("1")) 
				{
					paymentStatus = "Paid";
				}
				else
				{
					paymentStatus = "Unpaid";
				}

				// Add into the html table
				output += "<tr><td>" + aID + "</td>";
				output += "<td>" + docName + "</td>";
				output += "<td>" + specialization + "</td>";
				output += "<td>" + sessionDate + "</td>";
				output += "<td>" + sessionTime + "</td>";
				output += "<td>" + docFee + "</td>";
				output += "<td>" + workingHospitals + "</td>";
				output += "<td>" + status + "</td>";
				output += "<td>" + paymentStatus + "</td>";


				System.out.print(aID);
				output += "<td><a href=\"http://localhost:8080/Patient_Service/AppointmentService/Appointments/updateStatus?aID="
						+ aID + "\">Cancel</a></td>";
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
			output = "<table border=\"1\"><tr>" 
					+ "<th>docName</th>" 
					+ "<th>specialization</th>"
					+ "<th>WorkingHospital</th>"
					+"<th>Doctor Fee</th>"
					+"<th>View Doctor's Schedule</th></tr>";
					//+ "<th>Make Appointment</th></tr>";

			String query = "SELECT * FROM registereddoctors WHERE docName like '%" + docName + "%'"
					+ "AND specialization= '" + specialization + "'";

			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);

			// iterate through the rows in the result set
			while (rs.next()) {
				String docID = Integer.toString(rs.getInt("docID"));
				String drname = rs.getString("docName");
				String specialization1 = rs.getString("specialization");
				String workingHospital1 = rs.getString("workingHospitals");
				String docFee = Integer.toString(rs.getInt("docFee"));

				// Add into the html table
				output += "<tr><td>" + drname + "</td>";
				output += "<td>" + specialization1 + "</td>";
				output += "<td>" + workingHospital1 + "</td>";
				output += "<td>" + docFee + "</td>";

				// links
				//output += "<td><a href=\"http://localhost:8080/HospitalMSystem/AppointmentService/Appointments/display\">Make Appointment</a></td>";
				output += "<td><a href=\"http://localhost:8080/TestApplication/ItemService/sh/DisplayPatient\">View Schedule</a></td>";
			}
			con.close();

			// Complete the html table
			output += "</table>";
		}catch (Exception e) {
			output = "Error while reading the appointment details.";
			System.err.println(e.getMessage());
		}
		return output;
	}



	/*--------------------------cancel appointment by updating status ----------------------------*/
	//only appointments that will be held in the future can be cancelled
	public String updateStatus(String aID) {
		String output = "";

		try {
			Connection con = obj.connect();

			if (con == null) {
				return "Error while connecting to the database for updating.";
			}
			//get current date
			LocalDate today = LocalDate.now();

			//this query will only cancel appointments where the appointment date > current date
			String query = "UPDATE appointment SET status=0 WHERE DATEDIFF(sessionDate,'"+today+"')>0 and aID="+aID;

			PreparedStatement preparedStmt = con.prepareStatement(query);

			// execute the statement
			preparedStmt.execute();
			con.close();

			output = "Appointment Cancelled";
		} catch (Exception e) {
			output = "Error while updating patient details";
			System.err.println(e.getMessage());
		}

		return output;
	}
}
