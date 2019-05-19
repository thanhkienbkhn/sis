package model;

public class Subject {

	private String subjectID;
	private String subjectName;
	private int credit;
	private String time;
	private String status;

	public Subject() {

	}

	public Subject(String subjectID, String subjectName, int credit, String time, String status) {

		this.subjectID = subjectID;
		this.subjectName = subjectName;
		this.credit = credit;
		this.time = time;
		this.status = status;

	}

	public String getSubjectID() {
		return subjectID;
	}

	public void setSubjectID(String subjectID) {
		this.subjectID = subjectID;
	}

	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

	public int getCredit() {
		return credit;
	}

	public void setCredit(int credit) {
		this.credit = credit;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void printOut(Subject s) {
		System.out.printf("%-20s %-30s %-20s %-20s %-30s %n", s.getSubjectID(), s.getSubjectName(), s.getCredit(),
				s.getTime(), s.getStatus());
	}

}
