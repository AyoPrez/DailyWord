package com.ayoprez.restfulservice;

import com.ayoprez.savedQuotes.SavedQuotes;

import java.util.ArrayList;

import deilyquote.UserQuotes;
import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Path;

/**
 * Created by AyoPrez on 12/04/15.
 */
public interface QuoteAPI {
    @GET("/Quotes/{personality}/{language}/{id_u}")
    void getQuoteWithPersonality(@Path("personality") String Personality,
                                 @Path("language") String Language,
                                 @Path("id_u") int Id_U,
                                 Callback<UserQuotes> response);

    @GET("/Quotes/{language}/{id_u}")
    void getQuoteWithoutPersonality(@Path("language") String Language,
                                    @Path("id_u") int Id_U,
                                    Callback<UserQuotes> response);

    @GET("/Quotes/{id_u}")
    void getUserQuotes(@Path("id_u") int Id_U,
                       Callback<ArrayList<SavedQuotes>> response);

}