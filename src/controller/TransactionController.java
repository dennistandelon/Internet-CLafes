package controller;

import java.util.Vector;

import model.*;

public class TransactionController {

	/*
	 * Method to add new Transaction header and
	 * transaction detail data
	 * */
	public static String AddTransaction(int TransactionID, Vector<PCBook> PcBook, int StaffID) {
		if(!TransactionHeader.AddNewTransactionHeader(TransactionID,StaffID, PcBook.get(0).getBookedDate())) {
			return "Error while adding new transaction header";
		}
		
		if(!TransactionDetail.AddTransactionDetail(TransactionID, PcBook)){
			return "Error while adding new transaction details";
		}
		
		return "Success";
	}
	
	public static Vector<TransactionHeader> GetAllTransactionHeaderData(){
		// Accessing Transaction Header model public interface
		return TransactionHeader.GetAllTransactionHeaderData();
	}
	
	public static Vector<TransactionDetail> GetAllTransactionDetail(int TransactionID){
		// Accessing Transaction Detail model public interface
		return TransactionDetail.GetAllTransactionDetail(TransactionID);
	}

	public static Vector<TransactionDetail> GetUserTransactionDetail(int UserID){
		// Accessing Transaction Detail model public interface
		return TransactionDetail.GetUserTransactionDetail(UserID);
	}
}
