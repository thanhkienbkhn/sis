package model;

public class Classes {

	private String classID;
	private int numberOfStudent;
	private int maxStudent;

	public Classes() {
	}

	public Classes(String classID, int numberOfStudent, int maxStudent) {
		this.classID = classID;
		this.numberOfStudent = numberOfStudent;
		this.maxStudent = maxStudent;
	}

	public String getClassID() {
		return classID;
	}

	public void setClassID(String classID) {
		this.classID = classID;
	}

	public int getNumberOfStudent() {
		return numberOfStudent;
	}

	public void setNumberOfStudent(int numberOfStudent) {
		this.numberOfStudent = numberOfStudent;
	}

	public int getMaxStudent() {
		return maxStudent;
	}

	public void setMaxStudent(int maxStudent) {
		this.maxStudent = maxStudent;
	}

	public void printOut(Classes c) {
		
		System.out.printf("%-20s%-20s%-20s%n", c.getClassID(), c.getNumberOfStudent(), c.getMaxStudent());

	}
}
