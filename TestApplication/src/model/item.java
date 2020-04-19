package model;



import model.search;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


import database.*;

public class item {

	// create db connection
	dbconnect obj = new dbconnect();
	
	
	public String insertItem(String sid, String hid, String hname, String did, String dname, String special,
			String date, String start, String end, String room, String sts) {
		
		String output = "";
		
		try {
			Connection con = obj.connect();
			if (con == null)
			{
				return "Error while connecting to the database";
			}
			// create a prepared statement
			String query = "insert into schedule"
					+ "(`ID`, `scheduleID`, `HospitalID`, `HospitalName`, `DoctorID`, `DoctorName`, `Speciality`, `Date`, `StartTime`, `EndTime`, `RoomNumber`,`Status`)"
					+ " values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, sid);
			preparedStmt.setString(3, hid);
			preparedStmt.setString(4, hname);
			preparedStmt.setString(5, did);
			preparedStmt.setString(6, dname);
			preparedStmt.setString(7, special);
			preparedStmt.setString(8, date);
			preparedStmt.setString(9, start);
			preparedStmt.setString(10, end);
			preparedStmt.setString(11, room);
			preparedStmt.setString(12, sts);

			// execute the statement
			preparedStmt.execute();
			// System.out.print("successfuly inserted");
			con.close();
			output = "Inserted successfully schedule details";

		} catch (Exception e) {
			
			output = "Error while inserting items";
			System.err.println(e.getMessage());
		}

		return output;
	}

	
	
	
	
	
	
	
	
	
	public String readItems() {
		String output = "";
		try {
			Connection con = obj.connect();
			if (con == null) {
				return "Error while connecting to the database for reading.";
			}
			// Prepare the html table to be displayed
			output = "<table border=\"1\" align=\"center\"><tr><th>scheduleID</th>"
					+ "<th>HospitalID</th><th>HospitalName</th>" + "<th>DoctorID</th>" + "<th>DoctorName</th>"
					+ "<th>Speciality</th>" + "<th>Date</th>" + "<th>StartTime</th>" + "<th>EndTime</th>"
					+ "<th>RoomNumber</th>" + "<th>Status</th>" + "<th>Remove</th></tr>";

			String query = "select * from schedule";
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
		} catch (Exception e) {
			output = "Error while reading the items.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	





	public String deleteItem(String ID) {
		String output = "";
		try {
			Connection con = obj.connect();
			if (con == null) {
				return "Error while connecting to the database for deleting.";
			}

			// create a prepared statement
			String query = "delete from schedule where ID=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values

			preparedStmt.setInt(1, Integer.parseInt(ID));

			// execute the statement
			preparedStmt.execute();
			con.close();

			output = "Deleted successfully from schedule ";
		} catch (Exception e) {
			output = "Error while deleting the item.";
			System.err.println(e.getMessage());
		}

		return output;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public String updateItem(int id, String sid, String hid, String hname, String did, String dname, String special,
			String date, String start, String end, String room,String sat) {
		String output = "";

		try {
			Connection con = obj.connect();
			if (con == null) {

				return "Error while connecting to the database for updating.";
			}

			// create a prepared statement

			String query = "UPDATE schedule SET scheduleID=?, HospitalID=?, HospitalName=?, DoctorID=?, DoctorName=?, Speciality=?, Date=?, StartTime=?, EndTime=?, RoomNumber=?, Status=? WHERE ID=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values

			preparedStmt.setString(1, sid);
			preparedStmt.setString(2, hid);
			preparedStmt.setString(3, hname);
			preparedStmt.setString(4, did);
			preparedStmt.setString(5, dname);
			preparedStmt.setString(6, special);
			preparedStmt.setString(7, date);
			preparedStmt.setString(8, start);
			preparedStmt.setString(9, end);
			preparedStmt.setString(10, room);
			preparedStmt.setString(11, sat);
			preparedStmt.setInt(12, id);
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

	// view admin scheduled time by doctors to confirmations (Extract from schedule
	// table) (service method) --------------------------------
	
	

	public String DisplayDoctor() {
		String output = "";
		try {
			Connection con = obj.connect();
			if (con == null) {
				return "Error while connecting to the database for reading.";
			}
			// Prepare the html table to be displayed
			output = "<table border=\"1\" align=\"center\"><tr><th>scheduleID</th>"
					+ "<th>HospitalName</th><th>DoctorName</th>" + "<th>Speciality</th>" + "<th>Date</th>"
					+ "<th>StartTime</th>" + "<th>EndTime</th>" + "<th>RoomNumber</th>" + "<th>Status</th>";

			String query = "select * from schedule";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			// iterate through the rows in the result set
			while (rs.next()) {
				String id = rs.getString("ID");
				String sid = rs.getString("scheduleID");
				String hn = rs.getString("HospitalName");
				String dn = rs.getString("DoctorName");
				String sp = rs.getString("Speciality");
				String da = rs.getString("Date");
				String st = rs.getString("StartTime");
				String en = rs.getString("EndTime");
				String rm = rs.getString("RoomNumber");
				String sts = rs.getString("Status");

				// Add into the html table
				output += "<tr><td>" + sid + "</td>";
				output += "<td>" + hn + "</td>";
				output += "<td>" + dn + "</td>";
				output += "<td>" + sp + "</td>";
				output += "<td>" + da + "</td>";
				output += "<td>" + st + "</td>";
				output += "<td>" + en + "</td>";
				output += "<td>" + rm + "</td>";
				output += "<td>" + sts + "</td>";
				/*
				 * output += "<td>" + rm + "</td>"; output += "<td>" + sts + "</td>";
				 */
				// buttons
				/*
				 * output += "<td><input name=\"btnRemove\" " +
				 * " type=\"button\" value=\"Update\"></td>" +
				 * "<td><form method=\"post\" action=\"TimeCollector.jsp\">" +
				 * "<input name=\"btnRemove\" " +
				 * " type=\"submit\" value=\"Update\" action=\"TimeCollector.jsp\">" +
				 * "<input name=\"id\" type=\"hidden\" " + " value=\"" + id + "\">" +
				 * "</form></td></tr>";
				 */

			}
			con.close();
			// Complete the html table
			output += "</table>";
		} catch (Exception e) {
			output = "Error while reading the items.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	// insert only doctors confirmed schedule data to (docschedule table)
	// -------------------------------------

	public String insertSchedule(String sid, String hname, String dname, String special, String date, String start,
			String end, String room, String sts) {
		String output = "";
		try {
			Connection con = obj.connect();
			if (con == null) {
				return "Error while connecting to the database";
			}
			// create a prepared statement
			String query = "insert into docschedule"
					+ "(`scheduleID`, `HospitalName`, `DoctorName`, `Speciality`, `Date`, `StartTime`, `EndTime`, `RoomNumber`,`Status`)"
					+ " values (?, ?, ?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, sid);
			preparedStmt.setString(3, hname);
			preparedStmt.setString(4, dname);
			preparedStmt.setString(5, special);
			preparedStmt.setString(6, date);
			preparedStmt.setString(7, start);
			preparedStmt.setString(8, end);
			preparedStmt.setString(9, room);
			preparedStmt.setString(10, sts);

			// execute the statement
			preparedStmt.execute();
			// System.out.print("successfuly inserted");
			con.close();
			output = "Inserted successfully";
		} catch (Exception e) {
			output = "Error while inserting items";
			System.err.println(e.getMessage());
		}
		return output;
	}

	
	// insert only doctors confirmed schedule data to (docschedule table) (Service
	// method) -------------------------------------

	public String insertConfirmSchedule(String sid, String hname, String dname, String special, String date,
			String start, String end, String room, String sts) {
		String output = "";
		try {
			Connection con = obj.connect();
			if (con == null) {
				return "Error while connecting to the database";
			}
			// create a prepared statement
			String query = "insert into docschedule"
					+ "(`ID`, `scheduleID`, `HospitalName`, `DoctorName`, `Speciality`, `Date`, `StartTime`, `EndTime`, `RoomNumber`,`Status`)"
					+ " values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, sid);
			preparedStmt.setString(3, hname);
			preparedStmt.setString(4, dname);
			preparedStmt.setString(5, special);
			preparedStmt.setString(6, date);
			preparedStmt.setString(7, start);
			preparedStmt.setString(8, end);
			preparedStmt.setString(9, room);
			preparedStmt.setString(10, sts);

			// execute the statement
			preparedStmt.execute();
			// System.out.print("successfuly inserted");
			con.close();
			output = "Inserted successfully";
		} catch (Exception e) {
			output = "Error while inserting items";
			System.err.println(e.getMessage());
		}
		return output;
	}

	

	// Remove confirmed schedule details from docschedule table (Service method)
	// ------------------------

	public String RemoveRecord(String ID) {
		String output = "";
		try {
			Connection con = obj.connect();
			if (con == null) {
				return "Error while connecting to the database for deleting.";
			}

			// create a prepared statement
			String query = "delete from docschedule where ID=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values

			preparedStmt.setInt(1, Integer.parseInt(ID));

			// execute the statement
			preparedStmt.execute();
			con.close();

			output = "Deleted successfully";
		} catch (Exception e) {
			output = "Error while deleting the item.";
			System.err.println(e.getMessage());
		}

		return output;
	}

	// view confirmed schedule table details (Service method)
	// -----------------------------------

	public String ViewTable() {
		String output = "";
		try {
			Connection con = obj.connect();
			if (con == null) {
				return "Error while connecting to the database for reading.";
			}
			// Prepare the html table to be displayed
			output = "<table border=\"1\" align=\"center\"><tr><th>scheduleID</th>"
					+ "<th>HospitalName</th><th>DoctorName</th>" + "<th>Speciality</th>" + "<th>Date</th>"
					+ "<th>StartTime</th>" + "<th>EndTime</th>" + "<th>RoomNumber</th>" + "<th>Status</th>"
					+ "<th>Modifiy</th></tr>";
			String query = "select * from docschedule";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			// iterate through the rows in the result set
			while (rs.next()) {
				String id = rs.getString("ID");
				String sid = rs.getString("scheduleID");
				
				String hn = rs.getString("HospitalName");
				
				String dn = rs.getString("DoctorName");
				String sp = rs.getString("Speciality");
				String da = rs.getString("Date");
				String st = rs.getString("StartTime");
				String en = rs.getString("EndTime");
				String rm = rs.getString("RoomNumber");
				String sts = rs.getString("Status");

				// Add into the html table
				output += "<tr><td>" + sid + "</td>";
				output += "<td>" + hn + "</td>";
				output += "<td>" + dn + "</td>";
				output += "<td>" + sp + "</td>";
				output += "<td>" + da + "</td>";
				output += "<td>" + st + "</td>";
				output += "<td>" + en + "</td>";
				output += "<td>" + rm + "</td>";
				output += "<td>" + sts + "</td>";
				/*
				 * output += "<td>" + rm + "</td>"; output += "<td>" + sts + "</td>";
				 */
				// buttons
				output += "<td><input name=\"btnRemove\" "
						/* + " type=\"button\" value=\"Update\"></td>" */
						/* + "<td><form method=\"post\" action=\"TimeCollector.jsp\">" */
						/* + "<input name=\"btnRemove\" " */
						+ " type=\"submit\" value=\"Update\" action=\"TimeCollector.jsp\">"
						+ "<input name=\"id\" type=\"hidden\" " + " value=\"" + id + "\">" + "</form></td></tr>";
			}
			con.close();
			// Complete the html table
			output += "</table>";
		} catch (Exception e) {
			output = "Error while reading the items.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	// display to the patients if only accepted schedules (Service
	// method)----------------------------------

	public String DisplayPatients() {
		String output = "";
		try {
			Connection con = obj.connect();
			if (con == null) {
				return "Error while connecting to the database for reading.";
			}
			// Prepare the html table to be displayed
			output = "<table border=\"1\" align=\"center\"><tr><th>scheduleID</th>"
					+ "<th>HospitalName</th><th>DoctorName</th>" + "<th>Speciality</th>" + "<th>Date</th>"
					+ "<th>StartTime</th>" + "<th>EndTime</th>" + "<th>RoomNumber</th>" + "<th>Status</th>";
			/* + "<th>Modifiy</th></tr>"; */
			String query = "select * from schedule where Status = 'YES'";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			// iterate through the rows in the result set
			while (rs.next()) {
				String id = rs.getString("ID");
				String sid = rs.getString("scheduleID");
				// String hid = rs.getString("HospitalID");
				String hn = rs.getString("HospitalName");
				// String did = rs.getString("DoctorID");
				String dn = rs.getString("DoctorName");
				String sp = rs.getString("Speciality");
				String da = rs.getString("Date");
				String st = rs.getString("StartTime");
				String en = rs.getString("EndTime");
				String rm = rs.getString("RoomNumber");
				String sts = rs.getString("Status");

				insertConfirmSchedule(id, hn, dn, sp, da, st, en, rm, sts);

				// Add into the html table
				output += "<tr><td>" + sid + "</td>";
				output += "<td>" + hn + "</td>";
				output += "<td>" + dn + "</td>";
				output += "<td>" + sp + "</td>";
				output += "<td>" + da + "</td>";
				output += "<td>" + st + "</td>";
				output += "<td>" + en + "</td>";
				output += "<td>" + rm + "</td>";
				output += "<td>" + sts + "</td>";
				
			}
			con.close();
			// Complete the html table
			output += "</table>";
		} catch (Exception e) {
			output = "Error while reading the items.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	
	
	
	// Update only status for the given schedule id (Service method)
	// -----------------------------------------

	public String updateDoctorStatus(String id, String status) {
		String output = "";

		try {
			Connection con = obj.connect();
			if (con == null) {

				return "Error while connecting to the database for updating.";
			}

			// create a prepared statement

			String query = "UPDATE schedule SET Status=? WHERE scheduleID=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values

			preparedStmt.setString(1, status);
			preparedStmt.setString(2, id);
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
	
	
	
// Insert and Display to the patients only schedule confirmed doctors details	

	public String Test() {
		String output = "";
		try {
			Connection con = obj.connect();
			if (con == null) {
				return "Error while connecting to the database for reading.";
			}
			// Prepare the html table to be displayed

			String query = "select * from schedule where Status = 'YES'";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			// iterate through the rows in the result set
			while (rs.next()) {
				String id = rs.getString("ID");
				String sid = rs.getString("scheduleID");
				String hn = rs.getString("HospitalName");
				String dn = rs.getString("DoctorName");
				String sp = rs.getString("Speciality");
				String da = rs.getString("Date");
				String st = rs.getString("StartTime");
				String en = rs.getString("EndTime");
				String rm = rs.getString("RoomNumber");
				String sts = rs.getString("Status");

				insertConfirmSchedule(sid, hn, dn, sp, da, st, en, rm, sts);

			}
			con.close();
			// Complete the html table
			/* output += "</table>"; */
		} catch (Exception e) {
			output = "Error while transfering data to docschedule table";
			System.err.println(e.getMessage());
		}
		return output;
	}

	
	
	
	
	
	
	// request message insert method
	
	
	
	
	public String insertMessage(String sid,String did, String dname, String date, String message) {
		String output = "";
		try {
			Connection con = obj.connect();
			if (con == null) {
				return "Error while connecting to the database";
			}
			// create a prepared statement
			String query = "insert into request"
					+ "(`ID`, `scheduleID`, `DoctorID`, `DoctorName`,`Date`,`Message`)"
					+ " values (?, ?, ?, ?, ?, ?)";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, sid);
			preparedStmt.setString(3, did);
			preparedStmt.setString(4, dname);
			preparedStmt.setString(5, date);
			preparedStmt.setString(6, message);

			// execute the statement
			preparedStmt.execute();
			// System.out.print("successfuly inserted");
			con.close();
			output = "Inserted successfully";

		} catch (Exception e) {
			output = "Error while inserting items";
			System.err.println(e.getMessage());
		}

		return output;
	}

	
	
	// read request message table ------------------------------------
	
	
	public String readRequest() {
		String output = "";
		try {
			Connection con = obj.connect();
			if (con == null) {
				return "Error while connecting to the database for reading.";
			}
			// Prepare the html table to be displayed
			output = "<table border=\"1\" align=\"center\"><tr><th>scheduleID</th>"
					 + "<th>DoctorID</th>" + "<th>DoctorName</th>"
					 + "<th>Date</th>" 
					+ "<th>Message</th>";

			String query = "select * from request";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			// iterate through the rows in the result set
			while (rs.next()) {
				String id = rs.getString("ID");
				String sid = rs.getString("scheduleID");
				String did = rs.getString("DoctorID");
				String dn = rs.getString("DoctorName");
				String da = rs.getString("Date");
				String sts = rs.getString("Message");
				// Add into the html table
				output += "<tr><td>" + sid + "</td>";
				
				output += "<td>" + did + "</td>";
				output += "<td>" + dn + "</td>";
				
				output += "<td>" + da + "</td>";
				
				
				output += "<td>" + sts + "</td>";
				
			}
			con.close();
			// Complete the html table
			/* output += "</table>"; */
		} catch (Exception e) {
			output = "Error while reading the items.";
			System.err.println(e.getMessage());
		}
		return output;
	}
	
	
	
	
	// search doctors via doctor name and doctor id
	
	
	public String docSearch(String dname,String idd) {
		String output = "";
		try {
			Connection con = obj.connect();
			if (con == null) {
				return "Error while connecting to the database for reading.";
			}
			// Prepare the html table to be displayed
			output = "<table border=\"1\" align=\"center\"><tr><th>scheduleID</th>"
					+ "<th>HospitalID</th><th>HospitalName</th>" + "<th>DoctorID</th>" + "<th>DoctorName</th>"
					+ "<th>Speciality</th>" + "<th>Date</th>" + "<th>StartTime</th>" + "<th>EndTime</th>"
					+ "<th>RoomNumber</th>" + "<th>Status</th>" + "<th>Remove</th></tr>";

			String query = "select * from schedule where `DoctorName` like '%"+dname +"%' and `DoctorID` like '%"+idd +"%'";
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
		} catch (Exception e) {
			output = "Error while reading the items.";
			System.err.println(e.getMessage());
		}
		return output;
	}	
	
	
	
	
	
	
	  public search ShowTypeById (int docid)
	  
	  {
	  
	  List<search> list = viewTypes(docid);
	 
	  if(!list.isEmpty()) {
	 
	  return list.get(0);
	  
	 }
	  return null;
	  
	  }
	  
	  
	  
	  public List<search> viewTypes(int docid) {
	  
	  List<search> TypeList = new ArrayList<>();
	  
	  try { Connection con = obj.connect();
	  if (con == null) {
	  
	  System.out.println("Error While reading from database"); 
	  return TypeList;
	  
	  }
	  System.out.println("Error While reading from database");
	  String query;
	  
	  if(docid == 0) {
	  
	  query = "select * from schedule";
	  
	  }
	  
	  else {
	  
	 query = "select * from schedule where `ID`="+docid; 
	 } 
	  Statement stmt =con.createStatement();
	  ResultSet results = stmt.executeQuery(query);
	  
	  while (results.next()) {
	  
	  
	  
	  search type = new search(results.getInt("ID"), 
			  results.getInt("scheduleID"),
	  results.getString("HospitalID"),
	  results.getString("HospitalName"),
	  results.getString("DoctorID"),
	  results.getString("DoctorName"),
	  results.getString("Speciality"),
	  results.getString("Date"),
	  results.getString("StartTime"),
	  results.getString("EndTime"),
	  results.getInt("RoomNumber"),
	  results.getString("Status"));
	  
	  TypeList.add(type); 
	  }
	  con.close(); 
	  } catch (Exception e) {
		  
	  System.out.println("Error While Reading");
	  System.err.println(e.getMessage());

	  }
	  
	  return TypeList;
	  
	  }
	 
	
	
	
	
	




	
	
	
	
	
	
	
	
public search getHospital(int id) {
	
		 String output = ""; 
	
		 search h = new search();
		
		Connection con = obj.connect();
		if (con == null) {
			return h;
		}
		
		 output = "<table border=\"1\" align=\"center\"><tr><th>scheduleID</th>" +
		  "<th>HospitalID</th><th>HospitalName</th>" + "<th>DoctorID</th>" +
		  "<th>DoctorName</th>" + "<th>Speciality</th>" + "<th>Date</th>" +
		  "<th>StartTime</th>" + "<th>EndTime</th>" + "<th>RoomNumber</th>" +
		  "<th>Status</th>" + "<th>Remove</th></tr>";
		 
		
		
		
		String sql = "SELECT * FROM schedule where `ID`="+id;
		
		
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			
			while(rs.next()) {
				
				
				h.setId(rs.getInt(1));
				h.setSid(rs.getInt(2));
				h.setHospitalID(rs.getString(3));
				h.setHospitalName(rs.getString(4));
				h.setDocid(rs.getString(5));
				h.setDocname(rs.getString(6));
				h.setSpeciality(rs.getString(7));
				h.setDate(rs.getString(8));
				h.setStartTime(rs.getString(9));
				h.setEndTime(rs.getString(10));
				h.setRoomNo(rs.getInt(11));
				h.setStatus(rs.getString(12));
				
				
				
				
                  int idd = h.getId();
				  int sidd = h.getSid();
				  String hidd = h.getHospitalID();
				  String hnd = h.getHospitalName();
				  String didd = h.getDocid();
				  String dnd=h.getDocname(); 
				  String spd =h.getSpeciality();
				  String dad = h.getDate();
				  String std = h.getStartTime();
				  String end = h.getEndTime();
			      int rm = h.getRoomNo();	  
			      String status = h.getStatus();
			      
				  // Add into the html table output
				  
				output  += "<tr><td>" + sidd + "</td>"; output += "<td>" + hidd + "</td>"; output +=
				  "<td>" + hnd + "</td>"; output += "<td>" + didd + "</td>"; output += "<td>" +
				 dnd + "</td>"; output += "<td>" + spd + "</td>"; output += "<td>" + dad +
				  "</td>"; output += "<td>" + std + "</td>"; output += "<td>" + end + "</td>";
				  output += "<td>" + rm+ "</td>"; output += "<td>" + status + "</td>";
				 
				
			}
				
			
			
			con.close();
			// Complete the html table
			output += "</table>";
				
		}catch (Exception e) {
			
			System.out.println("Successfully executed..!");
			
			System.out.println(e);
		}
		
		return h;
	}
	

}




	

