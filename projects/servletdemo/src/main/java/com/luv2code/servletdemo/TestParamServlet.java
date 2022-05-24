package com.luv2code.servletdemo;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class TestParamServlet
 */
@WebServlet("/TestParamServlet")
public class TestParamServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TestParamServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// Step 1: set content type
		response.setContentType("text/html");
				
		// Step 2: get printwriter
		PrintWriter printWriter = response.getWriter();
		
		// Step 3: read configuration params
		ServletContext servletContext = getServletContext(); // inherit from HttpServlet
		String maxCartSize = servletContext.getInitParameter("max-shopping-cart-size");
		String teamName = servletContext.getInitParameter("project-team-name");
		
		// Step 4: read configuration servlet params
		String theGreetingMessage = getInitParameter("greeting");
		String theServiceLevel = getInitParameter("serviceLevel");
		
		// Step 5: generate HTML content
		printWriter.println("<html><body>");
		printWriter.println("<p>Max cart: " + maxCartSize + "</p>");
		printWriter.println("<p>Team name: " + teamName + "</p>");
		printWriter.println("<p>Greeting: " + theGreetingMessage + "</p>");
		printWriter.println("<p>Service Level: " + theServiceLevel + "</p>");
		printWriter.println("</body></html>");
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
