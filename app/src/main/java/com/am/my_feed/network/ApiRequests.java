package com.am.my_feed.network;

import com.am.my_feed.article.ArticleList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiRequests {


    @GET("top-headlines?country=in")
    Call<ArticleList> getHeadlines(@Query("country") String country,
                                   @Query("category") String category);


    @GET("everything")
    Call<ArticleList> getSearch(@Query("q") String query,
                                @Query("language") String language);


    @GET("competitions/{id}/stages")
    Call<Void> getRequests(@Path("id") String competitionId,
                           @Query("season") String seasonId,
                           @Header("Signature") String signature,
                           @Header("Token") String Token);


}
