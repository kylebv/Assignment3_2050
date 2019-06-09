<!DOCTYPE html>
<html lang="en">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

	<head>
		<meta charset="UTF-8">
		<title>Add new ticket page</title>
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
			<form  name="addNewTicket" action="Controller" method="POST" id="addNewTicket" enctype="multipart/form-data" >
				<div class="addTicketElement">
					<h1> New Ticket </h1>
				</div>
				<hr>
				<div class="addTicketElement">
					<p class="firstInputs">Issue Name:</p><textarea  id="issueName" name="issueName"></textarea>
				</div>
				<div class="addTicketElement">	
					<p class="firstInputs">Description:</p><textarea  id="issueDescription" name="issueDescription"></textarea>
				</div>
				<div class="addTicketElement">
					Category:
					<br>
					<div class="radios">
						<label class="radioButton"> Network: <input type="radio" name="issueCategory"  value="Network"/></label>
						<label class="radioButton"> Software: <input type="radio" name="issueCategory" value="Software"/></label>
						<label class="radioButton"> HardWare: <input type="radio" name="issueCategory" value="Hardware"/></label>
						<label class="radioButton"> Email: <input type="radio" name="issueCategory" value="Email"/></label>
						<label class="radioButton"> Account: <input type="radio" name="issueCategory" value="Account"/></label>
					</div>
				</div>
				<div class="addTicketElement">	
					<p class="firstInputs">Upload File:</p><input type="file" name ="file upload" class="fileUpload" value="file upload"/>
				</div>
				<hr>
				<input name="page" type="hidden" value="addNewTicket"/>
				<div class="submitElement">	
					<input class= "submitButton" type="submit"/>
				</div>	
			</form>
			<c:if test = "${requestScope.error != null}">
				<div id="errorMessage">
					<p> Error adding ticket, please try again. </p>
				</div>
			</c:if>
		</div>
	</body>
</html>