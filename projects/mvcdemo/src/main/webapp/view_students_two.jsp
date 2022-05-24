<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>

<head>

	<title></title>

	<style></style>

</head>

<body>

	<h2>Student Table Demo</h2>
	<hr>
	
	<table border="1">
	
		<tr>
			<th>First Name</th>
			<th>Last Name</th>
			<th>Email</th>
		</tr>

		<c:forEach var="student" items="${student_list}">
			
			<tr>
				<td>${student.firstName}</td>
				<td>${student.lastName}</td>
				<td>${student.email}</td>
			</tr>
				
		</c:forEach>

	</table>

</body>

</html>