package util;

import java.sql.*;


/*
 * This class using Singleton design pattern
 * Only one instance allowed, used as the global instance
 * */

public final class Database {

	/*
	 * Database Configuration Variables
	 * 
	 * Replace with your Own Database Configuration,
	 * This application using default MySQL Server Configuration
	 * */
	private final String USERNAME = "root"; 
	private final String PASSWORD = ""; 
	private final String HOST = "localhost"; 
	private final String PORT = "3306"; 
	private final String DATABASE = "warnetdb";
	private final String CONNECTION = String.format("jdbc:mysql://%s:%s/%s", HOST, PORT, DATABASE);
	
	private Connection connect; // Database Connection Instance
	private Statement statement; // SQL Query Statement Instance
	private static Database db; // This class global instance
	
	private Database() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connect = DriverManager.getConnection(CONNECTION, USERNAME, PASSWORD);
			statement = connect.createStatement();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Database Connection Failed! Application Terminated.");
			System.exit(1);
		}
	}
	
	// Method to access global Database instance
	public static Database getConnection() {
		
		// Synchronized block for Concurrency Control
		synchronized (Database.class) {			
			/*
			 *  Return new instance if the Database has never been instantiate,
			 *  else will return instantiated database instance
			 * */
			if(db == null) {
				db = new Database();
			}
			return db;
		}
	}
	
	
	/*
	 * This method used to execute SELECT Statement
	 * */
	public ResultSet executeQuery(String Query) {
		ResultSet rs = null;
		try {
			rs = statement.executeQuery(Query);
		} catch (Exception e) {
			System.out.println("Failed to Execute Query");
		}
		return rs;
	}
	
	
	/* 
	 * This method used to execute INSERT, UPDATE, DELETE Statement
	 * */
	public boolean executeUpdate(String Query) {
		try {
			statement.executeUpdate(Query);
		} catch (Exception e) {
			System.out.println("Failed to Execute Query");
			return false;
		}
		return true;
	}
	
	
	/*
	 * This method used to execute SQL Statement using prepared statement
	 * Gain: Avoid SQL Injection on query
	 * Loss: Slower execution time than normal executeQuery
	 * */
	public PreparedStatement prepareStatement(String Query) {
		PreparedStatement ps = null;
		try {
			ps = connect.prepareStatement(Query);
		} catch (Exception e) {
			System.out.println("Error while generating prepared statement");
		}
		return ps;
	}
	

}