<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<html>

<head>

	<title></title>

	<style></style>

</head>

<body>

	<c:set var="data" value="Singapore,Toyko,Mumbai,London" />
	
	<h3>Split Demo</h3>
	
	<c:set var="citiesArray" value="${fn:split(data, ',')}" />
	
	<c:forEach var="city" items="${citiesArray}">
		<p>${city}</p>
	</c:forEach>

	<h3>Join Demo</h3>
	
	<c:set var="fun" value="${fn:join(citiesArray, '*')}" />
	
	<p>Result of joining: ${fun}</p>

</body>

</html>