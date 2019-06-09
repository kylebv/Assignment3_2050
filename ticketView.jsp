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
		
		<div class="pageContent">
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
					
						
					<c:choose>
						
						<c:when ${requestSession.roleID} =='ADMIN'>
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
						
						<c:when ${requestSession.roleID} =='USER' && ${requestScope.ticket.Status}=='completed'}> 
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
						
						<c:when ${requestSession.roleID} =='USER'}> 
							<form name="updateTicket" action="Controller" method="POST" id="updateTicket">
								<div class="addTicketElement">	
									<p class="firstInputs">Comment:</p><textarea  id="issueCommentBody" name="issueCommentBody"></textarea>
								</div>
								<input name="page" type="hidden" value="ticketView"/>
								<input  type="submit" value="Update Ticket"/>
							</form>
						</c:when>
						
						<c:otherwise>
							<jsp:foward page="Controller"/>
						</c:otherwise>
						
					</c:choose>
					
					<c:if test="${requestSession.roleID} == 'ADMIN' && ${requestScope.ticket.Status}=='resolved'">
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