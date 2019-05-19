package model;

public class RegisterInfo {

	private String subjectID;
	private String subjectName;
	private int credits;
	private String registeredTime;

	public RegisterInfo() {
	}

	public RegisterInfo(String subjectID, String subjectName, int credits, String registeredTime) {
		this.subjectID = subjectID;
		this.subjectName = subjectName;
		this.credits = credits;
		this.registeredTime = registeredTime;
	}

	public String getSubjectID() {
		return subjectID;
	}

	public void setSubjectId(String subjectId) {
		this.subjectID = subjectId;
	}

	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

	public int getCredits() {
		return credits;
	}

	public void setCredits(int credits) {
		this.credits = credits;
	}

	public String getRegisteredTime() {
		return registeredTime;
	}

	public void setRegisteredTime(String registeredTime) {
		this.registeredTime = registeredTime;
	}

	public void printOut(RegisterInfo reg) {
		System.out.printf("%-20s %-30s %-20s %-20s %n", reg.getSubjectID(), reg.getSubjectName(), reg.getCredits(),
				reg.getRegisteredTime());
	}
}
