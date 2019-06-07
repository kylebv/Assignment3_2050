import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.util.*;
import models.*;


@WebServlet(urlPatterns = {"/Controller"})
public class Controller extends HttpServlet {
	
    public void doPost(HttpServletRequest request, HttpServletResponse response) 
    	throws ServletException, IOException {
			
		//-------------Get-Information---------------------------------------
		HttpSession session = request.getSession() //inside a servlet
		String userName = session.getAttribute("j_username");
		String role = null;//Get role from database
		String pageSrc = request.getAttribute("page");
		QueryClass qc = QueryClass();
		//--------------------------------------------------------------------------
		String nextJSP = null;
		//--------------Decision-Tree-For-Page-State--------------------------------
		switch(pageSrc){
			case "login":
				String submitUserName = request.getAttribute("userName");
				String submitPassword = request.getAttribute("password");
				if(submitUserName != null || submitPassword != null){
					boolean login = true;//Check login (NEED TO CORRECT)!!!
					if(login){
						//Set login to session(NEED TO CORRECT)!!!
						nextJSP = sendTicketHome(request,session,qc,null);
					}else{
						nextJSP = sendLogin(request,session,"ERROR!!!!!!!!");
					}
				}else nextJSP = sendLogin(request,session,null);
			break;
			case "ticketHome":
				nextJSP = navigationBarCheck(request,session,qc);
				if(nextJSP == null){
					String category = request.getAttribute("categoryBar");
					String viewTicket = request.getAttribute("viewTicket");
					if(categoryBar != null) nextJSP = sendTicketHome(request,session,qc,categoryBar);
					else if(viewTicket != null) nextJSP = sendTicketView(request,session,qc,viewTicket);
					else nextJSP = sendTicketHome(request,session,qc,null);
				}
			break;
			case "ticketView":
				nextJSP = navigationBarCheck(request,session,qc);
				if(nextJSP == null){
					
				}			
			break;
			case "ticketToArticle":
				nextJSP = navigationBarCheck(request,session,qc);
				if(nextJSP == null){
					
				}			
			break;
			case "addNewTicket":
				nextJSP = navigationBarCheck(request,session,qc);
				if(nextJSP == null){
					String issueName = request.getAttribute("issueName");
					String issueDescription = request.getAttribute("issueDescription");
					String issueCategory = request.getAttribute("issueCategory");
					//Get files
					if(issueName != null && issueDescription != null && issueCategory != null){
						qc.addTicket(userName,issueName,issueDescription,issueCategory,null/*not null*/);//NULL needs to be changed to file paths!!!!!!
						//ADDD FILES
						nextJSP = sendTicketHome(request,session,qc,null);
					}else nextJSP = sendAddNewTicket(request,session,"ERROR!!!!!!!!");
				}			
			break;
			case "knowledgeBase":
				nextJSP = navigationBarCheck(request,session,qc);
				if(nextJSP == null){
					String category = request.getAttribute("categoryBar");
					String viewArticle = request.getAttribute("viewArticle");
					if(categoryBar != null) nextJSP = sendKnowledgeBase(request,session,qc,categoryBar);
					else if(viewArticle != null) nextJSP = sendArticle(request,session,qc,viewArticle);
					else nextJSP = sendKnowledgeBase(request,session,qc,null);
				}			
			break;
			case "article":
				nextJSP = navigationBarCheck(request,session,qc);
				if(nextJSP == null){
					String viewArticle = request.getAttribute("viewArticle");
					nextJSP = sendArticle(request,session,qc,viewArticle);
				}			
			break;			
			default:
				nextJSP = sendLogin(request,session,null);
			break;
		}
		request.setAttribute("roleID",role);
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP);
		dispatcher.forward(request,response);
		//--------------------------------------------------------------------------
	} 
	
	private String navigationBarCheck(HttpServletRequest request, HttpSession session, QueryClass qc){
		String nvgbar = request.getAttribute("navigationBar");
		switch(nvgbar){
			case "tickets": return sendTicketHome(request,session,qc,null);
			case "knowledgeBase": return sendKnowledgeBase(request,session,qc,null);
			case "Logout": return sendLogin(request,session,null);
			default: return null;
		}
	}
	
	private void logout( HttpServletRequest request, HttpSession session){
	//DO this LATER
	}
	
	private String sendLogin(HttpServletRequest request, HttpSession session, String loginError){
		logout(request,session);
		if(loginError != null) request.setAttribute("isLoginError",loginError);
		return "ticketHome.jsp";
	}
	
	private String sendTicketHome(HttpServletRequest request, HttpSession session, QueryClass qc, String sortType){
		String role = null;//Get role from database
		String userName = session.getAttribute("j_username");
		List<TicketModel> list = null;
		if(role.equals("ADMIN")){
			if(sortType == null) list = qc.getAllTickets();
			else list = qc.getAllTicketsByCategory(sortType);
		}else 
			list = qc.getAllTicketsByUser(userName);
		request.setAttribute("tickets",list);
		return "ticketHome.jsp";
	}
	
	private String sendTicketView(HttpServletRequest request, HttpSession session, QueryClass qc, String ID){
		request.setAttribute("ticket",qc.getTicket(ID));
		request.setAttribute("files",qc.getFiles(ID));
		return "ticketView.jsp";
	}
	
	private String sendKnowledgeBase(HttpServletRequest request, HttpSession session, QueryClass qc, String sortType){
		List<KnowledgeBaseArticleModel> list = null;
		if(sortType == null) list = qc.getAllArticles();
		else list = qc.getAllArticlesByCategory(sortType);
		request.setAttribute("articles",list);
		return "knowledgeBase.jsp";
	}
	
	private String sendArticle(HttpServletRequest request, HttpSession session, QueryClass qc, String ID){
		request.setAttribute("ticket",qc.getArticle(ID));
		request.setAttribute("files",qc.getFiles(ID));
		return "article.jsp";
	}
	
	private String sendTicketToArticle(HttpServletRequest request, HttpSession session, QueryClass qc, String ID){
		request.setAttribute("ticket",qc.getTicket(ID));
		request.setAttribute("files",qc.getFiles(ID));
		return "ticketToArticle.jsp";
	}
	
	private String sendAddNewTicket(HttpServletRequest request, HttpSession session, String error){
		if(error != null) request.setAttribute("error",error);
		return "addNewTicket.jsp";
	}

}
