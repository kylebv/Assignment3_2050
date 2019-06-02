
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@page import="models.User" %>
<jsp:useBean id="game" class="dealOrNoDeal.game" scope="session" />

<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="UTF-8">
		<title>Login Page</title>
		<script src="JavaScriptLibrary.js"></script>
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/CssSheet.css" />
	</head>
	<body>
		<div class=pageheader> <h1> IT Services </h1> <div>
		
		<div id=pageContent>
			<div id=loginForm>
				<form action="Controller" method="POST">	
					<div> User Name: 	<input type="text" name="userName" size="5" /> </div>
					<div> Password: 	<input type="text" name="password" size="5" /> </div>
					<div> <input type="submit" value="Login" />
				</form>
			</div>
		
			<c:if test = "${param.isLogin != null}">
				<div id=errorMessage>
					<p> Error when loging on, please try again. </p>
				</div>
			</c:if>
		</div>
		
	</body>
</html>