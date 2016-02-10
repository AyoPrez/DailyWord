package com.ayoprez.savedWords;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.ViewStub;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Spinner;

import com.ayoprez.deilylang.AbstractBaseActivity;
import com.ayoprez.deilylang.DetectDeviceLanguage;
import com.ayoprez.deilylang.R;
import com.ayoprez.login.SessionManager;
import com.ayoprez.restfulservice.GetSavedWords;
import com.ayoprez.userProfile.ProfileScreen;

import java.util.ArrayList;
import java.util.Locale;

import butterknife.ButterKnife;
import butterknife.Bind;
import de.greenrobot.event.EventBus;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

/**
 * Created by AyoPrez on 24/05/15.
 */
public class SavedWordsScreen extends AbstractBaseActivity {
private static final String LOG_TAG = SavedWordsScreen.class.getSimpleName();

    //TODO deep refactoring here. Use some library
    private Spinner spinner;
    private ArrayList<String> titlesList;
    private ArrayList<Fragment> fragmentsList;

    private SpinnerAdapter spinnerAdapter;
    protected Dialog loadDialog;

    private ArrayList<String> basicLevelType = new ArrayList<String>();
    private ArrayList<String> easyLevelType = new ArrayList<String>();
    private ArrayList<String> normalLevelType = new ArrayList<String>();
    private ArrayList<String> hardLevelType = new ArrayList<String>();

    private ArrayList<String> basicLevelWord = new ArrayList<String>();
    private ArrayList<String> easyLevelWord = new ArrayList<String>();
    private ArrayList<String> normalLevelWord = new ArrayList<String>();
    private ArrayList<String> hardLevelWord = new ArrayList<String>();

    private String language;
    private ArrayList<String> spinnerLanguages = new ArrayList<>();

    @Bind(R.id.viewStub_no_internet)
    ViewStub viewStubNoInternet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_savedwords_screen);
        setVolumeControlStream(AudioManager.STREAM_MUSIC);
        ButterKnife.bind(this);

        EventBus.getDefault().register(this);

        initToolbar();

        if(isNetworkAvailable()) {
            getSavedWords(getUserId());
        }else{
            viewStubNoInternet.setVisibility(View.VISIBLE);
        }
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
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

    private void fillArrayLists(){

        //This is an example
        titlesList = new ArrayList<>();
        fragmentsList = new ArrayList<>();

        titlesList.add(getString(R.string.basic));
        titlesList.add(getString(R.string.easy));
        titlesList.add(getString(R.string.normal));
        titlesList.add(getString(R.string.hard));

        fragmentsList.add(LevelBasicFragment.newInstance(basicLevelWord, basicLevelType, language));
        fragmentsList.add(LevelEasyFragment.newInstance(easyLevelWord, easyLevelType, language));
        fragmentsList.add(LevelNormalFragment.newInstance(normalLevelWord, normalLevelType, language));
        fragmentsList.add(LevelHardFragment.newInstance(hardLevelWord, hardLevelType, language));
    }

    @Override
    protected void initToolbar(){
        super.initToolbar();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        spinnerAdapter = new SpinnerAdapter(toolbar.getContext(), R.layout.spinner_textview);

        if(Locale.getDefault().getDisplayLanguage().equals(Locale.ENGLISH)){
            spinnerAdapter.add(getString(R.string.Spanish));
        }else{
            spinnerAdapter.add(getString(R.string.English));
        }

        spinner = (Spinner) toolbar.findViewById(R.id.spinner_nav);
        spinner.setVisibility(View.VISIBLE);
        spinner.setAdapter(spinnerAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                getSavedWords(getUserId());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

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
    }

    private void initViewPager(ViewPager viewPager, ArrayList<String> fragmentsTitles, ArrayList<Fragment> fragments){
        MainViewPagerAdapter adapter = new MainViewPagerAdapter(getSupportFragmentManager(), fragmentsTitles, fragments);
        viewPager.setOffscreenPageLimit(1);
        viewPager.setAdapter(adapter);
    }

    protected void getSavedWords(int userId){
        if(DetectDeviceLanguage.getISO3Language().toLowerCase().equals("spa")){
            new GetSavedWords(this).sendEnglishUserWordsRequest(userId, "spanish");
        }else{
            new GetSavedWords(this).sendSpanishUserWordsRequest(userId, "english");
        }

        createLoadDialog();
    }

    protected void switchFragment(){
        fillArrayLists();
        initTabs(titlesList, fragmentsList);

        cancelDialog();
    }

    protected void separateWordByLevel(ArrayList<SavedWords> savedWords){

            for (SavedWords saveWords : savedWords){
            switch(saveWords.getLevel().toLowerCase().trim()){
                case "basic": {
                    basicLevelWord.add(saveWords.getWord());
                    basicLevelType.add(saveWords.getType());
                    break;
                }
                case "easy": {
                    easyLevelWord.add(saveWords.getWord());
                    easyLevelType.add(saveWords.getType());
                    break;
                }
                case "normal": {
                    normalLevelWord.add(saveWords.getWord());
                    normalLevelType.add(saveWords.getType());
                    break;
                }
                case "hard":{
                    hardLevelWord.add(saveWords.getWord());
                    hardLevelType.add(saveWords.getType());
                    break;
                }
            }
        }

        switchFragment();
    }

    private ArrayList<String> getLanguages(ArrayList<SavedWords> savedWordsArrayList){
        ArrayList<String> languages = new ArrayList<>();

        for(int i = 0; i < savedWordsArrayList.size(); i++){
            if (!languages.contains(savedWordsArrayList.get(i).getLanguage())){
                languages.add(savedWordsArrayList.get(i).getLanguage());
            }
        }
        return languages;
    }

    private void setSpinnerLanguages(ArrayList<String> languages){
        spinnerAdapter.removeAll();
        for(String language : languages) {
            spinnerAdapter.add(translateLanguage(language));
        }
    }

    private String translateLanguage(String language){
        String finalLanguage = language;

        if(language.toLowerCase().trim().equals("english")){
            finalLanguage = getString(R.string.English);
        }

        if(language.toLowerCase().trim().equals("spanish")){
            finalLanguage = getString(R.string.Spanish);
        }

        return finalLanguage;
    }

    public void onEvent(ArrayList<SavedWords> savedWords){
        if(savedWords.size() > 0) {
            language = savedWords.get(0).getLanguage();
            spinnerLanguages = getLanguages(savedWords);
            setSpinnerLanguages(spinnerLanguages);
            separateWordByLevel(savedWords);
        }else{
            viewStubNoInternet.setLayoutResource(R.layout.empty_list_layout);
            viewStubNoInternet.setVisibility(View.VISIBLE);
            cancelDialog();
        }
    }
}