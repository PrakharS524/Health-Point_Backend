<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="css/form.css" rel="stylesheet" type="text/css">
</head>
<body>
	
	<header>
			<jsp:include page="../navigation_bar.jsp" />
	</header>
	<main id=main>
		<form action="adminAdd" method="post" onsubmit="return validateForm()">
	 		<p class="heading">Admin Details Form</p>
			<!-- Admin Name -->
			<div class="form-group">
			  <label for="name">Admin Name:</label><br>
			  <input type="text"id="name" name="name" pattern="[A-Za-z\s]{3,40}" 
			         title="Name must contain only letters and be between 3 and 40 characters"
			         minlength="3" maxlength="40" required><br><br>
			</div>
			
			<div class="form-group">	 
				<!-- Admin Email -->
				<label for="email">Admin Email:</label><br>
				<input type="email" id="email" name="email" minlength="6" maxlength="35"
				       title="Email must be between 6 and 35 characters" required><br><br>
			</div>
			
			
			<div class="form-group">
				<!-- Admin Password -->
				<label for="password">Admin Password:</label><br>
				<input type="password" id="password" name="password"  pattern=".{6,35}"
				       title="Password must be between 6 and 35 characters"
				       minlength="6" maxlength="35" required><br><br>
			</div>
			<div class="form-group">
				<label for="position">Admin position:</label><br>
				<select id="position" name="position" required>
				  <option value="">-- Select Position --</option>
				  <option value="1">1</option>
				  <option value="2">2</option>
				  <option value="3">3</option>
				  <option value="4">4</option>
				  <option value="5">5</option>
				</select><br><br>
			</div>
			<input type="submit" value="Submit">
	
		</form>
	</main>

</body>
</html>