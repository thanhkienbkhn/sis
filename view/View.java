package view;

import java.util.ArrayList;

import java.util.Scanner;

import controller.DataController;
import model.Classes;
import model.RegisterInfo;
import model.Student;
import model.Subject;

/*-
 * This is an application named Student Infomation System or SIS in short. SIS allows student to register a new account 
 so that user can log in to SIS for various purposes.  
 	
 * There are only 2 modes in the app: User mode & Admin mode
  	
  	@User mode:
  		- In this mode, for a first time of logging in, users must complete all the information needed to continue using services.
  		- Once users have completed their information, they can use several tools such as: 
  			+ Review their own information
  			+ Register new subjects
  			+ Remove subjects
  			+ Check out the status of classes 
  			+ Some bonus functions: sort student by ID, sort subject by credits...
  
  	@Admin mode:
  		 In this mode, the admin also have ability to:
  			+ View student/subject information
  			+ Add/remove students or subjects
  			+ Adjust the number of student in each class and the status as well
  		
  
 */


public class View {

	private static int menu = 0;
	private static String input;
	private static Scanner scanner = new Scanner(System.in);
	private static DataController controller = new DataController();
	private static int studentID;

	public static void main(String[] args) {

		/*-
		 	There are 4 values with menu variable:
		 		menu = 0: loginMenu
		 		menu = 1: userMode
		 		menu = 2: adminMode
		 		menu = -1: stop this app
		 
		 */

		while (menu != -1) {

			controller.getConnection();

			switch (menu) {
			case 0:
				loginMenu();
				break;

			case 1:
				userMode();
				break;

			case 2:
				adminMode();
				break;
			}
		}
	}

