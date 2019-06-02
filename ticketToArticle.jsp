
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
			<div id=articleList>
				<div>
					<c:out value="${article.title}">
				</div>
				<div>
					<c:out value="${article.user}">
				</div>
				<div>
					<c:out value="${article.category}">
				</div>
				<div>
					<c:out value="${article.body}">
				</div>
				<div>
					<c:out value="${article.dateCreated}">
				</div>
				<div id=solutionForm>
				<form action="Controller" method="POST">	
					<div> Solution: <br/>
					<input type="text" name="solution" size="40" /> </div>
				</form>
			</div>
				<div>
					<c:forEach items="${article.files}" var="FileModel">
						<div id="${element.fileExtention}">
							<c:out value="${element.fileExtention}">
						</div>
					</c:forEach>
				</div>
				<div>
					<span class=ToArticle> Make Into Article </span> <!--- submit form -->
				</div>
			</div>
			
		</div>
		
	</body>
</html>