package Registrar;

import java.util.*;

public class StudentRepositoryList {

	private static Scanner reader = new Scanner(System.in);
	LinkedList<Student> repo = new LinkedList<Student>();
	
	private Student enterStudentInformation() {
		System.out.println("Enter student name:");
		String n = reader.next("[A-Za-z \\s]+");
		System.out.println("Enter student id (####-XXXXXX):");
		String i = reader.next("[0-9]{1,4}-[A-Z]{2,6}");
		Student stA = new Student(n, i);
		if (this.enterStudentGPA(stA)) {
			this.insertOrdered(stA);
		}
		return stA;
	}
	
	private boolean enterStudentGPA(Student stud)
	{
		System.out.println("How many credits does this student have?");
		int c = reader.nextInt();
		stud.setCredit(c);
		System.out.println("How many grade points has this student earned?");
		int p = reader.nextInt();
		System.out.println("fyi: This student's GPS is: " + ((float)p/((float)c)) + ". Is that correct (Y/N)?");
		String confirm = reader.next("[Y|N|y|n]");
		if (confirm.equalsIgnoreCase("Y")) {
			stud.setGradePointsEarned(p);
			return true;
		} else {
			return this.enterStudentGPA(stud);
		}
	}
	
	protected void insertOrdered(Student stA)
	{
		int i = 0; // index
		for (Student curr : repo) {
			if (curr.compareTo(stA) > 0) {
				repo.add(i, stA);
				return;
			} // else { // continue through loop }
			i++; // index
		}
		repo.add(stA);
	}
	
	protected Student getStudent(String id)
	{
		for (Student curr : repo) {
			if (curr.getStudentId().equalsIgnoreCase(id)) {
				return curr;
			}
		}
		return null;
	}
	
	private Queue<String> getStudentInformation()
	{
		Queue<String> student_ids = new LinkedList<String>();
		System.out.println("How many students would you like to get?");
		int count = reader.nextInt();
		for (int i = 0; i < count; i++) {
			System.out.println("Please enter the student id:");
			String id = reader.next("[0-9]{1,4}-[A-Z]{2,6}");
			student_ids.add(id);
		}
		reader.close();
		return student_ids;
	}
	
	private void printStudentInformation(Queue<String> student_ids)
	{
		while (!student_ids.isEmpty()) {
			String id = student_ids.poll();
			Student stud = this.getStudent(id);
			if (stud == null) {
				System.out.println("There is no student with id: " + id);
			} else {
				System.out.println(stud.studentRecord());
			}
		}
	}
	
	public static void main(String[] args) {
		StudentRepositoryList demo = new StudentRepositoryList();
		System.out.println("How many students would you like to input?");
		int count = reader.nextInt();
		for (int i = 0; i < count; i++) {
			demo.enterStudentInformation();
		}
		System.out.println("Student repository list: " + demo.repo);
		Queue<String> list = demo.getStudentInformation();
		demo.printStudentInformation(list);
	}
	
}
