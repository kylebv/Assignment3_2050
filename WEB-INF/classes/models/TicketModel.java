package models;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

/**
 * Created by kyleb on 2/06/2019.
 */
public class TicketModel extends KnowledgeBaseArticleModel {
    String status;
    List<CommentModel> comment;

    public TicketModel() {
    }

    public TicketModel(String user, String category, String title, String body, int userID, int ticketID, Date dateCreated, List<FileModel> files, String status, List<CommentModel> comment) {
        super(user, category, title, body, userID, ticketID, dateCreated, files);
        this.status = status;
        this.comment = comment;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<CommentModel> getComments() {
        return comment;
    }

    public void setComments(List<CommentModel> comment) {
        this.comment = comment;
    }
}
