package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

import util.Database;

public class PC {

	private String PC_ID;
	private String PC_Condition;
	
	private static Database db = Database.getConnection();
	
	public PC(String pC_ID, String pC_Condition) {
		super();
		PC_ID = pC_ID;
		PC_Condition = pC_Condition;
	}
	
	public static boolean UpdatePCCondition(String PcID, String Condition) {
		String query = "UPDATE pc SET PC_Condition = ? WHERE PC_ID = ?";
		
		try {
			PreparedStatement ps = db.prepareStatement(query);
			ps.setString(1, Condition);
			ps.setString(2, PcID);
			
			ps.execute();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
	
	public static boolean DeletePC(String PcID) {
		String query = "DELETE FROM pc WHERE PC_ID = ?";
		
		try {
			PreparedStatement ps = db.prepareStatement(query);
			ps.setString(1, PcID);
			
			ps.execute();
		} catch (Exception e) {
			return false;
		}
		
		return true;
	}

	public static boolean AddNewPC(String PcID) {
		String query = "INSERT INTO pc VALUES(?,'Usable')";
		
		try {
			PreparedStatement ps = db.prepareStatement(query);
			ps.setString(1, PcID);
			ps.execute();
		} catch (Exception e) {
			return false;
		}
		
		return true;
	}
	
	public static PC GetPCDetail(String PcID) {
		String query = "SELECT * FROM pc WHERE PC_ID = ?";
		
		try {			
			PreparedStatement ps = db.prepareStatement(query);
			ps.setString(1, PcID);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				String PC_ID = rs.getString("PC_ID");
				String condition = rs.getString("PC_Condition");
				
				return new PC(PC_ID,condition);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return null;
	}
	
	public static Vector<PC> GetAllPCData(){
		Vector<PC> pcList = new Vector<PC>();
		
		String query = "SELECT * FROM pc";
		try {
			ResultSet rs = db.executeQuery(query);
			while(rs.next()) {
				String PcID = rs.getString("PC_ID");
				String condition = rs.getString("PC_Condition");
				
				pcList.add(new PC(PcID,condition));
			}
			
		} catch (Exception e) {
			
		}
		
		return pcList;
	}
	
	
	// Getter & Setter
	public String getPC_ID() {
		return PC_ID;
	}

	public void setPC_ID(String pC_ID) {
		PC_ID = pC_ID;
	}

	public String getPC_Condition() {
		return PC_Condition;
	}

	public void setPC_Condition(String pC_Condition) {
		PC_Condition = pC_Condition;
	}
	
}
