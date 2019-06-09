/* System component model
Seng2050 -  Web Engineering (Assignment 3)
Created by: Kyle Vincent
Modified by : Angus Simmons, Jeff Layton, Kyle Vincent, Sam Williams
Last edit: 9/6/19 */
package models;
/////////////////////////////////LIBRARY IMPORTS///////////////////////
import java.time.LocalDateTime;
import java.util.Date;
/////////////////////////////USER DEFINED CLASS///////////////////////
public class CommentModel {
/////////////////////////////////DECLARATIONS///////////////////////////
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
/////////////////////////////////GETTERS///////////////////////////////////
    public int getTicketID() {
        return ticketID;
    }
    public String getUser() {
        return user;
    }
    public String getBody() {
        return body;
    }
    public Date getDate() {
        return date;
    }
/////////////////////////////////SETTERS///////////////////////////////////	
    public void setDate(Date date) {
        this.date = date;
    }
	public void setTicketID(int ticketID) {
        this.ticketID = ticketID;
    }
	public void setUser(String user) {
        this.user = user;
    }
	   public void setBody(String body) {
        this.body = body;
    }
}