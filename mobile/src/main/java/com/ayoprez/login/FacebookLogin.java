package com.ayoprez.login;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.widget.Toast;

import com.ayoprez.deilylang.ErrorHandler;
import com.ayoprez.deilylang.R;
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
    private static final String LOG_TAG = FacebookLogin.class.getSimpleName();

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
                ErrorHandler.getInstance().informUser(context, context.getString(R.string.errorDefault));
            }

            @Override
            public void onError(FacebookException exception) {
                ErrorHandler.getInstance().Error(LOG_TAG, exception.toString());
                ErrorHandler.getInstance().informUser(context, context.getString(R.string.errorDefault));
            }
        });
    }

    public void getUserData(){
        GraphRequest request = GraphRequest.newMeRequest(AccessToken.getCurrentAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
            @Override
            public void onCompleted(JSONObject object, GraphResponse response) {
                try {
                    //and fill them here like so.
                    user = new User(object.getString("name"), object.getString("id"));

                    new GetUser(context).sendUserDataRequest(user.getSocial_Id(), TYPE_ID, user.getName());

                } catch (JSONException e) {
                    ErrorHandler.getInstance().Error(LOG_TAG, e.toString());
                    ErrorHandler.getInstance().informUser(context, context.getString(R.string.errorDefault));
                }
            }
        });

        request.executeAsync();
    }

}