package com.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBHelper {
	
	private final static String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
    //private final static String DB_URL = "jdbc:mysql://localhost:3306/epariksh_exam_db";
	private final static String DB_URL = "jdbc:mysql://localhost:3306/epariksh_angelInst_exam_db";
	public final static String DB_NAME = "epariksh_angelInst_exam_db.";
	public final static String ANGEL_INST_ADMIN1 = "angel_admin1";
	public final static String ANGEL_INST_ADMIN2 = "angel_admin2";

	public static Connection getConnection() {
		//For DVL
		/*Connection conn = null;
		String userName = "epariksh_test"; 
		String password = "test@123";*/
		
		//For Angel Institute - PROD
		Connection conn = null;
		  String userName = "epariksh_exam"; 
		  String password = "ParikshaPanel$125";
		  
		  //For local development
		  /*Connection conn = null;
		  String userName = "root"; 
		  String password = "root";*/
		  
		  try {
		  Class.forName(JDBC_DRIVER);
		  conn = DriverManager.getConnection(DB_URL,userName,password);
		  System.out.println("Connection created successfully");
		  } catch (Exception e) {
		  e.printStackTrace();
		  }
		  return conn;
		  /*Connection conn = null;
		  String userName = "root"; 
		  String password = "root";
		  try {
		  Class.forName(JDBC_DRIVER);
		  conn = DriverManager.getConnection(DB_URL,userName,password);
		  System.out.println("Connection created successfully");
		  } catch (Exception e) {
		  e.printStackTrace();
		  }
		  return conn;*/
	}

}
