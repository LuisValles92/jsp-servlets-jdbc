package com.luv2code.web.jdbc;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.*;
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
		studentDbUtil = new StudentDbUtil(dataSource);
		
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
					
				case "LOAD":
					loadStudent(request, response);
					break;
					
				case "DELETE":
					deleteStudent(request, response);
					break;
					
				default:
					listStudents(request, response);
					
			}
			
		} catch (NumberFormatException numberFormatException) {
			throw new ServletException(numberFormatException);
		} catch (SQLException sqlException) {
			throw new ServletException(sqlException);
		} catch (ServletException servletException) {
			throw new ServletException(servletException);
		} catch (IOException ioException) {
			throw new ServletException(ioException);
		} catch (Exception exception) {
			throw new ServletException(exception);
		}
		
	}

	private void listStudents(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
		
		// Get students from db util
		List<Student> students = studentDbUtil.getStudents();
		
		// Add students to the request
		request.setAttribute("STUDENT_LIST", students);
		
		// Send to JSP page (view)
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/list-students.jsp");
		requestDispatcher.forward(request, response);
		
	}
	
	private void loadStudent(HttpServletRequest request, HttpServletResponse response) throws NumberFormatException, SQLException, Exception, ServletException, IOException {
		
		// Read student id from form data
		final String id = request.getParameter("id");
		
		if (id == null) {
			throw new Exception("Missing id parameter");
		}
		
		// Get student form database (db util)
		final Student student = studentDbUtil.getStudent(id);
		
		// Place student in the request attribute
		request.setAttribute("THE_STUDENT", student);
		
		// Send to jsp page: update-student-form.jsp
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/update-student-form.jsp");
		requestDispatcher.forward(request, response);
		
	}
	
	private void deleteStudent(HttpServletRequest request, HttpServletResponse response) throws NumberFormatException, SQLException, Exception, IOException {
		
		// Read student id from form data
		final String id = request.getParameter("id");
		
		if (id == null) {
			throw new Exception("Missing id parameter");
		}
		
		// Delete student from database
		studentDbUtil.deleteStudent(id);
		
		sendRedirect(request, response);
		
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			
			// Read the "command" parameter
			final String theCommand = request.getParameter("command");
			
			if (theCommand == null) {
				throw new Exception("Missing command parameter");
			}
			
			// Route to the appropiate method
			switch (theCommand) {
			
				case "POST":
					postStudent(request, response);
					break;
			 
				case "PUT":
					putStudent(request, response);
					break;
					
				default:
					throw new Exception("Command invalid: " + theCommand);
					
			}
			
		} catch (SQLException sqlException) {
			throw new ServletException(sqlException);
		} catch (Exception exception) {
			throw new ServletException(exception);
		}
		
	}
	
	private void postStudent(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		
		// Read student info from form data
		final String firstName = request.getParameter("firstName");
		final String lastName = request.getParameter("lastName");
		final String email = request.getParameter("email");
		
		// Create a new student object
		final Student student = new Student(firstName, lastName, email);
		
		// Add the student to the database
		studentDbUtil.postStudent(student);
		
		sendRedirect(request, response);
		
	}
	
	private void putStudent(HttpServletRequest request, HttpServletResponse response) throws Exception, SQLException, IOException {
		
		// Read student info from form data
		final String id = request.getParameter("id");
		
		if (id == null) {
			throw new Exception("Missing id parameter");
		}
		
		final String firstName = request.getParameter("firstName");
		final String lastName = request.getParameter("lastName");
		final String email = request.getParameter("email");
		
		// Create a new student object
		final Student student = new Student(Integer.parseInt(id), firstName, lastName, email);
		
		// Perform update on database
		studentDbUtil.putStudent(student);
		
		sendRedirect(request, response);
		
	}

	private void sendRedirect(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		// Send back to main page (the student list)
		// SEND AS REDIRECT to avoid multiple-browser reload issue
		response.sendRedirect(request.getContextPath() + "/StudentControllerServlet");
		
	}

}
