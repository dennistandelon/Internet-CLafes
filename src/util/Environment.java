package util;

public class Environment {

	// Define Constants used in the application.
	public static int SCREEN_WIDTH = 800;
	public static int SCREEN_HEIGHT = 520;
	
	// Define Databases Credentials Constants
	public static String USERNAME = "root";
	public static String PASSWORD = "";
	public static String HOST = "localhost";
	public static String PORT = "3306";
	public static String DATABASE = "warnetdb";
	public static String CONNECTION = String.format("jdbc:mysql://%s:%s/%s", HOST, PORT, DATABASE);

}
