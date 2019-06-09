package models;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * Created by kyleb on 2/06/2019.
 */
public class CommentModel {
    String user, body;
    int ticketID;
    Date date;

    public CommentModel() {
    }

    public CommentModel(String user, String body, int ticketID, Date date) {
        this.user = user;
        this.body = body;
        this.ticketID = ticketID;
        this.date = date;
    }

    public int getTicketID() {
        return ticketID;
    }

    public void setTicketID(int ticketID) {
        this.ticketID = ticketID;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
