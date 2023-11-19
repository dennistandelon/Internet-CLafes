package model;

import java.sql.Date;

public class Report {

	private int Report_ID;
	private String UserRole;
	private int PC_ID;
	private String ReportNote;
	private Date ReportDate;
	
	public Report(int report_ID, String userRole, int pC_ID, String reportNote, Date reportDate) {
		super();
		Report_ID = report_ID;
		UserRole = userRole;
		PC_ID = pC_ID;
		ReportNote = reportNote;
		ReportDate = reportDate;
	}

	
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

	public int getPC_ID() {
		return PC_ID;
	}

	public void setPC_ID(int pC_ID) {
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
