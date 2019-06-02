
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
					<c:out value="${ticket.title}">
				</div>
				<div>
					<c:out value="${ticket.user}">
				</div>
				<div>
					<c:out value="${ticket.category}">
				</div>
				<div>
					<c:out value="${ticket.body}">
				</div>
				<div>
					<c:out value="${ticket.dateCreated}">
				</div>
				<div>
					<c:forEach items="${ticket.comment}" var="CommentModel">
						<div id="${element.userID}">
							<div class=body>
								<c:out value="${element.body}">
							</div>
							<div class=user>
								<c:out value="${element.user}">
							</div>
						</div>
					</c:forEach>
				</div>
				<div>
					<c:forEach items="${ticket.files}" var="FileModel">
						<div id="${element.fileExtention}">
							<c:out value="${element.fileExtention}">
						</div>
					</c:forEach>
				</div>
				<div>
					<c:choose>
						<c:when test="${User == \"ADMIN\"}">
							<span class=logoutButton> Make Into Article </span> 
						</c:when>
						<c:otherwise>
							<span class=logoutButton> Update Status </span> 
						</c:otherwise>
					</c:choose>
				</div>
			</div>
			
		</div>
		
	</body>
</html>