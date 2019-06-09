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
public class KnowledgeBaseArticleModel {
/////////////////////////////////DECLARATIONS///////////////////////////
    String user, category, title, body;
    int userID, articleID, ticketID;
    Date dateCreated;
    List<FileModel> files;
    public KnowledgeBaseArticleModel(String user, String category, String title, String body, int userID, int articleID, int ticketID, Date dateCreated, List<FileModel> files) {
        this.user = user;
        this.category = category;
        this.title = title;
        this.body = body;
        this.userID = userID;
        this.articleID = articleID;
        this.ticketID = ticketID;
        this.dateCreated = dateCreated;
        this.files = files;
    }
	 public KnowledgeBaseArticleModel() {
        files = new LinkedList<>();
    }
/////////////////////////////////GETTERS///////////////////////////////////
    public int getTicketID() {
        return ticketID;
    }
    public String getUser() {
        return user;
    }
    public String getCategory() {
        return category;
    }
    public String getTitle() {
        return title;
    }
    public String getBody() {
        return body;
    }
    public int getUserID() {
        return userID;
    }
    public int getArticleID() {
        return articleID;
    }
    public Date getDateCreated() {
        return dateCreated;
    }
    public List<FileModel> getFiles() {
        return files;
    }
/////////////////////////////////SETTERS///////////////////////////////////	
	public void setFiles(List<FileModel> files) {
        this.files = files;
    }
	public void setTicketID(int ticketID) {
        this.ticketID = ticketID;
    }
	 public void setUser(String user) {
        this.user = user;
    }
	  public void setCategory(String category) {
        this.category = category;
    }
	 public void setTitle(String title) {
        this.title = title;
    }
	public void setBody(String body) {
        this.body = body;
    }
	 public void setUserID(int userID) {
        this.userID = userID;
    }
	public void setArticleID(int articleID) {
        this.articleID = articleID;
    }
	public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }
}