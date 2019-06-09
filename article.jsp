
<!DOCTYPE html>
<html lang="en">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

	<head>
		<meta charset="UTF-8">
		<title>Article</title>
		<script src="JavaScriptLibrary.js"></script>
		<link href="styles/stylesheet.css" type="text/css" rel="stylesheet"  />
	</head>
	<body>
		<form name="navigationBarF" action="Controller" method="POST" id="navFormId">
			<input name="page" type="hidden" value="ticketHome"/>
			<input name="navigationBar" type="hidden" value="" id="navigationBarE"/>
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
			<div id=articleList>
				<div>
					<c:out value="${article.title}" />
				</div>
				<div>
					<c:out value="${article.user}" />
				</div>
				<div>
					<c:out value="${article.category}" />
				</div>
				<div>
					<c:out value="${article.body}" />
				</div>
				<div>
					<c:out value="${article.dateCreated}" />
				</div>
				<div>
					<c:forEach items="${article.files}" var="FileModel">
						<div id="${FileModel.fileExtention}">
							<c:out value="${FileModel.fileExtention}" />
						</div>
					</c:forEach>
				</div>
			</div>
			
		</div>
		
	</body>
</html>