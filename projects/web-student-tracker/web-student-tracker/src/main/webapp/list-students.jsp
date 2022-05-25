<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>

<head>

	<title>Student Tracker App</title>

	<link type="text/css" rel="stylesheet" href="css/style.css">

</head>

<body>

	<div id="wrapper">
		<div id="header">
			<h2>FooBar University</h2>
		</div>
	</div>
	
	<div id="container">
		<div id="content">
		
			<!-- Put the new button: Add Student -->
			<input type="button" value="Add Student" 
				onclick="window.location.href='add-student-form.jsp'; return false;"
				class="add-student-button" />
		
			<table>
				<tr>
					<th>First Name</th>
					<th>Last Name</th>
					<th>Email</th>
					<th>Actions</th>
				</tr>
				<c:forEach var="student" items="${STUDENT_LIST}">
				
					<!-- Set up a link to update a student -->
					<c:url var="updateLink" value="StudentControllerServlet">
						<c:param name="command" value="LOAD" />
						<c:param name="id" value="${student.id}" />
					</c:url>
					
					<!-- Set up a link to delete a student -->
					<c:url var="deleteLink" value="StudentControllerServlet">
						<c:param name="command" value="DELETE" />
						<c:param name="id" value="${student.id}" />
					</c:url>
				
					<tr>
						<td>${student.firstName}</td>
						<td>${student.lastName}</td>
						<td>${student.email}</td>
						<td>
							<!-- http://localhost:8080/web-student-tracker/StudentControllerServlet;jsessionid=3F11CC444D7B4EF30C26D3B772A7A7AF?command=LOAD&id=1 -->
							<a href="${updateLink}">Update</a>
							|
							<!-- http://localhost:8080/web-student-tracker/StudentControllerServlet;jsessionid=0E5CE090CCD7A71A068CCC586C7DCA59?command=DELETE&id=5 -->
							<a href="${deleteLink}"
							onclick="if (!(confirm('Are you sure you want to delete this student?'))) return false;">Delete</a>
						</td>
					</tr>

				</c:forEach>
			</table>
		</div>
	</div>

</body>

</html>