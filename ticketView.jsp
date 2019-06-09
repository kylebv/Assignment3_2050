
<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="UTF-8">
		<title>Login Page</title>
		<script src="JavaScriptLibrary.js"></script>
		<link href="styles/stylesheet.css" type="text/css" rel="stylesheet"  />
	</head>
	<body>
	
		<form name="navigationBar" action="Controller" method="POST" id="navigationBar">
			<input name="page" type="hidden" value="ticketView"/>
			<input name="navigationBar" type="hidden" value=""/>
		</form>
		
		
		<div class="pageheader"> 
			<img src="images/logoSml.png" id="logoSml" alt="placeholder" >
			<div class="navagationBar"> 
					<button class="button" onclick="clickHandlerNavBar('tickets')"> Tickets </button>
					<button class="button" onclick="clickHandlerNavBar('knowledgeBase')"> KnowledgeBase </button> 
					<button class="button" onclick="clickHandlerNavBar('logout')"> Logout </button> 				
			</div>
		</div>
		
		<div id=pageContent>
			<div id=articleList>
				<div>
					<c:out value="${requestScope.ticket.title}">
				</div>
				<div>
					<c:out value="${requestScope.ticket.user}">
				</div>
				<div>
					<c:out value="${requestScope.ticket.category}">
				</div>
				<div>
					<c:out value="${requestScope.ticket.body}">
				</div>
				<div>
					<c:out value="${requestScope.ticket.dateCreated}">
				</div>
				<div>
					<c:forEach items="${requestScope.ticket.comment}" var="CommentModel">
						<div>
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
					<c:forEach items="${requestScope.ticket.files}" var="FileModel">
						<div id="${element.fileExtention}">
							<c:out value="${element.fileExtention}">
						</div>
					</c:forEach>
				</div>
				<div>
					<c:if test="${requestSession.roleID == 'ADMIN' && requestScope.ticket.Status=='resolved'}">
						<form name="toArticle" action="Controller" method="POST" id="toArticle">
							<input name="page" type="hidden" value="ticketView"/>
							<input name="toArticle" type="hidden" value="toArticle"/>
							<input  type="submit" value="Submit to Article"/>
						</form>
					</c:if>
					
					<c:choose>
						
						<c:otherwise>
							<span class=logoutButton> Update Status </span> 
						</c:otherwise>
					</c:choose>
				</div>
			</div>
			
		</div>
		
	</body>
</html>