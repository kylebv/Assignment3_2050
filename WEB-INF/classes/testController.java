
//Author: Kyle Vincent
//Date: 12/5/19
//Student no.:3166360
//Subject: SENG2050
//Project: Assignment 2
//Student Type: 2nd Year Bachelor of IT(Software Engineering) Undergraduate
//University: University of Newcastle, Ourimbah Campus
//Description: the servlet that sets up the game


import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.util.List;

@WebServlet(urlPatterns={"/testController"})
public class testController extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head><link rel=\"stylesheet\" href=\"style.css\"><title>Clarit Theatre - Booking Details</title></head>");
        out.println("<body>");








        List<String> ls = QueryClass.getCategories();
        for (String s: ls)
        {
            out.println("<p>"+s+"</p><br>");
        }
        out.println(""+ls);







        out.println("</body>");
        out.println("</html>");
    }
}
