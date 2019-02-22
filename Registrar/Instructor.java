package Registrar;

import java.io.Serializable;

public class Instructor extends Person {

	private int instructorId;
	private String department;
	
	public Instructor(String name, int id) {
		super(name, id);
		// an instructor can also be a student
		this.instructorId = id;
	}
	
	public void setDepartment(String dept) {
		this.department = dept;
	}
	
	public String getDepartment() {
		return this.department;
	}
	
}
