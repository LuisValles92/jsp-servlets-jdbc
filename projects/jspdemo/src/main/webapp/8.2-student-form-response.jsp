<html>

<head>

	<title>Student Confirmation Title</title>

</head>

<body>

	<p>The student is confirmed: <%= request.getParameter("firstName") %> <%= request.getParameter("lastName") %></p>
	<p>The student is confirmed: ${param.firstName} ${param.lastName}</p>

</body>
	
</html>