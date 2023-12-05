package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

import util.Database;

public class Job {

	private int JobID;
	private int UserID;
	private String PC_ID;
	private String JobStatus;
	
	private static Database db = Database.getConnection();
	
	public Job(int jobID, int userID, String pC_ID, String jobStatus) {
		super();
		JobID = jobID;
		UserID = userID;
		PC_ID = pC_ID;
		JobStatus = jobStatus;
	}
	
	
	public static boolean AddNewJob(int UserID, String PcID) {
		String query = "INSERT INTO job (UserID,PC_ID,JobStatus)"
				+ " VALUES(?,?,'Uncomplete')";
		
		try {
			PreparedStatement ps = db.prepareStatement(query);
			ps.setInt(1, UserID);
			ps.setString(2, PcID);
			
			ps.execute();
			
		} catch (Exception e) {
			return false;
		}
		
		return true;
	}
	
	public static boolean UpdateJobStatus(int jobID, String jobStatus) {
		String query = "UPDATE job SET JobStatus = ? WHERE JobID = ?";
		
		try {
			PreparedStatement ps = db.prepareStatement(query);
			ps.setString(1, jobStatus);
			ps.setInt(2, jobID);
			
			ps.execute();
		} catch (Exception e) {
			return false;
		}
		
		return true;
	}
	
	public static Job GetPcOnWorkingList(String PcID) {
		String query = "SELECT * FROM job WHERE PC_ID = ? AND JobStatus = 'Uncomplete'";
		
		try {
			PreparedStatement ps = db.prepareStatement(query);
			ps.setString(1, PcID);
			
			ResultSet rs =  ps.executeQuery();
			if(rs.next()) {
				int jobID = rs.getInt("JobID");
				int userID = rs.getInt("UserID");
				String pC_ID = rs.getString("PC_ID");
				String jobStatus = rs.getString("JobStatus");
				
				return new Job(jobID, userID, pC_ID, jobStatus);
			}
		} catch (Exception e) {

		}
		
		return null;
	}
	
	public static Vector<Job> GetTechnicianJob(int UserID) {	
		Vector<Job> jobList = new Vector<Job>();
		
		String query = "SELECT * FROM job WHERE UserID = ?";
		try {
			PreparedStatement ps = db.prepareStatement(query);
			ps.setInt(1, UserID);
			
			ResultSet rs =  ps.executeQuery();
			while(rs.next()) {
				int jobID = rs.getInt("JobID");
				int userID = rs.getInt("UserID");
				String pC_ID = rs.getString("PC_ID");
				String jobStatus = rs.getString("JobStatus");
				
				jobList.add(new Job(jobID, userID, pC_ID, jobStatus));
			}
		} catch (Exception e) {

		}
		
		return jobList;
	}
	
	public static Vector<Job> GetAllJobData() {	
		Vector<Job> jobList = new Vector<Job>();
		
		String query = "SELECT * FROM job";
		try {
			
			ResultSet rs =  db.executeQuery(query);
			while(rs.next()) {
				int jobID = rs.getInt("JobID");
				int userID = rs.getInt("UserID");
				String pC_ID = rs.getString("PC_ID");
				String jobStatus = rs.getString("JobStatus");
				
				jobList.add(new Job(jobID, userID, pC_ID, jobStatus));
			}
		} catch (Exception e) {

		}
		
		return jobList;
	}
	
	
	// Getter & Setter
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
	
	public String getPC_ID() {
		return PC_ID;
	}
	
	public void setPC_ID(String pC_ID) {
		PC_ID = pC_ID;
	}
	
	public String getJobStatus() {
		return JobStatus;
	}
	
	public void setJobStatus(String jobStatus) {
		JobStatus = jobStatus;
	}
	
	
}
