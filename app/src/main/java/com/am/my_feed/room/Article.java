package com.am.my_feed.room;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import com.am.my_feed.article.Source;
import com.google.gson.annotations.SerializedName;

import org.jetbrains.annotations.NotNull;

@Entity
public class Article  {
    @SerializedName("content")
    private String content;
    @PrimaryKey
    @SerializedName("publishedAt")
    @NotNull
    private String publishedAt;
    @SerializedName("author")
    private String author;
    @SerializedName("urlToImage")
    private String urlToImage;
    @SerializedName("title")
    private String title;
    @SerializedName("source")
    @Ignore
    private Source source;
    @SerializedName("description")
    @Ignore
    private String description;
    @SerializedName("url")
    private String url;

    public String getContent ()
    {
        return content;
    }

    public void setContent (String content)
    {
        this.content = content;
    }

    public String getPublishedAt ()
    {
        return publishedAt;
    }

    public void setPublishedAt (String publishedAt)
    {
        this.publishedAt = publishedAt;
    }

    public String getAuthor ()
    {
        return author;
    }

    public void setAuthor (String author)
    {
        this.author = author;
    }

    public String getUrlToImage ()
    {
        return urlToImage;
    }

    public void setUrlToImage (String urlToImage)
    {
        this.urlToImage = urlToImage;
    }

    public String getTitle ()
    {
        return title;
    }

    public void setTitle (String title)
    {
        this.title = title;
    }

    public Source getSource ()
    {
        return source;
    }

    public void setSource (Source source)
    {
        this.source = source;
    }

    public String getDescription ()
    {
        return description;
    }

    public void setDescription (String description)
    {
        this.description = description;
    }

    public String getUrl ()
    {
        return url;
    }

    public void setUrl (String url)
    {
        this.url = url;
    }

    @Override
    public String toString()
    {
        return "Article [content = "+content+", publishedAt = "+publishedAt+",\n author = "+author+", urlToImage = "+urlToImage
                +", title = "+title+", source = "+source+", description = "+description+", url = "+url+"]";
    }

    public Article() {
    }

    public Article(@NotNull String publishedAt, String urlToImage, String title) {
        this.publishedAt = publishedAt;
        this.urlToImage = urlToImage;
        this.title = title;
    }
}
