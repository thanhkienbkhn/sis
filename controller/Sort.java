package controller;

import java.util.Comparator;

import model.Classes;
import model.RegisterInfo;
import model.Subject;

class SortSubjectByID implements Comparator<Subject> {

	@Override
	public int compare(Subject s1, Subject s2) {
		return s2.getSubjectID().compareTo(s1.getSubjectID());
	}

}

class SortSubjectByStatus implements Comparator<Subject> {

	@Override
	public int compare(Subject s1, Subject s2) {

		String status1 = s1.getStatus();
		String status2 = s2.getStatus();
		return status2.compareTo(status1);
	}
}

class SortSubjectByCredit implements Comparator<Subject> {

	@Override
	public int compare(Subject s1, Subject s2) {

		int credit1 = s1.getCredit();
		int credit2 = s2.getCredit();
		return (credit1 - credit2);
	}
}

class SortRegisterInfoByTime implements Comparator<RegisterInfo> {

	@Override
	public int compare(RegisterInfo r1, RegisterInfo r2) {
		return r2.getRegisteredTime().compareTo(r1.getRegisteredTime());
	}

}

class SortRegisterInfoByID implements Comparator<RegisterInfo> {

	@Override
	public int compare(RegisterInfo r1, RegisterInfo r2) {
		return r2.getSubjectID().compareTo(r1.getSubjectID());
	}

}

class SortRegisterInfoByCredit implements Comparator<RegisterInfo> {

	@Override
	public int compare(RegisterInfo r1, RegisterInfo r2) {

		int credit1 = r1.getCredits();
		int credit2 = r2.getCredits();
		return (credit1 - credit2);
	}
}

class SortClassByID implements Comparator<Classes> {

	@Override
	public int compare(Classes c1, Classes c2) {
		return c2.getClassID().compareTo(c1.getClassID());
	}

}

class SortClassByNumberOfStudent implements Comparator<Classes> {

	@Override
	public int compare(Classes c1, Classes c2) {

		return c1.getNumberOfStudent() - c2.getNumberOfStudent();
	}
}
