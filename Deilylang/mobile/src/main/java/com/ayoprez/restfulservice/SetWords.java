package com.ayoprez.restfulservice;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import de.greenrobot.event.EventBus;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by AyoPrez on 9/08/15.
 */
public class SetWords {
    public static final String ENDPOINT = "http://deilylang.com/api/index.php/";

    private UserAPI userAPI;
    private Context context;

    public SetWords(Context context){
        this.context = context;

        RestAdapter restAdapter = new RestAdapter.Builder()
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setEndpoint(ENDPOINT)
                .build();

        userAPI = restAdapter.create(UserAPI.class);
    }

    public void sendUserWord(final int id_u, final int id_word, final String languageMobile, final String languageNew){

        userAPI.postUserWord(id_u, id_word, languageMobile, languageNew, new Callback<SaveWordResult>() {

            @Override
            public void success(SaveWordResult bool, Response response) {

                if (bool.getRes()) {
                    Log.e("DeilyLang", "Show dialog");
                    Toast.makeText(context, "Show Dialog", Toast.LENGTH_LONG).show();
                } else {
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
}
