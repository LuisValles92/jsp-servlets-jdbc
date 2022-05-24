<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%
	// Just create some sample data ... normally provided by MVC
	String[] cities= {"Mumbai", "Singapore", "Philadelphia"};

	pageContext.setAttribute("myCities", cities);
%>

<html>

<head>

	<title></title>

	<style></style>

</head>

<body>
	
	<c:forEach var="city" items="${myCities}">
	
		<p>${city}</p>
		
	</c:forEach>
	
</body>

</html>