package com.am.my_feed.network;

import com.am.my_feed.article.ArticleList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiRequests {


    @GET("top-headlines?category=general&apiKey=c130a159f67742cd90c30fc79abebbed")
    Call<ArticleList> getHeadlines();


    @GET("competitions/{id}/stages")
    Call<Void> getRequests(@Path("id") String competitionId,
                          @Query("season") String seasonId,
                          @Header("Signature") String signature,
                          @Header("Token") String Token);


}
