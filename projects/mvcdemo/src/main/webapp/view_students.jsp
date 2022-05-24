<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>

<head>

	<title></title>

	<style></style>

</head>

<body>

	<c:forEach var="student" items="${student_list}">
		<p>${student}</p>
	</c:forEach>

</body>

</html>