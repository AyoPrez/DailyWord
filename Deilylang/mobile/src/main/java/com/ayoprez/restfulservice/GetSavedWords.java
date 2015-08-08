package com.ayoprez.restfulservice;

import android.content.Context;
import android.util.Log;

import com.ayoprez.notification.LaunchNotification;
import com.ayoprez.savedWords.SavedWords;

import java.util.ArrayList;

import de.greenrobot.event.EventBus;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by AyoPrez on 30/05/15.
 */
public class GetSavedWords {

    public static final String ENDPOINT = "http://deilylang.com/api/index.php/";

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

    public void sendEnglishUserWordsRequest(int id, final String languageMobile){
        new LaunchNotification(context);

        wordsAPI.getSavedEnglishWords(id, languageMobile,new Callback<ArrayList<SavedWords>>() {

            @Override
            public void success(ArrayList<SavedWords> words, Response response) {
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

    public void sendSpanishUserWordsRequest(int id, final String languageMobile){
        new LaunchNotification(context);

        wordsAPI.getSavedSpanishWords(id, languageMobile, new Callback<ArrayList<SavedWords>>() {

            @Override
            public void success(ArrayList<SavedWords> words, Response response) {
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