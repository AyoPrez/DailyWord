package com.ayoprez.login;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.ayoprez.deilylang.MainActivity;
import com.ayoprez.deilylang.R;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.identity.TwitterAuthClient;
import com.twitter.sdk.android.core.identity.TwitterLoginButton;

import java.util.Arrays;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.OnClick;
import io.fabric.sdk.android.Fabric;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

/**
 * Created by AyoPrez on 22/05/15.
 */
public class LoginActivity extends Activity {

    private Context context;
    private TwitterLoginButton twitterLoginButton;
    private LoginButton facebookLoginButton;
    private CallbackManager callbackManager;
    private SignInButton googleLoginButton;
    private GoogleApiClient mGoogleApiClient;
    private static final int RC_SIGN_IN = 0;
    private boolean signedInUser;

    @OnClick(R.id.login_continue)
    void loginContinue(){
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TwitterAuthConfig authConfig = new TwitterAuthConfig(getString(R.string.t_key), getString(R.string.t_secret));
        Fabric.with(this, new Twitter(authConfig));
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_login);
        ButterKnife.inject(this);

        this.context = this;

        facebookLogin();
        twitterLogin();
//        googleLogin();
    }


//    @Override
//    protected void onStop() {
//        super.onStop();
//
//        if (mGoogleApiClient.isConnected()) {
//            mGoogleApiClient.disconnect();
//        }
//    }

    private void logoutTwitter(){
        Twitter.getSessionManager().clearActiveSession();
        Twitter.logOut();
    }

    User user;

    private void askForTwitterMail(final TwitterSession session){
        TwitterAuthClient authClient = new TwitterAuthClient();
        authClient.requestEmail(session, new Callback() {
            @Override
            public void success(Result result) {
                user = new User(session.getUserName(), result.data.toString());
            }

            @Override
            public void failure(TwitterException exception) {
                // Do something on failure
            }
        });
    }

    private void twitterLogin(){
        twitterLoginButton = (TwitterLoginButton) findViewById(R.id.twitter_login_button);

        twitterLoginButton.setCallback(new Callback<TwitterSession>() {
            @Override
            public void success(Result<TwitterSession> result) {

                askForTwitterMail(result.data);

                startSession(context, user);

                //Toast.makeText(context, "Come on Twitter! " + result.data.getUserName(), Toast.LENGTH_LONG).show();
            }

            @Override
            public void failure(TwitterException exception) {
                Toast.makeText(context, "Come off Twitter! " + exception.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }



    private void facebookLogin(){
        callbackManager = CallbackManager.Factory.create();

        facebookLoginButton = (LoginButton) findViewById(R.id.login_button);
        final List<String> permissionNeeds = Arrays.asList("email", "public_profile");
        facebookLoginButton.setReadPermissions(permissionNeeds);

        facebookLoginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                // App code
                Toast.makeText(context, "Come on Facebook! " + loginResult.getAccessToken().getUserId(), Toast.LENGTH_LONG).show();
            }

            @Override
            public void onCancel() {
                // App code
                Toast.makeText(context, "Come off Facebook! ", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onError(FacebookException exception) {
                // App code
                Toast.makeText(context, "Come error Facebook! ", Toast.LENGTH_LONG).show();
            }
        });
    }


//    ///////////////////////////////////// Google Login ///////////////////////////////////////////
//    private void googleLogin(){
//        mGoogleApiClient.connect();
//
//        mGoogleApiClient = new GoogleApiClient.Builder(this)
//                .addConnectionCallbacks(this)
//                .addOnConnectionFailedListener(this)
//                .addApi(Plus.API, Plus.PlusOptions.builder().build())
//                .addScope(Plus.SCOPE_PLUS_LOGIN)
//                .build();
////
////        googleLoginButton = (SignInButton) findViewById(R.id.sign_in_button);
////
////        googleLoginButton.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View v) {
////                googlePlusLogin();
////            }
////        });
//    }
//
//    @Override
//    public void onConnected(Bundle bundle) {
//        signedInUser = false;
//        Toast.makeText(this, "Connected", Toast.LENGTH_LONG).show();
//        getProfileInformation();
//    }
//
//    private void getProfileInformation() {
//        try {
//            if (Plus.PeopleApi.getCurrentPerson(mGoogleApiClient) != null) {
//
//                Person currentPerson = Plus.PeopleApi.getCurrentPerson(mGoogleApiClient);
//
//                String personName = currentPerson.getDisplayName();
//
//                String personPhotoUrl = currentPerson.getImage().getUrl();
//
//                String email = Plus.AccountApi.getAccountName(mGoogleApiClient);
//
//                Toast.makeText(this, "Come On! " + personName, Toast.LENGTH_LONG).show();
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//    }
//
//    @Override
//    public void onConnectionSuspended(int cause) {
//        mGoogleApiClient.connect();
//        Toast.makeText(this, "Come Off! " + cause, Toast.LENGTH_LONG).show();
//    }
//
//    private void googlePlusLogin() {
//        if (!mGoogleApiClient.isConnecting()) {
//            signedInUser = true;
//        }
//    }
//
//    @Override
//    public void onConnectionFailed(ConnectionResult connectionResult) {
//        Toast.makeText(this, "Come Error! " + connectionResult.getErrorCode(), Toast.LENGTH_LONG).show();
//    }
//
//    ///////////////////////////////////////////////////////////////////////////////////////

    private void startSession(Context applicationContext, User user){
        new SessionManager(applicationContext).createLoginSession(user.getName(), user.getMail());
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        twitterLoginButton.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);

//        switch (requestCode) {
//            case RC_SIGN_IN:
//                if (resultCode == RESULT_OK) {
//                    signedInUser = false;
//                }
//
//                if (!mGoogleApiClient.isConnecting()) {
//
//                    mGoogleApiClient.connect();
//                }
//                break;
//        }
    }


    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

}