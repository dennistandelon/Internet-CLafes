package controller;

import java.sql.Date;
import java.util.Vector;

import model.PCBook;

public class PCBookController {

	public static String DeleteBookData(int BookID) {
		
		if(!PCBook.DeleteBookData(BookID)) {
			return "Failed to delete pc book data";
		}
		
		return "Success";
	}
	
	public static PCBook GetPCBookedData(String PcID, Date date) {
		return PCBook.GetPCBookedData(PcID, date);
	}

	public static String AssignUserToNewPC(int UserID, String NewPCID) {
		
		if(!PCBook.AssignUserToNewPC(UserID, NewPCID)) {
			return "Failed to assign user";
		}
		
		return "Success";
	}
	
	public static PCBook GetPCBookedDetail(int BookID) {
		return PCBook.GetPCBookedDetail(BookID);
	}
	
	public static String AddNewBook(String PcID, int UserID, Date BookedDate) {
		
		if(!PCBook.AddNewBook(PcID, UserID, BookedDate)) {
			return "Failed to add new book";
		}
		
		return "Success";
	}
	
	public static String FinishBook(Vector<PCBook> PCBooks) {
	
		if(!PCBook.FinishBook(PCBooks)) {
			return "Failed to finish books";
		}
		
		return "Success";
	}
	
	public static Vector<PCBook> GetAllPCBookedData(){
		return PCBook.GetAllPCBookedData();
	}
	
	public static Vector<PCBook> GetPCBookedByDate(Date date){
		return PCBook.GetPCBookedByDate(date);
	}
	
}
