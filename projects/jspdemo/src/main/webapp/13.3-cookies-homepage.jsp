<%@page import="java.net.URLDecoder"%>
<html>

<body>

	<h3>Training Portal</h3>
	
	<!-- Read the favorite programming language cookie -->
	<%
		// The default ... if there are no cookies
		String favoriteLanguage = "Java";
	
		// Get the cookies from the browser request
		Cookie[] theCookies = request.getCookies();
		
		// Find our favorite language cookie
		if (theCookies != null) {
			for (Cookie cookie: theCookies) {
				if ("myApp.favoriteLanguage".equals(cookie.getName())) {
					// Decode cookie data ... handle case of languages with spaces in them
					favoriteLanguage = URLDecoder.decode(cookie.getValue(), "UTF-8");
					break;
				}
			}
		}
	%>
	
	<!--  Now show a personalized page ... use the favoriteLanguage variable -->
	
	<!--  Show new books for this language -->
	<h4>New Books for <%= favoriteLanguage %></h4>
	<ul>
		<li>blah blah blah</li>
		<li>blah blah blah</li>
	</ul>
	
	<h4>Latest News Reports for <%= favoriteLanguage %></h4>
	<ul>
		<li>blah blah blah</li>
		<li>blah blah blah</li>
	</ul>
	
	<h4>Hot Jobs for <%= favoriteLanguage %></h4>
	<ul>
		<li>blah blah blah</li>
		<li>blah blah blah</li>
	</ul>
	
	<hr>
	<a href="13.1-cookies-personalize-form.html">Personalize this page</a>
	
</body>

</html>