package Registrar;

import java.io.Serializable;
import java.util.*;

public class Student extends Person implements Serializable, Comparable {

	private String studentId;
	private int numberOfCredits;
	private int totalGradePointsEarned;
	
	public Student(String name, String studentId) {
		super(name, 0);
		this.studentId = this.regex(studentId, "([0-9]{1,4}-[A-Z]{2,6})", 1);
	}
	
	public String getName() {
		return this.name;
	}
	
	public String getStudentId() {
		return this.studentId;
	}
	
	public boolean equals(Student compare) {
		return (this.studentId == compare.getStudentId());
	}
	
	public void setCredit(int credits) {
		this.numberOfCredits = credits;
	}
	
	public int getCredits() {
		return this.numberOfCredits;
	}
	
	public void setGradePointsEarned(int points) {
		this.totalGradePointsEarned = points;
	}
	
	public int getGradePointsEarned() {
		return this.totalGradePointsEarned;
	}
	
	public float getGPA() {
		return ((float) this.totalGradePointsEarned)/((float) this.numberOfCredits);
	}

	// allow LinkedList to hold the records in the sorted order 
	public int compareTo(Object o) {
		Student stud = (Student)o;
		if (stud.getStudentId() != "") {
			String[] me = this.studentId.split("-");
			String[] you = stud.getStudentId().split("-");
			int numMe = Integer.parseInt(me[0]);
			int numYou = Integer.parseInt(you[0]);
			if (numMe < numYou) {
				return -1;
			} else if (numMe == numYou) {
				if (me[1].compareToIgnoreCase(you[1]) < 0) {
					return -1;
				} else {
					return 1;
				}
			} else {
				return 1;
			}
		} // else invalid student
		return 0;
	}

	public String toString()
	{
		return "(" + this.name + ", " + this.studentId + ")";
	}

	public String studentRecord()
	{
		return "(" + this.studentId + ". " + this.name + ": CRT-"
			+ this.numberOfCredits + ", GPE-" + this.totalGradePointsEarned
			+ ", GPA-" + this.getGPA() + ")";
	}
	
}
