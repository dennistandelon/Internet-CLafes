package controller;

import java.util.Vector;

import model.Job;
import model.User;

public class JobController {

	public static String AddNewJob(int UserID, String PcID) {
	
		// Validate either the user is a technician or not
		boolean isTechnician = false;
		Vector<User> techs = UserController.GetAllTechnician();
		for (User user : techs) {
			if(user.getUserID() == UserID) {
				isTechnician = true;
				break;
			}
		}
		
		if(!isTechnician) {
			return "User is not a Technician";
		}
		
		// Validate is the PC Id exist in the database
		if(PCController.GetPCDetail(PcID) == null) {
			return "PC doesn't Exist";
		}
		
		// Trying to access the Job model public interface
		if(!Job.AddNewJob(UserID, PcID)) {
			return "Failed to add new job";
		}
		
		return "Success";
	}
	
	public static String UpdateJobStatus(int jobID, String jobStatus) {
		
		// Validate the job is exist
		boolean isJob = false;
		Vector<Job> jobs = GetAllJobData();
		for (Job job : jobs) {
			if(job.getJobID() == jobID) {
				isJob = true;
				break;
			}
		}
		
		if(!isJob) {
			return "Job ID didn't exist";
		}
		
		// Validate the status is between Complete and UnComplete
		if(!jobStatus.equals("Complete") && !jobStatus.equals("UnComplete")) {
			return "Job Status Must be Complete or UnComplete";
		}
		
		// Trying to access Job model interface
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
