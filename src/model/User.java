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
	
	public static Vector<User> GetAllUserData(){
		
		Vector<User> userList = new Vector<User>();
		
		String Query = "SELECT * FROM user";
		
		try {
			ResultSet rs = db.executeQuery(Query);
			while(rs.next()) {
				int userID = rs.getInt("UserID");
				String userName = rs.getString("UserName");
				String userPassword = rs.getString("UserPassword");
				String userAge = rs.getString("UserPassword");
				String userRole = rs.getString("UserRole");
				
				userList.add(new User(userID, userName, userPassword, userAge, userRole));
			}
		} catch (Exception e) {
			
		}
		
		return userList;
	}
	
	public static User GetUserData(String Username, String Password) {
		
		String query = "SELECT * FROM user WHERE Username = ?";
		
		try {			
			PreparedStatement ps = db.prepareStatement(query);
			ps.setString(1, Username);
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {

				int userID = rs.getInt("UserID");
				String userName = rs.getString("UserName");
				String userPassword = rs.getString("UserPassword");
				String userAge = rs.getString("UserPassword");
				String userRole = rs.getString("UserRole");
				
				return new User(userID, userName, userPassword, userAge, userRole);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return null;
	}
	
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
	
	
	public static Vector<User> GetAllTechnician(){
		String query = "SELECT * FROM user WHERE Role = 'Computer Technician'";
		
		Vector<User> techList = new Vector<User>();
		
		try {			
			ResultSet rs = db.executeQuery(query);
			
			while(rs.next()) {

				int userID = rs.getInt("UserID");
				String userName = rs.getString("UserName");
				String userPassword = rs.getString("UserPassword");
				String userAge = rs.getString("UserPassword");
				String userRole = rs.getString("UserRole");
				
				techList.add(new User(userID, userName, userPassword, userAge, userRole));
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return techList;
	}

	
	// Method do search username by UserID
	// Used for better search while adding  Transaction Detail
	public static String searchName(int userID) {
		String query = "SELECT * FROM user WHERE UserID = ?";
		
		try {			
			PreparedStatement ps = db.prepareStatement(query);
			ps.setInt(1, userID);
			
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				return rs.getString("Username");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return "";
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