	private static void adminMode() {
		
		int choice;
		boolean outOfAdminMode = false;
		
		do {
			System.out.println("\t\t\tWelcome Administrator\n");
			System.out.println("============================================================================");
			System.out.print("1. Student Management\t\t");
			System.out.print("2. Class Management\t\t");
			System.out.println("3. Log out");

			input = scanner.nextLine();

			if (input.matches("[1-3]{1}")) {

				choice = Integer.parseInt(input);

				switch (choice) {
				case 1:

					do {
						System.out.println("\t\tStudent Management");
						System.out.println("=========================================================");
						System.out.println("1. Show student registered history");
						System.out.println("2. Show student information list");
						System.out.println("3. Remove a student from list");
						System.out.println("4. Get back ");

						input = scanner.nextLine();

						if (input.matches("[1-4]{1}")) {

							choice = Integer.parseInt(input);

							switch (choice) {
							case 1:
								controller.showStudentRegisterLog();
								System.out.println("Press any key to get back");
								scanner.nextLine();
								continue;

							case 2:
								controller.showStudentInfomationList();
								System.out.println("Press any key to get back");
								scanner.nextLine();
								continue;

							case 3:
								do {
									System.out.println("Enter the student ID to remove");
									input = scanner.nextLine();

									try {
										studentID = Integer.parseInt(input);
										if (!controller.checkStudentID(studentID)) {
											controller.removeStudent(studentID);

											System.out.println(
													"Press any key to continue removing or press 0 to get back");
											input = scanner.nextLine();

											if (input.matches("0")) {
												break;
											}
										} else {
											System.out.println("The student ID does not exist!");
											System.out.println(
													"Press any key to continue removing or press 0 to get back");
											input = scanner.nextLine();

											if (input.matches("0")) {
												break;
											}
										}

									} catch (Exception e) {
										System.out.println(
												"Invalid student ID, press any key to try again or press 0 to exit");
										input = scanner.nextLine();
										if (input.matches("0")) {
											break;
										}

									}
								} while (true);

							case 4:
								choice = 0;
								break;
							}
						} else {
							System.out.println("Invalid input, press any key to try again or press 0 to get back ");
							input = scanner.nextLine();
							if (input.matches("0")) {
								break;
							} else {
								continue;
							}
						}

						if (choice == 0) {
							break;
						}
					} while (true);
					break;

				case 2:

					do {
						System.out.println("\n\n\t\t\tClass Management");
						System.out.println("======================================================================");
						System.out.println("1. Classes List");
						System.out.println("2. Students In A Class");
						System.out.println("3. Modify The Maximum Student In A Class");
						System.out.println("4. Modify The Status Of A Class");
						System.out.println("5. Get back");

						input = scanner.nextLine();

						if (input.matches("[1-5]{1}")) {

							choice = Integer.parseInt(input);

							switch (choice) {

							case 1:
								controller.showClassList();
								System.out.println("Press any key to get back");
								scanner.nextLine();
								continue;

							case 2:
								System.out.println("Enter the class ID");

								do {
									String classID = scanner.nextLine();
									if (controller.checkSubjectIDToRegister(classID) == 1
											|| controller.checkSubjectIDToRegister(classID) == -1) {
										controller.showStudentListInClass(classID);

										System.out.println("Press any key to get back");
										scanner.nextLine();
										break;
									} else {
										System.out.println("Invalid subject ID, try again: ");
									}
								} while (true);

								continue;

							case 3:

								controller.showClassList();

								do {
									System.out.println("Enter the class ID");

									String classID = scanner.nextLine();

									if (controller.checkSubjectIDToRegister(classID) == 1
											|| controller.checkSubjectIDToRegister(classID) == -1) {

										System.out.println("Enter the new maximum number of student");

										do {
											input = scanner.nextLine();

											if (input.matches("[0-9]{1,2}")) {
												int max = Integer.parseInt(input);

												if (max > 5 && max < 10) {
													controller.modifyMaxStudent(classID, max);
													System.out.println("Press any key to get back");
													scanner.nextLine();
													break;
												} else {
													System.out.println(
															"Cannot set new value <= 5 or >= 10. Enter again: ");
												}
											} else {
												System.out.println("Invalid input, try again: ");
											}
										} while (true);
										break;
									} else {
										System.out.println("Invalid subject ID, try again: ");
									}
								} while (true);

								continue;

							case 4:
								controller.showSubjectInfo();
								System.out.println("\nEnter the class ID");

								do {
									String classID = scanner.nextLine();

									if (controller.checkSubjectIDToRegister(classID) == 1
											|| controller.checkSubjectIDToRegister(classID) == -1) {

										System.out.println("Enter new status");
										System.out.println("1. Opened" + "\t\t2. Closed");

										do {
											input = scanner.nextLine();

											if (input.matches("[12]{1}")) {
												int max = Integer.parseInt(input);

												if (max == 1) {
													controller.modifyStatus(classID, "Opened");
													System.out.println("Press any key to get back");
													scanner.nextLine();
													break;
												} else {
													controller.modifyStatus(classID, "Closed");
													System.out.println("Press any key to get back");
													scanner.nextLine();
													break;
												}
											} else {
												System.out.println("Invalid input try again: ");
											}
										} while (true);
										break;
									} else {
										System.out.println("Invalid class ID, try again: ");
									}
								} while (true);

								continue;

							case 5:
								choice = 0;
								break;

							}
							break;
						} else {
							System.out.println("Invalid input, press any key to try again or press 0 to get back ");
							input = scanner.nextLine();
							if (input.matches("0")) {
								break;
							} else {
								continue;
							}
						}
					} while (true);
					break;

				case 3:
					outOfAdminMode = true;
					menu = 0;
					break;
				}
			} else {
				System.out.println("Invalid input, try again");
			}
		} while (!outOfAdminMode);

	}

