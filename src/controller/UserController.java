package controller;

import java.util.Vector;

import model.User;

public class UserController {
	
	public static User GetUserData(String Username, String Password) {
		// Accessing User model public interface
		return User.GetUserData(Username, Password);
	}

	public static String AddNewUser(String Username, String Password, String ConfirmPassword, Integer Age) {

		// Check if the Username string is empty or only contains spaces
		if(Username.isBlank()) {
			return "Username error: Cannot be empty!";
		}
		
		// Check if the Username is less then 7 characters
		if(Username.length() < 7) {
			return "Username error: Minimal 7 character long.";
		}
		
		// Check if the username is not unique
		Vector<User> userList = User.GetAllUserData();

		boolean isUnique = true;
		for (User user : userList) {
			if(Username.toLowerCase().equals(user.getUserName().toLowerCase())) {
				isUnique = false;
				break;
			}
		}
		
		if(!isUnique) {
			return "Username error: Must be unique.";
		}
		
		// Check if the password is empty or only conatins blank spaces
		if(Password.isBlank()) {
			return "Password error: Password cannot be empty!";
		}
		
		// Check if the password length is less than 6 characters
		if(Password.length() < 6) {
			return "Password error: Minimal 6 Character long.";
		}
		
		// Check the password is alphanumeric or not
		boolean isAlphanumeric = true;
		boolean containsNumber = false;
		boolean containsAlphabet = false;
		
		for (int i=0; i<Password.length(); i++) {
            char c = Password.charAt(i);
            if (!Character.isLetterOrDigit(c)) {
            	isAlphanumeric = false;
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
			isAlphanumeric = false;
		}
		
		if(!isAlphanumeric) {
			return "Password error: Must be Alphanumeric";
		}
		
		// Check if the confirm password length is empty or only contains blank spaces
		if(ConfirmPassword.isBlank()) {
			return "Password error: Confirm Password cannot be empty!";
		}
		
		// Check if the confirm password is not equals to the password
		if(!ConfirmPassword.equals(Password)) {
			return "Password error: Confirm password must same with Password.";
		}
		
		// Check if the age is not between 13-65 inclusive
		if(Age < 13 || Age > 65) {
			return "Age error: Must be between 13 – 65 (inclusive).";
		}
		
		// Accessing User model public interface
		if(User.AddNewUser(Username, Password, Age)) {
			return "Success registering new user";
		}
		
		return "Error data";
	}
	
	public static String ChangeUserRole(int UserID, String NewRole) {
		
		// Validate user role between Admin, Customer, Operator, Computer Technician
		if(!NewRole.equals("Admin") && !NewRole.equals("Customer") && !NewRole.equals("Operator") && !NewRole.equals("Computer Technician")) {
			return "Role Must be selected";
		}
		
		// Accessing User model public interface
		if(!User.ChangeUserRole(UserID, NewRole)) {
			return "Failed to change new Role";
		}
		
		return "Success";
	}
	
	public static Vector<User> GetAllTechnician(){
		// Accessing User model public interface
		return User.GetAllTechnician();
	}
	
	public static Vector<User> GetAllUserData(){
		// Accessing User model public interface
		return User.GetAllUserData();
	}
	
}
