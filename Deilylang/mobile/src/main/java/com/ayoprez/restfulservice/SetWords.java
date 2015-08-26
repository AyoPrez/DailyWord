package com.ayoprez.restfulservice;

import android.content.Context;
import android.util.Log;

import com.ayoprez.deilylang.R;
import com.ayoprez.deilylang.Utils;
import com.crashlytics.android.Crashlytics;

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

        userAPI.postUserWord(id_u, id_word, languageMobile, languageNew, new Callback<Integer>() {

            @Override
            public void success(Integer bool, Response response) {

                if (bool == 1) {
                    getConfirmedDialog();
                } else {
                    //Crashlitics
                    Crashlytics.getInstance().log("SentWords: Response False");
                    Log.e("DeilyLang", "Error: SetWords = null");
                }
            }

            @Override
            public void failure(RetrofitError error) {
                Log.e("RequestError", "Error: " + error.getMessage());
                //Crashlytics
                Crashlytics.getInstance().log("Error SetWords: " + error);
                EventBus.getDefault().post(error);
            }
        });
    }

    public void getConfirmedDialog(){
        new Utils().Create_Dialog(context, context.getString(R.string.doneDialog), context.getString(R.string.buttonAcceptDialog), context.getString(R.string.doneDialogTitle));
    }
}
