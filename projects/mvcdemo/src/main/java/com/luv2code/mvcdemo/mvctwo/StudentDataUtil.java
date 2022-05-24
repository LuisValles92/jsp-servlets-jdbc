package com.luv2code.mvcdemo.mvctwo;

import java.util.*;

public class StudentDataUtil {

	public static List<Student> getStudents() {
		
		// Create an empty list
		List <Student> students = new ArrayList<>();
		
		// Add sample data
		students.add(new Student("Mary", "Public", "mary@luv2code.com"));
		students.add(new Student("John", "Doe", "john@luv2code.com"));
		students.add(new Student("Ajay", "Rao", "ajay@luv2code.com"));
		
		// Return the list
		return students;
		
	}
	
}
