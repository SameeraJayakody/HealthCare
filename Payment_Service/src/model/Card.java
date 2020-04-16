package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Card {

	public Connection connect() {
		Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/hospital_database", "root", "");
			// For testing
			System.out.print("Successfully connected");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}

	public String insertCard(String cardNo, String holderName, String expiryDate, String cvv, String paymentNo) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database";
			}

			// create a prepared statement
			String query = "insert into card(`cardNo`,`cardHolderName`,`expiryDate`,`cvv`,`paymentNo`)" + " values ( ?, ?, ?, ?, ?)";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setString(1, cardNo);
			preparedStmt.setString(2, holderName);
			preparedStmt.setString(3, expiryDate);
			preparedStmt.setInt(4, Integer.parseInt(cvv));
			preparedStmt.setInt(5, Integer.parseInt(paymentNo));

			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Inserted successfully";
		} catch (Exception e) {
			output = "Error while inserting";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String readCard() {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for reading.";
			}
			// Prepare the html table to be displayed
			output = "<table border=\"1\"><tr><th>Card No</th>" + "<th>Payment No</th><th>Card Holder Name</th>"
					+ "<th>Expiry Date</th>" + "<th>CVV</th>" + "<th>Update</th><th>Remove</th></tr>";
			String query = "select * from card";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			// iterate through the rows in the result set
			while (rs.next()) {
				String cardNo = rs.getString("cardNo");
				String paymentNo = Integer.toString(rs.getInt("paymentNo"));
				String holderName = rs.getString("cardHolderName");
				String expiryDate = rs.getString("expiryDate");
				String cvv = Integer.toString(rs.getInt("cvv"));
				// Add into the html table
				output += "<tr><td>" + cardNo + "</td>";
				output += "<td>" + paymentNo + "</td>";
				output += "<td>" + holderName + "</td>";
				output += "<td>" + expiryDate + "</td>";
				output += "<td>" + cvv + "</td>";
				// buttons
				output += "<td><input name=\"btnUpdate\" " + " type=\"button\" value=\"Update\"></td>"
						+ "<td><form method=\"post\" action=\"card.jsp\">" + "<input name=\"btnRemove\" "
						+ " type=\"submit\" value=\"Remove\"class=\"btn btn-danger\">"
						+ "<input name=\"cardNo\" type=\"hidden\" " + " value=\"" + cardNo + "\">"
						+ "</form></td></tr>";
			}
			con.close();
			// Complete the html table
			output += "</table>";
		} catch (Exception e) {
			output = "Error while reading the card details.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String updateCard(String cardNo, String holderName, String expiryDate, String cvv) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for updating.";
			}
			// create a prepared statement
			String query = "UPDATE card SET cardHolderName=?,expiryDate=?,cvv=? WHERE cardNo=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setString(1, holderName);
			preparedStmt.setString(2, expiryDate);
			preparedStmt.setInt(3, Integer.parseInt(cvv));
			preparedStmt.setString(4, cardNo);
			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Updated successfully";
		} catch (Exception e) {
			output = "Error while updating the card details.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String deleteCard(String cardNo) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for deleting.";
			}
			// create a prepared statement
			String query = "delete from card where cardNo=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setString(1, cardNo);
			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Deleted successfully";
		} catch (Exception e) {
			output = "Error while deleting card details.";
			System.err.println(e.getMessage());
		}
		return output;
	}
}
