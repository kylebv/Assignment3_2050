
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
				<div class=Buttons>
					<span class=ticketButton> Tickets </span> 
					<span class=KBButton> Knowledge Base </span> 
					<span class=logoutButton> Logout </span> 
				</div>
			</div>
		</div>
		
		<div id=pageContent>
			<c:if test="${User.roleID == ADMIN}">
				<div id=sortingBar> 
					<h2> Select Type: </h2>
					<div class=Buttons> 
						<span class=networkButton> Network </span> 
						<span class=softwareButton> Software </span> 
						<span class=hardwareButton> Hardware </span> 
						<span class=emailButton> Email </span> 
						<span class=accountButton> Account </span> 
						<span class=defaultButton> Default </span> 
					</div>
				</div>
			</c:if>
			<div id=articleList>
				    <c:forEach items="${tickets}" var="TicketModel">
						<div id="${element.ticketID}">
							<c:out value="${element.title}">
							<span class=viewButton> View Ticket </span> 
						</div>
					</c:forEach>
			</div>
			
		</div>
		
	</body>
</html>
