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
	
	public static Vector<TransactionDetail> GetUserTransactionDetail(int UserID){
		Vector<TransactionDetail> trList = new Vector<TransactionDetail>();
		
		String query = "SELECT transactiondetail.* "
				+ "FROM user u, transactiondetail td"
				+ "WHERE u.Username = td.CustomerName AND u.UserID = ?";
		
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
	
	public static boolean AddTransactionDetail(int TransactionID, Vector<PCBook> listPCBook) {
		String query = "INSERT INTO transactiondetail"
				+ " VALUES(?,?,?,?)";
		
		try {
			
			PreparedStatement ps = db.prepareStatement(query);
			for (PCBook pcBook : listPCBook) {				
				ps.setInt(1, TransactionID);
				ps.setString(2, pcBook.getPC_ID());
				ps.setString(3, User.searchName(pcBook.getUserID()));
				ps.setDate(4, pcBook.getBookedDate());
				
				ps.execute();
			}
			
		} catch (Exception e) {
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
