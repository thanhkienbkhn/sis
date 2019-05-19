package controller;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

import model.Classes;
import model.RegisterInfo;
import model.Student;
import model.Subject;

public class DataController {

	String url = "jdbc:mysql://localhost:3306/sis?useSSL=false";
	String user = "root";
	String password = "anhkhongbiet";
	Connection connection = null;

	public int checkAndShowCreditInfo(int studentID) {

		/*-
		  This method returns 3 values 
		  	-1: Cannot remove credits 
		  	 0: Cannot add more credits 
		  	 1: Can add more credits
		 */

		String sql = "SELECT * FROM credit_overall WHERE student_id = " + studentID;
		try {
			Statement statement = connection.createStatement();
			ResultSet res = statement.executeQuery(sql);

			System.out.printf("%-25s %-25s %-25s %n", "Maximum Credits", "Registered Credits", "Remaining Credits");
			System.out.printf("%-25s %-25s %-25s %n", "---------------", "------------------", "-----------------");

			if (res.next()) {
				for (int i = 2; i <= 4; i++) {
					System.out.printf("%-26d", res.getInt(i));
				}
				System.out.println("\n");

				if (res.getInt(4) == 0) {
					return 0;
				} else if (res.getInt(3) == 0) {
					return -1;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 1;

	}

	public void sortRegisterInfoByCredit(ArrayList<RegisterInfo> list) {

		Collections.sort(list, new SortRegisterInfoByCredit());
		for (RegisterInfo registerInfo : list) {
			registerInfo.printOut(registerInfo);
		}
		System.out.println("\n\n");
	}

	public void sortRegisterInfoByID(ArrayList<RegisterInfo> list) {

		Collections.sort(list, new SortRegisterInfoByID());
		for (RegisterInfo registerInfo : list) {
			registerInfo.printOut(registerInfo);
		}
		System.out.println("\n\n");
	}

	public void sortRegisterInfoByTime(ArrayList<RegisterInfo> list) {

		Collections.sort(list, new SortRegisterInfoByTime());
		for (RegisterInfo registerInfo : list) {
			registerInfo.printOut(registerInfo);
		}
		System.out.println("\n\n");
	}

	public ArrayList<RegisterInfo> showRegisterInfo(int studentID) {

		System.out.println("\n\n\t\t\t\tRegistered Subjects List\n\n");
		System.out.printf("%-20s %-30s %-20s %-20s %n", "Subject ID", "Subject Name", "Credits", "Registered Time");
		System.out.println(
				"-----------------------------------------------------------------------------------------------------");

		String sql = "SELECT r.subject_id , s.subject_name, s.credits, r.registered_time " + " FROM register_info r "
				+ "JOIN subject s " + "WHERE r.student_id = " + studentID + " AND r.subject_id = s.subject_id ";

		ArrayList<RegisterInfo> registerList = new ArrayList<>();

		try {
			Statement statement = connection.createStatement();
			ResultSet res = statement.executeQuery(sql);

			while (res.next()) {
				ArrayList<Object> list = new ArrayList<>();

				String result;
				for (int i = 1; i <= 4; i++) {
					if (i == 3) {
						int credits = res.getInt(3);
						list.add(credits);
						continue;
					}
					result = res.getString(i);
					list.add(result);
				}

				RegisterInfo reg = new RegisterInfo((String) list.get(0), (String) list.get(1), (int) list.get(2),
						(String) list.get(3));
				registerList.add(reg);
			}

			for (RegisterInfo reg : registerList) {
				reg.printOut(reg);
			}
			System.out.println("\n\n");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return registerList;
	}

	public ArrayList<String> getRegisterInfo(int id) {

		ArrayList<String> list = new ArrayList<String>();
		String sql = "SELECT r.subject_id , s.subject_name, s.credits, r.registered_time " + " FROM register_info r "
				+ "JOIN subject s " + "WHERE r.student_id = " + id + " AND r.subject_id = s.subject_id ";

		try {
			Statement statement = connection.createStatement();
			ResultSet res = statement.executeQuery(sql);

			if (res.next()) {
				for (int i = 1; i <= 4; i++) {
					String string = res.getString(i);
					list.add(string);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;

	}

	public void sortSubjectInfoByStatus(ArrayList<Subject> list) {

		Collections.sort(list, new SortSubjectByStatus());

		for (Subject subject : list) {
			subject.printOut(subject);
		}
		System.out.println("\n\n");
	}

	public void sortSubjectInfoByID(ArrayList<Subject> list) {

		Collections.sort(list, new SortSubjectByID());

		for (Subject subject : list) {
			subject.printOut(subject);
		}
		System.out.println("\n\n");
	}

	public void sortSubjectInfoByCredit(ArrayList<Subject> list) {

		Collections.sort(list, new SortSubjectByCredit());

		for (Subject subject : list) {
			subject.printOut(subject);
		}
		System.out.println("\n\n");
	}

	public ArrayList<Subject> showSubjectInfo() {

		System.out.println("\t\t\t\tSubjects List\n\n");
		System.out.printf("%-20s %-30s %-20s %-20s %-30s %n", "Subject ID", "Subject Name", "Credits", "School Time",
				"Status");
		System.out.println(
				"-----------------------------------------------------------------------------------------------------");

		String sql = "SELECT * FROM subject ";
		ArrayList<Subject> subjectList = new ArrayList<>();

		try {
			Statement statement = connection.createStatement();
			ResultSet res = statement.executeQuery(sql);

			while (res.next()) {
				ArrayList<Object> list = new ArrayList<>();
				String result;

				for (int i = 1; i <= 5; i++) {
					if (i == 3) {
						int credit = res.getInt(3);
						list.add(credit);
						continue;
					}
					result = res.getString(i);
					list.add(result);
				}

				Subject subject = new Subject((String) list.get(0), (String) list.get(1), (int) list.get(2),
						(String) list.get(3), (String) list.get(4));
				subjectList.add(subject);
			}

			for (Subject s : subjectList) {
				s.printOut(s);
			}

			System.out.println("\n\n");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return subjectList;
	}

	public void showStudentInfo(int id) {

		ArrayList<String> list = getStudentInfo(id);

		System.out.printf("%-15s %-25s %-10s %-30s %-20s %-20s %-20s %-30s %n %n", "Student ID", "Full Name", "Sex",
				"Faculty", "Address", "Date Of Birth", "Phone Number", "Email");
		System.out.printf("%-15s %-25s %-10s %-30s %-20s %-20s %-20s %-30s %n", list.get(0), list.get(1), list.get(2),
				list.get(3), list.get(4), list.get(5), list.get(6), list.get(7));
		System.out.println("\n\n");
	}

	public ArrayList<String> getStudentInfo(int id) {

		ArrayList<String> list = new ArrayList<String>();
		String sql = "SELECT * FROM log WHERE student_id = " + id;

		try {
			Statement statement = connection.createStatement();
			ResultSet res = statement.executeQuery(sql);

			if (res.next()) {
				for (int i = 1; i <= 8; i++) {
					String string = res.getString(i);
					list.add(string);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;

	}

	public String getStudentName(int id) {

		String fullName = "";
		String sql = "SELECT full_name FROM log WHERE student_id = " + id;

		try {
			Statement statement = connection.createStatement();
			ResultSet res = statement.executeQuery(sql);

			if (res.next()) {
				fullName = res.getString("full_name");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return fullName;
	}

	public void getConnection() {

		try {
			connection = DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public boolean checkAccount(int id, String password) {

		String sql = "SELECT student_id, password FROM log";

		try {
			Statement statement = connection.createStatement();
			ResultSet res = statement.executeQuery(sql);

			while (res.next()) {
				int number = res.getInt("student_id");
				String pass = res.getString("password");

				if (id == number && password.equals(pass)) {
					return true;
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean checkStudentID(int id) {

		String sql = "SELECT student_id FROM log";

		try {
			Statement statement = connection.createStatement();
			ResultSet res = statement.executeQuery(sql);

			while (res.next()) {
				int number = res.getInt(1);
				if (id == number) {
					return false;
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return true;
	}

	public void registerAccount(int id, String password) {

		// This is a stored procedure

		SimpleDateFormat formater = new SimpleDateFormat("dd/MM/YYYY HH:mm:ss");
		String date = formater.format(new Date());
		String sql = "{CALL register(?,?,?)}";

		try {
			CallableStatement call = connection.prepareCall(sql);
			call.setInt(1, id);
			call.setString(2, password);
			call.setString(3, date);
			call.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void addStudentToDB(Student student) {

		String sql = "UPDATE log SET full_name = '" + student.getFullName() + "', " + "sex = '" + student.getSex()
				+ "', " + "faculty = '" + student.getFaculty() + "', " + "address = '" + student.getAddress() + "', "
				+ "birth_date = '" + student.getBirthDate() + "', " + "phone = '" + student.getPhone() + "', "
				+ "email = '" + student.getEmail() + "' WHERE student_id = " + student.getStudentID();

		try {
			Statement statement = connection.createStatement();
			statement.executeUpdate(sql);

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public boolean unfilledInfo(int id) {

		String sql = "SELECT * FROM log";
		try {
			Statement statement = connection.createStatement();
			ResultSet res = statement.executeQuery(sql);

			while (res.next()) {
				String checkInfo = res.getString("address");

				if (checkInfo.equals("unknown")) {
					return true;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public int registerSubject(int studentID, String subjectID) {

		/*-
		  This method returns 3 values: 
		  	-1: The remain_credit < the subjectID credits to register 
		   	 0: Duplicate subjectID 
		   	 1: Valid subjectID
		 */

		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		String registeredTime = formatter.format(new Date());
		String sql = "INSERT INTO register_info VALUES " + "( '" + studentID + "', '" + subjectID + "', '"
				+ registeredTime + "');";
		String sql2 = "SELECT remain_credit FROM credit_overall WHERE student_id = " + studentID;
		String checkCredit = "SELECT credits FROM subject WHERE subject_id = '" + subjectID + "'";

		try {
			Statement statement = connection.createStatement();
			Statement statement1 = connection.createStatement();
			Statement statement2 = connection.createStatement();

			ResultSet resSql2 = statement1.executeQuery(sql2);
			ResultSet resCheckCredit = statement2.executeQuery(checkCredit);

			while (resSql2.next() && resCheckCredit.next()) {

				if (resSql2.getInt(1) >= resCheckCredit.getInt(1)) {
					statement.executeUpdate(sql);
					return 1;
				} else {
					return -1;
				}
			}

		} catch (SQLException e) {
			return 0;
		}
		return 0;

	}

	public void removeSubject(int studentID, String subjectID) {

		// This is the `remove` Stored Procedure in database `sis`

		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		String removeTime = formatter.format(new Date());
		String sql = "{CALL remove(?,?,?)}";

		try {
			CallableStatement call = connection.prepareCall(sql);
			call.setInt(1, studentID);
			call.setString(2, subjectID);
			call.setString(3, removeTime);
			call.execute();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public int checkSubjectIDToRegister(String subjectID) {

		/*-
		 	This method returns 3 values 
		 		-1: The subject has been closed, cannot register it
		 		 0: The subjectID input is invalid 
		 		 1: The subject is opened, users can register it
		 */

		String sql = "SELECT subject_id, status FROM subject";
		try {
			Statement statement = connection.createStatement();
			ResultSet res = statement.executeQuery(sql);

			while (res.next()) {
				String checkID = res.getString(1);
				String checkStatus = res.getString(2);

				if (checkID.equalsIgnoreCase(subjectID) && checkStatus.equalsIgnoreCase("Closed")) {
					return -1;
				} else if (checkID.equalsIgnoreCase(subjectID) && checkStatus.equalsIgnoreCase("Opened")) {
					return 1;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return 0;
	}

	public boolean checkSubjectIDToRemove(int studentID, String subjectID) {

		String sql = "SELECT subject_id FROM register_info WHERE student_id = " + studentID;

		try {
			Statement statement = connection.createStatement();
			ResultSet res = statement.executeQuery(sql);

			while (res.next()) {
				String checkID = res.getString(1);
				if (checkID.equalsIgnoreCase(subjectID)) {
					return true;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}

	public ArrayList<Classes> showClassList() {

		System.out.println("\n\n\t\tClasses In This Term\n");
		System.out.printf("%-20s%-20s%-20s%n", "Class ID", "Number Of Student", "Maximum Student");
		System.out.println("------------------------------------------------------------");
		String sql = "SELECT * FROM class";
		ArrayList<Classes> classList = new ArrayList<>();

		try {

			Statement statement = connection.createStatement();
			ResultSet res = statement.executeQuery(sql);

			while (res.next()) {
				Classes c = new Classes(res.getString(1), res.getInt(2), res.getInt(3));
				classList.add(c);
			}

			for (Classes c : classList) {
				c.printOut(c);
			}
			System.out.println("\n\n");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return classList;
	}

	public void showStudentListInClass(String classID) {

		/*-
		 	This method is a stored procedure called `show_class` 
		 	It returns a list of student in a specific class
		
		 */

		String sql = "{CALL show_class(?)}";
		System.out.println("\t\t\tList Of Students In The Class " + classID.toUpperCase() + "\n");
		System.out.printf("%-20s%-20s%-20s%-20s %n", "Student ID", "Full Name", "Sex", "Faculty");
		System.out.println("--------------------------------------------------------------------------------");

		try {
			CallableStatement call = connection.prepareCall(sql);
			call.setString(1, classID);
			ResultSet res = call.executeQuery();

			while (res.next()) {
				ArrayList<String> list = new ArrayList<>();
				for (int i = 1; i <= 4; i++) {
					list.add(res.getString(i));
				}
				for (String string : list) {
					System.out.printf("%-20s", string);
				}
				System.out.println();
			}
			System.out.println("\n\n");

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public boolean checkFullClass(String classID) {

		/*-
		 	This is a Function in database `sis`, checks whether the number of student in a class is full or not
		  	
		 */
		String sql = "SELECT check_full(?)";
		boolean check = false;

		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, classID);
			ResultSet res = statement.executeQuery();

			while (res.next()) {
				check = res.getBoolean(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return check;
	}

	public void sortClassByNumberOfStudent(ArrayList<Classes> list) {

		Collections.sort(list, new SortClassByNumberOfStudent());

		for (Classes classes : list) {
			classes.printOut(classes);
		}
		System.out.println("\n\n");
	}

	public void sortClassByID(ArrayList<Classes> list) {

		Collections.sort(list, new SortClassByID());

		for (Classes classes : list) {
			classes.printOut(classes);
		}
		System.out.println("\n\n");
	}

	public void showStudentRegisterLog() {

		System.out.println("\n\n\t\tStudent Registered History");
		System.out.println("================================================================================");
		System.out.printf("%-20s%-20s%-20s%-20s%n", "Student ID", "Subject ID", "Action", "Action Time");

		String sql = "SELECT * FROM register_log";
		try {
			Statement statement = connection.createStatement();
			ResultSet res = statement.executeQuery(sql);

			while (res.next()) {
				System.out.printf("%-20s%-20s%-20s%-20s%n", res.getString(1), res.getString(2), res.getString(3),
						res.getString(4));
			}
			System.out.println("\n\n");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void showStudentInfomationList() {

		System.out.println("\n\n\t\t\t\t\tStudent Infomation List\n");
		System.out.println(
				"=================================================================================================================");
		System.out.printf("%-20s%-20s%-20s%-20s%-20s%-20s%-20s%-20s%-20s%-20s%n", "Student ID", "Full Name", "Sex",
				"Faculty", "Address", "Date Of Birth", "Phone Number", "Email", "Password", "Registered Date");

		String sql = "SELECT * FROM log";

		try {
			Statement statement = connection.createStatement();
			ResultSet res = statement.executeQuery(sql);

			while (res.next()) {
				for (int i = 1; i <= 10; i++) {
					System.out.printf("%-20s", res.getString(i));
				}
			}
			System.out.println();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void removeStudent(int studentID) {

		String sql = "DELETE FROM log WHERE student_id = " + studentID;

		try {
			Statement statement = connection.createStatement();
			statement.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("\n\n\t\tRemoved Successfully\n\n");

	}

	public void modifyMaxStudent(String classID, int newMax) {

		String sql = "UPDATE class SET max_student =" + newMax + " WHERE class_id = '" + classID + "'";

		try {
			Statement statement = connection.createStatement();
			statement.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("\n\n\t\tModified Successfully\n\n");
	}

	public void modifyStatus(String subjectID, String newStatus) {

		String sql = "UPDATE subject SET status = '" + newStatus + "' WHERE subject_id = '" + subjectID + "'";

		try {
			Statement statement = connection.createStatement();
			statement.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("\n\n\t\tModified Successfully\n\n");
	}

	/*
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 */
}
