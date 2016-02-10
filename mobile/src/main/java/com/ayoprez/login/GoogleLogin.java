package com.ayoprez.login;

import android.content.Context;

import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;

/**
 * Created by AyoPrez on 25/07/15.
 */
public class GoogleLogin {

    private Context context;
    private SignInButton googleLoginButton;
    private GoogleApiClient mGoogleApiClient;
    private static final int RC_SIGN_IN = 0;
    private boolean signedInUser;

    public GoogleLogin(Context context){
        this.context = context;
    }

//
//    ///////////////////////////////////// Google Login ///////////////////////////////////////////
//    private void googleLogin(){
//        mGoogleApiClient.connect();
//
//        mGoogleApiClient = new GoogleApiClient.Builder(context)
//                .addConnectionCallbacks(context)
//                .addOnConnectionFailedListener(context)
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
//        Toast.makeText(context, "Connected", Toast.LENGTH_LONG).show();
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
//                Toast.makeText(context, "Come On! " + personName, Toast.LENGTH_LONG).show();
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
//        Toast.makeText(context, "Come Off! " + cause, Toast.LENGTH_LONG).show();
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
//        Toast.makeText(context, "Come Error! " + connectionResult.getErrorCode(), Toast.LENGTH_LONG).show();
//    }
//
//    ///////////////////////////////////////////////////////////////////////////////////////

}
