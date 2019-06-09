
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
		
		<div class="pageContent">
			<c:if test="${requestScope.roleID == 'ADMIN'}">
				<div id=sortingBar> 
				
				<div class="addTicketElement">
					<p> Select Type: </p>
					<div class="radios">
							<label class="radioButton"> Network: <input type="radio" name="issueCategory"  value="Network"/></label>
							<label class="radioButton"> Software: <input type="radio" name="issueCategory" value="Software"/></label>
							<label class="radioButton"> HardWare: <input type="radio" name="issueCategory" value="Hardware"/></label>
							<label class="radioButton"> Email: <input type="radio" name="issueCategory" value="Email"/></label>
							<label class="radioButton"> Account: <input type="radio" name="issueCategory" value="Account"/></label>
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
			
		</div>
		
		
	</body>
</html>
