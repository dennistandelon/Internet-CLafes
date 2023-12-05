package controller;

import java.util.Vector;

import model.PC;

public class PCController {

	public static Vector<PC> GetAllPCData() {
		return PC.GetAllPCData();
	}
	
	public static String UpdatePCCondition(String PcID, String Condition) {
	
		if(!Condition.equals("Usable") && !Condition.equals("Maintenance") && !Condition.equals("Broken")) {
			return "Select the condition";
		}
		
		if(!PC.UpdatePCCondition(PcID, Condition)) {
			return "Failed to Update PC Condition";
		}
		
		return "Success";
	}
	
	public static String DeletePC(String PcID) {
		
		if(!PC.DeletePC(PcID)) {
			return "Failed to Delete PC";
		}
		
		return "Success";
	}

	public static String AddNewPC(String PcID) {
		if(!PC.AddNewPC(PcID)) {
			return "Failed to Add New PC";
		}
		
		return "Success";
	}
	
	public static PC GetPCDetail(String PcID){
		return PC.GetPCDetail(PcID);
	}
	
}
