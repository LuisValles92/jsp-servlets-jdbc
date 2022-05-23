<%@page import="java.util.*"%>
<html>

<body>
	
	<!-- Step 1: Create HTML form -->
	<form action="12-session-todo-demo.jsp">
		<label for="theItem">Add new item:</label>
		<input type="text" id="theItem" name="theItem" />
		<input type="submit" value="Submit" />
	</form>	
	
	<!-- Step 2: Add new item to "To Do" list -->
	<%
		// Get the TO DO items from the session
		List<String> items = (List<String>) session.getAttribute("myToDoList");
		
		// If the TO DO items doesn't exist, the create a new one
		if (items == null) {
			items = new ArrayList<String>();
			session.setAttribute("myToDoList", items);
		}
		
		// See if there is form data to add
		final String theItem = request.getParameter("theItem");
		final boolean isItemNotEmpty = theItem != null && theItem.trim().length() > 0;
		final boolean isItemNotDuplicate = theItem != null && !items.contains(theItem.trim());
		
		if (isItemNotEmpty && isItemNotDuplicate) {
			out.println("<p>Item entered: " + theItem + "</p>");
			items.add(theItem);
		}
	%>
	
	<!-- Step 3: Display all "To Do" item from session -->
	<hr>
	<b>To List Items:</b>
	<ol>
	<%
		for (String item: items) {
			out.println("<li>" + item + "</li>");
		}
	%>
	</ol>

</body>

</html>