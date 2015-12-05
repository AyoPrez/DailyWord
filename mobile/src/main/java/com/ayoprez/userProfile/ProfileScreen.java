package com.ayoprez.userProfile;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.ayoprez.deilylang.AbstractBaseActivity;
import com.ayoprez.deilylang.MainActivity;
import com.ayoprez.deilylang.R;
import com.ayoprez.preferences.Preferences;
import com.ayoprez.savedWords.SavedWordsScreen;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by AyoPrez on 31/05/15.
 */
public class ProfileScreen extends AbstractBaseActivity{

    @OnClick(R.id.b_save) void mSavedButton(){
        goToNewScreen(SavedWordsScreen.class);
        //Idea: Que esta pantalla sea un deplegable desde un lado o desde abajo
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_layout);
        ButterKnife.bind(this);

        initToolbar();
    }

    @Override
    protected void initToolbar(){
        super.initToolbar();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToNewScreen(MainActivity.class);
            }
        });
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
                Intent i = new Intent(this, Preferences.class);
                startActivity(i);
                return true;
        }
        return true;
    }
}