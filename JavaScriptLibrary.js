
//Function assigns the submitted ID to the form and submits it (gamePage.jsp). 
//Used for clicking the images
function clickHandler(id){
	var h = document.getElementById("hiddenForm");
	h.value = id;
	document.forms["hiddenF"].submit();
}
