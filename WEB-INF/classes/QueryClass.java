import models.*;
import javax.naming.NamingException;
import javax.sql.*;
import java.io.*;
import java.sql.*;
import java.util.*;
/**
 * Created by kyleb on 2/06/2019.
 */

public class QueryClass {
    public static List<TicketModel> getAllTickets(){
        String query = "SELECT ID, username, categoryID, title, body, dateCreated, statusID FROM Ticket order by dateCreated desc;";
        List<TicketModel> ticketList = new LinkedList<>();
        try(Connection connection = DBConnector.getConnection(); //step 1
            Statement statement = connection.createStatement(); //step 2
            ResultSet result = statement.executeQuery(query);){ //step 3 and 4
            while(result.next()){ //step 5
                TicketModel ticket = new TicketModel();
                // you should be validating the following,
                // this is just an example to get you started
                ticket.setUser(result.getString(1));
                ticket.setTicketID(result.getInt(0));
                ticket.setCategory(result.getString(2));
                ticket.setDateCreated(result.getDate(5));
                ticket.setBody(result.getString(4));
                ticket.setTitle((result.getString(3)));
                ticket.setStatus(result.getString(6));
                ticketList.add(ticket);
            }
            connection.close();
        }
        catch(SQLException e){
            System.err.println(e.getMessage());
            System.err.println(e.getStackTrace());
        } catch (NamingException e) {
            e.printStackTrace();
        }
        return ticketList;
    }

