/* System component model
Seng2050 -  Web Engineering (Assignment 3)
Created by: Kyle Vincent
Modified by : Angus Simmons, Jeff Layton, Kyle Vincent, Sam Williams
Last edit: 9/6/19 */
package models;
/////////////////////////////////LIBRARY IMPORTS///////////////////////
import java.time.LocalDateTime;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
/////////////////////////////USER DEFINED CLASS///////////////////////
public class TicketModel extends KnowledgeBaseArticleModel {
/////////////////////////////////DECLARATIONS///////////////////////////
    String status;
    List<CommentModel> comments;
    public TicketModel() {
        files = new LinkedList<>();
        comments = new LinkedList<>();
    }
    public TicketModel(String user, String category, String title, String body, int userID, int articleID, int ticketID, Date dateCreated, List<FileModel> files, String status, List<CommentModel> comments) {
        super(user, category, title, body, userID, articleID, ticketID, dateCreated, files);
        this.status = status;
        this.comments = comments;
    }
/////////////////////////////////GETTERS///////////////////////////////////
    public String getStatus() {
        return status;
    }
	public List<CommentModel> getComments() {
        return comments;
    }
/////////////////////////////////SETTERS///////////////////////////////////
    public void setStatus(String status) {
        this.status = status;
    }
    public void setComments(List<CommentModel> comments) {
        this.comments = comments;
    }
}