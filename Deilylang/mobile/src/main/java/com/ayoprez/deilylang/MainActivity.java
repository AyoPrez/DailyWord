package com.ayoprez.deilylang;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import com.ayoprez.database.CreateDatabase;
import com.ayoprez.database.UserMomentsRepository;
import com.ayoprez.login.LoginActivity;
import com.ayoprez.login.SessionManager;
import com.ayoprez.newMoment.NewMomentActivity;
import com.ayoprez.notification.StartAndCancelAlarmManager;
import com.ayoprez.userProfile.ProfileScreen;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import butterknife.OnItemLongClick;
import deilyword.UserMoments;

public class MainActivity extends AppCompatActivity {

    @InjectView(R.id.reviewList) ListView mReviewList;

    @OnClick(R.id.b_main) void mMomentsButton(){
        Intent newMomentIntent = new Intent(this, NewMomentActivity.class);
        startActivity(newMomentIntent);
        ((Activity) this).finish();
    }

    @OnItemLongClick(R.id.reviewList) boolean longItem(int position){
        showAlertDialogToDeleteItem(this, position);
        return true;
    }

    private ReviewAdapter mReviewAdapter;
    private Toolbar toolbar;
    private SessionManager sessionManager;

    //Dependencies
    //-ReviewAdapter
    //-CreateDatabase
    //-UserMomentRepository
    //-StartAndCancelAlarmManager
    //-SessionManager

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        sessionManager = new SessionManager(this);

        new CreateDatabase(this);

        showReviewList(this);
    }

    public List<UserMoments> getDataFromDatabaseToListView(Context mContext) {
        return new UserMomentsRepository().getAllMoments(mContext);
    }

    private void showReviewList(Context mContext) {
        mReviewAdapter = new ReviewAdapter(mContext, getDataFromDatabaseToListView(mContext));
        mReviewList.setAdapter(mReviewAdapter);
    }

    public void showAlertDialogToDeleteItem(final Context mContext, final int selectedItem) {
//        UserMomentsRepository userMomentsRepository = new UserMomentsRepository();
        final List<UserMoments> userMoments = getDataFromDatabaseToListView(mContext);
//        final int momentId = (int)userMomentsRepository.getIdFromData(mContext, userMoments.get(selectedItem));

        new AlertDialog.Builder(this)
                .setTitle(R.string.deleteMomentDialogTitle)
                .setMessage(R.string.deleteMomentDialog)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        try {
                            deleteItemFromDatabase(mContext, selectedItem);
                            new StartAndCancelAlarmManager(mContext, userMoments.get(0)).cancelAlarmManager();
                            showReviewList(mContext);
                        } catch (Exception e) {
                            Toast.makeText(mContext, getString(R.string.errorDeletingMoment), Toast.LENGTH_LONG).show();
                        }
                    }
                }).create().show();
    }

    private void deleteItemFromDatabase(Context context, int selectedItem) {
        new UserMomentsRepository().deleteMomentWithId(context, getIdToDelete(context, selectedItem));
    }

    public ListView getListView(){
        return mReviewList;
    }

    private long getIdToDelete(Context context, int selectedItem){
        UserMoments userMoments = new UserMoments();
        userMoments.setLanguage(getDataFromDatabaseToListView(context).get(selectedItem).getLanguage());
        userMoments.setTime(getDataFromDatabaseToListView(context).get(selectedItem).getTime());
        userMoments.setLevel(getDataFromDatabaseToListView(context).get(selectedItem).getLevel());

        return new UserMomentsRepository().getIdFromData(context, userMoments);
    }

    private void goToLoginScreen(){
        Intent i = new Intent(this, LoginActivity.class);
        startActivity(i);
        this.finish();
    }

    private void goToUserProfile(){
        Intent i = new Intent(this, ProfileScreen.class);
        startActivity(i);
        this.finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_word_screen, menu);

        MenuItem loginItem = menu.findItem(R.id.action_signIn);
        if(sessionManager.isLoggedIn()){
            loginItem.setTitle(sessionManager.getUserDetails().get("name"));
        }

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_signIn:
                if(!sessionManager.isLoggedIn()){
                    goToLoginScreen();
                }else{
                    goToUserProfile();
                }

                return true;
            case R.id.action_settings:
                Intent i = new Intent(this, Preferences.class);
                startActivity(i);
                return true;
        }
        return true;
    }
}