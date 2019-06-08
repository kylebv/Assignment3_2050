
<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="UTF-8">
		<title>Add new ticket page</title>
		<script src="JavaScriptLibrary.js"></script>
		<link href="styles/stylesheet.css" type="text/css" rel="stylesheet"  />
	</head>
	<body>
		<form name="navigationBar" action="Controller" method="POST" id="navigationBar">
			<input name="page" type="hidden" value="addNewTicket"/>
			<input name="navigationBar" type="hidden" value=""/>
		</form>
		
		<div id=pageheader> 
			<h1> IT Services </h1> 
			<div id=navagationBar> 
				<button onclick="clickHandlerNavBar('tickets')"> Tickets </button>
				<button onclick="clickHandlerNavBar('knowledgeBase')"> KnowledgeBase </button> 
				<button onclick="clickHandlerNavBar('logout')"> Logout </button> 				
			</div>
		</div>
		
		<div id=pageContent>
			<form>
			<br>Issue Name:<input type="text" name="issueName"/>
			<br>Issue Description:<input type="text" name="issueDescription"/>
			<div>Category:
			<br>Network:<input type="radio" name="issueCategory" value="Network"/>
			Software: <input type="radio" name="issueCategory" value="Software"/>
			HardWare: <input type="radio" name="issueCategory" value="Hardware"/>
			Email: <input type="radio" name="issueCategory" value="Email"/>
			Account: <input type="radio" name="issueCategory" value="Account"/></div>
			<button name ="file upload" value ="file upload"/><br>
			<input name="page" type="hidden" value="addNewTicket"/>
			<input type="submit"/>
			</form>
			<c:if test = "${requestScope.error != null}">
				<div id=errorMessage>
					<p> Error adding ticket, please try again. </p>
				</div>
			</c:if>
		</div>
		
	</body>
</html>