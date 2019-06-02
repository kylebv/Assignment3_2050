package models;

import java.time.LocalDateTime;

/**
 * Created by kyleb on 2/06/2019.
 */
public class CommentModel {
    String user, body;
    int userID;
    LocalDateTime date;

    public CommentModel() {
    }

    public CommentModel(String user, String body, int userID, LocalDateTime date) {
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

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }
}
