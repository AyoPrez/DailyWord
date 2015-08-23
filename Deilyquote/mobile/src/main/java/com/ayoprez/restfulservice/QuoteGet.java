package com.ayoprez.restfulservice;

import android.content.Context;
import android.util.Log;

import com.ayoprez.notification.LaunchNotification;
import com.ayoprez.savedQuotes.SavedQuotes;

import java.util.ArrayList;

import de.greenrobot.event.EventBus;
import deilyquote.UserQuotes;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by AyoPrez on 12/04/15.
 */
public class QuoteGet {

    public static final String ENDPOINT = "http://deilyquote.ayoprez.com/api/index.php/";

    private QuoteAPI quoteAPI;

    public QuoteGet(){
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setEndpoint(ENDPOINT)
                .build();

        quoteAPI = restAdapter.create(QuoteAPI.class);
    }

    public void getQuoteWithPersonality(Context context, final String personality, final String language, final int id_u){
        new LaunchNotification(context);

        quoteAPI.getQuoteWithPersonality(personality, language, id_u, new Callback<UserQuotes>() {

            @Override
            public void success(UserQuotes quote, Response response) {
                EventBus.getDefault().post(quote);
            }

            @Override
            public void failure(RetrofitError error) {
                Log.e("RequestError", "Error: " + error.getMessage());
                //Crashlytics
            }
        });
    }

    public void getQuoteWithoutPersonality(Context context, final String language, final int id_u){
        new LaunchNotification(context);

        quoteAPI.getQuoteWithoutPersonality(language, id_u, new Callback<UserQuotes>() {

            @Override
            public void success(UserQuotes quote, Response response) {
                EventBus.getDefault().post(quote);
            }

            @Override
            public void failure(RetrofitError error) {
                Log.e("RequestError", "Error: " + error.getMessage());
                //Crashlytics
            }
        });
    }

    public void getUserQuotes(int id_u){
        quoteAPI.getUserQuotes(id_u, new Callback<ArrayList<SavedQuotes>>() {
            @Override
            public void success(ArrayList<SavedQuotes> savedQuotes, Response response) {
                EventBus.getDefault().post(savedQuotes);
            }

            @Override
            public void failure(RetrofitError error) {
                Log.e("RequestError", "Error SavedQuotes: " + error.getMessage());
                //Crashlytics
            }
        });
    }
}