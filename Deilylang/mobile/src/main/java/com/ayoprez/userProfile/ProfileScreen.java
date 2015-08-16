package com.ayoprez.userProfile;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.ayoprez.deilylang.MainActivity;
import com.ayoprez.deilylang.R;
import com.ayoprez.login.SessionManager;
import com.ayoprez.savedWords.SavedWordsScreen;

import butterknife.ButterKnife;
import butterknife.OnClick;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

/**
 * Created by AyoPrez on 31/05/15.
 */
public class ProfileScreen extends AppCompatActivity{

    private Toolbar toolbar;
    private SessionManager sessionManager;

    @OnClick(R.id.b_save) void mSavedButton(){
        Intent savedIntent = new Intent(this, SavedWordsScreen.class);
        startActivity(savedIntent);
        //Idea: Que esta pantalla sea un deplegable desde un lado o desde abajo
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_layout);
        ButterKnife.inject(this);

        initToolbar();

        sessionManager = new SessionManager(this);
    }

    private void initToolbar(){
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backToMainIntent();
            }
        });
    }

    private void backToMainIntent(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_word_screen, menu);

        MenuItem loginItem = menu.findItem(R.id.action_signIn);
        if(sessionManager.isLoggedIn()){
            loginItem.setTitle(getString(R.string.action_logout));
        }

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_signIn:
                sessionManager.logoutUser();
                finish();
                return true;
            case R.id.action_settings:

                return true;
        }
        return true;
    }

    //Custom font
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
}
