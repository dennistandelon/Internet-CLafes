package controller;

import java.util.Vector;

import model.PC;

public class PCController {

	public static Vector<PC> GetAllPCData() {
		// Accessing PC model public interface
		return PC.GetAllPCData();
	}
	
	public static String UpdatePCCondition(String PcID, String Condition) {
	
		// Validate if the condition inserted is between Usable, Maintenance or Broken
		if(!Condition.equals("Usable") && !Condition.equals("Maintenance") && !Condition.equals("Broken")) {
			return "Invalid condition (Usable | Maintenance | Broken)";
		}
		
		// Accessing PC model public interface
		if(!PC.UpdatePCCondition(PcID, Condition)) {
			return "Failed to Update PC Condition";
		}
		
		return "Success";
	}
	
	public static String DeletePC(String PcID) {
		
		// Accessing PC model public interface
		if(!PC.DeletePC(PcID)) {
			return "Failed to Delete PC";
		}
		
		return "Success";
	}

	public static String AddNewPC(String PcID) {
		Vector<PC> pcs = GetAllPCData();
		
		// Check if the PcID is empty or only contains blank spaces
		if(PcID.isBlank()) {
			return "PC ID cannot be empty";
		}

		// Check if the PC ID is laready exist
		for (PC pc : pcs) {
			if(pc.getPC_ID().equals(PcID)) {
				return "PC ID must be Unique";
			}
		}
		
		// Accessing PC model public interface
		if(!PC.AddNewPC(PcID)) {
			return "Failed to Add New PC";
		}
		
		return "Success";
	}
	
	public static PC GetPCDetail(String PcID){
		// Accessing PC model public interface
		return PC.GetPCDetail(PcID);
	}
	
}
