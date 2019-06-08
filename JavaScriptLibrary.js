
function clickHandlerCatBar(value){
	var h = document.getElementById("categoryBar");
	h.categoryBar = value;
	document.forms["categoryBar"].submit();
}

function clickHandlerNavBar(value){
	var h = document.getElementById("navagationBar");
	h.navagationBar = value;
	document.forms["navagationBar"].submit();
}

function clickHandlerViewTicket(value){
	var h = document.getElementById("viewTicket");
	h.viewTicket = value;
	document.forms["viewTicket"].submit();
}

function clickHandlerViewArticle(value){
	var h = document.getElementById("viewArticle");
	h.viewArticle = value;
	document.forms["viewArticle"].submit();
}

