<!DOCTYPE html>
<html lang="en">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

	<head>
		<meta charset="UTF-8">
		<title>Ticket View</title>
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
					<c:out value="${requestScope.ticket.title}" />
				</div>
				<div>
					<c:out value="${requestScope.ticket.user}" />
				</div>
				<div>
					<c:out value="${requestScope.ticket.category}" />
				</div>
				<div>
					<c:out value="${requestScope.ticket.body}" />
				</div>
				<div>
					<c:out value="${requestScope.ticket.dateCreated}" />
				</div>
				<div>
					<c:forEach items="${requestScope.Comments}" var="CommentModel" >
						<div>
							<div class=body>
								<c:out value="${CommentModel.body}" />
							</div>
							<div class=user>
								<c:out value="${CommentModel.user}" />
							</div>
						</div>
					</c:forEach>
				</div>
				<div>
					<c:forEach items="${requestScope.ticket.files}" var="FileModel">
						<div id="${FileModel.fileExtention}">
							<c:out value="${FileModel.fileExtention}" />
						</div>
					</c:forEach>
				</div>
				<div>
					
						
					<c:choose>
						
						<c:when test="${sessionScope.roleID =='ADMIN'}">
							<form name="updateTicket" action="Controller" method="POST" id="updateTicket">
								<div class="addTicketElement">	
									<p class="firstInputs">Comment:</p><textarea  id="issueCommentTitle" name="issueCommentTitle"></textarea>
									<div class="radios">
										<label class="submitButton"> In Progress <input type="radio" name="issueStatus"  value="inProgress"/></label>
										<label class="submitButton"> Completed <input type="radio" name="issueStatus" value="completed"/></label>
									</div>
									<input name="page" type="hidden" value="ticketView"/>
									<input  type="submit" value="Update Ticket"/>
								</div>
							</form>
						</c:when>
						
						<c:when test="${sessionScope.roleID =='USER' && requestScope.ticket.Status=='Completed'}"> 
							<form name="updateTicket" action="Controller" method="POST" id="updateTicket">
								<div class="addTicketElement">	
									<p class="firstInputs">Comment:</p><textarea  id="issueCommentBody" name="issueCommentBody"></textarea>
									<div class="radios">
										<label class="submitButton"> Reject Solution <input type="radio" name="issueStatus"  value="inProgress"/></label>
										<label class="submitButton"> Accept Solution <input type="radio" name="issueStatus" value="resolved"/></label>	
									</div>
									<input name="page" type="hidden" value="ticketView"/>								
								    <input  type="submit" value="Update Ticket"/>
								</div>
							</form>
						</c:when>
						
						<c:when test="${sessionScope.roleID =='USER'}"> 
							<form name="updateTicket" action="Controller" method="POST" id="updateTicket">
								<div class="addTicketElement">	
									<p class="firstInputs">Comment:</p><textarea  id="issueCommentBody" name="issueCommentBody"></textarea>
								</div>
								<input name="page" type="hidden" value="ticketView"/>
								<input  type="submit" value="Update Ticket"/>
							</form>
						</c:when>
					</c:choose>
					
					<c:if test="${sessionScope.roleID == 'ADMIN' && requestScope.ticket.Status}=='Resolved'}">
						<form name="toArticle" action="Controller" method="POST" id="toArticle">
							<input name="page" type="hidden" value="ticketView"/>
							<input name="toArticle" type="hidden" value="toArticle"/>
							<input  type="submit" value="Submit to Article"/>
						</form>
					</c:if>
					
				</div>
			</div>
			
		</div>
		
	</body>
</html>