package com.ayoprez.login;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.ayoprez.deilylang.R;
import com.ayoprez.restfulservice.GetUser;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;

/**
 * Created by AyoPrez on 25/07/15.
 */
public class GoogleLogin {
    private static final String TAG = GoogleLogin.class.getSimpleName();

    private static final int RC_SIGN_IN = 9001;

    private String TYPE_ID = "g";
    private Context context;
    private GoogleSignInOptions gso;
    private GoogleApiClient mGoogleApiClient;
    private User user;

    public GoogleLogin(Context context){
        this.context = context;
    }

    private void getUserData(){
        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .build();

        mGoogleApiClient = new GoogleApiClient.Builder(getLoginActivity())
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();
    }

    private LoginActivity getLoginActivity(){
        return ((LoginActivity)context);
    }

    public void googleLogin(){
        getUserData();

        final Button signInButton = (Button) getLoginActivity().findViewById(R.id.sign_in_button);

        googleButtonStyle(signInButton);

//        final SignInButton signInButton = (SignInButton) getLoginActivity().findViewById(R.id.sign_in_button);
//        signInButton.setSize(SignInButton.SIZE_STANDARD);
//        signInButton.setScopes(gso.getScopeArray());
//        signInButton.setColorScheme(SignInButton.COLOR_DARK);

        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signIn();
            }
        });
    }

    private void googleButtonStyle(Button googleButton){
        googleButton.setBackgroundColor(context.getResources().getColor(R.color.google));
        googleButton.setText(context.getString(com.google.android.gms.R.string.common_signin_button_text_long));

        //Add the icon in the desired position
        googleButton.setCompoundDrawables(null, null, null, null);
        googleButton.setCompoundDrawablePadding(0);
        googleButton.setPadding(10, 30, 10, 30);
    }

    private void signIn() {
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        getLoginActivity().startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    public void activityResult(int requestCode, Intent data){
        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleSignInResult(result);
        }
    }

    private void handleSignInResult(GoogleSignInResult result) {
        Log.d(TAG, "handleSignInResult:" + result.isSuccess());
        if (result.isSuccess()) {
            GoogleSignInAccount acct = result.getSignInAccount();
            Log.e(TAG, "Name: " + acct.getDisplayName() + ", ID: " + acct.getId());

            Log.i(TAG, "UI true");

            user = new User(acct.getDisplayName(), acct.getId());

            new GetUser(context).sendUserDataRequest(user.getSocial_Id(), TYPE_ID, user.getName());
        } else {
            Log.i(TAG, "UI false");
//            updateUI(false);
        }
    }

}
