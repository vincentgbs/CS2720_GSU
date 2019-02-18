Course Requirements:

A Student class that minimally stores the following data fields for a student: • name
• student id number
• number of credits
• total grade points earned
The following methods should also be provided:
■ A constructor that initializes the name and id fields
■ A method that returns the student name field
■ A method that returns the student ID field
■ A method that determines if two student objects are equal if their student id numbers are the same (override equals from the class object )
■ Methods to set and retrieve the total number of credits
■ Methods to set and retrieve the total number of grade points earned
■ A method that returns the CPA (grade points divided by credits)
An Instructor class that minimally stores the following data fields for an instructor:
• name
• faculty id number
• department
The following methods that should be provided
■ A constructor that initializes the name and id fields
■ Methods to set and retrieve the instructor's department
A Course class that minimally stores the following data for a course: • name of the course
• course registration code
• maximum number of 35 students
• instructor
• number of students
• students registered in the course (an array)
The following methods should also be provided:
■ A constructor that initializes the name, registration code, and maximum number of students
■ Methods to set and retrieve the instructor
■ A method to search for a student in the course; the search should be based on an ID number.
■ A method to add a student to the course. If the course is full, then an exception with an appropriate message should be raised (try creating your own exception class for this). Also, be sure that the student is not already registered in the course. The list of students should be in the order that they registered.
■ A method to remove a student from the course. If the student is not found, then an exception with an appropriate message should be raised (use the same exception class mentioned above).
■ A method that will allow Course objects to be output to a file using object serialization
■ A method that will allow Course objects to be read in from a file created with Object serialization
You will note that the Student and Instructor classes described above have some commonality. Create a Person class that captures this commonality and uses it as a base class tor Student and Instructor. This class should be responsible for the name and id fields and also provide a toString method that returns a string of the form name, id. This will be the inherited toString method for the Student and Instructor classes.
b) Implement the previous classes in Java. Write a main program that can serve as a test class that tests all of the methods created and demonstrates that they are working.
c) Write a second main program that allows user to:
i. create a course, prompting the user for all the course information ii. add students to the course
iii. check to see if a student is registered in the course, and
iv. remove a student from the course
