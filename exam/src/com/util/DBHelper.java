package com.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBHelper {
	
	private final static String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
    private final static String DB_URL = "jdbc:mysql://localhost:3306/exam_db";
    
	public static Connection getConnection() {
    	Connection conn = null;
		  String userName = "root"; 
		  String password = "";
		  try {
		  Class.forName(JDBC_DRIVER);
		  conn = DriverManager.getConnection(DB_URL,userName,password);
		  System.out.println("Connection created successfully");
		  } catch (Exception e) {
		  e.printStackTrace();
		  }
		  return conn;
	}

}
