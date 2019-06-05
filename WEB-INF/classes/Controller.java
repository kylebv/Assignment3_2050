import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;


@WebServlet(urlPatterns = {"/MainPage"})
public class MainPage extends HttpServlet {
	
    public void doPost(HttpServletRequest request, HttpServletResponse response) 
    	throws ServletException, IOException {
			
		//-------------Get-Information---------------------------------------
		HttpSession session = request.getSession() //inside a servlet
		String userName = session.getAttribute("j_username");
		String role = null;//Get role from database
		//--------------------------------------------------------------------------
		
		//--------------Decision-Tree-For-Page-State--------------------------------
		if(
		//--------------------------------------------------------------------------
	} 

}
