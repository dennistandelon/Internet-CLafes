package model;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

import util.Database;

public class TransactionHeader {

	private int TransactionID;
	private int StaffID;
	private String StaffName;
	private Date TransactionDate;
	
	private static Database db = Database.getConnection();
	
	public TransactionHeader(int transactionID, int staffID, String staffName, Date transactionDate) {
		super();
		TransactionID = transactionID;
		StaffID = staffID;
		StaffName = staffName;
		TransactionDate = transactionDate;
	}

	public static Vector<TransactionHeader> GetAllTransactionHeaderData() {
		Vector<TransactionHeader> thList = new Vector<TransactionHeader>();
		
		String query = "SELECT * FROM transactionheader";
		
		try {
			ResultSet rs = db.executeQuery(query);
			
			while(rs.next()) {
				int transactionID = rs.getInt("TransactionID");
				int staffID = rs.getInt("StaffID");
				String staffName = rs.getString("StaffName");
				Date transactionDate = rs.getDate("TransactionDate");
				
				thList.add(new TransactionHeader(transactionID, staffID, staffName, transactionDate));
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return thList;
	}
	
	public static boolean AddNewTransactionHeader(int id, int StaffID, Date TransactionDate) {
		
		String query = "INSERT INTO transactionheader"
				+ " VALUES(?,?,?,?)";
		
		try {
			PreparedStatement ps = db.prepareStatement(query);
			ps.setInt(1, id);
			ps.setInt(2, StaffID);
			ps.setString(3, User.searchName(StaffID));
			ps.setDate(4, TransactionDate);
			
			ps.execute();
			
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

	public int getStaffID() {
		return StaffID;
	}

	public void setStaffID(int staffID) {
		StaffID = staffID;
	}

	public String getStaffName() {
		return StaffName;
	}

	public void setStaffName(String staffName) {
		StaffName = staffName;
	}

	public Date getTransactionDate() {
		return TransactionDate;
	}

	public void setTransactionDate(Date transactionDate) {
		TransactionDate = transactionDate;
	}
		
}
