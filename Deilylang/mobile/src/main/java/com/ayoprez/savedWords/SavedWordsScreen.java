package com.ayoprez.savedWords;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Spinner;

import com.ayoprez.deilylang.R;
import com.ayoprez.login.SessionManager;
import com.ayoprez.restfulservice.GetSavedWords;
import com.ayoprez.userProfile.ProfileScreen;

import java.util.ArrayList;

import butterknife.ButterKnife;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

/**
 * Created by AyoPrez on 24/05/15.
 */
public class SavedWordsScreen extends AppCompatActivity {

    private Toolbar toolbar;
    private SessionManager sessionManager;
    private Spinner spinner;
    private ArrayList<String> titlesList;
    private ArrayList<Fragment> fragmentsList;
    private SpinnerAdapter spinnerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_savedwords_screen);
        ButterKnife.inject(this);

        initToolbar();
        fillArrayLists();
        initTabs(titlesList, fragmentsList);
    }

    private void fillArrayLists(){

        //This is an example
        titlesList = new ArrayList<>();
        fragmentsList = new ArrayList<>();

        titlesList.add(getString(R.string.basic));
        titlesList.add(getString(R.string.easy));
        titlesList.add(getString(R.string.normal));
        titlesList.add(getString(R.string.hard));

        fragmentsList.add(new LevelBasicFragment());
        fragmentsList.add(new LevelEasyFragment());
        fragmentsList.add(new LevelNormalFragment());
        fragmentsList.add(new LevelHardFragment());
    }

    private void initToolbar(){
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        spinnerAdapter = new SpinnerAdapter(toolbar.getContext(), R.layout.spinner_textview);

        spinnerAdapter.add(getString(R.string.English));
        spinnerAdapter.add(getString(R.string.Spanish));

        spinner = (Spinner) toolbar.findViewById(R.id.spinner_nav);
        spinner.setVisibility(View.VISIBLE);
        spinner.setAdapter(spinnerAdapter);
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

    private void initTabs(ArrayList<String> fragmentsTitles, ArrayList<Fragment> fragments){

        ViewPager viewPager = (ViewPager) findViewById(R.id.viewPager_userWords);
        initViewPager(viewPager, fragmentsTitles, fragments);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabLayout_userWords);
        tabLayout.setupWithViewPager(viewPager);

        getSavedWords(1);
    }

    private void initViewPager(ViewPager viewPager, ArrayList<String> fragmentsTitles, ArrayList<Fragment> fragments){
        MainViewPagerAdapter adapter = new MainViewPagerAdapter(getSupportFragmentManager(), fragmentsTitles, fragments);
        viewPager.setAdapter(adapter);
    }

    private void getSavedWords(int userId){
        if(spinner.getSelectedItem().equals(getString(R.string.English))){
            new GetSavedWords(this).sendEnglishUserWordsRequest(userId, "spanish");
        }else{
            if (spinner.getSelectedItem().equals(getString(R.string.Spanish))){
                new GetSavedWords(this).sendSpanishUserWordsRequest(userId, "english");
            }
        }
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
}