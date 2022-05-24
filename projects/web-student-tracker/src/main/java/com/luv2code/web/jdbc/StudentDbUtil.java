package com.luv2code.web.jdbc;

import java.sql.*;
import java.util.*;

import javax.sql.DataSource;

public class StudentDbUtil {

	private DataSource dataSource;

	public StudentDbUtil(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	public List<Student> getStudents() throws Exception {
		
		List<Student> students = new ArrayList<>();
		
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		
		try {
			
			// Get a connection
			connection = dataSource.getConnection();
			
			// Create sql statement
			statement = connection.createStatement();
			
			// Execute query
			final String sql = "select * from student order by last_name";
			resultSet = statement.executeQuery(sql);
			
			// Process result set
			while (resultSet.next()) {
				
				// Retrieve data from result set row
				final int id = resultSet.getInt("id");
				final String firstName = resultSet.getString("first_name");
				final String lastName = resultSet.getString("last_name");
				final String email = resultSet.getString("email");
				
				// Create new student object
				final Student student = new Student(id, firstName, lastName, email);
				
				// Add it to the list of students
				students.add(student);
				
			}
			
			return students;
			
		} finally {
			
			// Close JDBC objects
			close(connection, statement, resultSet);
			
		}
		
	}

	private void close(Connection connection, Statement statement, ResultSet resultSet) {
		
		try {
			
			if (resultSet != null) {
				resultSet.close();
			}
			
			if (statement != null) {
				statement.close();
			}
			
			if (connection != null) {
				connection.close(); // Doesn't really close it ... just puts back in connection pool
			}
			
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		
	}
	
}
