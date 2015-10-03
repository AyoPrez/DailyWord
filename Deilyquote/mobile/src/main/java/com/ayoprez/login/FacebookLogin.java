package com.ayoprez.login;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;

import com.ayoprez.deilyquote.R;
import com.ayoprez.restfulservice.GetUser;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.List;

/**
 * Created by AyoPrez on 25/07/15.
 */
public class FacebookLogin {

    private String TYPE_ID = "f";
    private LoginButton facebookLoginButton;
    private Context context;
    public CallbackManager callbackManager;
    private User user;

    public FacebookLogin(Context context){
        this.context = context;
    }

    public void initFacebook(){
        FacebookSdk.sdkInitialize(context);
    }

    private LoginActivity getLoginActivity(){
        return ((LoginActivity)context);
    }

    public void logoutFacebook(){
        LoginManager.getInstance().logOut();
    }

    public void changeLoginButtonStyle(LoginButton facebookButton){
        float fbIconScale = 1.45F;

        Drawable drawable = getLoginActivity().getResources().getDrawable(com.facebook.R.drawable.com_facebook_button_icon);
        drawable.setBounds(0, 0, (int) (drawable.getIntrinsicWidth() * fbIconScale), (int) (drawable.getIntrinsicHeight() * fbIconScale));

        //Add the icon in the desired position
        facebookButton.setCompoundDrawables(null, null, null, null);
        facebookButton.setCompoundDrawablePadding(0);
        facebookButton.setPadding(10, 30, 10, 30);
    }

    public void facebookLogin(){
        callbackManager = CallbackManager.Factory.create();
        facebookLoginButton = (LoginButton) getLoginActivity().findViewById(R.id.login_button);
        changeLoginButtonStyle(facebookLoginButton);
        final List<String> permissionNeeds = Arrays.asList("email", "public_profile");
        facebookLoginButton.setReadPermissions(permissionNeeds);

        facebookLoginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                getUserData();
            }

            @Override
            public void onCancel() {
                // App code
                //Toast.makeText(context, "Come off Facebook! ", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onError(FacebookException exception) {
                // App code
                //Toast.makeText(context, "Come error Facebook! ", Toast.LENGTH_LONG).show();
            }
        });
    }

    public void getUserData(){
        GraphRequest request = GraphRequest.newMeRequest(AccessToken.getCurrentAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
            @Override
            public void onCompleted(JSONObject object, GraphResponse response) {
                // Application code
                Log.v("LoginActivity", response.toString());
                try {
                    //and fill them here like so.
                    user = new User(object.getString("name"), object.getString("id"));

                    new GetUser(context).sendUserDataRequest(user.getSocial_Id(), TYPE_ID, user.getName());

                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.v("DeilyLang", "UserDataError: " + e.toString());
                }
            }
        });

        request.executeAsync();
    }
}
