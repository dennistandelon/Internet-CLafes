package model;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

import util.Database;

public class TransactionDetail {

	private int TransactionID;
	private String PC_ID;
	private String CustomerName;
	private Date BookedTime;

	private static Database db = Database.getConnection();
	
	public TransactionDetail(int transactionID, String pC_ID, String customerName, Date bookedTime) {
		super();
		TransactionID = transactionID;
		PC_ID = pC_ID;
		CustomerName = customerName;
		BookedTime = bookedTime;
	}
	
	/*
	 * Method to get user specified transaction detail data from databases
	 * @params	UserID, UserID from the authorized user
	 * @returns trList, the transaction details data
	 * */
	public static Vector<TransactionDetail> GetUserTransactionDetail(int UserID){
		Vector<TransactionDetail> trList = new Vector<TransactionDetail>();
		
		/* Query to get transaction details data by joining with user table
		 * by UserName and CustomerName
		 * */
		String query = "SELECT td.* FROM transactiondetail td JOIN user u ON u.UserName = td.CustomerName WHERE u.UserID = ?";
		
		try {
			PreparedStatement ps = db.prepareStatement(query);
			ps.setInt(1, UserID);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				int transactionID = rs.getInt("TransactionID");
				String pC_ID = rs.getString("PC_ID");
				String customerName = rs.getString("CustomerName");
				Date bookedTime = rs.getDate("BookedTime");
				
				trList.add(new TransactionDetail(transactionID, pC_ID, customerName, bookedTime));
			}
			
		} catch (Exception e) {
			
		}
		
		return trList;
	}
	
	/*
	 * Method to get all transaction detail data from databases
	 * @params	TransactionID, transaction id of the TransactionDetails
	 * @returns tdList, the transaction details data
	 * */
	public static Vector<TransactionDetail> GetAllTransactionDetail(int TransactionID){
		Vector<TransactionDetail> tdList = new Vector<TransactionDetail>(); 
		
		String query = "SELECT * FROM transactiondetail WHERE TransactionID = ?";
		try {
			PreparedStatement ps = db.prepareStatement(query);
			ps.setInt(1, TransactionID);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				int transactionID = rs.getInt("TransactionID");
				String pC_ID = rs.getString("PC_ID");
				String customerName = rs.getString("CustomerName");
				Date bookedTime = rs.getDate("BookedTime");
				
				tdList.add(new TransactionDetail(transactionID, pC_ID, customerName, bookedTime));
			}
			
		} catch (Exception e) {
			
		}
		
		return tdList;
	}
	
	/*
	 * Method to insert new transaction details
	 * @params	TransactionID, transaction id of the related transaction header
	 * 			listPCBook, the finished book
	 * @returns true if execution success, false if the execution failed
	 * */
	public static boolean AddTransactionDetail(int TransactionID, Vector<PCBook> listPCBook) {
		String query = "INSERT INTO transactiondetail"
				+ " VALUES(?,?,?,?)";
		
		try {
			
			PreparedStatement ps = db.prepareStatement(query);
			for (PCBook pcBook : listPCBook) {				
				ps.setInt(1, TransactionID);
				ps.setString(2, pcBook.getPC_ID());
				
				// Search username by UserID
				String user_query = "SELECT * FROM user WHERE UserID = ?";
				String username = "";
				try {			
					PreparedStatement user_ps = db.prepareStatement(user_query);
					user_ps.setInt(1, pcBook.getUserID());
					
					ResultSet rs = user_ps.executeQuery();
					if(rs.next()) {
						username = rs.getString("UserName");
					}
				} catch (Exception e) {
					// TODO: handle exception
				}
				
				ps.setString(3, username);
				ps.setDate(4, pcBook.getBookedDate());
				
				ps.execute();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
	
	
	// Getter & Setter
	public int getTransactionID() {
		return TransactionID;
	}

	public void setTransactionID(int transactionID) {
		TransactionID = transactionID;
	}

	public String getPC_ID() {
		return PC_ID;
	}

	public void setPC_ID(String pC_ID) {
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
