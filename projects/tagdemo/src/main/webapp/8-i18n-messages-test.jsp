<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<c:set var="theLocale" 
value="${not empty param.theLocale ? param.theLocale : pageContext.request.locale}" 
scope="session" />

<fmt:setLocale value="${theLocale}" />

<fmt:setBundle basename="com.luv2code.jsp.tagdemo.i18n.resources.mylabels" />

<html>

<head>

	<title></title>

	<style></style>

</head>

<body>

	<a href="8-i18n-messages-test.jsp?theLocale=es_ES">Spanish (ES)</a>
	|
	<a href="8-i18n-messages-test.jsp?theLocale=en_US">English (US)</a>
	|
	<a href="8-i18n-messages-test.jsp?theLocale=de_DE">German (DE)</a>

	<hr>

	<p><fmt:message key="label.greeting"/></p>
	<p><fmt:message key="label.firstName"/> <i>John</i></p>
	<p><fmt:message key="label.lastName"/> <i>Doe</i></p>
	<p><fmt:message key="label.welcome"/></p>
	
	<hr>
	
	<p>Selected locale: ${theLocale}</p>

</body>

</html>