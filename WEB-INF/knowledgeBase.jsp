
<!DOCTYPE html>
<html lang="en">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

	<head>
		<meta charset="UTF-8">
		<title>Knowledge base</title>
		<script src="JavaScriptLibrary.js"></script>
	<link href="styles/stylesheet.css" type="text/css" rel="stylesheet"  />
	</head>
	<body>
		<form name="navigationBarF" action="Controller" method="POST" id="navFormId">
			<input name="page" type="hidden" value="knowledgeBase"/>
			<input name="navigationBar" type="hidden" value="" id="navigationBarE"/>
		</form>
		
		
		<form name="categoryBarF" action="Controller" method="POST" id="catFormId">
			<input name="page" type="hidden" value="knowledgeBase"/>
			<input name="categoryBar" type="hidden" value=""  id="categoryBarE"/>
		</form>
		
		<form name="viewArticleF" action="Controller" method="POST" id="viewArticleFormId">
			<input name="page" type="hidden" value="knowledgeBase"/>
			<input name="viewArticle" type="hidden" value="" id="viewArticleE"/>
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
					<button class="radioButton" onclick="clickHandlerCatBar('Network')"> Network </button>
					<button class="radioButton" onclick="clickHandlerCatBar('Software')"> Software </button> 
					<button class="radioButton" onclick="clickHandlerCatBar('Hardware')"> Hardware </button> 				
					<button class="radioButton" onclick="clickHandlerCatBar('Email')"> Email </button> 
					<button class="radioButton" onclick="clickHandlerCatBar('Account')"> Account </button> 				
				</div>
			</div>
			<div id=articleList class="radios">
				    <c:forEach items="${requestScope.articles}" var="KnowledgeBaseArticleModel">
						<div id="${KnowledgeBaseArticleModel.articleID}">
							<c:out value="${KnowledgeBaseArticleModel.title}" />
							<button onclick="clickHandlerViewArticle(${KnowledgeBaseArticleModel.articleID})"class="browseButton"> View Article </button> 
						</div>
					</c:forEach>
			</div>
			
		</div>
		
	</body>
</html>