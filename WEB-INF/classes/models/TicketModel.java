package models;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by kyleb on 2/06/2019.
 */
public class TicketModel extends KnowledgeBaseArticleModel {
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<CommentModel> getComments() {
        return comments;
    }

    public void setComments(List<CommentModel> comments) {
        this.comments = comments;
    }
}
