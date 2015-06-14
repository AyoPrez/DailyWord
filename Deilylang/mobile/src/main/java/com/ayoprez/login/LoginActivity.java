package com.ayoprez.login;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.ayoprez.deilylang.MainActivity;
import com.ayoprez.deilylang.R;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by AyoPrez on 22/05/15.
 */
public class LoginActivity extends Activity {
    @InjectView(R.id.imageView)
    ImageView imageView;
    @InjectView(R.id.login_mail)
    EditText loginMail;
    @InjectView(R.id.login_pass)
    EditText loginPass;
    @InjectView(R.id.app_loginButton)
    Button appLoginButton;
    @InjectView(R.id.facebook_loginButton)
    Button facebookLoginButton;
    @InjectView(R.id.twitter_loginButton)
    Button twitterLoginButton;
    @InjectView(R.id.google_loginButton)
    Button googleLoginButton;

    @OnClick(R.id.app_loginButton)
    void loginButton(){
        startSession(this, new User(1, "Ayo", "arezrod@gmail.com", "jalapeño"));

        if(new SessionManager(this).isLoggedIn()){
            Intent i = new Intent(this, MainActivity.class);
            startActivity(i);
            finish();
        }
    }

    @OnClick(R.id.login_continue)
    void loginContinue(){
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.inject(this);
    }

    private void startSession(Context applicationContext, User user){
        new SessionManager(applicationContext).createLoginSession(user.getName(), user.getMail());
    }
}