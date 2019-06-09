import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import javax.servlet.*;

@WebServlet("/uploadController")
@MultipartConfig(maxFileSize = 16177215)    // upload file's size up to 16MB
public class uploadController extends HttpServlet {
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {

       // obtains the upload file part in this multipart request
       Part filePart = request.getPart("file");
	   String pageFrom = request.getParameter("pageFrom");
	   String viewTicket = request.getParameter("viewTicket");
       if(filePart!=null){
           QueryClass.addFile(Integer.parseInt(viewTicket), filePart);
       }
  
       // forwards back to the controller
        request.setAttribute("page", pageFrom);
		request.setAttribute("viewTicket", viewTicket);
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/Controller?page=" + pageFrom + "&viewTicket=" + viewTicket);//NEEd TO CHANGE SO WEB INF HOLD JSP
		dispatcher.forward(request,response);
    }
}