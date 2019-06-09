
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import javax.servlet.*;
import javax.naming.NamingException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * A servlet that retrieves a file from MySQL database and lets the client
 * download the file.
 * @author www.codejava.net, adapted by Kyle Vincent
 */
@WebServlet("/downloadController")
public class downloadController extends HttpServlet {

    // size of byte buffer to send file
    private static final int BUFFER_SIZE = 4096;

    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        // get upload id from URL's parameters
        int uploadId = Integer.parseInt(request.getParameter("getid"));
        String pageFrom = request.getParameter("pageFrom");

        Connection connection = null; // connection to the database

        try {
            connection = DBConnector.getConnection();

            // queries the database
            String query = "SELECT ID, fileExtension, fileData FROM TicketFile where ID = ? and isDeleted = 0;";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, uploadId);

            ResultSet result = statement.executeQuery();
            if (result.next()) {
                // gets file name and file blob data
                String fileName = result.getString("fileExtension");
                Blob blob = result.getBlob("fileData");
                InputStream inputStream = blob.getBinaryStream();
                int fileLength = inputStream.available();

                System.out.println("fileLength = " + fileLength);

                ServletContext context = getServletContext();

                // sets MIME type for the file download
                String mimeType = context.getMimeType(fileName);
                if (mimeType == null) {
                    mimeType = "application/octet-stream";
                }

                // set content properties and header attributes for the response
                response.setContentType(mimeType);
                response.setContentLength(fileLength);
                String headerKey = "Content-Disposition";
                String headerValue = String.format("attachment; filename=\"%s\"", fileName);
                response.setHeader(headerKey, headerValue);

                // writes the file to the client
                OutputStream outStream = response.getOutputStream();

                byte[] buffer = new byte[BUFFER_SIZE];
                int bytesRead = -1;

                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    outStream.write(buffer, 0, bytesRead);
                }

                inputStream.close();
                outStream.close();
				request.setAttribute("previousPage",pageFrom);
                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/Controller");//NEEd TO CHANGE SO WEB INF HOLD JSP
				dispatcher.forward(request,response);
            } else {
                // no file found
                response.getWriter().print("File not found for the id: " + uploadId);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            response.getWriter().print("SQL Error: " + ex.getMessage());
        } catch (IOException ex) {
            ex.printStackTrace();
            response.getWriter().print("IO Error: " + ex.getMessage());
        } catch (NamingException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                // closes the database connection
                try {
                    connection.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
}