package models;

import java.util.List;

/**
 * Created by kyleb on 2/06/2019.
 */
public class KnowledgeBaseListModel {
    List<KnowledgeBaseArticleModel> articleList;

    public KnowledgeBaseListModel(List<KnowledgeBaseArticleModel> articleList) {
        this.articleList = articleList;
    }

    public KnowledgeBaseListModel() {
    }

    public List<KnowledgeBaseArticleModel> getArticleList() {
        return articleList;
    }

    public void setArticleList(List<KnowledgeBaseArticleModel> articleList) {
        this.articleList = articleList;
    }
}
