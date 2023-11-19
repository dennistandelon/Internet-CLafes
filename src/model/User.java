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
	
	//Additional Attribute for Database call
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
			
			while(rs.next()) {

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
				+ " VALUES('" + Username + "','" + Password + "'," + Age + ",'Customer')";
		
		try {
			db.executeUpdate(query);
		} catch (Exception e) {
			return false;
		}
		
		return true;
	}

	
	
	// --- Getter & Setter ---
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
