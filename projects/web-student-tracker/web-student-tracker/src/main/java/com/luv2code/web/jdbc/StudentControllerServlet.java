package com.luv2code.web.jdbc;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.sql.DataSource;

/**
 * Servlet implementation class StudentControllerServlet
 */
@WebServlet("/StudentControllerServlet")
public class StudentControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private StudentDbUtil studentDbUtil;
	
	@Resource(name="jdbc/web_student_tracker")
	private DataSource dataSource;
	
	@Override
	public void init() throws ServletException {
		super.init();
		
		// Create our student db util ... and pass in the connection pool / datasource
		try {
			
			studentDbUtil = new StudentDbUtil(dataSource);
			
		} catch (Exception exception) {
			throw new ServletException(exception);
		}
		
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			
			// Read the "command" parameter
			String theCommand = request.getParameter("command");
			
			if (theCommand == null) {
				theCommand = "LIST";
			}
			
			// Route to the appropiate method
			switch (theCommand) {
			
				case "LIST":
					// List the students ... in MVC fashion
					listStudents(request, response);
					break;
				
				case "ADD":
					addStudent(request, response);
					break;
					
				case "LOAD":
					loadStudent(request, response);
					break;
					
				case "UPDATE":
					updateStudent(request, response);
					break;
					
				default:
					listStudents(request, response);
					
			}
			
			
			
		} catch (Exception exception) {
			throw new ServletException(exception);
		}
		
	}

	private void listStudents(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		// Get students from db util
		List<Student> students = studentDbUtil.getStudents();
		
		// Add students to the request
		request.setAttribute("STUDENT_LIST", students);
		
		// Send to JSP page (view)
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/list-students.jsp");
		requestDispatcher.forward(request, response);
		
	}
	
	private void addStudent(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		// Read student info from form data
		final String firstName = request.getParameter("firstName");
		final String lastName = request.getParameter("lastName");
		final String email = request.getParameter("email");
		
		// Create a new studen object
		final Student student = new Student(firstName, lastName, email);
		
		// Add the student to the database
		studentDbUtil.addStudent(student);
		
		// Send back to main page (the student list)
		listStudents(request, response);
		
	}
	
	private void loadStudent(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		// Read student id from form data
		final String id = request.getParameter("id");
		
		// Get student form database (db util)
		final Student student = studentDbUtil.getStudent(id);
		
		// Place student in the request attribute
		request.setAttribute("THE_STUDENT", student);
		
		// Send to jsp page: update-student-form.jsp
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/update-student-form.jsp");
		requestDispatcher.forward(request, response);
		
	}
	
	private void updateStudent(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		// Read student info from form data
		final int id = Integer.parseInt(request.getParameter("id"));
		final String firstName = request.getParameter("firstName");
		final String lastName = request.getParameter("lastName");
		final String email = request.getParameter("email");
		
		// Create a new student object
		final Student student = new Student(id, firstName, lastName, email);
		
		// Perform update on database
		studentDbUtil.updateStudent(student);
		
		// Send back to main page (the student list)
		listStudents(request, response);
		
	}

}
