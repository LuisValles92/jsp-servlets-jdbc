<%@page import="java.util.Date"%>
<html>

<body>

	<h3>Hello World of Java!</h3>

	<p>
		The time on the server is
		<%= new Date() %></p>

	<p>
		Converting a string to uppercase:
		<%= new String("Hello World").toUpperCase() %></p>
	<p>
		25 multiplied by 4 equals:
		<%= 25*4 %></p>
	<p>
		Is 75 less than 69?
		<%= 75 < 69 %></p>

</body>

</html>