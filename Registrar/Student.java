package Registrar;

import java.io.Serializable;

public class Student extends Person implements Serializable {

	private int studentId;
	private int numberOfCredits;
	private int totalGradePointsEarned;
	
	public Student(String name, int id) {
		super(name, id);
		// a student could also be an instructor
		this.studentId = this.id;
	}

	public String getName() {
		return this.name;
	}
	
	public int getStudentId() {
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
	
}