    public static void addComment(int ticketID, String title, String body)
    {
        String query = "INSERT INTO TicketComment (ticketID, title, body, dateCreated) VALUES ("+ticketID+", '"+title+"', '"+body+"', NOW());";
        try(Connection connection = DBConnector.getConnection(); //step 1
            Statement statement = connection.createStatement(); //step 2
            ResultSet result = statement.executeQuery(query);) {
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void addTicket(String username, String title, String body, String category, List<String> filePaths)
    {
        String query = "INSERT INTO Ticket (username, title, body, category, dateCreated) VALUES ("+username+", '"+title+"', '"+body+"', '"+category+"', NOW());";
        try(Connection connection = DBConnector.getConnection(); //step 1
            Statement statement = connection.createStatement(); //step 2
            ResultSet result = statement.executeQuery(query);) {
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        int ticketID;
        query = "SELECT ID from Ticket WHERE username = '"+username+"' and title = '"+title+"' and body = '"+body+"';";
        try(Connection connection = DBConnector.getConnection(); //step 1
            Statement statement = connection.createStatement(); //step 2
            ResultSet result = statement.executeQuery(query);) {
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        for(String s : filePaths)
        {
            addFile();
        }
    }

    public static void addFile(int ticketID, String filePath)
    {
        try {
            Connection connection = DBConnector.getConnection();
            File file = new File(filePath);
            FileInputStream input = new FileInputStream(file);
            String updateSQL = "insert into TicketFile (ticketID, fileName, fileData, isDeleted) values (?, ?, ?, 0)) ";
            String fileName = filePath.substring(filePath.lastIndexOf("\\")+1);
            PreparedStatement pstmt = connection.prepareStatement(updateSQL);
            pstmt.setBinaryStream(2, input);
            pstmt.setInt(0, ticketID);
            pstmt.setString(1, fileName);
            pstmt.executeUpdate();
            connection.close();
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (NamingException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<TicketModel> getAllTicketsByUser(String username){
        String query = "SELECT ID, username, categoryID, title, body, dateCreated, statusID FROM Ticket where username = '"+username+"' order by dateCreated desc;";
        List<TicketModel> ticketList = new LinkedList<>();
        try(Connection connection = DBConnector.getConnection(); //step 1
            Statement statement = connection.createStatement(); //step 2
            ResultSet result = statement.executeQuery(query);){ //step 3 and 4
            while(result.next()){ //step 5
                TicketModel ticket = new TicketModel();
                // you should be validating the following,
                // this is just an example to get you started
                ticket.setUser(result.getString(1));
                ticket.setTicketID(result.getInt(0));
                ticket.setCategory(result.getString(2));
                ticket.setDateCreated(result.getDate(5));
                ticket.setBody(result.getString(4));
                ticket.setTitle((result.getString(3)));
                ticket.setStatus(result.getString(6));
                ticketList.add(ticket);
            }
            connection.close();
        }
        catch(SQLException e){
            System.err.println(e.getMessage());
            System.err.println(e.getStackTrace());
        } catch (NamingException e) {
            e.printStackTrace();
        }
        return ticketList;
    }

    public static List<KnowledgeBaseArticleModel> getAllArticles(){
        String query = "SELECT ID, username, categoryID, title, body, dateCreated FROM KnowledgeBaseArticle order by dateCreated desc;";
        List<KnowledgeBaseArticleModel> articleList = new LinkedList<>();
        try(Connection connection = DBConnector.getConnection(); //step 1
            Statement statement = connection.createStatement(); //step 2
            ResultSet result = statement.executeQuery(query);){ //step 3 and 4
            while(result.next()){ //step 5
                KnowledgeBaseArticleModel article = new KnowledgeBaseArticleModel();
                // you should be validating the following,
                // this is just an example to get you started
                article.setUser(result.getString(1));
                article.setTicketID(result.getInt(0));
                article.setCategory(result.getString(2));
                article.setDateCreated(result.getDate(5));
                article.setBody(result.getString(4));
                article.setTitle((result.getString(3)));
                articleList.add(article);
            }
            connection.close();
        }
        catch(SQLException e){
            System.err.println(e.getMessage());
            System.err.println(e.getStackTrace());
        } catch (NamingException e) {
            e.printStackTrace();
        }
        return articleList;
    }

    public static List<KnowledgeBaseArticleModel> getAllArticlesByCategory(String category){
        String query = "SELECT ID, username, categoryID, title, body, dateCreated FROM KnowledgeBaseArticle where categoryID = '"+category+"' order by dateCreated desc;";
        List<KnowledgeBaseArticleModel> articleList = new LinkedList<>();
        try(Connection connection = DBConnector.getConnection(); //step 1
            Statement statement = connection.createStatement(); //step 2
            ResultSet result = statement.executeQuery(query);){ //step 3 and 4
            while(result.next()){ //step 5
                KnowledgeBaseArticleModel article = new KnowledgeBaseArticleModel();
                // you should be validating the following,
                // this is just an example to get you started
                article.setUser(result.getString(1));
                article.setTicketID(result.getInt(0));
                article.setCategory(result.getString(2));
                article.setDateCreated(result.getDate(5));
                article.setBody(result.getString(4));
                article.setTitle((result.getString(3)));
                articleList.add(article);
            }
            connection.close();
        }
        catch(SQLException e){
            System.err.println(e.getMessage());
            System.err.println(e.getStackTrace());
        } catch (NamingException e) {
            e.printStackTrace();
        }
        return articleList;
    }

    public static List<String> getAllCategories(){
        String query = "SELECT ID FROM Category;";
        List<String> categoryList = new LinkedList<>();
        try(Connection connection = DBConnector.getConnection(); //step 1
            Statement statement = connection.createStatement(); //step 2
            ResultSet result = statement.executeQuery(query);){ //step 3 and 4
            while(result.next()){ //step 5
                String c = result.getString(1);
                // you should be validating the following,
                // this is just an example to get you started
                categoryList.add(c);
            }
            connection.close();
        }
        catch(SQLException e){
            System.err.println(e.getMessage());
            System.err.println(e.getStackTrace());
        } catch (NamingException e) {
            e.printStackTrace();
        }
        return categoryList;
    }

    public static void getFile(int fileID){
        String query = "SELECT ID, fileName, fileData FROM TicketFile where ID = "+fileID+";";
        File f;
        try(Connection connection = DBConnector.getConnection(); //step 1
            Statement statement = connection.createStatement(); //step 2
            ResultSet result = statement.executeQuery(query);){
            f = new File(result.getString(1));
            FileOutputStream output = new FileOutputStream(f);//step 3 and 4
            InputStream input = result.getBinaryStream(2);
            byte[] buffer = new byte[1024];
            while (input.read(buffer) > 0) {
                output.write(buffer);
            }
            connection.close();
        }
        catch(SQLException e){
            System.err.println(e.getMessage());
            System.err.println(e.getStackTrace());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static List<CommentModel> getComments(int ticketID){
        String query = "SELECT ID, username, body, ticketID, dateCreated FROM TicketComment order by dateCreated desc;";
        List<CommentModel> comments = new LinkedList<>();
        try(Connection connection = DBConnector.getConnection(); //step 1
            Statement statement = connection.createStatement(); //step 2
            ResultSet result = statement.executeQuery(query);){ //step 3 and 4
            while(result.next()){ //step 5
                CommentModel comment = new CommentModel();
                // you should be validating the following,
                // this is just an example to get you started
                comment.setUser(result.getString(1));
                comment.setDate(result.getDate(2));
                comment.setBody(result.getString(4));
                comments.add(comment);
            }
            connection.close();
        }
        catch(SQLException e){
            System.err.println(e.getMessage());
            System.err.println(e.getStackTrace());
        } catch (NamingException e) {
            e.printStackTrace();
        }
        return comments;
    }
    public static List<FileModel> getFiles(int ticketID){
        String query = "SELECT ID, fileName FROM TicketFie where isDeleted = 0 and ticketID = "+ticketID+";";
        List<FileModel> files = new LinkedList<>();
        try(Connection connection = DBConnector.getConnection(); //step 1
            Statement statement = connection.createStatement(); //step 2
            ResultSet result = statement.executeQuery(query);){ //step 3 and 4
            while(result.next()){ //step 5
                FileModel file = new FileModel();
                // you should be validating the following,
                // this is just an example to get you started
                file.setFileID(result.getInt(0));
                file.setFileName(result.getString(1));

                files.add(file);
            }
            connection.close();
        }
        catch(SQLException e){
            System.err.println(e.getMessage());
            System.err.println(e.getStackTrace());
        } catch (NamingException e) {
            e.printStackTrace();
        }
        return files;
    }

    public static TicketModel getTicket(int ticketID){
        String query = "SELECT ID, username, categoryID, title, body, dateCreated, statusID FROM Ticket order by dateCreated desc;";
        TicketModel ticket = new TicketModel();
        try(Connection connection = DBConnector.getConnection(); //step 1
            Statement statement = connection.createStatement(); //step 2
            ResultSet result = statement.executeQuery(query);){ //step 3 and 4
            // you should be validating the following,
            // this is just an example to get you started
            ticket.setUser(result.getString(1));
            ticket.setTicketID(result.getInt(0));
            ticket.setCategory(result.getString(2));
            ticket.setDateCreated(result.getDate(5));
            ticket.setBody(result.getString(4));
            ticket.setTitle((result.getString(3)));
            ticket.setStatus(result.getString(6));
            connection.close();
        }
        catch(SQLException e){
            System.err.println(e.getMessage());
            System.err.println(e.getStackTrace());
        } catch (NamingException e) {
            e.printStackTrace();
        }
        ticket.setFiles(getFiles(ticketID));
        ticket.setComments(getComments(ticketID));
        return ticket;
    }
}