	private static void userMode() {

		boolean outOfUserMode = false;
		boolean mainDoWhile = true;

		if (controller.unfilledInfo(studentID)) {
			completeInformation();
		}

		System.out.println("\n\t\tHello " + controller.getStudentName(studentID));

		do {
			System.out.println("===========================================================");
			System.out.println("0. Log out");
			System.out.println("1. Student Infomation");
			System.out.println("2. Subject List");
			System.out.println("3. Classes In This Term");
			System.out.println("4. Registered Subject Management");

			int choice = 0;

			// This do-while loop checks if the input is correct
			do {
				input = scanner.nextLine();

				if (input.matches("[0-4]{1}")) {
					choice = Integer.parseInt(input);
					break; // Input is correct, break out of this do-while loop
				}
			} while (true);

			switch (choice) {

			case 0:
				outOfUserMode = true;
				break;

			case 1:
				System.out.println("\n\n\n");
				controller.showStudentInfo(studentID);
				System.out.println("\nPress any key to get back");

				input = scanner.nextLine();

				break;

			case 2:
				System.out.println("\n\n");
				ArrayList<Subject> subjectList = controller.showSubjectInfo();

				do {
					System.out.println("1. Sort subjects by status");
					System.out.println("2. Sort subjects by ID");
					System.out.println("3. Sort subjects by credits");
					System.out.println("0. Get back");

					input = scanner.nextLine();
					if (input.matches("[0-3]")) {

						choice = Integer.parseInt(input);

						switch (choice) {

						case 0:
							mainDoWhile = true;
							break;

						case 1:
							controller.sortSubjectInfoByStatus(subjectList);
							break;

						case 2:
							controller.sortSubjectInfoByID(subjectList);
							break;

						case 3:
							controller.sortSubjectInfoByCredit(subjectList);
							break;

						}

						if (choice == 0) {
							break;
						}
						System.out.println("Press 0 to get back, or any key to continue sorting");
						input = scanner.nextLine();

						if (input.matches("0")) {
							break;
						} else {
							System.out.println("Enter number to sort: ");
							continue;
						}

					} else {
						System.out.println("Invalid input, try again:");
						continue;
					}
				} while (true);

				break;

			case 3:

				do {
					System.out.println("\n\n");
					System.out.println("1. Show List Of Classes In This Term");
					System.out.println("2. Student List In A Class");
					System.out.println("0. Get Back");

					input = scanner.nextLine();

					if (input.matches("[0-2]")) {

						choice = Integer.parseInt(input);

						switch (choice) {

						case 0:
							break;

						case 1:
							ArrayList<Classes> classList = controller.showClassList();
							System.out.println("");
							System.out.println("1. Sort class by ID");
							System.out.println("2. Sort class by the number of student");
							System.out.println("0. Get Back");

							input = scanner.nextLine();

							if (input.matches("[0-2]")) {
								choice = Integer.parseInt(input);

								switch (choice) {
								case 0:
									break;

								case 1:
									controller.sortClassByID(classList);
									System.out.println("Press any key to get back");
									scanner.nextLine();
									choice = 0;
									break;

								case 2:

									controller.sortClassByNumberOfStudent(classList);
									System.out.println("Press any key to get back");
									scanner.nextLine();
									choice = 0;
									break;
								}
							} else {
								System.out.println("Invalid input, try again:");
								continue;
							}

							if (choice == 0) {
								continue;
							}

							break;

						case 2:

							System.out.println("Enter the class ID");

							do {
								String subjectID = scanner.nextLine();
								if (controller.checkSubjectIDToRegister(subjectID) == 1
										|| controller.checkSubjectIDToRegister(subjectID) == -1) {
									controller.showStudentListInClass(subjectID);
									System.out.println("Press any key to get back");
									scanner.nextLine();
									break;
								} else {
									System.out.println("Invalid subject ID, try again: ");
								}
							} while (true);

							continue;
						}
					} else {
						System.out.println("Invalid input, try again: ");
						continue;
					}

					if (choice == 0) {
						break;
					}
				} while (true);

				break;

			case 4:
				ArrayList<String> list = controller.getRegisterInfo(studentID);

				if (list.size() == 0) {
					System.out.println("\n\n\n\t\tYou haven't registered any subject yet!");
				} else {
					controller.showRegisterInfo(studentID);
					controller.showSubjectInfo();
					controller.checkAndShowCreditInfo(studentID);
				}

				do {
					System.out.println("\n\n\n");
					System.out.println("0. Back to menu");
					System.out.println("1. Register a new subject");
					System.out.println("2. Remove a subject");
					System.out.println("3. Sort subject by...");

					do {
						input = scanner.nextLine();
						if (input.matches("[0-3]{1}")) {
							choice = Integer.parseInt(input);
							break;
						} else {
							System.out.println("Invalid input, try again: ");
						}
					} while (true);

					switch (choice) {

					case 0:
						mainDoWhile = true;
						break;

					case 1:

						if (controller.checkAndShowCreditInfo(studentID) == 0) {
							System.out.println("You can not register more credits, press any key to get back");
							input = scanner.nextLine();
							continue;
						}

						controller.showSubjectInfo();
						System.out.println("\nEnter the subject ID to register: ");

						String subjectID;

						do {

							subjectID = scanner.nextLine();
							subjectID = subjectID.toUpperCase();

							if (controller.checkSubjectIDToRegister(subjectID) == 1
									&& !controller.checkFullClass(subjectID)) {
								int checkRegister = controller.registerSubject(studentID, subjectID);

								if (checkRegister == 1) {
									System.out.println("\n\n\t\tRegistered Successfully\n\n");

									if (controller.checkAndShowCreditInfo(studentID) == 0) {
										System.out.println(
												"You have already reached the maximum credits allowed, press any key to get back");
										input = scanner.nextLine();
										input = "0";
										mainDoWhile = false;
										break; // Get back to case 3
									} else {
										System.out.println(
												"Press any key to register more subject, or press 0 to get back");

										input = scanner.nextLine();

										if (input.matches("0")) {
											mainDoWhile = false;
											break; // Get back to case 3
										} else {
											System.out.println("\nEnter the subject ID to register: ");
											continue;
										}
									}

								} else if (checkRegister == 0) {
									System.out.println(
											"Duplicated subject. Press any key to try again or press 0 to get back");
									input = scanner.nextLine();

									if (input.matches("0")) {
										mainDoWhile = false;
										break;
									} else {
										System.out.println("Enter the subject ID again: ");
									}
								} else if (checkRegister == -1) {
									System.out.println(
											"Overload credits. Press any key to try another one or press 0 to get back");
									input = scanner.nextLine();

									if (input.matches("0")) {
										mainDoWhile = false;
										break;
									} else {
										System.out.println("Enter the subject ID again: ");
									}
								}

							} else if (controller.checkSubjectIDToRegister(subjectID) == 0) {
								System.out.println(
										"The subject ID does not exist. Press any key to try again or press 0 to get back ");
								input = scanner.nextLine();

								if (input.matches("0")) {
									mainDoWhile = false;
									break;
								} else {
									System.out.println("Enter the subject ID again: ");
									continue;
								}
							} else if (controller.checkFullClass(subjectID)) {
								System.out.println("The class of this subject is full, "
										+ "press any key to try again or press 0 to get back");
								input = scanner.nextLine();

								if (input.matches("0")) {
									mainDoWhile = false;
									break;
								} else {
									System.out.println("Enter the subject ID again: ");
									continue;
								}
							} else {
								System.out
										.println("The status of this subject is closed so that you cannot register it, "
												+ "press any key to try again or press 0 to get back");
								input = scanner.nextLine();

								if (input.matches("0")) {
									mainDoWhile = false;
									break;
								} else {
									System.out.println("Enter the subject ID again: ");
									continue;
								}
							}
						} while (true);

						break;

					case 2:

						if (controller.checkAndShowCreditInfo(studentID) == -1) {
							System.out.println(
									"Can not remove because of your registered subjects list is empty, press any key to get back!");
							input = scanner.nextLine();
							continue;
						}
						System.out.println("Enter the subject ID to remove: ");

						do {
							subjectID = scanner.nextLine();
							subjectID = subjectID.toUpperCase();

							if (controller.checkSubjectIDToRemove(studentID, subjectID)) {
								controller.removeSubject(studentID, subjectID);
								controller.checkAndShowCreditInfo(studentID);
								System.out.println("\n\n\t\tRemoved Successfully\n\n");
								input = "0";
								mainDoWhile = false;
								break; // Get back to case 3
							} else {
								System.out.println(
										"The subject ID does not exist in your list. Press any key to try again or press 0 to get back ");
								input = scanner.nextLine();

								if (input.matches("0")) {
									mainDoWhile = false;
									break;
								} else {
									System.out.println("Enter the subject ID again: ");
								}
							}
						} while (true);

						break;

					case 3:

						ArrayList<RegisterInfo> registerList = controller.showRegisterInfo(studentID);

						do {
							System.out.println("1. By credits");
							System.out.println("2. By ID");
							System.out.println("3. By registered time");
							System.out.println("0. Get back");

							input = scanner.nextLine();
							if (input.matches("[0-3]")) {
								choice = Integer.parseInt(input);

								switch (choice) {

								case 0:
									mainDoWhile = false;
									break;

								case 1:
									controller.sortRegisterInfoByCredit(registerList);
									break;

								case 2:
									controller.sortRegisterInfoByID(registerList);
									break;

								case 3:
									controller.sortRegisterInfoByTime(registerList);
									break;

								}

								if (choice == 0) {
									mainDoWhile = false;
									break;
								}

								System.out.println("Press 0 to get back, or any key to continue sorting");
								input = scanner.nextLine();

								if (input.matches("0")) {
									mainDoWhile = false;
									break;
								} else {
									System.out.println("Enter number to sort: ");
									continue;
								}

							} else {
								System.out.println("Invalid input, try again:");
								continue;
							}
						} while (true);

						break;
					}

					if (input.matches("0") && mainDoWhile == false) {
						continue; // Get back to case 3
					}

					break;
				} while (true);

				break;
			}

		} while (!outOfUserMode);

		menu = 0;
	}

