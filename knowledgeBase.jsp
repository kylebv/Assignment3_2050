
<!DOCTYPE html>
<html lang="en">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

	<head>
		<meta charset="UTF-8">
		<title>Login Page</title>
		<script src="JavaScriptLibrary.js"></script>
	<link href="styles/stylesheet.css" type="text/css" rel="stylesheet"  />
	</head>
	<body>
		<form name="navigationBar" action="Controller" method="POST">
			<input name="page" type="hidden" value="ticketHome"/>
			<input name="navigationBar" type="hidden" value="" id="navigationBar"/>
		</form>
		
		
		<form name="categoryBar" action="Controller" method="POST" >
			<input name="page" type="hidden" value="knowledgeBase"/>
			<input name="categoryBar" type="hidden" value="" id="categoryBar"/>
		</form>
		
		<form name="viewArticle" action="Controller" method="POST" >
			<input name="page" type="hidden" value="knowledgeBase"/>
			<input name="viewArticle" type="hidden" value="" id="viewArticle"/>
		</form>
	
		<div class="pageheader"> 
			<img src="images/logoSml.png" id="logoSml" alt="placeholder" >
			<div class="navagationBar"> 
					<button class="button" onclick="clickHandlerNavBar('tickets')"> Tickets </button>
					<button class="button" onclick="clickHandlerNavBar('knowledgeBase')"> KnowledgeBase </button> 
					<button class="button" onclick="clickHandlerNavBar('logout')"> Logout </button> 				
			</div>
		</div>
		
		
		<div class="pageContent">
			<div id=sortingBar> 
				<div class="addTicketElement">
					<h1> Select Type: </h1>
				</div><hr>
				<div class="radios"> 
					<button class="radioButton" onclick="clickHandlerCatBar('network')"> Network </button>
					<button class="radioButton" onclick="clickHandlerCatBar('software')"> Software </button> 
					<button class="radioButton" onclick="clickHandlerCatBar('hardware')"> Hardware </button> 				
					<button class="radioButton" onclick="clickHandlerCatBar('email')"> Email </button> 
					<button class="radioButton" onclick="clickHandlerCatBar('account')"> Account </button> 				
				</div>
			</div>
			<div id=articleList class="radios">
				    <c:forEach items="${requestScope.articles}" var="KnowledgeBaseArticleModel">
						<div id="${KnowledgeBaseArticleModel.ticketID}">
							<c:out value="${KnowledgeBaseArticleModel.title}">
							<button onclick="clickHandlerViewArticle(${KnowledgeBaseArticleModel.ticketID})"class="browseButton"> View Article </button> 
						</div>
					</c:forEach>
			</div>
			
		</div>
		
	</body>
</html>