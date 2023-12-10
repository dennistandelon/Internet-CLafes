package controller;

import java.util.Vector;

import model.Report;

public class ReportController {

	/*
	 * Method to add new report data to database
	 * */
	public static String AddNewReport(String UserRole, String PcID, String ReportNote) {
		
		// Validate if the pc id is not exist in the database
		if(PCController.GetPCDetail(PcID) == null) {
			return "PC with this ID doesn't exist";
		}
		
		// Validate if the Report note is empty or only contains blank spaces
		if(ReportNote.isEmpty()) {
			return "Report Note must been filled";
		}
		
		// Accessing Report model public interface
		if(!Report.AddNewReport(UserRole, PcID, ReportNote)) {
			return "Failed to add new report";
		}
		
		return "Success";
	}
	
	public static Vector<Report> GetAllReportData(){
		// Accessing Report model public interface
		return Report.GetAllReportData();
	}

}
