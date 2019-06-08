
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
			<input name="page" type="hidden" value="article"/>
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
				<div>
					<c:forEach items="${article.files}" var="FileModel">
						<div id="${element.fileExtention}">
							<c:out value="${element.fileExtention}">
						</div>
					</c:forEach>
				</div>
			</div>
			
		</div>
		
	</body>
</html>