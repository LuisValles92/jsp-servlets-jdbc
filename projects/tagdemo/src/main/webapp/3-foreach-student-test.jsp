<%@page import="java.util.*"%>
<%@page import="com.luv2code.jsp.tagdemo.Student"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%
	// Just create some sample data ... normally provided by MVC
	List<Student> data = new ArrayList<>();
	
	data.add(new Student("John", "Doe", false));
	data.add(new Student("Maxwell", "Johnson", false));
	data.add(new Student("Mary", "Public", true));
	
	pageContext.setAttribute("myStudents", data);
%>

<html>

<head>

	<title></title>

	<style></style>

</head>

<body>

	<table border="1">

	<tr>
		<th>First Name</th>
		<th>Last Name</th>
		<th>Gold Customer</th>
	</tr>
	
	<c:forEach var="student" items="${myStudents}">
	
		<tr>
			<td>${student.firstName}</td>
			<td>${student.lastName}</td>
			<td>${student.goldCustomer}</td>
		</tr>
		
	</c:forEach>

	</table>

</body>

</html>