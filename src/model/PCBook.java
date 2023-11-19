package model;

import java.sql.Date;

public class PCBook {

	private int BookID;
	private int PC_ID;
	private int UserID;
	private Date BookedDate;
	
	public PCBook(int bookID, int pC_ID, int userID, Date bookedDate) {
		super();
		BookID = bookID;
		PC_ID = pC_ID;
		UserID = userID;
		BookedDate = bookedDate;
	}

	public int getBookID() {
		return BookID;
	}

	public void setBookID(int bookID) {
		BookID = bookID;
	}

	public int getPC_ID() {
		return PC_ID;
	}

	public void setPC_ID(int pC_ID) {
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
