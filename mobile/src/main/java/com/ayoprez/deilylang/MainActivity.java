package com.ayoprez.deilylang;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.ayoprez.database.CreateDatabase;
import com.ayoprez.database.UserMomentsRepository;
import com.ayoprez.login.LoginActivity;
import com.ayoprez.newMoment.NewMomentActivity;
import com.ayoprez.preferences.Preferences;
import com.ayoprez.savedWords.SavedWords;
import com.ayoprez.savedWords.SavedWordsScreen;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnItemLongClick;

import com.ayoprez.utils.Utils;
import com.crashlytics.android.Crashlytics;
import com.crashlytics.android.answers.CustomEvent;

public class MainActivity extends AbstractBaseActivity {

    //TODO Dependencies
    //-new CreateDatabase
    //-new ReviewList

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_container);
        ButterKnife.bind(this);

        new CreateDatabase(this);

        initToolbar();

        showFragment(getCorrectFragment(this));
    }

    private Fragment getCorrectFragment(Context context){
        if(Utils.getInstance().isMomentsFull(context)){
            return MomentMainFragment.getInstance();
        }else{
            return NoMomentMainFragment.getInstance();
        }
    }

    private void showFragment(Fragment fragment){
        getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment).commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_word_screen, menu);
        MenuItem loginItem = menu.findItem(R.id.action_signIn);

        loginItem.setTitle(sessionManager.isLoggedIn() ? getUserName() : getString(R.string.action_login));

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_signIn:
                goToNewScreen(!sessionManager.isLoggedIn() ? LoginActivity.class : SavedWordsScreen.class);
                return true;
            case R.id.action_settings:
                startActivity(new Intent(this, Preferences.class));
                return true;
        }
        return true;
    }
}