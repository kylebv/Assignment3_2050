


<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="UTF-8">
		<title>Login Page</title>
		<script src="JavaScriptLibrary.js"></script>
		<link href="styles/stylesheet.css" type="text/css" rel="stylesheet"  />
	</head>
	<body>
		<div class=pageheader> <h1> IT Services </h1> <div>
		
		<div id=pageContent>
			<div id=loginForm>
				<img src="images/logo.png" id="logo" alt="placeholder" ><hr>
				<p>Sign In</p>
				<form action="Controller" method="POST">	
					<div> 	<input type="text" id= "formEntry" name="userName" size="5" /> </div>
					<div>  	<input type="text" id= "formEntry" name="password" size="5" /> </div>
					<div> <input id= "submitButton" type="submit" value="Sign In" /> </div>
				</form>
			</div>
			
			<div id=errorMessage>
				<p> Error when Login </p>
			</div>
			
		</div>
		
	</body>
</html>