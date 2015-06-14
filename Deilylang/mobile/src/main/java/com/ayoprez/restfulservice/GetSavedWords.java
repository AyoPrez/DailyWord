package com.ayoprez.restfulservice;

import android.content.Context;
import android.util.Log;

import com.ayoprez.notification.LaunchNotification;
import com.ayoprez.savedWords.SavedWords;

import de.greenrobot.event.EventBus;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by AyoPrez on 30/05/15.
 */
public class GetSavedWords {

    public static final String ENDPOINT = "http://deilylang.com/index.php/";
//    public static final String ENDPOINT = "http://localhost/deilylang-temp/index.php";

    private WordsAPI wordsAPI;
    private Context context;

    public GetSavedWords(Context context){
        this.context = context;

        RestAdapter restAdapter = new RestAdapter.Builder()
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setEndpoint(ENDPOINT)
                .build();

        wordsAPI = restAdapter.create(WordsAPI.class);
    }

    public void sendWordRequest(int id, final String level){
        new LaunchNotification(context);

        wordsAPI.getSavedWords(id, level, new Callback<SavedWords>() {

            @Override
            public void success(SavedWords words, Response response) {
                EventBus.getDefault().post(words);
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