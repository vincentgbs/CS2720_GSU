package Registrar;

import java.io.*;
import java.util.StringTokenizer;
import java.util.regex.*;

public class Course {
	
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
		if (maxNum > 0) {
			this.maxStudents = maxNum;
			this.registered = new Student[maxNum];
		} else { // DEFAULT VALUE if user enters invalid argument
			this.maxStudents = 35;
			this.registered = new Student[35];
		}
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
			} else {
				System.out.println("Student is already enrolled in course");
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
	
	private String serializeInstructor(Instructor instructor) {
		if (instructor == null) { return ""; }
		return instructor.toString() + ", " + instructor.getDepartment();
	}
	
	private Instructor unserializeInstructor(String instructor) {
		StringTokenizer teacher = new StringTokenizer(instructor, ",");
		String name = (String) teacher.nextElement();
		int id = Integer.parseInt(((String) teacher.nextElement()).trim());
		String dept = (String) teacher.nextElement();
		Instructor unserial = new Instructor(name, id);
		unserial.setDepartment(dept);
		return unserial;
	}
	
	private String serializeStudent(Student student) {
		if (student == null) { return ""; }
		int credits = 0 + student.getCredits();
		int points = 0 + student.getGradePointsEarned();
		return student.toString() + ", " + credits +  ", " + points;
	}
	
	private Student unserializeStudent(String student) {
		StringTokenizer pupil = new StringTokenizer(student, ",");
		String name = (String) pupil.nextToken();
		int stId = Integer.parseInt(((String) pupil.nextToken()).trim());
		Student unserial = new Student(name, stId);
		if (pupil.hasMoreTokens()) {
			int credits = Integer.parseInt(((String) pupil.nextToken()).trim());
			unserial.setCredit(credits);			
		}
		if (pupil.hasMoreTokens()) { 
			int points = Integer.parseInt(((String) pupil.nextToken()).trim());
			unserial.setGradePointsEarned(points);
		}
		return unserial;
	}
	
	public void serialize(String filename) {
		// A method that will allow Course objects to be output to a file using object serialization
		StringBuilder contentBuilder = new StringBuilder();
		contentBuilder.append("<name>"+this.name+"</name>").append("\n");
		contentBuilder.append("<regCode>"+this.regCode+"</regCode>").append("\n");
		contentBuilder.append("<maxStudents>"+this.maxStudents+"</maxStudents>").append("\n");
		contentBuilder.append("<teacher>"+this.serializeInstructor(this.teacher)+"</teacher>").append("\n");
		contentBuilder.append("<registered>").append("\n");
		for (int i=0; i < this.numStudents ;i++) {
			contentBuilder.append(this.serializeStudent(this.registered[i]) + "|");
		}
		contentBuilder.append("</registered>");
		String courseString = contentBuilder.toString();
		try {
			FileOutputStream file = new FileOutputStream(filename);
			PrintStream out = new PrintStream(file);
			// serialize the object
			out.print(courseString);
			out.close(); 
			file.close(); 
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Course unserialize(String filename) {
		// A method that will allow Course objects to be read in from a file created with Object serialization
		StringBuilder contentBuilder = new StringBuilder();
	    try (BufferedReader br = new BufferedReader(new FileReader(filename)))
	    {
	        String sCurrentLine;
	        while ((sCurrentLine = br.readLine()) != null)
	        {
	            contentBuilder.append(sCurrentLine).append("\n");
	        }
	    }
	    catch (IOException e)
	    {
	        e.printStackTrace();
	    }
	    String courseString = contentBuilder.toString();
	    String name = this.regex(courseString, "<name>(.*?)</name>", 1);
	    int regCode = Integer.parseInt(this.regex(courseString, "<regCode>(.*?)</regCode>", 1));
	    int maxStudents = Integer.parseInt(this.regex(courseString, "<maxStudents>(.*?)</maxStudents>", 1));
	    Course unserial = new Course(name, regCode, maxStudents);
	    String teacher = this.regex(courseString, "<teacher>(.*?)</teacher>", 1);
	    if (teacher != "") {
	    	this.setInstructor(this.unserializeInstructor(teacher));
	    }
	    String registered = this.regex(courseString, "<registered>(.*?)</registered>", 1);
	    registered = registered.substring(0, registered.length()-11);
	    StringTokenizer studentList = new StringTokenizer(registered, "|", false);
	    while(studentList.hasMoreTokens()) {
	    	Student stA = this.unserializeStudent(studentList.nextToken());
	    	unserial.addStudent(stA);
	    }
		return unserial;
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
	
	// additional helper function
	public String viewCourse() {
		String stringOfCourse = "";
		stringOfCourse += "Course Name: " + this.name;
		stringOfCourse += "\nCourse Code: " + this.regCode;
		stringOfCourse += "\nMax Students: " + this.maxStudents;
		stringOfCourse += "\nEnrolled: " + this.numStudents;
		stringOfCourse += "\nInstructor: " + this.teacher + "\n";
		return stringOfCourse;
	}
	
	// additional helper function
	private String regex(String text, String regex, int match) {
	    Pattern pattern = Pattern.compile(regex, Pattern.DOTALL);
	    Matcher matcher = pattern.matcher(text);
	    if (matcher.find()) {
	    	return matcher.group(match);
	    } else {
	     	System.out.println(regex + " doesnt match anything");
	    }
	    return "";
	}
}