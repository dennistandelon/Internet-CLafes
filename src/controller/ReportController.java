package controller;

import java.util.Vector;

import model.Report;

public class ReportController {

	public static String AddNewReport(String UserRole, String PcID, String ReportNote) {
		
		if(PCController.GetPCDetail(PcID) == null) {
			return "PC with this ID doesn't exist";
		}
		
		if(ReportNote.isEmpty()) {
			return "Report Note must been filled";
		}
		
		if(!Report.AddNewReport(UserRole, PcID, ReportNote)) {
			return "Failed to add new report";
		}
		
		return "Success";
	}
	
	public static Vector<Report> GetAllReportData(){
		return Report.GetAllReportData();
	}

}
