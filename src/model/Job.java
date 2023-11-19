package model;


public class Job {

	private int JobID;
	private int UserID;
	private int PC_ID;
	private String JobStatus;
	
	public Job(int jobID, int userID, int pC_ID, String jobStatus) {
		super();
		JobID = jobID;
		UserID = userID;
		PC_ID = pC_ID;
		JobStatus = jobStatus;
	}
	
	public int getJobID() {
		return JobID;
	}
	
	public void setJobID(int jobID) {
		JobID = jobID;
	}
	
	public int getUserID() {
		return UserID;
	}
	
	public void setUserID(int userID) {
		UserID = userID;
	}
	
	public int getPC_ID() {
		return PC_ID;
	}
	
	public void setPC_ID(int pC_ID) {
		PC_ID = pC_ID;
	}
	
	public String getJobStatus() {
		return JobStatus;
	}
	
	public void setJobStatus(String jobStatus) {
		JobStatus = jobStatus;
	}
	
	
}
