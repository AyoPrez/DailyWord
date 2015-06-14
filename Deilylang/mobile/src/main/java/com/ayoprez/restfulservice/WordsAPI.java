package com.ayoprez.restfulservice;

import com.ayoprez.deilylang.WordFromDatabase;
import com.ayoprez.savedWords.SavedWords;

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

    @GET("/English/{Id}/{Level}")
    void getSavedWords(@Path("Id") int Id, @Path("Level") String Level, Callback<SavedWords> response);
}