package controller;

import java.util.Vector;

import model.Job;

public class JobController {

	public static String AddNewJob(int UserID, String PcID) {
	
		if(!Job.AddNewJob(UserID, PcID)) {
			return "Failed to add new job";
		}
		
		return "Success";
	}
	
	public static String UpdateJobStatus(int jobID, String jobStatus) {
		
		if(!Job.UpdateJobStatus(jobID, jobStatus)) {
			return "Failed to update job status";
		}
		
		return "Success";
	}
	
	public static Vector<Job> GetAllJobData() {	
		return Job.GetAllJobData();
	}
	
	public static Job GetPcOnWorkingList(String PcID) {
		return Job.GetPcOnWorkingList(PcID);
	}
	
	public static Vector<Job> GetTechnicianJob(int UserID) {	
		return Job.GetTechnicianJob(UserID);
	}

}
