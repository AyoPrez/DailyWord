package com.ayoprez.restfulservice;

import android.content.Context;
import android.util.Log;

import com.ayoprez.login.LoginActivity;
import com.ayoprez.login.User;

import de.greenrobot.event.EventBus;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by AyoPrez on 8/08/15.
 */
public class GetUser {
    public static final String ENDPOINT = "http://deilyquote.ayoprez.com/api/index.php/";

    private UserAPI userAPI;
    private Context context;

    public GetUser(Context context){
        this.context = context;

        RestAdapter restAdapter = new RestAdapter.Builder()
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setEndpoint(ENDPOINT)
                .build();

        userAPI = restAdapter.create(UserAPI.class);
    }

    public void sendUserDataRequest(final String social_id, final String type_id, final String userName){

        userAPI.getUserLogin(social_id, type_id, new Callback<User>() {

            @Override
            public void success(User user, Response response) {
                if (user != null && user.getId_U() != 0) {

                    ((LoginActivity)context).startSession(context, new User(userName, user.getId_U()));

                } else {
                    //Crashlitics
                    Log.e("DeilyLang", "Error: GetUser = null");
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