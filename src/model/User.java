package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

import util.Database;

public class User {

	private int UserID;
	private String UserName;
	private String UserPassword;
	private String UserAge;
	private String UserRole;
	
	private static Database db = Database.getConnection();
	
	public User(int userID, String userName, String userPassword, String userAge, String userRole) {
		super();
		UserID = userID;
		UserName = userName;
		UserPassword = userPassword;
		UserAge = userAge;
		UserRole = userRole;
	}
	
	/*
	 * Method to get all user data from databases
	 * @return userList, all user data exist in the database 
	 * */
	public static Vector<User> GetAllUserData(){
		
		Vector<User> userList = new Vector<User>();
		
		String Query = "SELECT * FROM user";
		
		try {
			// Get result from query execution
			ResultSet rs = db.executeQuery(Query);
			while(rs.next()) {
				int userID = rs.getInt("UserID");
				String userName = rs.getString("UserName");
				String userPassword = rs.getString("UserPassword");
				String userAge = rs.getString("UserAge");
				String userRole = rs.getString("UserRole");
				
				userList.add(new User(userID, userName, userPassword, userAge, userRole));
			}
		} catch (Exception e) {
			
		}
		
		return userList;
	}
	
	/*
	 * Method to get user data from databases
	 * @params 	Username, the username inputted by user
	 * 			Password, the password inputted by user
	 * @return User if there is user exist in the database 
	 * 			or null if there is no valid user exist in the database
	 * */
	public static User GetUserData(String Username, String Password) {
		
		String query = "SELECT * FROM user WHERE Username = ? and UserPassword = ?";
		
		try {
			// Get result from parameterized query execution			
			PreparedStatement ps = db.prepareStatement(query);
			ps.setString(1, Username);
			ps.setString(2, Password);
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {

				int userID = rs.getInt("UserID");
				String userName = rs.getString("UserName");
				String userPassword = rs.getString("UserPassword");
				String userAge = rs.getString("UserAge");
				String userRole = rs.getString("UserRole");
				
				return new User(userID, userName, userPassword, userAge, userRole);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return null;
	}
	
	/*
	 * Method to get user data from databases
	 * @params 	Username, the username inputted by user
	 * 			Password, the password inputted by user
	 * 			Age, the user age inputted by user
	 * @return true if execution success, false if execution failed
	 * */
	public static boolean AddNewUser(String Username, String Password, Integer Age) {
		
		String query = "INSERT INTO user (UserName,UserPassword,UserAge,UserRole)"
				+ " VALUES(?,?,?,'Customer')";
		
		try {
			PreparedStatement ps = db.prepareStatement(query);
			ps.setString(1, Username);
			ps.setString(2, Password);
			ps.setInt(3, Age);
			
			ps.execute();
			
		} catch (Exception e) {
			return false;
		}
		
		return true;
	}
	
	
	/*
	 * Method to change UserRole data from databases
	 * @params 	UserID, the id of the user to change role
	 * 			NewRole, the new user role between(Customer, Admin, Computer Technician, Operator)
	 * @return techList, all technician data
	 * */
	public static boolean ChangeUserRole(int UserID, String NewRole) {
		
		String query = "UPDATE user SET UserRole= ? WHERE UserID = ?";
		
		try {
			PreparedStatement ps = db.prepareStatement(query);
			ps.setString(1, NewRole);
			ps.setInt(2, UserID);
			
			ps.execute();
		} catch (Exception e) {
			return false;
		}
		
		return true;
	}
	
	/*
	 * Method to get all technician data from databases
	 * (Technician = User with UserRole Computer Technician)
	 * @return techList, all technician data
	 * */
	public static Vector<User> GetAllTechnician(){
		String query = "SELECT * FROM user WHERE UserRole = 'Computer Technician'";
		
		Vector<User> techList = new Vector<User>();
		
		try {			
			ResultSet rs = db.executeQuery(query);
			
			while(rs.next()) {

				int userID = rs.getInt("UserID");
				String userName = rs.getString("UserName");
				String userPassword = rs.getString("UserPassword");
				String userAge = rs.getString("UserAge");
				String userRole = rs.getString("UserRole");
				
				techList.add(new User(userID, userName, userPassword, userAge, userRole));
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return techList;
	}
	
	// Getter & Setter 
	public int getUserID() {
		return UserID;
	}

	public void setUserID(int userID) {
		UserID = userID;
	}

	public String getUserName() {
		return UserName;
	}

	public void setUserName(String userName) {
		UserName = userName;
	}

	public String getUserPassword() {
		return UserPassword;
	}

	public void setUserPassword(String userPassword) {
		UserPassword = userPassword;
	}

	public String getUserAge() {
		return UserAge;
	}

	public void setUserAge(String userAge) {
		UserAge = userAge;
	}

	public String getUserRole() {
		return UserRole;
	}

	public void setUserRole(String userRole) {
		UserRole = userRole;
	}

}
