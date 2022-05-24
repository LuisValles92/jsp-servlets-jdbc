package com.luv2code.web.jdbc;

import java.io.*;
import java.sql.*;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.sql.DataSource;

/**
 * Servlet implementation class TestServlet
 */
@WebServlet("/TestServlet")
public class TestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	// Define datasource/connection pool for Resource Injection
	@Resource(name="jdbc/web_student_tracker") // Same name as in context.xml
	private DataSource dataSource;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// Step 1: Set up the printwriter
		PrintWriter printWriter = response.getWriter();
		response.setContentType("text/plain");
		
		// Step 2: Get a connection to the database
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		
		try {
			
			connection = dataSource.getConnection();
			
			// Step 3: Create a SQL statements
			statement = connection.createStatement();
			
			// Step 4: Execute SQL query
			final String sql = "select * from student";
			resultSet = statement.executeQuery(sql);
			
			// Step 5: Process the result set
			while (resultSet.next()) {
				String email = resultSet.getString("email");
				printWriter.println(email);
			}
			
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		
	}

}
