
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
		<form name="navigationBarF" action="Controller" method="POST" id="navFormId">
			<input name="page" type="hidden" value="ticketHome"/>
			<input name="navigationBar" type="hidden" value="" id="navigationBarE"/>
		</form>
		
		<form name="categoryBarF" action="Controller" method="POST" id="catFormId">
			<input name="page" type="hidden" value="ticketHome"/>
			<input name="categoryBar" type="hidden" value=""  id="categoryBarE"/>
		</form>
		
		<form name="viewTicketF" action="Controller" method="POST" id="viewTicketFormId">
			<input name="page" type="hidden" value="ticketHome"/>
			<input name="viewTicket" type="hidden" value="" id="viewTicketE"/>
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
			<c:if test="${requestScope.roleID == 'ADMIN'}">
				<div id=sortingBar> 
				<div class="addTicketElement">
					<div class="radios"> 
					<button class="radioButton" onclick="clickHandlerCatBar('network')"> Network </button>
					<button class="radioButton" onclick="clickHandlerCatBar('software')"> Software </button> 
					<button class="radioButton" onclick="clickHandlerCatBar('hardware')"> Hardware </button> 				
					<button class="radioButton" onclick="clickHandlerCatBar('email')"> Email </button> 
					<button class="radioButton" onclick="clickHandlerCatBar('account')"> Account </button> 				
				</div>
				</div>	
			</div>	
			</c:if>
			
			<div id=articleList>
				<div class="addTicketElement">
				    <c:forEach items="${requestScope.tickets}" var="TicketModel">
						<div id="${TicketModel.ticketID}">
							<c:out value="${TicketModel.title}"/>
							<hr>
							<button class="button" onclick="clickHandlerViewTicket(${TicketModel.ticketID})"class=viewButton> View Ticket </button> 
						</div>
					</c:forEach>
				</div>
			</div>
			
			<div>
			<c:if test="${requestScope.roleID == 'ADMIN'}">
				<form name="addNewTicket" action="Controller" method="POST" id="addNewTicket">
					<input name="page" type="hidden" value="ticketHome" />		
					<input type="submit" name="newTicket" value="newTicket"/>
				</form>
			</c:if>
			</div>
			
		</div>
		
		
	</body>
</html>
