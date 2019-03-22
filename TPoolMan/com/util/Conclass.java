package com.util;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conclass {

	public static Connection getCon() throws ClassNotFoundException, SQLException
	{
		
		 Class.forName("oracle.jdbc.driver.OracleDriver");
	     Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","tpool","hr");
	     
	     return con;
	}
	
	
}