	private static void loginMenu() {

		boolean outOfLogin = false;
		int choice;
		String password;

		do {
			System.out.println("\n\n\n\t\t\t\t\t\tStudent Infomation System");
			System.out.println(
					"------------------------------------------------------------------------------------------------------------------\n");
			System.out.print("1. Login\t\t");
			System.out.print("2. Create an account\t\t");
			System.out.print("3. Login as an administrator\t\t\t");
			System.out.println("0. Exit\n\n");

			do {
				input = scanner.nextLine();
				if (input.matches("[0-3]{1}")) {
					choice = Integer.parseInt(input);
					break;
				} else {
					System.out.println("Invalid input, try again: ");
				}
			} while (true);

			boolean loginMenu = false;

			switch (choice) {

			case 0:
				System.out.println("\t\t\tThank you!");
				outOfLogin = true;
				menu = -1;
				break;

			case 1:
				System.out.println("Enter your ID: ");

				do {
					input = scanner.nextLine();

					if (input.matches("^20[0-9]{6}")) {
						studentID = Integer.parseInt(input);

						if (!controller.checkStudentID(studentID)) {
							break;
						} else {
							System.out.println("The ID does not exist, press any key to try again or press 0 to exit ");
							input = scanner.nextLine();

							if (input.equals("0")) {
								loginMenu = true;
								break;
							} else {
								System.out.println("Enter your ID again: ");
							}
						}

					} else {
						System.out.println("Invalid input, press any key to try again or press 0 to exit ");
						input = scanner.nextLine();

						if (input.equals("0")) {
							loginMenu = true;
							break;
						} else {
							System.out.println("Enter your ID again: ");
						}
					}
				} while (true);

				if (loginMenu) {
					break;
				}

				System.out.println("Enter your password: ");

				do {
					password = scanner.nextLine();

					if (controller.checkAccount(studentID, password)) {

						outOfLogin = true;
						menu = 1;
						break;
					} else {
						System.out.println("Incorrect password, press any key to try again or press 0 to exit ");
						input = scanner.nextLine();

						if (input.equals("0")) {
							break;
						} else {
							System.out.println("Enter your password again: ");
						}
					}
				} while (true);

				break;

			case 2:
				System.out.println("Enter a new ID containing totally 8 digits: 20xxxxxx, x is a digit ");

				do {
					input = scanner.nextLine();
					if (input.matches("^20[0-9]{6}")) {
						studentID = Integer.parseInt(input);

						if (controller.checkStudentID(studentID)) {
							break;
						} else {
							System.out.println("The ID already exists, press any key to try again or press 0 to exit ");
							input = scanner.nextLine();

							if (input.equals("0")) {
								loginMenu = true;
								break;
							} else {
								System.out.println("Enter the ID again: ");
							}
						}
					} else {
						System.out.println("Invalid input, try again: ");
					}
				} while (true);

				if (loginMenu) {
					break;
				}

				System.out.println("Enter a password including 8 characters, bigins with an uppercase");

				do {
					password = scanner.nextLine();

					if (password.matches("^[A-Z]{1}[A-Za-z0-9]{7}$")) {

						System.out.println("Confirm your password: ");
						String check_pass = scanner.nextLine();

						if (check_pass.equals(password)) {
							break;
						} else {
							System.out.println("The password did not match each other!\nEnter your password again: ");
							continue;
						}
					} else {
						System.out.println("Invalid input, try again: ");
					}

				} while (true);

				// Register:

				controller.registerAccount(studentID, password);

				if (studentID < 20200000) {
					System.out.println("\t\tYour account has been created successfully! "
							+ "However, we recommend you to create an ID after 20200000 for a long-term support");
				} else {
					System.out.println("\t\tRegistered Successfully!");
				}

				menu = 0;
				outOfLogin = true;
				break;

			case 3:
				System.out.println("Enter your admin ID");

				do {
					input = scanner.nextLine();

					if (input.matches("20132143")) {
						System.out.println("Enter your password");

						do {
							password = scanner.nextLine();
							if (password.matches("Bachkhoa")) {
								input = "0";
								menu = 2;
								outOfLogin = true;
								break;
							} else {
								System.out.println("Invalid password, press any key to try again or 0 to exit ");
								input = scanner.nextLine();
								if (input.matches("0")) {
									break;
								} else {
									System.out.println("Enter the password again: ");
									continue;
								}
							}
						} while (true);

					} else {
						System.out.println("Invalid admin ID, press any key to try again or 0 to exit ");

						input = scanner.nextLine();
						if (input.matches("0")) {
							break;
						} else {
							System.out.println("Enter the ID again: ");
							continue;
						}
					}

					if (input.matches("0")) {
						break;
					}

				} while (true);

				break;
			}

		} while (!outOfLogin);

	}

