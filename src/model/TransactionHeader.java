package model;

import java.sql.Date;

public class TransactionHeader {

	private int TransactionID;
	private int PC_ID;
	private String CustomerName;
	private Date BookedTime;
	
	public TransactionHeader(int transactionID, int pC_ID, String customerName, Date bookedTime) {
		super();
		TransactionID = transactionID;
		PC_ID = pC_ID;
		CustomerName = customerName;
		BookedTime = bookedTime;
	}

	public int getTransactionID() {
		return TransactionID;
	}

	public void setTransactionID(int transactionID) {
		TransactionID = transactionID;
	}

	public int getPC_ID() {
		return PC_ID;
	}

	public void setPC_ID(int pC_ID) {
		PC_ID = pC_ID;
	}

	public String getCustomerName() {
		return CustomerName;
	}

	public void setCustomerName(String customerName) {
		CustomerName = customerName;
	}

	public Date getBookedTime() {
		return BookedTime;
	}

	public void setBookedTime(Date bookedTime) {
		BookedTime = bookedTime;
	}

	
}
