package com.ayoprez.login;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.facebook.AccessToken;
import com.facebook.FacebookSdk;
import com.twitter.sdk.android.Twitter;

import java.util.HashMap;

/**
 * Created by AyoPrez on 25/05/15.
 */
public class SessionManager {

    private SharedPreferences pref;
    private SharedPreferences.Editor editor;
    private Context context;

    private int PRIVATE_MODE = 0;

    private static final String PREF_NAME = "Pref";
    private static final String IS_LOGIN = "IsLoggedIn";
    public static final String KEY_NAME = "name";
    public static final String KEY_ID = "id";

    public SessionManager(Context context){
        this.context = context;
        pref = context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
        if(!FacebookSdk.isInitialized()){
            FacebookSdk.sdkInitialize(context);
        }
    }

    public void createLoginSession(String name, String id){
        editor.putBoolean(IS_LOGIN, true);
        editor.putString(KEY_ID, id);
        editor.putString(KEY_NAME, name);
        editor.commit();
    }

    public HashMap<String, String> getUserDetails(){
        HashMap<String, String> user = new HashMap<String, String>();

        user.put(KEY_NAME, pref.getString(KEY_NAME, pref.getString(KEY_NAME, null)));
        user.put(KEY_ID, pref.getString(KEY_ID, pref.getString(KEY_ID, null)));

        return user;
    }

    public boolean isLoggedIn(){
        return pref.getBoolean(IS_LOGIN, false);
    }

    public void checkLogin(){
        if(!this.isLoggedIn()){
            logoutUser();
        }
    }

    public void logoutUser(){
        editor.clear();
        editor.commit();

        //Logout Facebook
        if(AccessToken.getCurrentAccessToken() != null){
            new FacebookLogin(context).logoutFacebook();
        }

        //Logout Twitter
        if(Twitter.getSessionManager().getActiveSession() != null){
            new TwitterLogin(context).logoutTwitter();
        }

        Intent i = new Intent(context, LoginActivity.class);

        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        context.startActivity(i);
    }

}