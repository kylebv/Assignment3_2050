package models;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by kyleb on 2/06/2019.
 */
public class KnowledgeBaseArticleModel {
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

    public int getTicketID() {
        return ticketID;
    }

    public void setTicketID(int ticketID) {
        this.ticketID = ticketID;
    }

    public KnowledgeBaseArticleModel() {
        files = new LinkedList<>();

    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getArticleID() {
        return articleID;
    }

    public void setArticleID(int articleID) {
        this.articleID = articleID;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public List<FileModel> getFiles() {
        return files;
    }

    public void setFiles(List<FileModel> files) {
        this.files = files;
    }
}
