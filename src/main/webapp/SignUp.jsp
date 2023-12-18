<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/5.0.0/normalize.min.css">
<link rel='stylesheet' href='https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.2.0/css/font-awesome.min.css'><link rel="stylesheet" href="./style.css"><title>Insert title here</title>
</head>
<body>

  <div class="user-modal">
		<div class="user-modal-container">

		<!-- <div id="signup">-->


				<form class="form" action="addUser.html" method="post">
					<p class="fieldset">
						<label class="image-replace username" for="signup-username">Login</label>
						<input class="full-width has-padding has-border" id="signup-username" type="text" placeholder="Username" name="login">
						<span class="error-message">Your username can only contain numeric and alphabetic symbols!</span>
					</p>

				

					<p class="fieldset">
						<label class="image-replace password" for="signup-password">Password</label>
						<input class="full-width has-padding has-border" id="signup-password" type="password"  placeholder="Password" name="password">
						<a href="#0" class="hide-password">Show</a>
						<span class="error-message">Your password has to be at least 6 characters long!</span>
					</p>

					

					<p class="fieldset">
						<input class="full-width has-padding" type="submit" value="Create account">
					</p>
				</form>

	
				<!-- <a href="#0" class="cd-close-form">Close</a> -->
			</div>
			<!--</div>-->
</div>	
		
		  <script src='https://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script><script  src="./script.js"></script>
		




</body>
</html>