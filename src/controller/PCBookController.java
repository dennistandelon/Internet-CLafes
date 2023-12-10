package controller;

import java.sql.Date;
import java.util.Vector;

import model.PC;
import model.PCBook;
import model.TransactionHeader;
import util.StageManager;

public class PCBookController {

	public static String DeleteBookData(int BookID) {
		
		// Accessing PCBook model public interface
		if(!PCBook.DeleteBookData(BookID)) {
			return "Failed to delete pc book data";
		}
		
		return "Success";
	}
	
	public static PCBook GetPCBookedData(String PcID, Date date) {
		// Accessing PCBook model public interface
		return PCBook.GetPCBookedData(PcID, date);
	}

	public static String AssignUserToNewPC(int BookID, String NewPCID) {
		
		// Check if the NewPCID string is empty or only contains spaces
		if(NewPCID.isBlank()) {
			return "New PC ID cannot be blank";
		}
		
		// Check if the PC selected is booked
		if(GetPCBookedData(NewPCID, GetPCBookedDetail(BookID).getBookedDate()) != null) {
			return "PC didn't available on selected date";
		}
		
		// Check if the the PC_ID is not valid
		if(PCController.GetPCDetail(NewPCID) == null) {
			return "PC doesn't exist";
		}
		
		// Check the status of the PC
		if(!PCController.GetPCDetail(NewPCID).getPC_Condition().equals("Usable")) {
			return "PC is under maintenance or broken";
		}
		
		// Accessing PCBook model public interface
		if(!PCBook.AssignUserToNewPC(BookID, NewPCID)) {
			return "Failed to assign user";
		}
		
		return "Success";
	}
	

	public static PCBook GetPCBookedDetail(int BookID) {
		// Accessing PCBook model public interface
		return PCBook.GetPCBookedDetail(BookID);
	}
	
	public static String AddNewBook(String PcID, int UserID, Date BookedDate) {
		
		PC pcToBook = PCController.GetPCDetail(PcID);
		
		// Check if the PC ID is not valid
		if(pcToBook == null) {
			return "PC with ID: "+ PcID +" doesn't exist";
		}
		
		// Check if the PC is not usable
		if(!pcToBook.getPC_Condition().equals("Usable")) {
			return "This PC is not available for book";
		}
		
		boolean isBooked = false;

		// Validate if the pc is booked
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
		
		// Accessing PCBook model public interface
		if(!PCBook.AddNewBook(PcID, UserID, BookedDate)) {
			return "Failed to add new book";
		}
		
		return "Success";
	}
	
	public static String FinishBook(Vector<PCBook> PCBooks) {
	
		Vector<TransactionHeader> ths = TransactionController.GetAllTransactionHeaderData();
		
		// Accessing PCBook model public interface
		if(!PCBook.FinishBook(PCBooks)) {
			return "Failed to finish books";
		}
		
		// variable definition for transaction parameter
		int staffid = StageManager.getInstance().getUser().getUserID();
		int headerID = 1;
		if(ths != null) {
			headerID = ths.get(ths.size()-1).getTransactionID() + 1;
		} 
		
		// Generate new Transaction from the finished book
		TransactionController.AddTransaction(headerID, PCBooks, staffid);
		
		return "Success";
	}
	
	public static Vector<PCBook> GetAllPCBookedData(){
		// Accessing PCBook model public interface
		return PCBook.GetAllPCBookedData();
	}
	
	public static Vector<PCBook> GetPCBookedByDate(Date date){
		// Accessing PCBook model public interface
		return PCBook.GetPCBookedByDate(date);
	}
	
}