	private static void completeInformation() {

		boolean outOfCompleteInfoMode = false;
		boolean backToMenu = false;
		String fullName = "";
		String sex = "";
		String faculty = "";
		String birthDate = "";
		String address = "";
		String phone = "";
		String email = "";

		do {
			System.out.println("\n\t\tYour information is not fully filled. Please complete your information!\n\n\n");
			System.out.println("Enter your full name: ");
			fullName = scanner.nextLine();
			System.out.println("Enter your sex: 1. Male\t 2. Female 3. Others");

			// This do-while loop prompt the user to choose their sex until it's true
			do {
				input = scanner.nextLine();

				if (input.matches("[0-3]")) {
					int sexChoice = Integer.parseInt(input);

					switch (sexChoice) {

					case 1:
						sex = "Male";
						break;

					case 2:
						sex = "Female";
						break;

					case 3:
						sex = "Others";
						break;
					}
					break; // Break out of this do-while loop

				} else {
					System.out.println("Invalid input, press any key to try again or press 0 to exit ");
					input = scanner.nextLine();

					if (input.equals("0")) {
						backToMenu = true;
						break; // Break out of this do-while loop
					} else {
						System.out.println("Enter again: ");
					}
				}

			} while (true);

			if (backToMenu) {
				continue;
			}

			// Input for faculty

			System.out.println("Enter your faculty: ");
			faculty = scanner.nextLine();

			// Input for address

			System.out.println("Enter your address: ");
			address = scanner.nextLine();

			// Input for D.O.B
			// This do-while loop prompt the users to enter their correct DOB

			do {
				int month, date, year;
				System.out.println("Enter your month of birth: ");

				do {
					input = scanner.nextLine();

					if (input.matches("[0-9]{1,2}")) {
						month = Integer.parseInt(input);

						if (month > 12 || month == 0) {
							System.out.println("Invalid month, try again: ");
							continue;
						} else {
							break; // Break out of this do-while loop
						}
					} else {
						System.out.println("Invalid input, try again: ");
					}
				} while (true);

				System.out.println("Enter your date of birth: ");

				do {
					input = scanner.nextLine();
					if (input.matches("[0-9]{1,2}")) {
						date = Integer.parseInt(input);

						if ((date > 31 || date <= 0)
								|| ((month == 4 || month == 6 || month == 9 || month == 10 || month == 11) && date > 30)
								|| (month == 2 && date > 29)) {
							System.out.println("Invalid date, try again: ");
							continue;
						} else {
							break; // Break out of this do-while loop
						}
					} else {
						System.out.println("Invalid input, try again: ");
					}
				} while (true);

				System.out.println("Enter your year of birth: ");

				do {
					input = scanner.nextLine();

					if (input.matches("[0-9]{4}")) {
						year = Integer.parseInt(input);

						if (year > 2005 || year < 1950) {
							System.out.println("Invalid year, try again: ");
							continue;
						} else {
							break; // Break out of this do-while loop
						}
					} else {
						System.out.println("Invalid input, try again: ");
					}
				} while (true);

				if (month < 10 && date < 10) {
					birthDate = "0" + date + "/0" + month + "/" + year;
				} else if (month < 10) {
					birthDate = date + "/0" + month + "/" + year;
				} else if (date < 10) {
					birthDate = "0" + date + "/" + month + "/" + year;
				} else {
					birthDate = date + "/" + month + "/" + year;
				}

				break; // Correct D.O.B, break out of this do-while loop

			} while (true);

			// Input for phone number
			System.out.println("Enter your phone number: ");

			do {
				phone = scanner.nextLine();

				if (phone.matches("^0[239]{1}[0-9]{8}$")) {
					break;
				} else {
					System.out.println("Invalid phone number, try again: ");
				}

			} while (true);

			// Input for email
			System.out.println("Enter your email address: ");

			do {
				email = scanner.nextLine();

				if (email.matches("^[a-z]{1}[a-z0-9]{5,20}@[a-z]{1,10}mail.com$")) {
					break;
				} else {
					System.out.println("Invalid email address, try again: ");
				}

			} while (true);

			break;

		} while (!outOfCompleteInfoMode);

		Student student = new Student(studentID, fullName, sex, faculty, address, birthDate, phone, email);
		controller.addStudentToDB(student);
	}
	
}
