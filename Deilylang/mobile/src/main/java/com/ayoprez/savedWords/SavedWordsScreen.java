package com.ayoprez.savedWords;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;
import android.widget.FrameLayout;
import android.widget.TabWidget;

import com.ayoprez.deilylang.R;

import butterknife.ButterKnife;
import butterknife.InjectView;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

/**
 * Created by AyoPrez on 24/05/15.
 */
public class SavedWordsScreen extends FragmentActivity {

    @InjectView(android.R.id.tabs)
    TabWidget tabs;
    @InjectView(android.R.id.tabcontent)
    FrameLayout tabContent;
    @InjectView(android.R.id.tabhost)
    FragmentTabHost tabHost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_savedwords_screen);
        ButterKnife.inject(this);

        tabHost.setup(this, getSupportFragmentManager(), android.R.id.tabcontent);
        tabHost.addTab(tabHost.newTabSpec("tab1").setIndicator("Basic"), TabBasic.class, null);
        tabHost.addTab(tabHost.newTabSpec("tab2").setIndicator("Easy"), TabEasy.class, null);
        tabHost.addTab(tabHost.newTabSpec("tab3").setIndicator("Normal"), TabNormal.class, null);
        tabHost.addTab(tabHost.newTabSpec("tab4").setIndicator("Hard"), TabHard.class, null);
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
}