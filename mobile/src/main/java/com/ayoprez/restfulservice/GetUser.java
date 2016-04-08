package com.ayoprez.restfulservice;

import android.content.Context;
import android.util.Log;

import com.ayoprez.deilylang.ErrorHandler;
import com.ayoprez.deilylang.MainActivity;
import com.ayoprez.deilylang.MainApplication;
import com.ayoprez.deilylang.R;
import com.ayoprez.login.LoginActivity;
import com.ayoprez.login.User;
import com.crashlytics.android.Crashlytics;

import javax.inject.Inject;

import de.greenrobot.event.EventBus;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by AyoPrez on 8/08/15.
 */
public class GetUser {
    private static final String LOG_TAG = GetUser.class.getSimpleName();
    public static final String ENDPOINT = "http://deilylang.com/api/index.php/";

    private UserAPI userAPI;
    private Context context;
//    @Inject private RestAdapter restAdapter;


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
                    ErrorHandler.getInstance().Error(LOG_TAG, "");
                    ErrorHandler.getInstance().informUser(context, context.getString(R.string.errorDefault));
                }
            }

            @Override
            public void failure(RetrofitError error) {
                ErrorHandler.getInstance().Error(LOG_TAG, error.toString());
                ErrorHandler.getInstance().informUser(context, context.getString(R.string.errorDefault));
                EventBus.getDefault().post(error);
            }
        });
    }
}