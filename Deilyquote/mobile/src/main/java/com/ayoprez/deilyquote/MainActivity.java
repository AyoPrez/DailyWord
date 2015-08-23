package com.ayoprez.deilyquote;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
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
import com.ayoprez.notification.LaunchNotification;
import com.ayoprez.notification.StartAndCancelAlarmManager;
import com.ayoprez.preferences.Preferences;
import com.ayoprez.userProfile.ProfileScreen;
import com.crashlytics.android.Crashlytics;
import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.TwitterAuthConfig;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import butterknife.OnItemLongClick;
import deilyquote.UserMoments;
import deilyquote.UserQuotes;
import io.fabric.sdk.android.Fabric;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class MainActivity extends AppCompatActivity {

    // Note: Your consumer key and secret should be obfuscated in your source code before shipping.
    private static final String TWITTER_KEY = "SPdxhgW1TXTpGj9aVsdkRy8JM";
    private static final String TWITTER_SECRET = "GRJLdh0zCH5dgzBmZXlpoNg7Q9rZYeImpM0XRrX66ao8PNXjFy";


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

    //Tests
    Long id = new Long(1);
    String quote = "La gente piensa que concentrarse significa decir sí a aquello en lo que te concentras, pero no es así. Significa decir no a otras cientos de ideas buenas que hay.";
    String author = "Steve Jobs";
    String language = "spanish";
    String personality = "Emprendedor";

    @OnClick(R.id.buttonn) void newNotification(){
        try {
            new LaunchNotification(this).launchNotification(this, new UserQuotes(id, language, personality, null, null, quote, author));
        } catch (Exception e) {
            Log.e("DeilyQuoteError", "NotificationButton  " + e);
            e.printStackTrace();
        }
    }

    private ReviewAdapter mReviewAdapter;
    private Toolbar toolbar;
    private SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TwitterAuthConfig authConfig = new TwitterAuthConfig(TWITTER_KEY, TWITTER_SECRET);
        Fabric.with(this, new Crashlytics(), new Twitter(authConfig));
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        sessionManager = new SessionManager(this);

        //TODO Check if is already created
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
        UserMomentsRepository userMomentsRepository = new UserMomentsRepository();
        final List<UserMoments> userMoments = getDataFromDatabaseToListView(mContext);
        final int momentId = (int)userMomentsRepository.getIdFromData(mContext, userMoments.get(selectedItem));

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
//        userMoments.setLevel(getDataFromDatabaseToListView(context).get(selectedItem).getLevel());

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

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
}