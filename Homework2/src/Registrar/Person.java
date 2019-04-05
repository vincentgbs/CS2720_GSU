package Registrar;

import java.io.Serializable;
import java.util.regex.*;

public class Person implements Serializable {

	protected String name;
	protected int id;
	protected String otherId;
	
	// a person's id could be different from their student or instructor id
	public Person(String name, int id) {
		this.name = name;
		this.id = id;
	}
	
	// Homework 2 addition
	public Person(String name, String otherId) {
		this.name = name;
	}
	
	public String toString() {
		return this.name;
	}
	
	// additional helper function
	protected String regex(String text, String regex, int match) {
	    Pattern pattern = Pattern.compile(regex, Pattern.DOTALL);
	    Matcher matcher = pattern.matcher(text);
	    if (matcher.find()) {
	    	return matcher.group(match);
	    } // else
	    return "";
	}
}
