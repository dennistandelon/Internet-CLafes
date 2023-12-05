package controller;

import java.util.Vector;

import model.User;

public class UserController {
	
	public static User GetUserData(String Username, String Password) {
		return User.GetUserData(Username, Password);
	}

	public static String AddNewUser(String Username, String Password, String ConfirmPassword, Integer Age) {

		if(Username.isBlank()) {
			return "Username error: Cannot be empty!";
		}
		
		if(Username.length() < 7) {
			return "Username error: Minimal 7 character long.";
		}
		
		if(!isUnique(Username)) {
			return "Username error: Must be unique.";
		}
		
		if(Password.isBlank()) {
			return "Password error: Password cannot be empty!";
		}
		
		if(Password.length() < 6) {
			return "Password error: Minimal 6 Character long.";
		}
		
		if(!isAlphanumeric(Password)) {
			return "Password error: Must be Alphanumeric";
		}
		
		if(ConfirmPassword.isBlank()) {
			return "Password error: Confirm Password cannot be empty!";
		}
		
		if(!ConfirmPassword.equals(Password)) {
			return "Password error: Confirm password must same with Password.";
		}
		
		if(Age < 13 || Age > 65) {
			return "Age error: Must be between 13 – 65 (inclusive).";
		}
		
		if(User.AddNewUser(Username, Password, Age)) {
			return "Success registering new user";
		}
		
		return "Error data";
	}
	
	public static String ChangeUserRole(int UserID, String NewRole) {
		
		if(!NewRole.equals("Admin") && !NewRole.equals("Customer") && !NewRole.equals("Operator") && !NewRole.equals("Computer Technician")) {
			return "Role Must be selected";
		}
		
		if(!User.ChangeUserRole(UserID, NewRole)) {
			return "Failed to change new Role";
		}
		
		return "Success";
	}
	
	public static Vector<User> GetAllTechnician(){
		return User.GetAllTechnician();
	}
	
	public static Vector<User> GetAllUserData(){
		return User.GetAllUserData();
	}
	
	/* -- Additional Method --
	 * */
	
	// Method to check username is unique or no
	private static boolean isUnique(String username) {
		Vector<User> userList = User.GetAllUserData();

		for (User user : userList) {
			if(username.toLowerCase().equals(user.getUserName().toLowerCase())) {
				return false;
			}
		}
		
		return true;
	}
	
	// Method to check is the password alphanumeric or no
	private static boolean isAlphanumeric(String password) {
		boolean containsNumber = false;
		boolean containsAlphabet = false;
		
		for (int i=0; i<password.length(); i++) {
            char c = password.charAt(i);
            if (!Character.isLetterOrDigit(c)) {
            	return false;
            } 
            if(!containsNumber){
            	if(Character.isDigit(c)) {
            		containsNumber = true;
            	}
            } 
            if(!containsAlphabet) {
            	if(Character.isLetter(c)) {
            		containsAlphabet = true;
            	}
            }
        }
		
		if(!containsAlphabet || !containsNumber) {
			return false;
		}
		
		return true;
	}
	
	/*
	 * Method to check login credential
	 * */
	public static String validateLogin(String username, String password) {
		if(username.isBlank() || password.isBlank()) {
			return "Input cannot be blank!";
		}
		
		User user = User.GetUserData(username, password);
		if(user == null) {
			return "User doesn't exist";
		}
		
		if(!user.getUserPassword().equals(password)) {
			return "Invalid Password!";
		}
		
		return "success";
	}
	
	
	
}
