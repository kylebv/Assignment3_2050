import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.util.*;
import models.*;


@WebServlet(urlPatterns = {"/Controller"})
public class Controller extends HttpServlet {
	
	//Query class login/username functions
	//File stuff
	
    public void doPost(HttpServletRequest request, HttpServletResponse response) 
    	throws ServletException, IOException {
		//-------------Get-Information---------------------------------------
		HttpSession session = request.getSession(); 
		String userName = session.getAttribute("userName");
		String role = session.getAttribute("role");
		String pageSrc = request.getParameter("page");
		QueryClass qc = QueryClass();
		//--------------------------------------------------------------------------
		String nextJSP = null;
		//--------------Decision-Tree-For-Page-State--------------------------------
		switch(pageSrc){
			case "login"://All users
				String submitUserName = request.getParameter("userName");
				String submitPassword = request.getParameter("password");
				if(submitUserName != null || submitPassword != null){
					if(isVaildLogin(qc,submitUserName,submitPassword)){
						role = qc.getRoleByUserName(submitUserName);
						login(request,session,submitUserName,role);
						nextJSP = sendTicketHome(request,session,qc,null);
					}else nextJSP = sendLogin(request,session,"ERROR!!!!!!!!");
				}else nextJSP = sendLogin(request,session,null);
			break;
			case "ticketHome"://All users
				if(role == null) nextJSP = sendLogin(request,session,null);
				else{
					nextJSP = navigationBarCheck(request,session,qc);
					if(nextJSP == null){
						String category = request.getParameter("categoryBar");
						String viewTicket = request.getParameter("viewTicket");
						if(categoryBar != null) nextJSP = sendTicketHome(request,session,qc,categoryBar);
						else if(viewTicket != null) nextJSP = sendTicketView(request,session,qc,viewTicket);
						else nextJSP = sendTicketHome(request,session,qc,null);
					}
				}
			break;
			case "ticketView"://All users
				if(role == null) nextJSP = sendLogin(request,session,null);
				else{
					nextJSP = navigationBarCheck(request,session,qc);
					if(nextJSP == null){
						String updateTicket = request.getParameter("updateTicket");
						String toArticle = request.getParameter("toArticle");
						String viewTicket = request.getParameter("viewTicket");
						String issueStatus = request.getParameter("issueStatus");
						String issueCommentTitle = request.getParameter("issueCommentTitle");
						String issueCommentBody = request.getParameter("issueCommentBody");
						if(updateTicket != null){
							TicketModel ticket = qc.getTicket(viewTicket);
							boolean Error = false, Comment = false;
							if(!ticket.getStatus().equals(issueStatus)){
								switch(issueStatus){
									case "inProgress":
										if(role.equals("ADMIN") && (ticket.getStatus().equals("new") || ticket.getStatus().equals("completed")))
											ticket.setStatus(issueStatus);
										else if(role.equals("USER") && ticket.getStatus().equals("completed"))
											ticket.setStatus(issueStatus);
										else 
											Error = true;
									break;
									case "completed":
										if(role.equals("ADMIN") && (ticket.getStatus().equals("new") || ticket.getStatus().equals("inProgress")))
											ticket.setStatus(issueStatus);	
										else 
											Error = true;										
									break;
									case "resolved":
										if(role.equals("USER") && ticket.getStatus().equals("completed"))
											ticket.setStatus(issueStatus);			
										else 
											Error = true;										
									break;
									default:
										Error = true;
									break;
								}
							}
							Comment = issueCommentBody != null && issueCommentTitle != null;
							Error = issueCommentBody != null ^ issueCommentTitle != null;
							if(Error)request.setParameter("isError","t");
							else {
								if(Comment)qc.addComment(viewTicket,issueCommentTitle,issueCommentBody);
								//ADD TICKET STATUS CHANGE@#%@$%&@%$)$%^VQ#%Y(@%*V#*%YV%S#(UYK)N$VME%Y( S$M (S$%(YUS ($
							}
							nextJSP = sendTicketView(request,session,qc,viewTicket);
						}else if(toArticle != null){
							if( role.equals("ADMIN") && ticket.getStatus().equals("resolved"){
								//CHANGE TICKET TO ARTICLE@$%&@%$)$%^VQ#%Y(@%*V#*%YV%S#(UYK)N$VME%Y( S$M (S$%(YUS ($
								 nextJSP = sendTicketHome(request,session,qc,null);
							}else{
								nextJSP = sendTicketView(request,session,qc,viewTicket);
							}
						}else nextJSP = sendTicketView(request,session,qc,viewTicket);
					}
				}					
			break;
			// case "ticketToArticle"://ADMIN
				// if(!role.equals("ADMIN")) nextJSP = sendLogin(request,session,null);
				// else{
					// nextJSP = navigationBarCheck(request,session,qc);
					// if(nextJSP == null){
					
					// }
				// }
			// break;
			case "addNewTicket"://USERS
				if(!role.equals("USER")) nextJSP = sendLogin(request,session,null);
				else{
					nextJSP = navigationBarCheck(request,session,qc);
					if(nextJSP == null){
						String issueName = request.getParameter("issueName");
						String issueDescription = request.getParameter("issueDescription");
						String issueCategory = request.getParameter("issueCategory");
						//Get files
						if(issueName != null && issueDescription != null && issueCategory != null){
							qc.addTicket(userName,issueName,issueDescription,issueCategory,null/*not null*/);//NULL needs to be changed to file paths!!!!!!
							//ADDD FILES
							nextJSP = sendTicketHome(request,session,qc,null);
						}else nextJSP = sendAddNewTicket(request,session,"ERROR!!!!!!!!");
					}
				}					
			break;
			case "knowledgeBase"://All Users
				if(role == null) nextJSP = sendLogin(request,session,null);
				else{
					nextJSP = navigationBarCheck(request,session,qc);
					if(nextJSP == null){
						String category = request.getParameter("categoryBar");
						String viewArticle = request.getParameter("viewArticle");
						if(categoryBar != null) nextJSP = sendKnowledgeBase(request,session,qc,categoryBar);
						else if(viewArticle != null) nextJSP = sendArticle(request,session,qc,viewArticle);
						else nextJSP = sendKnowledgeBase(request,session,qc,null);
					}	
				}
			break;
			case "article"://All Users
				if(role == null) nextJSP = sendLogin(request,session,null);
				else{
					nextJSP = navigationBarCheck(request,session,qc);
					if(nextJSP == null){
						String viewArticle = request.getParameter("viewArticle");
						nextJSP = sendArticle(request,session,qc,viewArticle);
					}
				}					
			break;			
			default:
				nextJSP = sendLogin(request,session,null);
			break;
		}
		request.setAttribute("roleID",role);
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP);//NEEd TO CHANGE SO WEB INF HOLD JSP
		dispatcher.forward(request,response);
		//--------------------------------------------------------------------------
	} 
	
