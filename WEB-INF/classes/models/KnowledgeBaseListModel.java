/* System component model
Seng2050 -  Web Engineering (Assignment 3)
Created by: Kyle Vincent
Modified by : Angus Simmons, Jeff Layton, Kyle Vincent, Sam Williams
Last edit: 9/6/19 */
package models;
/////////////////////////////////LIBRARY IMPORTS///////////////////////
import java.util.List;
/////////////////////////////USER DEFINED CLASS///////////////////////   
public class KnowledgeBaseListModel {
/////////////////////////////////DECLARATIONS///////////////////////////
    List<KnowledgeBaseArticleModel> articleList;
    public KnowledgeBaseListModel(List<KnowledgeBaseArticleModel> articleList) {
        this.articleList = articleList;
    }
    public KnowledgeBaseListModel() {
    }
/////////////////////////////////GETTERS///////////////////////////////////
    public List<KnowledgeBaseArticleModel> getArticleList() {
        return articleList;
    }
/////////////////////////////////SETTERS///////////////////////////////////
    public void setArticleList(List<KnowledgeBaseArticleModel> articleList) {
        this.articleList = articleList;
    }
}