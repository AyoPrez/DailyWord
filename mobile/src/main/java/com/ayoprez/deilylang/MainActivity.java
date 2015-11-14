package com.ayoprez.deilylang;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;

import com.ayoprez.database.CreateDatabase;
import com.ayoprez.login.LoginActivity;
import com.ayoprez.newMoment.NewMomentActivity;
import com.ayoprez.preferences.Preferences;
import com.ayoprez.userProfile.ProfileScreen;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnItemLongClick;

public class MainActivity extends AbstractBaseActivity {
    @Bind(R.id.reviewList)
    ListView reviewList;

    @OnClick(R.id.b_main)
    void momentsButton(){
        Intent newMomentIntent = new Intent(this, NewMomentActivity.class);
        startActivity(newMomentIntent);
        ((Activity) this).finish();
    }

    @OnItemLongClick(R.id.reviewList)
    boolean longItem(int position){
        new ReviewList(reviewList).showAlertDialogToDeleteItem(this, position);
        return true;
    }

    //Tests
    //TODO hide
//    @OnClick(R.id.buttonn) void newNotification(){
//        Test.testNotification(this);
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        initToolbar();

        new CreateDatabase(this);

        new ReviewList(reviewList).showReviewList(this);
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
                goToNewScreen(!sessionManager.isLoggedIn() ? LoginActivity.class : ProfileScreen.class);
                return true;
            case R.id.action_settings:
                startActivity(new Intent(this, Preferences.class));
                return true;
        }
        return true;
    }
}