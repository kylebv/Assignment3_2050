import models.*;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.util.*;

@WebServlet(urlPatterns = {"/Controller"})
public class Controller extends HttpServlet {
	
	//File stuff
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		HttpSession session = request.getSession();
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/" + sendLogin(request,session,null));//NEEd TO CHANGE SO WEB INF HOLD JSP
		dispatcher.forward(request,response);
	}
	
    public void doPost(HttpServletRequest request, HttpServletResponse response) 
    	throws ServletException, IOException {
		//-------------Get-Information---------------------------------------
		HttpSession session = request.getSession(); 
		UserModel U = (UserModel) session.getAttribute("user");
		String userName = null;
		String role = null;
		if( U != null ){
			userName = U.getUsername();
			role = U.getRole();
		}
		String pageSrc = request.getParameter("page");				
		//--------------------------------------------------------------------------
		String nextJSP = null;
		//--------------Decision-Tree-For-Page-State--------------------------------
		switch(pageSrc){
			case "login"://All users
				String submitUserName = request.getParameter("userName");
				String submitPassword = request.getParameter("password");
				if(submitUserName != null || submitPassword != null){
					if(isVaildLogin(submitUserName,submitPassword)){
						U = QueryClass.getUser(submitUserName,submitPassword);
						userName = U.getUsername();
						role = U.getRole();
						login(request,session,U);
						nextJSP = sendTicketHome(request,session,null);
					}else nextJSP = sendLogin(request,session,"ERROR!!!!!!!!");
				}else nextJSP = sendLogin(request,session,null);
			break;
			case "ticketHome"://All users
				if(role == null) nextJSP = sendLogin(request,session,null);
				else{
					nextJSP = navigationBarCheck(request,session);
					if(nextJSP == null){
						String category = request.getParameter("categoryBar");
						String viewTicket = request.getParameter("viewTicket");
						String newTicket = request.getParameter("newTicket");
						if(category != null) nextJSP = sendTicketHome(request,session,category);
						else if(viewTicket != null) nextJSP = sendTicketView(request,session,viewTicket);
						else if(newTicket != null) nextJSP = sendAddNewTicket(request,session,null);
						else nextJSP = sendTicketHome(request,session,null);
					}
				}
			break;
			case "ticketView"://All users
				if(role == null) nextJSP = sendLogin(request,session,null);
				else{
					nextJSP = navigationBarCheck(request,session);
					if(nextJSP == null){
						String updateTicket = request.getParameter("updateTicket");
						String toArticle = request.getParameter("toArticle");
						String viewTicket = request.getParameter("viewTicket");
						String issueStatus = request.getParameter("issueStatus");
						String issueCommentBody = request.getParameter("issueCommentBody");
						String fileToDelete = request.getParameter("delid");
						TicketModel ticket = QueryClass.getTicket(Integer.parseInt(viewTicket));
						if(updateTicket != null){
							boolean Error = false, Comment = false;
							if(issueStatus != null){
								switch(issueStatus){
									case "In Progress":
										if(role.equals("ADMIN") && (ticket.getStatus().equals("New") || ticket.getStatus().equals("Completed")))
											ticket.setStatus(issueStatus);
										else if(role.equals("USER") && ticket.getStatus().equals("Completed"))
											ticket.setStatus(issueStatus);
										else 
											Error = true;
									break;
									case "Completed":
										if(role.equals("ADMIN") && (ticket.getStatus().equals("New") || ticket.getStatus().equals("In Progress")))
											ticket.setStatus(issueStatus);	
										else 
											Error = true;										
									break;
									case "Resolved":
										if(role.equals("USER") && ticket.getStatus().equals("Completed"))
											ticket.setStatus(issueStatus);			
										else 
											Error = true;										
									break;
									default:
										Error = true;
									break;
								}
							}
							Comment = issueCommentBody != null;
							if(Error) request.setAttribute("isError","t");
							else {
								if(Comment)QueryClass.addComment(new CommentModel(userName, issueCommentBody, Integer.parseInt(viewTicket), new Date()));
								QueryClass.setTicketStatus(Integer.parseInt(viewTicket),ticket.getStatus());
							}
							nextJSP = sendTicketView(request,session,viewTicket);
						}else if(toArticle != null){
							if( role.equals("ADMIN") && ticket.getStatus().equals("Resolved")){
								QueryClass.createArticle(Integer.parseInt(viewTicket));
								nextJSP = sendTicketHome(request,session,null);
							}else{
								nextJSP = sendTicketView(request,session,viewTicket);
							}
						}else if(fileToDelete != null){
							QueryClass.deleteFile(Integer.parseInt(fileToDelete));
							nextJSP = sendTicketView(request,session,viewTicket);
						}else nextJSP = sendTicketView(request,session,viewTicket);
					}
				}					
			break;
			case "addNewTicket"://USERS
				if(!role.equals("USER")) nextJSP = sendLogin(request,session,null);
				else{
					nextJSP = navigationBarCheck(request,session);
					if(nextJSP == null){
						String issueName = request.getParameter("issueName");
						String issueDescription = request.getParameter("issueDescription");
						String issueCategory = request.getParameter("issueCategory");
						if(issueName != null && issueDescription != null && issueCategory != null){
							QueryClass.addTicket(userName,issueName,issueDescription,issueCategory);
							nextJSP = sendTicketHome(request,session,null);
						}else nextJSP = sendAddNewTicket(request,session,"ERROR!!!!!!!!");
					}
				}					
			break;
			case "knowledgeBase"://All Users
				if(role == null) nextJSP = sendLogin(request,session,null);
				else{
					nextJSP = navigationBarCheck(request,session);
					if(nextJSP == null){
						String category = request.getParameter("categoryBar");
						String viewArticle = request.getParameter("viewArticle");
						if(category != null) nextJSP = sendKnowledgeBase(request,session,category);
						else if(viewArticle != null) nextJSP = sendArticle(request,session,viewArticle);
						else nextJSP = sendKnowledgeBase(request,session,null);
					}	
				}
			break;
			case "article"://All Users
				if(role == null) nextJSP = sendLogin(request,session,null);
				else{
					nextJSP = navigationBarCheck(request,session);
					if(nextJSP == null){
						String viewArticle = request.getParameter("viewArticle");
						nextJSP = sendArticle(request,session,viewArticle);
					}
				}					
			break;	
			case "downloadPage":
				if(role == null) nextJSP = sendLogin(request,session,null);
				else{
					String previousPage = request.getParameter("previousPage");
					String previousID = request.getParameter("previousID");
					if(previousPage.equals("article")){
						nextJSP = sendTicketView(request,session,previousID);
					}else if(previousPage.equals("ticketView")){
						nextJSP = sendArticle(request,session,previousID);
					}else{
						nextJSP = sendLogin(request,session,null);
					}
				}
			break;
			default:
				nextJSP = sendLogin(request,session,null);
			break;
		}
		request.setAttribute("roleID",role);
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/" + nextJSP);//NEEd TO CHANGE SO WEB INF HOLD JSP
		dispatcher.forward(request,response);
		//--------------------------------------------------------------------------
	} 
	
	private String navigationBarCheck(HttpServletRequest request, HttpSession session){
		String nvgbar = request.getParameter("navigationBar");
		if(nvgbar == null)return null;
		switch(nvgbar){
			case "tickets": return sendTicketHome(request,session,null);
			case "knowledgeBase": return sendKnowledgeBase(request,session,null);
			case "logout": return sendLogin(request,session,null);
			default: return null;
		}
	}
	
	private void logout( HttpServletRequest request, HttpSession session){
		session.setAttribute("user",null);
	}
	
	private void login( HttpServletRequest request, HttpSession session, UserModel u){
		session.setAttribute("user",u);
	}
	
	private boolean isVaildLogin(String name,String pw){
		UserModel u = QueryClass.getUser(name);
		if(pw != null && u != null){if(pw.equals(u.getPassword())) return true; }
		return false;
	}
	
	private String sendLogin(HttpServletRequest request, HttpSession session, String loginError){
		logout(request,session);
		if(loginError != null) request.setAttribute("isLoginError",loginError);
		return "login.jsp";
	}
	
	private String sendTicketHome(HttpServletRequest request, HttpSession session, String sortType){
		UserModel U = (UserModel) session.getAttribute("user");
		String userName = U.getUsername();
		String role = U.getRole();
		List<TicketModel> list = null;
		if(role.equals("ADMIN")){
			if(sortType == null) list = QueryClass.getTickets();
			else list = QueryClass.getTicketsByCategory(sortType);
		}else 
			list = QueryClass.getTicketsByUser(userName);
		request.setAttribute("tickets",list);
		return "ticketHome.jsp";
	}
	
	private String sendTicketView(HttpServletRequest request, HttpSession session, String id){
		int ID = Integer.parseInt(id);
		request.setAttribute("ticket",QueryClass.getTicket(ID));
		request.setAttribute("files",QueryClass.getFiles(ID));
		request.setAttribute("Comments",QueryClass.getTicket(ID).getComments());
		return "ticketView.jsp";
	}
	
	private String sendKnowledgeBase(HttpServletRequest request, HttpSession session, String sortType){
		List<KnowledgeBaseArticleModel> list = null;
		if(sortType == null) list = QueryClass.getArticles();
		else list = QueryClass.getArticlesByCategory(sortType);
		request.setAttribute("articles",list);
		return "knowledgeBase.jsp";
	}
	
	private String sendArticle(HttpServletRequest request, HttpSession session, String id){
		int ID = Integer.parseInt(id);
		request.setAttribute("article",QueryClass.getArticle(ID));
		request.setAttribute("files",QueryClass.getFiles(QueryClass.getArticle(ID).getTicketID()));
		return "article.jsp";
	}
	
	private String sendTicketToArticle(HttpServletRequest request, HttpSession session, String id){
		int ID = Integer.parseInt(id);
		request.setAttribute("ticket",QueryClass.getTicket(ID));
		request.setAttribute("files",QueryClass.getFiles(ID));
		return "ticketToArticle.jsp";
	}
	
	private String sendAddNewTicket(HttpServletRequest request, HttpSession session, String error){
		if(error != null) request.setAttribute("error",error);
		return "addNewTicket.jsp";
	}

}
