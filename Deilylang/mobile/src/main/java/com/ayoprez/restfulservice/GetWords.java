package com.ayoprez.restfulservice;

import android.content.Context;
import android.util.Log;

import com.ayoprez.deilylang.WordFromDatabase;
import com.ayoprez.notification.LaunchNotification;

import de.greenrobot.event.EventBus;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by AyoPrez on 12/04/15.
 */
public class GetWords {
    public static final String ENDPOINT = "http://deilylang.com/index.php/";
//    public static final String ENDPOINT = "http://localhost/deilylang-temp/index.php";

    private WordsAPI wordsAPI;
    private Context context;

    public GetWords(Context context){
        this.context = context;

        RestAdapter restAdapter = new RestAdapter.Builder()
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setEndpoint(ENDPOINT)
                .build();

        wordsAPI = restAdapter.create(WordsAPI.class);
    }

    public void sendWordRequest(final String level, final String languageMobile, final String languageNew){
        new LaunchNotification(context);

        wordsAPI.getWords(level, languageMobile, languageNew, new Callback<WordFromDatabase>() {

            @Override
            public void success(WordFromDatabase word, Response response) {
                EventBus.getDefault().post(word);
            }

            @Override
            public void failure(RetrofitError error) {
                Log.e("RequestError", "Error: " + error.getMessage());
                //Crashlytics
                EventBus.getDefault().post(error);
            }
        });
    }
}