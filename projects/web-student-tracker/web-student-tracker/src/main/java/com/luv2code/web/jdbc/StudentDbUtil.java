package com.luv2code.web.jdbc;

import java.sql.*;
import java.util.*;

import javax.sql.DataSource;

public class StudentDbUtil {

	private DataSource dataSource;

	public StudentDbUtil(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	public List<Student> getStudents() throws SQLException {
		
		final List<Student> students = new ArrayList<>();
		
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		
		try {
			
			// Get db connection
			connection = dataSource.getConnection();
			
			// Create sql statement
			statement = connection.createStatement();
			
			// Execute query
			final String sql = "select * from student order by first_name";
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
	
	public Student getStudent(String id) throws Exception {
		
		Student student = null;
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		int studentId;
		
		try {
			
			// Convert student id to int
			studentId = Integer.parseInt(id);
			
			// Get db connection
			connection = dataSource.getConnection();
			
			// Create sql to get selected student
			final String sql = "select * from student where id=?";
			preparedStatement = connection.prepareStatement(sql);
			
			// Set the param value for the student
			preparedStatement.setInt(1, studentId);
			
			// Execute query
			resultSet = preparedStatement.executeQuery();
			
			// Process result set
			if (resultSet.next()) {
				
				// Retrieve data from result set row
				final String firstName = resultSet.getString("first_name");
				final String lastName = resultSet.getString("last_name");
				final String email = resultSet.getString("email");
				
				// Use the studentId during construction
				student = new Student(studentId, firstName, lastName, email);
				
			} else {
				throw new Exception("Could not find student id: " + studentId);
			}
			
			return student;
			
		} finally {
			
			// Close JDBC objects
			close(connection, preparedStatement, resultSet);
			
		}
		
	}
	
	public void deleteStudent(String id) throws SQLException  {
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		int studentId;
		
		try {
			// Convert student id to int
			studentId = Integer.parseInt(id);
			
			// Get db connection
			connection = dataSource.getConnection();
			
			// Create sql to delete selected student
			final String sql = "delete from student where id=?";
			preparedStatement = connection.prepareStatement(sql);
			
			// Set the param value for the student
			preparedStatement.setInt(1, studentId);
			
			// Execute
			preparedStatement.execute();
			
		} finally {
			
			// Close JDBC objects
			close(connection, preparedStatement, null);
			
		}
		
	}
	
	public List<Student> searchStudents(String searchName) throws SQLException {
		
		final List<Student> students = new ArrayList<>();
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		try {
			
			// Get db connection
			connection = dataSource.getConnection();
			
			if (searchName != null && searchName.trim().length() > 0) {
				
				// Create sql to search for students by name
				final String sql = "select * from student " + ""
									+ "where lower(first_name) like ? or lower(last_name) like ? " 
									+ "order by first_name";
				preparedStatement = connection.prepareStatement(sql);
				
				// Set the param values for the student
				final String searchNameLike = "%" + searchName.toLowerCase() + "%"; 
				preparedStatement.setString(1, searchNameLike);
				preparedStatement.setString(2, searchNameLike);
				
			} else {
				
				// Create sql to get all students
				final String sql = "select * from student order by first_name";
				preparedStatement = connection.prepareStatement(sql);
				
			}
			
			// Execute
			resultSet = preparedStatement.executeQuery();
			
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
			close(connection, preparedStatement, resultSet);
			
		}
		
	}

	public void postStudent(Student student) throws SQLException {
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		try {
			
			// Get db connection
			connection = dataSource.getConnection();
			
			// Create sql to insert a new student
			final String sql = "insert into student "
								+ "(first_name, last_name, email) "
								+ "values (?, ?, ?)";
			preparedStatement = connection.prepareStatement(sql);
			
			// Set the param values for the student
			preparedStatement.setString(1, student.getFirstName());
			preparedStatement.setString(2, student.getLastName());
			preparedStatement.setString(3, student.getEmail());
			
			// Execute
			preparedStatement.execute();
			
		} finally {
			
			// Close JDBC objects
			close(connection, preparedStatement, null);
			
		}
		
	}
	
	public void putStudent(Student student) throws SQLException {
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		try {
			
			// Get db connection
			connection = dataSource.getConnection();
			
			// Create sql to update selected student
			final String sql = "update student "
								+ "set first_name=?, last_name=?, email=? "
								+ "where id=?";
			preparedStatement = connection.prepareStatement(sql);
			
			// Set the param values for the student
			preparedStatement.setString(1, student.getFirstName());
			preparedStatement.setString(2, student.getLastName());
			preparedStatement.setString(3, student.getEmail());
			preparedStatement.setInt(4, student.getId());
			
			// Execute
			preparedStatement.execute();
			
		} finally {
			
			// Close JDBC objects
			close(connection, preparedStatement, null);
			
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
