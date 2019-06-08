


<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="UTF-8">
		<title>Login Page</title>
		<script src="JavaScriptLibrary.js"></script>
		<link href="styles/stylesheet.css" type="text/css" rel="stylesheet"  />
	</head>
	<body>
		<div class="pageheader"> <img src="images/logoSml.png" id="logoSml" alt="placeholder" > <div>
		
		
			<div id="loginForm">
				<img src="images/logo.png" id="logo" alt="placeholder" ><hr>
				<p>Sign In</p>
				<form action="Controller" method="POST">	
					<div> 	<input type="text" id= "formEntry" name="userName" size="5" /> </div>
					<div>  	<input type="text" id= "formEntry" name="password" size="5" /> </div>
					<input name="page" type="hidden" value="login"/>
					<div> <input class= "submitButton" type="submit" value="Sign In" /> </div>
				</form>
			</div>
		
			<c:if test = "${requestScope.isLoginError != null}">
				<div id="errorMessage">
					<p> Error loging on, please try again. </p>
				</div>
			</c:if>

		
		
	</body>
</html>
