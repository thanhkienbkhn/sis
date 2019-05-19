package model;

public class Student {

	private int studentID;
	private String fullName;
	private String sex;
	private String faculty;
	private String address;
	private String birthDate;
	private String phone;
	private String email;

	public Student() {

	}

	public Student(int studentID, String fullName, String sex, String faculty, String address, String birthDate, String phone,
			String email) {

		this.studentID = studentID;
		this.fullName = fullName;
		this.sex = sex;
		this.faculty = faculty;
		this.address = address;
		this.birthDate = birthDate;
		this.phone = phone;
		this.email = email;

	}

	public String getFaculty() {
		return faculty;
	}

	public void setFaculty(String faculty) {
		this.faculty = faculty;
	}

	public int getStudentID() {
		return studentID;
	}

	public void setStudentID(int studentID) {
		this.studentID = studentID;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
