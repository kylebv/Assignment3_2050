
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
			<input name="page" type="hidden" value="knowledgeBase"/>
			<input name="navigationBar" type="hidden" value=""/>
		</form>
		
		<form name="categoryBar" action="Controller" method="POST" id="categoryBar">
			<input name="page" type="hidden" value="knowledgeBase"/>
			<input name="categoryBar" type="hidden" value=""/>
		</form>
		
		<form name="viewArticle" action="Controller" method="POST" id="viewArticle">
			<input name="page" type="hidden" value="knowledgeBase"/>
			<input name="viewArticle" type="hidden" value=""/>
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
			<div id=sortingBar> 
				<h2> Select Type: </h2>
				<div class=Buttons> 
					<button onclick="clickHandlerCatBar('network')"> Network </button>
					<button onclick="clickHandlerCatBar('software')"> Software </button> 
					<button onclick="clickHandlerCatBar('hardware')"> Hardware </button> 				
					<button onclick="clickHandlerCatBar('email')"> Email </button> 
					<button onclick="clickHandlerCatBar('account')"> Account </button> 				
				</div>
			</div>
			<div id=articleList>
				    <c:forEach items="${requestScope.articles}" var="KnowledgeBaseArticleModel">
						<div id="${element.ticketID}">
							<c:out value="${element.title}">
							<button onclick="clickHandlerViewArticle(${element.ticketID})"class=viewButton> View Article </button> 
						</div>
					</c:forEach>
			</div>
			
		</div>
		
	</body>
</html>