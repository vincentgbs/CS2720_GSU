package Registrar;

import java.io.*;

public class Course implements Serializable {
	
	private String name;
	private int regCode;
	private int maxStudents;
	private Instructor teacher;
	private int numStudents;
	private Student[] registered;
	
	public Course(String name, int regCode) {
		this.name = name;
		this.regCode = regCode;
		this.maxStudents = 35;
		this.registered = new Student[35];
	}
	
	public Course(String name, int regCode, int maxNum) {
		this.name = name;
		this.regCode = regCode;
		this.maxStudents = maxNum;
		this.registered = new Student[maxNum];
	}
	
	public void setInstructor(Instructor teacher) {
		this.teacher = teacher;
	}
	
	public Instructor getInstructor() {
		return this.teacher;
	}
	
	public int findStudent(int id) {
		for (int i = 0; i < numStudents; i++) {
			if (this.registered[i].getStudentId() == id) {
				return i;
			}
		}
		return -1;
	}
	
	public void addStudent(Student toAdd) {
		try {
			if (this.numStudents >= this.maxStudents) {
				throw new CourseException("This course is full");
			}
			if (this.findStudent(toAdd.getStudentId()) == -1) {
				int i = 0;
				while (this.registered[i] != null) {
					i++;
				}
				this.registered[i] = toAdd;
				this.numStudents++;
			}
		} catch (CourseException e) {
			e.printStackTrace();
		}
	}
	
	public void removeStudent(Student toRemove) {
		try {
			int check = this.findStudent(toRemove.getStudentId());
			if (check == -1) {
				throw new CourseException(toRemove.getName() + " is not enrolled");
			}
			for (; check < this.registered.length - 2; check++) {
				this.registered[check] = this.registered[check + 1];
			}
			this.registered[this.registered.length -1] = null;
			this.numStudents--;
		} catch (CourseException e) {
			e.printStackTrace();
		}
	}
	
	public void serialize(String filename) {
		// A method that will allow Course objects to be output to a file using object serialization
		try {
			FileOutputStream file = new FileOutputStream(filename);
			ObjectOutputStream out = new ObjectOutputStream(file);
			// serialize the object
			out.writeObject(this);
			out.close(); 
			file.close(); 
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Course unserialize(String filename) {
		// A method that will allow Course objects to be read in from a file created with Object serialization
		try
        {
            FileInputStream file = new FileInputStream(filename); 
            ObjectInputStream in = new ObjectInputStream(file);
            // unserialize the object
            Course ret = (Course)in.readObject();
            in.close(); 
            file.close(); 
            return ret;
        } catch(IOException e) { 
            e.printStackTrace(); 
        } catch(ClassNotFoundException e) { 
        	e.printStackTrace(); 
        }
		return null;
	}
	
	
	
	// additional helper functions...
	public String getName() {
		return this.name;
	}
	
	// additional helper function
	public String getStudents() {
		String stringOfStudents = "";
		int i = 0;
		while (this.registered[i] != null && i < this.maxStudents - 1) {
			stringOfStudents += (i+1) + "." + this.registered[i].toString();
			i++;
			if (this.registered[i] != null) {
				stringOfStudents += "; ";
			}
		}
		return stringOfStudents;
	}
}