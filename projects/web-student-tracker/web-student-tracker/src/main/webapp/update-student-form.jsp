<!DOCTYPE html>
<html>

<head>

	<title>Update Student</title>

	<link type="text/css" rel="stylesheet" href="css/style.css">
	<link type="text/css" rel="stylesheet" href="css/add-update-student-style.css">

</head>

<body>

	<div id="wrapper">
		<div id="header">
			<h2>FooBar University</h2>
		</div>
	</div>
	
	<div id="container">
		<h3>Update Student</h3>
		
		<form action="StudentControllerServlet" method="POST">
		
			<input type="hidden" name="command" value="PUT" />
			
			<input type="hidden" name="id" value="${THE_STUDENT.id}" />
			
			<table>
				<tbody>
					<tr>
						<td><label for="firstName">First name:</label></td>
						<td><input type="text" id="firstName" name="firstName"
								value="${THE_STUDENT.firstName}" /></td>
					</tr>
					<tr>
						<td><label for="lastName">Last name:</label></td>
						<td><input type="text" id="lastName" name="lastName"
								value="${THE_STUDENT.lastName}" /></td>
					</tr>
					<tr>
						<td><label for="email">Email:</label></td>
						<td><input type="text" id="email" name="email"
								value="${THE_STUDENT.email}" /></td>
					</tr>
					<tr>
						<td><label></label></td>
						<td><input type="submit" value="Save" class="save" /></td>
					</tr>
				</tbody>
			</table>
			
		</form>
		
		<div style="clear: both;"></div>
		<p>
			<a href="StudentControllerServlet">Back to List</a>
		</p>
		
	</div>

</body>

</html>