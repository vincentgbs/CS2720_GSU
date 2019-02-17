package Registrar;

public class Main {

	public void createACourse() {
		//
	}
	
	public void addToCourse(Student toAdd) {
		//
	}
	
	public boolean findStudent(Student toFind, Course toSearch) {
		return false;
	}
	
	public void removeFromCourse(Student toRemove) {
		//
	}
	
	public static void main(String[] args) {
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
//		System.out.println(cA.getStudents());
		cA.addStudent(stB); // added stA to cA
//		System.out.println(cA.getStudents());
		for (int i = 1; i < 35; i++) {
			Student stC = new Student("person" + i, 10000 + i);
			cA.addStudent(stC);
		}
		// there were already 2 students enrolled, so the last student should throw an error
//		System.out.println(cA.getStudents());
		cA.removeStudent(stB);
//		System.out.println(cA.getStudents());
		cA.removeStudent(stB);
		System.out.println("Course A before serialization: " + cA.getStudents());
		cA.serialize("test.txt");
		Course cB = cA.unserialize("test.txt");
		System.out.println("Course B after unserialization" + cB.getStudents());
	}

}
