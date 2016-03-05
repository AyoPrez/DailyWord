package com.ayoprez.wordscreen;

import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.*;

import com.ayoprez.deilylang.*;
import com.ayoprez.utils.SpeakerHelper;
import com.ayoprez.utils.Utils;
import com.ayoprez.login.SessionManager;
import com.ayoprez.notification.ShortTimeStartAndCancel;
import com.ayoprez.restfulservice.SetWords;
import com.crashlytics.android.Crashlytics;

import java.util.Locale;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import butterknife.ButterKnife;
import butterknife.Bind;
import butterknife.OnClick;
import com.crashlytics.android.answers.CustomEvent;
import de.greenrobot.event.EventBus;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class WordScreen extends AbstractBaseActivity {
    private static final String LOG_TAG = WordScreen.class.getSimpleName();

    private static final ScheduledExecutorService worker =
            Executors.newSingleThreadScheduledExecutor();
    private Context mContext;
    private Locale languageNew;
    private Locale languageUser;
    private Bundle bundle;
    private SpeakerHelper speak;

    @Bind(R.id.textView_WordScreen_1)
    TextView mWord1WordScreen;
    @Bind(R.id.textView_Type_1)
    TextView mType1WordScreen;
    @Bind(R.id.textView_WordScreen_2)
    TextView mWord2WordScreen;
    @Bind(R.id.textView_Type_2)
    TextView mType2WordScreen;
    @Bind(R.id.button_WordScreen_)
    Button mButtonSaveWord;
    @Bind(R.id.imageButton_WordScreen_1)
    ImageButton ibSpeaker1;
    @Bind(R.id.imageButton_WordScreen_2)
    ImageButton ibSpeaker2;
    @Bind(R.id.progressBar_1)
    ProgressBar pbSpeaker1;
    @Bind(R.id.progressBar_2)
    ProgressBar pbSpeaker2;

    private String WORDS_ID_KEY = "wordId";
    private String WORDS_ARRAY_KEY = "words";
    private String TYPES_ARRAY_KEY = "types";
    private String LANGUAGES_ARRAY_KEY = "languages";
    private String LEVEL_KEY = "level";

    //TODO Dependencies
    //-new SessionManager
    //-new SetWords
    //-new ShortTimeStartAndCancel


    @OnClick(R.id.imageButton_WordScreen_1) void speakerNewLanguage(){
        talkSpeech(languageNew, mWord1WordScreen.getText().toString());
    }

    @OnClick(R.id.imageButton_WordScreen_2) void speakerUserLanguage(){
        talkSpeech(languageUser, mWord2WordScreen.getText().toString());
    }

    @OnClick(R.id.button_WordScreen_) void buttonDone(){
        Integer wordId = bundle.getInt(WORDS_ID_KEY);
        String languageNew = bundle.getStringArray(LANGUAGES_ARRAY_KEY)[0];
        String languageDevice = bundle.getStringArray(LANGUAGES_ARRAY_KEY)[1];
        String level = bundle.getString("level");
        int id_user = Integer.valueOf(new SessionManager(mContext).getUserDetails().get("id"));

        new SetWords(this).sendUserWord(id_user, wordId, languageDevice, languageNew);
    }

    @OnClick(R.id.button_WordScreen_2) void buttonRemindMeLater(){
        Crashlytics.getInstance().answers.logCustom(new CustomEvent("WordScreen").putCustomAttribute("button", "RemindMeLater"));
        if(bundle != null){
            WordFromDatabase wordFromDatabase = new WordFromDatabase(
                    bundle.getInt(WORDS_ID_KEY),
                    bundle.getStringArray(WORDS_ARRAY_KEY),
                    bundle.getString(LEVEL_KEY),
                    bundle.getStringArray(TYPES_ARRAY_KEY),
                    bundle.getStringArray(LANGUAGES_ARRAY_KEY));

            if(new ShortTimeStartAndCancel(mContext, wordFromDatabase).startAlarmManager20MinutesLater()){

                Utils.getInstance().Create_Dialog(mContext, getString(R.string.laterDialog),
                        mContext.getString(R.string.buttonAcceptDialog),
                        mContext.getString(R.string.successSavingDialogTitle));
            }else{
                Utils.getInstance().Create_Dialog(mContext, getString(R.string.errorSavingMoment),
                        mContext.getString(R.string.buttonAcceptDialog),
                        mContext.getString(R.string.errorSavingDialogTitle));
            }
        }else{
            ErrorHandler.getInstance().Error(LOG_TAG, "Bundle null");
        }
    }

    @OnClick(R.id.button_report_WordScreen) void buttonReport(){
        Crashlytics.getInstance().answers.logCustom(new CustomEvent("WordScreen").putCustomAttribute("button", "ReportWrongTranslation"));
        //TODO make it easier. Just push the button, see that the report is sending, and done
        //TODO http://stackoverflow.com/questions/2020088/sending-email-in-android-using-javamail-api-without-using-the-default-built-in-a/2033124#2033124
        Intent i = new Intent(Intent.ACTION_SEND);
        i.setType("message/rfc822");
        i.putExtra(Intent.EXTRA_EMAIL  , new String[]{"report@deilylang.com"});
        i.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.Subject));
        i.putExtra(Intent.EXTRA_TEXT   , getString(R.string.Body) + " '" + mWord1WordScreen.getText().toString() + "' " +
                                            getString(R.string.Body2) + " '" + mWord2WordScreen.getText().toString() + "'" +
                                            getString(R.string.Body3));
        try {
            startActivity(Intent.createChooser(i, getString(R.string.Title)));
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(this, R.string.Error, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word_screen);
        setVolumeControlStream(AudioManager.STREAM_MUSIC);
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);

        speak = new SpeakerHelper(this);

        this.mContext = this;

        bundle = getIntent().getExtras();

        if(!new SessionManager(this).isLoggedIn()){
            mButtonSaveWord.setVisibility(View.GONE);
        }

        defineLocales(bundle.getStringArray(LANGUAGES_ARRAY_KEY));
        defineWords(bundle.getStringArray(WORDS_ARRAY_KEY));
        defineType(bundle.getStringArray(TYPES_ARRAY_KEY));
    }

    private void defineLocales(String[] languages){
        languageNew = DetectDeviceLanguage.getLocaleFromString(languages[0].toLowerCase());
        languageUser = DetectDeviceLanguage.getLocaleFromString(languages[1].toLowerCase());
    }

    private void defineWords(String[] wordsFromTables){
        mWord1WordScreen.setText(wordsFromTables[0]);
        mWord2WordScreen.setText(wordsFromTables[1]);
    }

    private void defineType(String[] typesFromTables){
        mType1WordScreen.setText(typesFromTables[0]);
        mType2WordScreen.setText(typesFromTables[1]);
    }

    private void talkSpeech(Locale language, String speech){
        speak.allow(true);
        speak.speak(language, speech);
    }

    public void onEventBackgroundThread(final Boolean ready){

        Runnable task = new Runnable() {
            public void run() {

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if(ready){
                            ibSpeaker1.setVisibility(View.VISIBLE);
                            pbSpeaker1.setVisibility(View.GONE);
                            ibSpeaker2.setVisibility(View.VISIBLE);
                            pbSpeaker2.setVisibility(View.GONE);
                        }else{
                            ibSpeaker1.setVisibility(View.GONE);
                            pbSpeaker1.setVisibility(View.VISIBLE);
                            ibSpeaker2.setVisibility(View.VISIBLE);
                            pbSpeaker2.setVisibility(View.GONE);
                        }
                    }
                });
            }
        };

        worker.schedule(task, 8, TimeUnit.SECONDS);
    }
}