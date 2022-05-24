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
			
			// List the students ... in MVC fashion
			listStudents(request, response);
			
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

}
