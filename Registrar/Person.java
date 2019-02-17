package Registrar;

import java.io.Serializable;

public class Person implements Serializable {

	protected String name;
	protected int id;
	
	// should a person's id be different from their student or instructor id?
	public Person(String name, int id) {
		this.name = name;
		this.id = id;
	}
	
	public String toString() {
		return this.name + ", " + this.id;
	}
	
}
