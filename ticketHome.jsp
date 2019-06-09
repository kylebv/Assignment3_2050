
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
		<form name="navigationBar" action="Controller" method="POST" id="navigationBar">
			<input name="page" type="hidden" value="ticketHome"/>
			<input name="navigationBar" type="hidden" value=""/>
		</form>
		
		<form name="categoryBar" action="Controller" method="POST" id="categoryBar">
			<input name="page" type="hidden" value="ticketHome"/>
			<input name="categoryBar" type="hidden" value=""/>
		</form>
		
		<form name="viewTicket" action="Controller" method="POST" id="viewTicket">
			<input name="page" type="hidden" value="ticketHome"/>
			<input name="viewTicket" type="hidden" value=""/>
		</form>
		
		<div class="pageheader"> 
			<img src="images/logoSml.png" id="logoSml" alt="placeholder" >
			<div class="navagationBar"> 
					<button class="button" onclick="clickHandlerNavBar('tickets')"> Tickets </button>
					<button class="button" onclick="clickHandlerNavBar('knowledgeBase')"> KnowledgeBase </button> 
					<button class="button" onclick="clickHandlerNavBar('logout')"> Logout </button> 				
			</div>
		</div>
		
		<div id=pageContent>
			<c:if test="${requestScope.roleID == 'ADMIN'}">
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
			</c:if>
			
			<div id=articleList>
				    <c:forEach items="${requestScope.tickets}" var="TicketModel">
						<div id="${element.ticketID}">
							<c:out value="${element.title}">
							<button onclick="clickHandlerViewTicket(${element.ticketID})"class=viewButton> View Ticket </button> 
						</div>
					</c:forEach>
			</div>
			
		</div>
		
		
	</body>
</html>
