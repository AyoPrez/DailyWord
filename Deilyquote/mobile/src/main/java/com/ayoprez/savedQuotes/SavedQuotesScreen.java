package com.ayoprez.savedQuotes;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;

import com.ayoprez.deilyquote.R;
import com.ayoprez.login.SessionManager;
import com.ayoprez.restfulservice.QuoteGet;
import com.ayoprez.userProfile.ProfileScreen;

import java.util.ArrayList;

import butterknife.ButterKnife;
import de.greenrobot.event.EventBus;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

/**
 * Created by AyoPrez on 24/05/15.
 */
public class SavedQuotesScreen extends AppCompatActivity {

    private Toolbar toolbar;
    protected SessionManager sessionManager;
    protected Dialog loadDialog;
    private RecyclerView savedQuotesRecyclerView;
    private RecyclerView.Adapter recyclerViewAdapter;
    private RecyclerView.LayoutManager recyclerViewLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_savedquotes_screen);
        setVolumeControlStream(AudioManager.STREAM_MUSIC);
        ButterKnife.inject(this);

        EventBus.getDefault().register(this);

        sessionManager = new SessionManager(this);

        initToolbar();

        getSavedQuotes(getUserId());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    private void createLoadDialog(){
        loadDialog = new Dialog(this);
        loadDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        loadDialog.setContentView(R.layout.dialog_load);
        loadDialog.show();
    }

    public Dialog getDialog(){
        return loadDialog;
    }

    private void cancelDialog(){
        getDialog().cancel();
    }

    private void initRecyclerView(ArrayList<SavedQuotes> savedQuotes){
        savedQuotesRecyclerView = (RecyclerView) findViewById(R.id.savedQuotesRecyclerView);

        savedQuotesRecyclerView.setHasFixedSize(true);
        recyclerViewLayoutManager = new LinearLayoutManager(this);
        savedQuotesRecyclerView.setLayoutManager(recyclerViewLayoutManager);
        recyclerViewAdapter = new SavedQuotesRecyclerViewAdapter(this, savedQuotes);
        savedQuotesRecyclerView.setAdapter(recyclerViewAdapter);
    }

    private void initToolbar(){
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backToProfileIntent();
            }
        });
    }

    private void backToProfileIntent(){
        Intent intent = new Intent(this, ProfileScreen.class);
        startActivity(intent);
        finish();
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    protected int getUserId(){
        return Integer.valueOf(sessionManager.getUserDetails().get("id"));
    }

    protected void getSavedQuotes(int userId){
        new QuoteGet().getUserQuotes(userId);
        createLoadDialog();
    }

    public void onEvent(ArrayList<SavedQuotes> savedQuotes){
        initRecyclerView(savedQuotes);
        cancelDialog();
    }
}