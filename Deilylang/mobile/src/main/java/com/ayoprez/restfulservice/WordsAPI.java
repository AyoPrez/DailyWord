package com.ayoprez.restfulservice;

import com.ayoprez.deilylang.WordFromDatabase;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Path;

/**
 * Created by AyoPrez on 12/04/15.
 */
public interface WordsAPI {
    @GET("/English/{Level}/{Language1}/{Language2}")
    void getWords(@Path("Level") String Level,
                  @Path("Language1") String LanguageMobile,
                  @Path("Language2") String LanguageNew ,
                  Callback<WordFromDatabase> response);
}