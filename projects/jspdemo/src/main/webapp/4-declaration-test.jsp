<html>

<body>

<h3>JSP Declaration &#60;&#37;&#33; &#37;&#62;</h3>

<%!
	String makeItLower(String data) {
		return data.toLowerCase();
	}
%>

<p>Lower case "Hello World": <%= makeItLower("Hello World") %></p>

</body>

</html>