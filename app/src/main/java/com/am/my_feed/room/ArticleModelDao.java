package com.am.my_feed.room;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

@Dao
public interface ArticleModelDao {
    @Query("SELECT * FROM Article")
    LiveData<List<Article>> getAllArticles();

    @Query("SELECT * FROM Article WHERE publishedAt = :publishedAt")
    Article getArticleById(String publishedAt);

    @Insert(onConflict = REPLACE)
    void insertArticle(Article article);

    @Delete
    void deleteArticle(Article article);
}
