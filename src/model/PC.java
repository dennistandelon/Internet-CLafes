package model;

public class PC {

	private int PC_ID;
	private String PC_Condition;
	
	public PC(int pC_ID, String pC_Condition) {
		super();
		PC_ID = pC_ID;
		PC_Condition = pC_Condition;
	}

	public int getPC_ID() {
		return PC_ID;
	}

	public void setPC_ID(int pC_ID) {
		PC_ID = pC_ID;
	}

	public String getPC_Condition() {
		return PC_Condition;
	}

	public void setPC_Condition(String pC_Condition) {
		PC_Condition = pC_Condition;
	}
	
}
