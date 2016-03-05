package com.ayoprez.restfulservice;

import android.content.Context;
import android.util.Log;

import com.ayoprez.deilylang.ErrorHandler;
import com.ayoprez.deilylang.R;
import com.ayoprez.utils.Utils;
import com.crashlytics.android.Crashlytics;

import de.greenrobot.event.EventBus;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

import java.util.Set;

/**
 * Created by AyoPrez on 9/08/15.
 */
public class SetWords {
    private static final String LOG_TAG = SetWords.class.getSimpleName();
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
                    ErrorHandler.getInstance().informUser(context, context.getString(R.string.errorDefault));
                }
            }

            @Override
            public void failure(RetrofitError error) {
                ErrorHandler.getInstance().Error(LOG_TAG, error.getMessage());
                ErrorHandler.getInstance().informUser(context, context.getString(R.string.errorDefaultAndTry));
                EventBus.getDefault().post(error);
            }
        });
    }

    public void getConfirmedDialog(){
        Utils.getInstance().Create_Dialog(context, context.getString(R.string.doneDialog), context.getString(R.string.buttonAcceptDialog), context.getString(R.string.doneDialogTitle));
    }
}
