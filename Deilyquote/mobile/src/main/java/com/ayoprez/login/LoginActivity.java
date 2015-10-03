package com.ayoprez.login;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.ayoprez.deilyquote.MainActivity;
import com.ayoprez.deilyquote.R;
import com.twitter.sdk.android.core.identity.TwitterLoginButton;

import butterknife.ButterKnife;
import butterknife.OnClick;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

/**
 * Created by AyoPrez on 22/05/15.
 */
public class LoginActivity extends AppCompatActivity {

    public TwitterLoginButton twitterLoginButton;
    private Context context;
    private User user;
    private FacebookLogin facebookLogin = new FacebookLogin(this);
    private TwitterLogin twitterLogin = new TwitterLogin(this);

    @OnClick(R.id.login_continue)
    void loginContinue(){
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        facebookLogin.initFacebook();
        setContentView(R.layout.activity_login);
        ButterKnife.inject(this);

        this.context = this;

        facebookLogin.facebookLogin();
        twitterLogin.twitterLogin();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        twitterLoginButton.onActivityResult(requestCode, resultCode, data);

        facebookLogin.callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    public void startSession(Context applicationContext, User user){
        new SessionManager(applicationContext).createLoginSession(user.getName(), String.valueOf(user.getId_U()));
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
}