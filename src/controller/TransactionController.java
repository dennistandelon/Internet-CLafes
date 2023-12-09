package controller;

import java.util.Vector;

import model.*;

public class TransactionController {

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
		return TransactionHeader.GetAllTransactionHeaderData();
	}
	
	public static Vector<TransactionDetail> GetAllTransactionDetail(int TransactionID){
		return TransactionDetail.GetAllTransactionDetail(TransactionID);
	}

	public static Vector<TransactionDetail> GetUserTransactionDetail(int UserID){
		return TransactionDetail.GetUserTransactionDetail(UserID);
	}
}
