package model;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

import util.Database;

public class PCBook {

	private int BookID;
	private String PC_ID;
	private int UserID;
	private Date BookedDate;
	
	private static Database db = Database.getConnection();
	
	public PCBook(int bookID, String pC_ID, int userID, Date bookedDate) {
		super();
		BookID = bookID;
		PC_ID = pC_ID;
		UserID = userID;
		BookedDate = bookedDate;
	}

	
	public static boolean DeleteBookData(int BookID) {
		String query = "DELETE FROM pcbook WHERE BookID = ?";
		
		try {
			PreparedStatement ps = db.prepareStatement(query);
			ps.setInt(1, BookID);
			
			ps.execute();
		} catch (Exception e) {
			return false;
		}
		
		return true;
	}
	
	public static PCBook GetPCBookedData(String PcID, Date date) {
		String query = "SELECT * FROM pcbook WHERE PC_ID = ? AND BookedDate = ?";
		
		try {			
			PreparedStatement ps = db.prepareStatement(query);
			ps.setString(1, PcID);
			ps.setDate(2, date);
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {

				int bookID = rs.getInt("BookID");
				String pC_ID = rs.getString("PC_ID");
				int userID = rs.getInt("UserID");
				Date bookedDate = rs.getDate("BookedDate");
				
				return new PCBook(bookID, pC_ID, userID, bookedDate);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return null;
	}
	
	public static PCBook GetPCBookedDetail(int BookID) {
		String query = "SELECT * FROM pcbook WHERE BookID = ?";
		
		try {			
			PreparedStatement ps = db.prepareStatement(query);
			ps.setInt(1, BookID);
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {

				int bookID = rs.getInt("BookID");
				String pC_ID = rs.getString("PC_ID");
				int userID = rs.getInt("UserID");
				Date bookedDate = rs.getDate("BookedDate");
				
				return new PCBook(bookID, pC_ID, userID, bookedDate);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return null;
	}
	
	public static boolean AddNewBook(String PcID, int UserID, Date BookedDate) {
		
		String query = "INSERT INTO pcbook (PC_ID,UserID,BookedDate) VALUES(?,?,?)";
		
		try {
			PreparedStatement ps = db.prepareStatement(query);
			ps.setString(1, PcID);
			ps.setInt(2, UserID);
			ps.setDate(3, BookedDate);
			
			ps.execute();
		} catch (Exception e) {
			return false;
		}
		
		return true;
	}
	
	public static boolean FinishBook(Vector<PCBook> PCBooks) {
	
		try {
			for (PCBook pcBook : PCBooks) {
				if(!PCBook.DeleteBookData(pcBook.getBookID())) {
					return false;
				}
			}
		} catch (Exception e) {
			return false;
		}
		
		return true;
	}
	
	public static Vector<PCBook> GetAllPCBookedData(){
		Vector<PCBook> booklist = new Vector<PCBook>();
		
		String Query = "SELECT * FROM pcbook";
		
		try {
			ResultSet rs = db.executeQuery(Query);
			while(rs.next()) {
				int bookID = rs.getInt("BookID");
				String pC_ID = rs.getString("PC_ID");
				int userID = rs.getInt("UserID");
				Date bookedDate = rs.getDate("BookedDate");
				
				booklist.add(new PCBook(bookID, pC_ID, userID, bookedDate));
			}
		} catch (Exception e) {
			
		}
		
		return booklist;
	}
	
	public static Vector<PCBook> GetPCBookedByDate(Date date){
		Vector<PCBook> booklist = new Vector<PCBook>();
		
		String query = "SELECT * FROM pcbook WHERE BookedDate = ?";
		
		try {			
			PreparedStatement ps = db.prepareStatement(query);
			ps.setDate(1, date);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {

				int bookID = rs.getInt("BookID");
				String pC_ID = rs.getString("PC_ID");
				int userID = rs.getInt("UserID");
				Date bookedDate = rs.getDate("BookedDate");
				booklist.add(new PCBook(bookID, pC_ID, userID, bookedDate));
			}
		} catch (Exception e) {
			
		}
		
		return booklist;
	}
	
	public static boolean AssignUserToNewPC(int BookID, String NewPCID) {
		String query = "UPDATE pcbook SET PC_ID = ? WHERE BookID = ?";
		
		try {
			PreparedStatement ps = db.prepareStatement(query);
			ps.setString(1, NewPCID);
			ps.setInt(2, BookID);
			
			ps.execute();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
	
	
	
	// Getter & Setter
	public int getBookID() {
		return BookID;
	}

	public void setBookID(int bookID) {
		BookID = bookID;
	}

	public String getPC_ID() {
		return PC_ID;
	}

	public void setPC_ID(String pC_ID) {
		PC_ID = pC_ID;
	}

	public int getUserID() {
		return UserID;
	}

	public void setUserID(int userID) {
		UserID = userID;
	}

	public Date getBookedDate() {
		return BookedDate;
	}

	public void setBookedDate(Date bookedDate) {
		BookedDate = bookedDate;
	}
	
	
}
