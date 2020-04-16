package com.login.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
	
	public static Connection createConnection()
	{
	Connection con = null;
	String url = "jdbc:mysql://127.0.0.1:3306/hosp?serverTimezone=UTC";
	String username = "root";
	String password = "";

	try
	{
	try
	{
	Class.forName("com.mysql.jdbc.Driver");
	}
	catch (ClassNotFoundException e)
	{
	e.printStackTrace();
	}
	con = DriverManager.getConnection(url, username, password);
	System.out.println("Post establishing a DB connection - "+con);
	}
	catch (Exception e)
	{
	e.printStackTrace();
	}

	return con;
	}
	
	public Connection connect()
	{
		
 
		 Connection con = null;
	try
	{
	Class.forName("com.mysql.jdbc.Driver");
	con= DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/hospitaldb",
	"root", "");
	//For testing
	System.out.println("Successfully connected---1");
	
	}
	catch(Exception e)
	{
	e.printStackTrace();
	}
	
	

	return con;
	}
}
