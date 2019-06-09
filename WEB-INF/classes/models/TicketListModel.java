/* System component model
Seng2050 -  Web Engineering (Assignment 3)
Created by: Kyle Vincent
Modified by : Angus Simmons, Jeff Layton, Kyle Vincent, Sam Williams
Last edit: 9/6/19 */
package models;
/////////////////////////////////LIBRARY IMPORTS///////////////////////
import java.util.List;
/////////////////////////////USER DEFINED CLASS///////////////////////   
public class TicketListModel {
/////////////////////////////////DECLARATIONS///////////////////////////
	List<TicketModel> ticketList;
    public TicketListModel(List<TicketModel> ticketList) {
        this.ticketList = ticketList;
    }
    public TicketListModel() {
    }
/////////////////////////////////GETTERS///////////////////////////////////
    public List<TicketModel> getTicketList() {
        return ticketList;
    }
/////////////////////////////////SETTERS///////////////////////////////////
    public void setTicketList(List<TicketModel> ticketList) {
        this.ticketList = ticketList;
    }
}