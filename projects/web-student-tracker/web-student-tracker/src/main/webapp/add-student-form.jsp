<!DOCTYPE html>
<html>

<head>

	<title>Add Student</title>

	<link type="text/css" rel="stylesheet" href="css/style.css">
	<link type="text/css" rel="stylesheet" href="css/add-update-student-style.css">
	
	<script type="text/javascript" src="js/student-validation.js"></script>

</head>

<body>

	<div id="wrapper">
		<div id="header">
			<h2>FooBar University</h2>
		</div>
	</div>
	
	<div id="container">
		<h3>Add Student</h3>
		
		<form action="StudentControllerServlet" method="POST"
			name="studentForm" onSubmit="return validateForm()">
		
			<input type="hidden" name="command" value="POST" />
			
			<table>
				<tbody>
					<tr>
						<td><label for="firstName">First name:</label></td>
						<td><input type="text" id="firstName" name="firstName"/></td>
					</tr>
					<tr>
						<td><label for="lastName">Last name:</label></td>
						<td><input type="text" id="lastName" name="lastName"/></td>
					</tr>
					<tr>
						<td><label for="email">Email:</label></td>
						<td><input type="text" id="email" name="email"/></td>
					</tr>
					<tr>
						<td><label></label></td>
						<td><input type="submit" value="Save" class="save" /></td>
					</tr>
				</tbody>
			</table>
			
		</form>
		
		<div class="clear-both"></div>
		<p>
			<a href="StudentControllerServlet">Back to List</a>
		</p>
		
	</div>

</body>

</html>