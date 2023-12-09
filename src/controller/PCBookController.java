package controller;

import java.sql.Date;
import java.util.Vector;

import model.PC;
import model.PCBook;
import model.TransactionHeader;
import util.StageManager;

public class PCBookController {

	public static String DeleteBookData(int BookID) {
		
		// Trying
		if(!PCBook.DeleteBookData(BookID)) {
			return "Failed to delete pc book data";
		}
		
		return "Success";
	}
	
	/*
	 * Delegate request to the model layer to
	 * get PCBook instance by PcID and date
	 * */
	public static PCBook GetPCBookedData(String PcID, Date date) {
		return PCBook.GetPCBookedData(PcID, date);
	}

	
	/*
	 * 
	 * */
	public static String AssignUserToNewPC(int BookID, String NewPCID) {
		
		if(NewPCID.isBlank()) {
			return "New PC ID cannot be blank";
		}
		
		if(GetPCBookedData(NewPCID, GetPCBookedDetail(BookID).getBookedDate()) != null) {
			return "PC didn't available on selected date";
		}
		
		if(PCController.GetPCDetail(NewPCID) == null) {
			return "PC doesn't exist";
		}
		
		if(!PCController.GetPCDetail(NewPCID).getPC_Condition().equals("Usable")) {
			return "PC is under maintenance or broken";
		}
		
		if(!PCBook.AssignUserToNewPC(BookID, NewPCID)) {
			return "Failed to assign user";
		}
		
		return "Success";
	}
	
	
	/*
	 * Delegate request to the model layer to
	 * get PCBook instance by BookID
	 * */
	public static PCBook GetPCBookedDetail(int BookID) {
		return PCBook.GetPCBookedDetail(BookID);
	}
	
	public static String AddNewBook(String PcID, int UserID, Date BookedDate) {
		
		PC pcToBook = PCController.GetPCDetail(PcID);
		if(pcToBook == null) {
			return "PC with ID: "+ PcID +" doesn't exist";
		}
		
		if(!pcToBook.getPC_Condition().equals("Usable")) {
			return "This PC is not available for book";
		}
		
		boolean isBooked = false;

		Vector<PCBook> books = PCBookController.GetPCBookedByDate(BookedDate);
		for (PCBook pcBook : books) {
			if(pcBook.getPC_ID().equals(PcID)) {
				isBooked = true;
				break;
			}
		}
		
		if(isBooked) {
			return "This PC have been booked for this date";
		}
		
		if(!PCBook.AddNewBook(PcID, UserID, BookedDate)) {
			return "Failed to add new book";
		}
		
		return "Success";
	}
	
	public static String FinishBook(Vector<PCBook> PCBooks) {
	
		Vector<TransactionHeader> ths = TransactionController.GetAllTransactionHeaderData();
		
		if(!PCBook.FinishBook(PCBooks)) {
			return "Failed to finish books";
		}
		
		int staffid = StageManager.getInstance().getUser().getUserID();
		int headerID = 1;
		if(ths != null) {
			headerID = ths.get(ths.size()-1).getTransactionID() + 1;
		} 
		TransactionController.AddTransaction(headerID, PCBooks, staffid);
		
		return "Success";
	}
	
	
	/*
	 * Delegate request to the model layer to 
	 * get All data of PCBook in a Vector<PCBook> instance
	 * */
	public static Vector<PCBook> GetAllPCBookedData(){
		return PCBook.GetAllPCBookedData();
	}
	
	/*
	 * Delegate request to the model layer to 
	 * get All data of PCBook in a Vector<PCBook> instance by date
	 * */
	public static Vector<PCBook> GetPCBookedByDate(Date date){
		return PCBook.GetPCBookedByDate(date);
	}
	
}
