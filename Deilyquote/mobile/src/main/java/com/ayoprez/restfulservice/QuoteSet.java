package com.ayoprez.restfulservice;

import android.content.Context;
import android.util.Log;

import com.ayoprez.deilyquote.R;
import com.ayoprez.deilyquote.Utils;

import de.greenrobot.event.EventBus;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by AyoPrez on 19/08/15.
 */
public class QuoteSet {
    public static final String ENDPOINT = "http://deilyquote.ayoprez.com/api/index.php/";

    private UserAPI userAPI;
    private Context context;

    public QuoteSet(Context context){
        this.context = context;

        RestAdapter restAdapter = new RestAdapter.Builder()
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setEndpoint(ENDPOINT)
                .build();

        userAPI = restAdapter.create(UserAPI.class);
    }

    public void sendUserQuote(final int id_u, final int id_word){

        userAPI.postUserQuote(id_u, id_word, new Callback<Integer>() {

            @Override
            public void success(Integer bool, Response response) {

                if (bool == 1) {
                    getConfirmedDialog();
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

    private void getConfirmedDialog(){
        new Utils().Create_Dialog(context,
                context.getString(R.string.doneDialog),
                context.getString(R.string.buttonAcceptDialog),
                context.getString(R.string.doneDialogTitle));
    }
}