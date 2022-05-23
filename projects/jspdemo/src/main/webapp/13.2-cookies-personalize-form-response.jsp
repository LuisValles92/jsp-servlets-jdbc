<%@page import="java.net.URLEncoder"%>
<html>

<head>
	<title>Confirmation</title>
</head>

<%
	// Read form data
	final String favoriteLanguage = URLEncoder.encode(request.getParameter("favoriteLanguage"), "UTF-8");

	// Create the cookie
	final Cookie theCookie = new Cookie("myApp.favoriteLanguage", favoriteLanguage);
	
	// Set the life span ... total number of seconds
	theCookie.setMaxAge(60*60*24*365); // for one year
	
	// Send cookie to browser
	response.addCookie(theCookie);
%>

<body>
	<p>Thanks! We set your favorite language to: ${param.favoriteLanguage}</p>
	<a href="13.3-cookies-homepage.jsp">Return to homepage.</a>
</body>

</html>