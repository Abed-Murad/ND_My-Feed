package com.am.my_feed.room;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.am.my_feed.article.Article;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

@Dao
public interface NoteModelDao {
    @Query("SELECT * FROM Article")
    LiveData<List<Article>> getAllNotes();

    @Query("SELECT * FROM Article WHERE publishedAt = :noteId")
    Article getNoteById(String noteId);

    @Insert(onConflict = REPLACE)
    void insertNote(Article note);

    @Delete
    void deleteNote(Article note);
}
