
<!DOCTYPE html>
<html lang="en">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="models.FileModel" %>
<%@ page import="java.util.List" %>

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
				<div class="addTicketElement">
					<c:out value="${requestScope.article.title}" />
				</div>
				<div class="addTicketElement">
					<c:out value="${requestScope.article.user}" />
				</div>
				<div class="addTicketElement">
					<c:out value="${requestScope.article.category}" />
				</div>
				<div class="addTicketElement">
					<c:out value="${requestScope.article.body}" />
				</div>
				<div class="addTicketElement">
					<c:out value="${requestScope.article.dateCreated}" />
				</div>
					<% List<FileModel> files = (List<FileModel>)request.getAttribute("files");
						if(files!=null){for(FileModel s : files)
						{ %>
					<p>ID: <%=s.getFileID()%>, name: <%=s.getFileName()%></p>
					<form method="get" action="downloadController">
						<input type="hidden" value="<%=s.getFileID()%>" name="getid"/>
						<input name="pageFrom" type="hidden" value="article"/>
						<input name="previousID" type="hidden" value="${article.ticketID}">
						<input type="submit" value="download File">
					</form>
					<br>
					<%}
					}%>
			</div>
			
		</div>
		
	</body>
</html>