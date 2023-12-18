<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/5.0.0/normalize.min.css">
<link rel='stylesheet' href='https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.2.0/css/font-awesome.min.css'>
<link rel="stylesheet" href="./style2.css">
<title>Insert title here</title>
</head>
<body>

<div class="user-modal">
		<div class="user-modal-container">
			
<form class="form" action="VerifyUser2.html" method="post">

					<p class="fieldset">
						<label class="image-replace username" for="signin-email">Admin Login</label>
						<input class="full-width has-padding has-border" id="signin-emailAdmin" type="text" placeholder="Admin Login" name="adminlogin">
						<span class="error-message">An account with this email address does not exist!</span>
					</p>
					
					
					<p class="fieldset">
						<label class="image-replace username" for="signin-email">Login</label>
						<input class="full-width has-padding has-border" id="signin-email" type="text" placeholder="Login" name="login">
						<span class="error-message">An account with this email address does not exist!</span>
					</p>

					<p class="fieldset">
						<label class="image-replace password" for="signin-password">Password</label>
						<input class="full-width has-padding has-border" id="signin-password" type="password"  placeholder="Password" name="password">
						<a href="#0" class="hide-password">Show</a>
						<span class="error-message">Wrong password! Try again.</span>
					</p>

					

					<p class="fieldset">
						<input class="full-width" type="submit" value="Login">
					</p>
				</form>
		
</div>
</div>
<script src='https://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>

<script  src="./script.js"></script>

</body>
</html>