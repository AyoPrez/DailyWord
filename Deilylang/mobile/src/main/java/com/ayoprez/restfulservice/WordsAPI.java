package com.ayoprez.restfulservice;

import com.ayoprez.deilylang.WordFromDatabase;
import com.ayoprez.savedWords.SavedWords;

import java.util.ArrayList;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Path;

/**
 * Created by AyoPrez on 12/04/15.
 */
public interface WordsAPI {
    @GET("/English/{Id_U}/{U_Language}/{Level}")
    void getNewEnglishWord(@Path("Level") String Level,
                           @Path("Id_U") int Id_U,
                           @Path("U_Language") String LanguageMobile,
                            Callback<WordFromDatabase> response);

    @GET("/Spanish/{Id_U}/{U_Language}/{Level}")
    void getNewSpanishWord(@Path("Level") String Level,
                    @Path("Id_U") int Id_U,
                    @Path("U_Language") String LanguageMobile,
                    Callback<WordFromDatabase> response);

    @GET("/English/{Id}/{U_Language}")
    void getSavedEnglishWords(@Path("Id") int Id,
                       @Path("U_Language") String LanguageMobile,
                       Callback<ArrayList<SavedWords>> response);

    @GET("/Spanish/{Id}/{U_Language}")
    void getSavedSpanishWords(@Path("Id") int Id,
                       @Path("U_Language") String LanguageMobile,
                       Callback<ArrayList<SavedWords>> response);
}