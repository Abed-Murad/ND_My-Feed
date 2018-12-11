package com.am.my_feed.article;

import com.am.my_feed.room.Article;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ArticleList {
    @SerializedName("articles")
    private List<Article> articleList;
    @SerializedName("totalResults")
    private String totalResults;
    @SerializedName("status")
    private String status;

    public List<Article> getArticles() {
        return articleList;
    }

    public void setArticles(List<Article> articles) {
        this.articleList = articles;
    }

    public String getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(String totalResults) {
        this.totalResults = totalResults;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "ArticleList [articles = " + articleList + ", totalResults = " + totalResults + ", status = " + status + "]";
    }
}
