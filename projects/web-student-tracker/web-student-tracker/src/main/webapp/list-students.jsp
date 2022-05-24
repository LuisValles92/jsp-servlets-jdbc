<%@page import="java.util.List, com.luv2code.web.jdbc.Student"%>
<!DOCTYPE html>
<html>

<head>

	<title>Student Tracker App</title>

	<link type="text/css" rel="stylesheet" href="css/style.css">

</head>

<%
	// Get the students from the request object (sent by servlet)
	List<Student> students = (List<Student>) request.getAttribute("STUDENT_LIST");
%>

<body>

	<div id="wrapper">
		<div id="header">
			<h2>FooBar University</h2>
		</div>
	</div>
	
	<div id="container">
		<div id="content">
			<table>
				<tr>
					<th>First Name</th>
					<th>Last Name</th>
					<th>Email</th>
				</tr>
				<% for(Student student : students) { %>
				
					<tr>
						<td><%= student.getFirstName() %></td>
						<td><%= student.getLastName() %></td>
						<td><%= student.getEmail() %></td>
					</tr>
					
				<% } %>
			</table>
		</div>
	</div>

</body>

</html>