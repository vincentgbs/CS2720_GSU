package Registrar;

import java.util.*;
//import java.util.regex.Pattern;

public class Main {
	
	private Scanner reader = new Scanner(System.in);
	private LinkedList<Course> courses = new LinkedList<Course>();
	
	public int mainMenu() {
		System.out.println("What would you like to do?");
		System.out.println("1. Create a course");
		System.out.println("2. Add a student to a course");
		System.out.println("3. Check if a student is in a course");
		System.out.println("4. Remove a student from a course");
		System.out.println("5. Exit the menu (and view created courses)");
		int o = this.reader.nextInt();
		while (o <= 0 || o >= 6) {
			System.out.println("That is an invalid option:");
			return this.mainMenu();
		}
		if (o == 1) {
			return this.createACourse();
		} else if (o == 2) {
			return this.addToCourse();
		} else if (o == 3) {
			return this.findStudent();
		} else if (o == 4) {
			return this.removeFromCourse();
		} else if (o == 5) {
			if (!this.courses.isEmpty()) {
				for (int i = 0; i < this.courses.size(); i++) {
				    System.out.println(this.courses.get(i).viewCourse());
				}
			}
			reader.close();
		}
		return 0;
	}
	
	private int createACourse() {
		System.out.println("What is the course name?");
		String n = this.reader.next("[A-Za-z0-9 ]+");
		System.out.println("What is the course number?");
		this.reader.nextLine();
		int c = this.reader.nextInt();
		System.out.println("What is the maximum number of students?");
		int m = this.reader.nextInt();
		Course toAdd = new Course(n, c, m);
		System.out.println("Who will teach this course?");
		String tn = this.reader.next("[A-Za-z ]+");
		System.out.println("What is their instructor id?");
		int ti = this.reader.nextInt();
		toAdd.setInstructor(new Instructor(tn, ti));
		this.courses.add(toAdd);
		System.out.println("Course created!");
		return this.mainMenu();
	}
	
	private void addACourse(Course c, Instructor i) {
		c.setInstructor(i);
		this.courses.add(c);
	}
	
	public int addToCourse() {
		System.out.println("What is this student's name?");
		String n = this.reader.next("[A-Za-z ]+");
		System.out.println("What is this student's id?");
		int i = this.reader.nextInt();
		Student a = new Student(n, i);
		System.out.println("What course would you like to add " + a.getName() + " to?");
		for (int x = 0; x < this.courses.size(); x++) {
		    System.out.println(x + ". " + this.courses.get(x).getName());
		}
		int c = this.reader.nextInt();
		System.out.println("Ok, we will try to add " + a.getName() + " to " + this.courses.get(c).getName());
		this.courses.get(c).addStudent(a);
		return this.mainMenu();
	}
	
	public int findStudent() {
		System.out.println("What course would you like to check enrollment for?");
		for (int x = 0; x < this.courses.size(); x++) {
		    System.out.println(x + ". " + this.courses.get(x).getName());
		}
		int c = this.reader.nextInt();
		System.out.println("And who are you looking for (student's id)?");
		int i = this.reader.nextInt();
		int spot = this.courses.get(c).findStudent(i);
		if (spot == -1) {
			System.out.println("They are not in this class");
		} else {
			System.out.println("They are in this class");
		}
		return this.mainMenu();
	}
	
	public int removeFromCourse() {
		System.out.println("What course would you like to remove a student from?");
		for (int x = 0; x < this.courses.size(); x++) {
		    System.out.println(x + ". " + this.courses.get(x).getName());
		}
		int c = this.reader.nextInt();
		System.out.println("And who are you looking to remove (student's id)?");
		int i = this.reader.nextInt();
		this.courses.get(c).removeStudent(new Student("", i));
		/* you can create a new student with the same id because previous specifications
		 * for this assignment used student id's to match students in both the Student and
		 * Course classes. */
		System.out.println("Ok, we will try to remove them from " + this.courses.get(c).getName());
		return this.mainMenu();
	}
	
	public static void quickDemo() {
		Student stA = new Student("Steve", 12345);
		Student stB = new Student("Tony", 98765);
		System.out.println("Student A's name is: " + stA.getName());
		System.out.println("Student B's name is: " + stB.getName());
		System.out.println("Student A's student id is: " + stA.getStudentId());
		System.out.println("Student B's student id is: " + stB.getStudentId());
		System.out.println(stA);
		System.out.println(stB);
		System.out.println("Student A equal student B? " + stA.equals(stB));
		System.out.println("Student A equal student A? " + stA.equals(stA));
		stA.setCredit(12);
		stB.setCredit(16);
		System.out.println("Student B has " + stB.getCredits() + " credits.");
		stA.setGradePointsEarned(42);
		stB.setGradePointsEarned(60);
		System.out.println("Student B has " + stB.getGradePointsEarned() + " grade points earned.");
		System.out.println("Student B has a GPA of " + stB.getGPA());
		Instructor inA = new Instructor("Dr Strange", 246);
		inA.setDepartment("Math");
		System.out.println(inA + "(id), is in the " + inA.getDepartment() + " department.");
		Course cA = new Course("Math 101", 101);
		cA.setInstructor(inA);
		System.out.println(cA.getInstructor() + "(id), is the instructor for " + cA.getName());
		String trueOrFalse = (cA.findStudent(stA.getStudentId())==-1?"False":"True");
		System.out.println("Check: " + stA.getName() + " is enrolled in " + cA.getName() + "? " + trueOrFalse);
		cA.addStudent(stA); // added stA to cA
		trueOrFalse = (cA.findStudent(stA.getStudentId())==-1?"False":"True");
		System.out.println("Now, " + stA.getName() + " is enrolled in " + cA.getName() + "? " + trueOrFalse);
		System.out.println(cA.getStudents());
		cA.addStudent(stB); // added stA to cA
		System.out.println(cA.getStudents());
		for (int i = 1; i < 35; i++) {
			Student stC = new Student("person" + i, 10000 + i);
			cA.addStudent(stC);
		}
		// there were already 2 students enrolled, so the last i student should throw an error
		System.out.println(cA.getStudents());
		cA.removeStudent(stB); // remove Tony
		System.out.println(cA.getStudents());
		cA.removeStudent(stB); // should throw an error because Tony was already removed
		System.out.println("Course A before serialization: \n" + cA.viewCourse());
		cA.serialize("test.txt");
		Course cB = cA.unserialize("test.txt");
		System.out.println("Course B after unserialization: \n" + cB.viewCourse());
	}
	
	public static void main(String[] args) {
//		quickDemo();
		Main demo = new Main();
		demo.addACourse(new Course("Math 101", 10113), new Instructor("Bruce Banner", 24680));
		demo.addACourse(new Course("English 101", 1015), new Instructor("Shuri", 13579));
		demo.addACourse(new Course("CS 101", 101319), new Instructor("Jarvis", 98765));
		demo.mainMenu();
	}

}