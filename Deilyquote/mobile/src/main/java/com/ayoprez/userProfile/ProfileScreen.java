package com.ayoprez.userProfile;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.ayoprez.deilyquote.R;
import com.ayoprez.login.SessionManager;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by AyoPrez on 31/05/15.
 */
public class ProfileScreen extends AppCompatActivity{

    private Toolbar toolbar;
    private SessionManager sessionManager;

    @OnClick(R.id.b_save) void mSavedButton(){
//        Intent savedIntent = new Intent(this, SavedWordsScreen.class);
//        startActivity(savedIntent);
        //Idea: Que esta pantalla sea un deplegable desde un lado o desde abajo
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_layout);
        ButterKnife.inject(this);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        sessionManager = new SessionManager(this);

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
}