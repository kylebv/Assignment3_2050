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

@WebServlet("/uploadController")
@MultipartConfig(maxFileSize = 16177215)    // upload file's size up to 16MB
public class uploadController extends HttpServlet {
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {

        // obtains the upload file part in this multipart request
        Part filePart1 = request.getPart("file1");
        Part filePart2 = request.getPart("file2");
        Part filePart3 = request.getPart("file3");

        // forwards to the message page
        response.sendRedirect("/testingFileInputandOutput.jsp?namey=");

    }
}