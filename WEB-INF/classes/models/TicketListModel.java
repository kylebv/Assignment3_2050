package models;

import java.util.List;

/**
 * Created by kyleb on 2/06/2019.
 */
public class TicketListModel {
    List<TicketModel> ticketList;

    public TicketListModel(List<TicketModel> ticketList) {
        this.ticketList = ticketList;
    }

    public TicketListModel() {
    }

    public List<TicketModel> getTicketList() {
        return ticketList;
    }

    public void setTicketList(List<TicketModel> ticketList) {
        this.ticketList = ticketList;
    }
}
