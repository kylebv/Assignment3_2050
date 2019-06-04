package models;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * Created by kyleb on 2/06/2019.
 */
public class CommentModel {
    String user, body;
    int userID;
    Date date;

    public CommentModel() {
    }

    public CommentModel(String user, String body, int userID, Date date) {
        this.user = user;
        this.body = body;
        this.userID = userID;
        this.date = date;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
