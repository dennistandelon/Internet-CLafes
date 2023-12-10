package model;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.Vector;

import util.Database;

public class Report {

	private int Report_ID;
	private String UserRole;
	private String PC_ID;
	private String ReportNote;
	private Date ReportDate;
	
	private static Database db = Database.getConnection();
	
	public Report(int report_ID, String userRole, String pC_ID, String reportNote, Date reportDate) {
		super();
		Report_ID = report_ID;
		UserRole = userRole;
		PC_ID = pC_ID;
		ReportNote = reportNote;
		ReportDate = reportDate;
	}

	/*
	 * Method to add new report data from database
	 * @params 	UserRole, the UserRole of User Model
	 * 			PcID, the PC_ID of the selected PC Model
	 * 			ReportNote, the report note user input
	 * @return true if the execution success, flase if the execution failed
	 * */
	public static boolean AddNewReport(String UserRole, String PcID, String ReportNote) {
		String query = "INSERT INTO report (UserRole,PC_ID,ReportNote,ReportDate)"
				+ " VALUES(?,?,?,?)";
		
		try {
			PreparedStatement ps = db.prepareStatement(query);
			ps.setString(1, UserRole);
			ps.setString(2, PcID);
			ps.setString(3, ReportNote);
			ps.setDate(4, Date.valueOf(LocalDate.now()));
			
			ps.execute();
			
		} catch (Exception e) {
			return false;
		}
		
		return true;
	}
	
	/*
	 * Method to get all report data from database
	 * @return reportList, all report data from database
	 * */
	public static Vector<Report> GetAllReportData(){
		Vector<Report> reportList = new Vector<Report>();
		
		String query = "SELECT * FROM report";
		try {
			ResultSet rs = db.executeQuery(query);
			while(rs.next()) {
				int reportID = rs.getInt("Report_ID");
				String pC_ID = rs.getString("PC_ID");
				String userRole = rs.getString("UserRole");
				String note = rs.getString("ReportNote");
				Date reportDate = rs.getDate("ReportDate");
				
				reportList.add(new Report(reportID, userRole, pC_ID, note, reportDate));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return reportList;
	}
	
	// Getter & Setter
	public int getReport_ID() {
		return Report_ID;
	}

	public void setReport_ID(int report_ID) {
		Report_ID = report_ID;
	}

	public String getUserRole() {
		return UserRole;
	}

	public void setUserRole(String userRole) {
		UserRole = userRole;
	}

	public String getPC_ID() {
		return PC_ID;
	}

	public void setPC_ID(String pC_ID) {
		PC_ID = pC_ID;
	}

	public String getReportNote() {
		return ReportNote;
	}

	public void setReportNote(String reportNote) {
		ReportNote = reportNote;
	}

	public Date getReportDate() {
		return ReportDate;
	}

	public void setReportDate(Date reportDate) {
		ReportDate = reportDate;
	}
}
