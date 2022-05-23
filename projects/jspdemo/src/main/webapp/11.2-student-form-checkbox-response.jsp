<html>

<head>

	<title>Student Confirmation Title</title>

</head>

<body>

	<p>The student is confirmed: <%= request.getParameter("firstName") %> <%= request.getParameter("lastName") %></p>
	<p>The student is confirmed: ${param.firstName} ${param.lastName}</p>
	<!--
	This only prints the first element
	<p>The student's favorite programming language: ${param.favoriteLanguage}</p> 
	-->
	<p>Favorite Programming Languages:</p>
	<ul>
		<%
			String[] favoriteLanguages = request.getParameterValues("favoriteLanguage");
		
			if (favoriteLanguages != null) {
				for (String favoriteLanguage: favoriteLanguages) {
					out.println("<li>"+ favoriteLanguage +"</li>");
				}
			}
		%>
	</ul>
	
</body>
	
</html>