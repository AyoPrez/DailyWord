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
    public static final String ENDPOINT = "http://deilylang.com/api/index.php/";

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

    public void sendEnglishWordRequest(final String level, final int id_u, final String languageMobile){
        new LaunchNotification(context);

        wordsAPI.getNewEnglishWord(level, id_u, languageMobile, new Callback<WordFromDatabase>() {

            @Override
            public void success(WordFromDatabase word, Response response) {
                if(word != null && word.getWord()[0] != null && word.getId() != 0) {
                    Log.e("DeilyLang", "GetWordsSuccess: " + word.getId());
                    EventBus.getDefault().post(word);
                }else{
                    //Crashlitics
                    Log.e("DeilyLang", "Error: GetWords = null");
                }
            }

            @Override
            public void failure(RetrofitError error) {
                Log.e("RequestError", "Error: " + error.getMessage());
                //Crashlytics
                EventBus.getDefault().post(error);
            }
        });
    }

    public void sendSpanishWordRequest(final String level, final int id_u, final String languageMobile){
        new LaunchNotification(context);

        wordsAPI.getNewSpanishWord(level, id_u, languageMobile, new Callback<WordFromDatabase>() {

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