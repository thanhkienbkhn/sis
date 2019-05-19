package model;

import java.util.Date;

public class Register {

	private String studentID;
	private String subjectID;
	private Date registeredTime;

	public Register() {

	}

	public Register(String studentID, String subjectID, Date registeredTime) {
		
		this.studentID = studentID;
		this.subjectID = subjectID;
		this.registeredTime = registeredTime;
	}

	public String getStudentID() {
		return studentID;
	}

	public void setStudentID(String studentID) {
		this.studentID = studentID;
	}

	public String getSubjectID() {
		return subjectID;
	}

	public void setSubjectID(String subjectID) {
		this.subjectID = subjectID;
	}

	public Date getRegisteredTime() {
		return registeredTime;
	}

	public void setRegisteredTime(Date registeredTime) {
		this.registeredTime = registeredTime;
	}
	
	
}