	private String navigationBarCheck(HttpServletRequest request, HttpSession session, QueryClass qc){
		String nvgbar = request.getParameter("navigationBar");
		switch(nvgbar){
			case "tickets": return sendTicketHome(request,session,qc,null);
			case "knowledgeBase": return sendKnowledgeBase(request,session,qc,null);
			case "Logout": return sendLogin(request,session,null);
			default: return null;
		}
	}
	
	private void logout( HttpServletRequest request, HttpSession session){
		session.setParameter("userName",null);
		session.setParameter("role",null);
	}
	
	private void login( HttpServletRequest request, HttpSession session, String name, String role){
		session.setAttribute("userName",name);
		session.setAttribute("role",role);
	}
	
	private boolean isVaildLogin(QueryClass qc,String name,String pw){
		String dbpw = qc.getPasswordByUserName(name);
		if(dbpw != null) if(dbpw.equals(pw)) return true;
		return false;
	}
	
	private String sendLogin(HttpServletRequest request, HttpSession session, String loginError){
		logout(request,session);
		if(loginError != null) request.setParameter("isLoginError",loginError);
		return "ticketHome.jsp";
	}
	
	private String sendTicketHome(HttpServletRequest request, HttpSession session, QueryClass qc, String sortType){
		String userName = session.getAttribute("userName");
		String role = session.getAttribute("role");
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
		if(error != null) request.setParameter("error",error);
		return "addNewTicket.jsp";
	}

}
