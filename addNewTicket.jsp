
<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="UTF-8">
		<title>Login Page</title>
		<script src="JavaScriptLibrary.js"></script>
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/CssSheet.css" />
	</head>
	<body>
		<div id=pageheader> 
			<h1> IT Services </h1> 
			<div id=navagationBar> 
				<form action="Controller" method="POST" id="tickets">
					<button type="submit"> Tickets </button> 
				</form>
				<form action="Controller" method="POST" id="knowledgeBase">
					<button type="submit"> knowledgeBase </button> 
				</form>	
				<form action="Controller" method="POST" id="Logout">
					<button type="submit"> Logout </button> 
				</form>					
			</div>
		</div>
		
		<div id=pageContent>
			<form>
			<input type="text" name="issueName" value="issueName"/><br>
			<input type="text" name="issueDescription" value="issueDescription"/><br>
			<input type="radio" name="issueCategory" value="Network"/>
			<input type="radio" name="issueCategory" value="Software"/>
			<input type="radio" name="issueCategory" value="Hardware"/>
			<input type="radio" name="issueCategory" value="Email"/>
			<input type="radio" name="issueCategory" value="Account"/><br>
			<input type="submit"/>
			</form>
		</div>
		
	</body>
</html>