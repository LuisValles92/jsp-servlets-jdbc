<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<html>

<head>

	<title></title>

	<style></style>

</head>

<body>

	<c:set var="data" value="luv2code" />
	
	<p>Length of the string <b>${data}</b>: ${fn:length(data)}</p>
	
	<p>Uppercase version of the string <b>${data}</b>: ${fn:toUpperCase(data)}</p>
	
	<p>Does the string <b>${data}</b> start with <b>luv</b>?: ${fn:startsWith(data, "luv")}</p>

</body>

</html>