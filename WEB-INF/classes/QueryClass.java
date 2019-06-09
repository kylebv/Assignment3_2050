import models.*;
import javax.naming.NamingException;
import javax.sql.*;
import java.io.*;
import java.sql.*;
import java.util.*;
import java.util.Date;

/**
 * Created by kyle vincent (3166360) on 2/06/2019.
 * this Query class holds most of the business logic and database insertion/updating of the website for Assignment 3
 */

public class QueryClass {
    //returns all the tickets for displaying as a list
    public static List<TicketModel> getTickets(){
        String query = "SELECT ID, username, categoryID, title, body, dateCreated, statusID FROM Ticket order by dateCreated desc;";
        List<TicketModel> ticketList = new LinkedList<>();
        try(Connection connection = DBConnector.getConnection(); //step 1
            Statement statement = connection.createStatement(); //step 2
            ResultSet result = statement.executeQuery(query);){ //step 3 and 4
            if(result.next()) {
                do { //step 5
                    TicketModel ticket = new TicketModel();
                    // you should be validating the following,
                    // this is just an example to get you started
                    ticket.setUser(result.getString("username"));
                    ticket.setTicketID(result.getInt("ID"));
                    ticket.setCategory(result.getString("categoryID"));
                    ticket.setDateCreated(result.getDate("dateCreated"));
                    ticket.setBody(result.getString("body"));
                    ticket.setTitle((result.getString("title")));
                    ticket.setStatus(result.getString("statusID"));
                    ticketList.add(ticket);
                }
                while (result.next());
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

    //updates a ticket's status
    public static void setTicketStatus(int _ticketID, String _status){
        String query = "UPDATE Ticket SET statusID = '"+_status+"' WHERE ID = "+_ticketID;
        try(Connection connection = DBConnector.getConnection(); //step 1
            Statement statement = connection.createStatement(); //step 2
            ResultSet result = statement.executeQuery(query);){ //step 3 and 4
            connection.close();
        }
        catch(SQLException e){
            System.err.println(e.getMessage());
            System.err.println(e.getStackTrace());
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

    //inserts a new comment into a ticket
    public static void addComment(CommentModel cm)
    {
        String query = "INSERT INTO TicketComment (ticketID, body, dateCreated, username) VALUES ("+cm.getTicketID()+", '"+cm.getBody()+"', NOW(), '"+cm.getUser()+"');";
        try(Connection connection = DBConnector.getConnection(); //step 1
            Statement statement = connection.createStatement(); //step 2
            ResultSet result = statement.executeQuery(query);) {
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //inserts a new ticket
    public static void addTicket(String username, String title, String body, String category, List<String> filePaths)
    {
        String query = "INSERT INTO Ticket (username, title, body, category, dateCreated) VALUES ('"+username+"', '"+title+"', '"+body+"', '"+category+"', NOW());";
        try(Connection connection = DBConnector.getConnection(); //step 1
            Statement statement = connection.createStatement(); //step 2
            ResultSet result = statement.executeQuery(query);) {
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        int ticketID = 0;
        query = "SELECT LAST_INSERT_ID('Ticket') as ID ;";
        try(Connection connection = DBConnector.getConnection(); //step 1
            Statement statement = connection.createStatement(); //step 2
            ResultSet result = statement.executeQuery(query);) {
            ticketID = result.getInt("ID");
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        for(String s : filePaths)
        {
            addFile(ticketID, s);
        }
    }

    //adds a file to the database for a said ticket
    public static void addFile(int ticketID, String filePath)
    {
        try {
            Connection connection = DBConnector.getConnection();
            File file = new File(filePath);
            FileInputStream input = new FileInputStream(file);
            String updateSQL = "insert into TicketFile (ticketID, fileName, fileData, isDeleted) values (?, ?, ?, 0)) ";
            String fileName = filePath.substring(filePath.lastIndexOf("\\")+1);
            PreparedStatement pstmt = connection.prepareStatement(updateSQL);
            pstmt.setBinaryStream(3, input);
            pstmt.setInt(1, ticketID);
            pstmt.setString(2, fileName);
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

    //gets a list of all the tickets by a certain user
    public static List<TicketModel> getTicketsByUser(String username){
        String query = "SELECT ID, username, categoryID, title, body, dateCreated, statusID FROM Ticket where username = '"+username+"' order by dateCreated desc;";
        List<TicketModel> ticketList = new LinkedList<>();
        try(Connection connection = DBConnector.getConnection(); //step 1
            Statement statement = connection.createStatement(); //step 2
            ResultSet result = statement.executeQuery(query);){ //step 3 and 4
            if(result.next()) {
                 do { //step 5
                    TicketModel ticket = new TicketModel();
                    // you should be validating the following,
                    // this is just an example to get you started
                    ticket.setUser(result.getString("username"));
                    ticket.setTicketID(result.getInt("ID"));
                    ticket.setCategory(result.getString("categoryID"));
                    ticket.setDateCreated(result.getDate("dateCreated"));
                    ticket.setBody(result.getString("body"));
                    ticket.setTitle((result.getString("title")));
                    ticket.setStatus(result.getString("statusID"));
                    ticketList.add(ticket);
                }
                while (result.next());
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

    //gets all the articles to display as a list
    public static List<KnowledgeBaseArticleModel> getArticles(){
        String query = "SELECT ID, username, categoryID, title, body, dateCreated FROM KnowledgeBaseArticle order by dateCreated desc;";
        List<KnowledgeBaseArticleModel> articleList = new LinkedList<>();
        try(Connection connection = DBConnector.getConnection(); //step 1
            Statement statement = connection.createStatement(); //step 2
            ResultSet result = statement.executeQuery(query);){ //step 3 and 4
            if(result.next()) {
                do { //step 5
                    KnowledgeBaseArticleModel article = new KnowledgeBaseArticleModel();
                    // you should be validating the following,
                    // this is just an example to get you started
                    article.setUser(result.getString("username"));
                    article.setTicketID(result.getInt("ID"));
                    article.setCategory(result.getString("categoryID"));
                    article.setDateCreated(result.getDate("dateCreated"));
                    article.setBody(result.getString("body"));
                    article.setTitle((result.getString("title")));
                    articleList.add(article);
                }
                while (result.next());
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

    //gets all the articles by category to display as a list
    public static List<KnowledgeBaseArticleModel> getArticlesByCategory(String category){
        String query = "SELECT ID, username, categoryID, title, body, dateCreated FROM KnowledgeBaseArticle where categoryID = '"+category+"' order by dateCreated desc;";
        List<KnowledgeBaseArticleModel> articleList = new LinkedList<>();
        try(Connection connection = DBConnector.getConnection(); //step 1
            Statement statement = connection.createStatement(); //step 2
            ResultSet result = statement.executeQuery(query);){ //step 3 and 4
            if(result.next()) {
                 do { //step 5
                    KnowledgeBaseArticleModel article = new KnowledgeBaseArticleModel();
                    // you should be validating the following,
                    // this is just an example to get you started
                    article.setUser(result.getString("username"));
                    article.setTicketID(result.getInt("ID"));
                    article.setCategory(result.getString("categoryID"));
                    article.setDateCreated(result.getDate("date"));
                    article.setBody(result.getString("body"));
                    article.setTitle(result.getString("title"));
                    articleList.add(article);
                }
                while (result.next());
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

    //returns a list of all of the categories
    public static List<String> getCategories(){
        String query = "SELECT ID FROM Category;";
        List<String> categoryList = new LinkedList<>();
        try(Connection connection = DBConnector.getConnection(); //step 1
            Statement statement = connection.createStatement(); //step 2
            ResultSet result = statement.executeQuery(query);){ //step 3 and 4
            while(result.next()){ //step 5
                String c = result.getString("ID");
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

    //grabs a file out of the database, based on the file id
    public static void getFile(int fileID){
        String query = "SELECT ID, fileName, fileData FROM TicketFile where ID = "+fileID+" and isDeleted = 0;";
        File f;
        try(Connection connection = DBConnector.getConnection(); //step 1
            Statement statement = connection.createStatement(); //step 2
            ResultSet result = statement.executeQuery(query);){
            f = new File(result.getString("fileName"));
            FileOutputStream output = new FileOutputStream(f);//step 3 and 4
            InputStream input = result.getBinaryStream("fileData");
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

    //sets the file as deleted in the db (as a flag)
    public static void deleteFile(int fileID){
        String query = "UPDATE TicketFile SET isDeleted = 1 where ID = "+fileID+";";
        int del = 0;
        try(Connection connection = DBConnector.getConnection(); //step 1
            Statement statement = connection.createStatement(); //step 2
            ResultSet result = statement.executeQuery(query);){
            connection.close();
        }
        catch(SQLException e){
            System.err.println(e.getMessage());
            System.err.println(e.getStackTrace());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //gets all the comments from a ticket, usually a helper method for getting a ticket
    public static List<CommentModel> getComments(int ticketID){
        String query = "SELECT ID, username, body, ticketID, dateCreated FROM TicketComment WHERE ticketID = "+ticketID+" ORDER BY dateCreated ASC;";
        List<CommentModel> comments = new LinkedList<>();
        try(Connection connection = DBConnector.getConnection(); //step 1
            Statement statement = connection.createStatement(); //step 2
            ResultSet result = statement.executeQuery(query);){ //step 3 and 4
            if(result.next()) {
                 do { //step 5
                     CommentModel comment = new CommentModel();
                     // you should be validating the following,
                     // this is just an example to get you started
                     comment.setUser(result.getString("username"));
                     comment.setDate(result.getDate("dateCreated"));
                     comment.setBody(result.getString("body"));
                     comments.add(comment);
                 }
                 while (result.next());
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

    //gets all the files from a ticket, usually a helper method for get ticket
    public static List<FileModel> getFiles(int ticketID){
        String query = "SELECT ID, fileName FROM TicketFie where isDeleted = 0 and ticketID = "+ticketID+";";
        List<FileModel> files = new LinkedList<>();
        try(Connection connection = DBConnector.getConnection(); //step 1
            Statement statement = connection.createStatement(); //step 2
            ResultSet result = statement.executeQuery(query);){ //step 3 and 4
            if(result.next()) {
                do { //step 5
                    FileModel file = new FileModel();
                    // you should be validating the following,
                    // this is just an example to get you started
                    file.setFileID(result.getInt("ID"));
                    file.setFileName(result.getString("fileName"));
                    files.add(file);
                }
                while (result.next());
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

    //returns a ticket and populates all the files and comments for a ticket
    public static TicketModel getTicket(int ticketID){
        String query = "SELECT ID, username, categoryID, title, body, dateCreated, statusID FROM Ticket WHERE ID = "+ticketID+";";
        TicketModel ticket = new TicketModel();
        try(Connection connection = DBConnector.getConnection(); //step 1
            Statement statement = connection.createStatement(); //step 2
            ResultSet result = statement.executeQuery(query);){ //step 3 and 4
            // you should be validating the following,
            // this is just an example to get you started
            ticket.setUser(result.getString("username"));
            ticket.setTicketID(result.getInt("ID"));
            ticket.setCategory(result.getString("categoryID"));
            ticket.setDateCreated(result.getDate("dateCreated"));
            ticket.setBody(result.getString("body"));
            ticket.setTitle((result.getString("title")));
            ticket.setStatus(result.getString("status"));
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

    //returns a ticket and populates all the files and comments for a ticket
    public static KnowledgeBaseArticleModel getArticle(int articleID){
        String query = "SELECT ID, username, categoryID, title, body, dateCreated FROM KnowledgeBaseArticle WHERE ID = "+articleID+";";
        KnowledgeBaseArticleModel article = new KnowledgeBaseArticleModel();
        try(Connection connection = DBConnector.getConnection(); //step 1
            Statement statement = connection.createStatement(); //step 2
            ResultSet result = statement.executeQuery(query);){ //step 3 and 4
            // you should be validating the following,
            // this is just an example to get you started
            article.setUser(result.getString("username"));
            article.setTicketID(result.getInt("ticketID"));
            article.setCategory(result.getString("categoryID"));
            article.setDateCreated(result.getDate("dateCreated"));
            article.setBody(result.getString("body"));
            article.setTitle((result.getString("title")));
            article.setArticleID(result.getInt("ID"));
            connection.close();
        }
        catch(SQLException e){
            System.err.println(e.getMessage());
            System.err.println(e.getStackTrace());
        } catch (NamingException e) {
            e.printStackTrace();
        }
        article.setFiles(getFiles(article.getTicketID()));
        return article;
    }

    public static UserModel getUser(String username, String password){
        String query = "SELECT username, password FROM User WHERE username = '"+username+"' AND password = '"+password+"'";
        UserModel user = new UserModel();
        try(Connection connection = DBConnector.getConnection(); //step 1
            Statement statement = connection.createStatement(); //step 2
            ResultSet result = statement.executeQuery(query);){ //step 3 and 4
            // you should be validating the following,
            // this is just an example to get you started
            user.setPassword(result.getString("password"));
            user.setUsername(result.getString("username"));
            connection.close();
        }
        catch(SQLException e){
            System.err.println(e.getMessage());
            System.err.println(e.getStackTrace());
        } catch (NamingException e) {
            e.printStackTrace();
        }
        query = "SELECT username, roleID FROM UserRole WHERE username = '"+username+"'";
        try(Connection connection = DBConnector.getConnection(); //step 1
            Statement statement = connection.createStatement(); //step 2
            ResultSet result = statement.executeQuery(query);){ //step 3 and 4
            // you should be validating the following,
            // this is just an example to get you started
            user.setRole(result.getString("roleID"));
            connection.close();
        }
        catch(SQLException e){
            System.err.println(e.getMessage());
            System.err.println(e.getStackTrace());
        } catch (NamingException e) {
            e.printStackTrace();
        }
        return user;
    }
    public static UserModel getUser(String username){
        String query = "SELECT username, password FROM User WHERE username = '"+username+"';";
        UserModel user = new UserModel();
        try(Connection connection = DBConnector.getConnection(); //step 1
            Statement statement = connection.createStatement(); //step 2
            ResultSet result = statement.executeQuery(query);){ //step 3 and 4
            // you should be validating the following,
            // this is just an example to get you started
            user.setPassword(result.getString("password"));
            user.setUsername(result.getString("username"));
            connection.close();
        }
        catch(SQLException e){
            System.err.println(e.getMessage());
            System.err.println(e.getStackTrace());
        } catch (NamingException e) {
            e.printStackTrace();
        }
        query = "SELECT username, roleID FROM UserRole WHERE username = '"+username+"';";
        try(Connection connection = DBConnector.getConnection(); //step 1
            Statement statement = connection.createStatement(); //step 2
            ResultSet result = statement.executeQuery(query);){ //step 3 and 4
            // you should be validating the following,
            // this is just an example to get you started
            user.setRole(result.getString("roleID"));
            connection.close();
        }
        catch(SQLException e){
            System.err.println(e.getMessage());
            System.err.println(e.getStackTrace());
        } catch (NamingException e) {
            e.printStackTrace();
        }
        return user;
    }

    //creates an article by adding all the comments to the bottom of the ticket body
    //separated by a break line, and showing the user for each comment
    public static void createArticle(int ticketID){
        TicketModel ticket = getTicket(ticketID);
        KnowledgeBaseArticleModel article = new KnowledgeBaseArticleModel();
        article.setCategory(ticket.getCategory());
        article.setDateCreated(new Date());
        article.setTitle(ticket.getTitle());
        article.setTicketID(ticket.getTicketID());
        for (CommentModel c : ticket.getComments())
        {
            article.setBody(article.getBody()+"<br>"+c.getUser()+"<br>"+c.getBody());
        }
        String query = "INSERT INTO KnowledgeBaseArticle (username, ticketID, categoryID, title, body, dateCreated) " +
                "VALUES ('"+article.getUser()+"', "+article.getTicketID()+", '"+article.getCategory()+"', '"+article.getTitle()+"', '"+article.getBody()+"', NOW())";
        try(Connection connection = DBConnector.getConnection(); //step 1
            Statement statement = connection.createStatement(); //step 2
            ResultSet result = statement.executeQuery(query);){ //step 3 and 4
            connection.close();
        }
        catch(SQLException e){
            System.err.println(e.getMessage());
            System.err.println(e.getStackTrace());
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }
}
