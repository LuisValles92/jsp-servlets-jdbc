<%@page import="java.util.Date"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>

<head>

	<title></title>

	<style></style>

</head>

<body>

	<c:set var="stuff" value="<%= new Date() %>"></c:set>
	<p>Time on the server is ${stuff}</p>

</body>

</html>