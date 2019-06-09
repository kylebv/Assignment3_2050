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
		
		<div class="ticketContent">
		<div class="addTicketElement"><h2 class="ticketLabel">User Ticket</h2></div><hr>
			<div id=articleList>
				<div class="viewTicketElement">
					<p class="ticketLabel">Ticket Name:</p>
					<div class= "cOut"><c:out value="${requestScope.ticket.title}" /></div>
				</div>
				<div class="viewTicketElement">
					<p class="ticketLabel">User:</p>
					<div class= "cOut"><c:out value="${requestScope.ticket.user}" /></div>
				</div>
				<div class="viewTicketElement">
					<p class="ticketLabel">Issue Category:</p>
					<div class= "cOut"><c:out  value="${requestScope.ticket.category}" /></div>
				</div>
				<div class="viewTicketElement">
					<p class="ticketLabel">Description:</p>
					<div class= "cOut"><c:out value="${requestScope.ticket.body}" /></div>
				</div>
				<div class="viewTicketElement">
					<p class="ticketLabel">Date Created:</p>
					<div class= "cOut"><c:out value="${requestScope.ticket.dateCreated}" /></div>
				</div>
				<hr>
				<div>
					<c:forEach items="${requestScope.Comments}" var="CommentModel" >
						<div>
							<div class="viewTicketElement">
								<p class="ticketLabel">Comment added:</p>
								<div class= "cOut"><c:out value="${CommentModel.body}" /></div>
							</div>
							<div class="viewTicketElement">
								<p class="ticketLabel">Comment By:</p>
								<div class= "cOut"><c:out value="${CommentModel.user}" /></div>
							</div>
						</div>
						<br>
					</c:forEach>
				</div>
				<div class="viewTicketElement">
					<c:forEach items="${requestScope.ticket.files}" var="FileModel">
						<div id="${FileModel.fileExtention}">
							<div class= "cOut"><c:out value="${FileModel.fileExtention}" /></div>
						</div>
					</c:forEach>
				</div>
				<div>
					
						
					<c:choose>
						
						<c:when test="${sessionScope.roleID =='ADMIN'}">
							<form name="updateTicket" action="Controller" method="POST" id="updateTicket">
								<div class="viewTicketElement">	
									<p class="ticketLabel">Comment:</p><textarea  id="issueCommentTitle" name="issueCommentTitle"></textarea>
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
								<div class="viewTicketElement">	
									<p class="ticketLabel">Comment:</p><textarea  id="issueCommentBody" name="issueCommentBody"></textarea>
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
								<div class="viewTicketElement">	
									<p class="ticketLabel">Comment:</p><textarea  id="issueCommentBody" name="issueCommentBody"></textarea>
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