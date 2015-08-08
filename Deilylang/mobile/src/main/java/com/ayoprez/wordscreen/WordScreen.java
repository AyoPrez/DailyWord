package com.ayoprez.wordscreen;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.ayoprez.deilylang.R;
import com.ayoprez.deilylang.Utils;
import com.ayoprez.deilylang.WordFromDatabase;
import com.ayoprez.login.SessionManager;
import com.ayoprez.notification.ShortTimeStartAndCancel;

import java.util.Locale;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class WordScreen extends Activity {

    private Context mContext;
    private TextToSpeech textToSpeech;
    private String[] wordsFromTables;
    private String[] typesFromTables;
    private Locale languageNew;
    private Locale languageUser;
    private Bundle bundle;

    @InjectView(R.id.imageButton_WordScreen_1)
    ImageButton mImageButton1WordScreen;
    @InjectView(R.id.textView_WordScreen_1)
    TextView mWord1WordScreen;
    @InjectView(R.id.textView_Type_1)
    TextView mType1WordScreen;
    @InjectView(R.id.imageButton_WordScreen_2)
    ImageButton mImageButton2WordScreen;
    @InjectView(R.id.textView_WordScreen_2)
    TextView mWord2WordScreen;
    @InjectView(R.id.textView_Type_2)
    TextView mType2WordScreen;
    @InjectView(R.id.button_WordScreen_)
    Button mButtonSaveWord;
    @InjectView(R.id.button_WordScreen_2)
    Button mButtonRemainLater;
    @InjectView(R.id.button_report_WordScreen)
    Button mButtonReport;

    private String WORDS_ID_KEY = "wordId";
    private String WORDS_ARRAY_KEY = "words";
    private String TYPES_ARRAY_KEY = "types";
    private String LANGUAGES_ARRAY_KEY = "languages";
    private String LEVEL_KEY = "level";

    @OnClick(R.id.button_WordScreen_) void buttonDone(){
        Integer wordId = bundle.getInt(WORDS_ID_KEY);
        String languageNew = "english";
        String languageDevice = Locale.getDefault().getDisplayLanguage();
        String level = bundle.getString("level");

        //Poner que se cierre si se ha guardado la palabra
    }

    @OnClick(R.id.button_WordScreen_2) void buttonRemindMeLater(){
        if(bundle != null){
            WordFromDatabase wordFromDatabase = new WordFromDatabase(
                    bundle.getInt(WORDS_ID_KEY),
                    bundle.getStringArray(WORDS_ARRAY_KEY),
                    bundle.getString(LEVEL_KEY),
                    bundle.getStringArray(TYPES_ARRAY_KEY),
                    bundle.getStringArray(LANGUAGES_ARRAY_KEY));

            if(new ShortTimeStartAndCancel(mContext, wordFromDatabase).startAlarmManager20MinutesLater()){

                new Utils().Create_Dialog(mContext, getString(R.string.laterDialog),
                        mContext.getString(R.string.buttonAcceptDialog),
                        mContext.getString(R.string.successSavingDialogTitle));
            }else{
                new Utils().Create_Dialog(mContext, getString(R.string.errorSavingMoment),
                        mContext.getString(R.string.buttonAcceptDialog),
                        mContext.getString(R.string.errorSavingDialogTitle));
            }
        }else{
            //Crashlitics
            Log.e("DeilyLang", "Error: Button remind me later ");
        }
    }

    @OnClick(R.id.button_report_WordScreen) void buttonReport(){
        //TODO make it easier. Just push the button, see that the report is sending, and done
        //TODO http://stackoverflow.com/questions/2020088/sending-email-in-android-using-javamail-api-without-using-the-default-built-in-a/2033124#2033124
        Intent i = new Intent(Intent.ACTION_SEND);
        i.setType("message/rfc822");
        i.putExtra(Intent.EXTRA_EMAIL  , new String[]{"report@deilylang.com"});
        i.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.Subject));
        i.putExtra(Intent.EXTRA_TEXT   , getString(R.string.Body) + " '" + wordsFromTables[0] + "' " +
                                            getString(R.string.Body2) + " '" + wordsFromTables[1] + "'" +
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
        ButterKnife.inject(this);

        this.mContext = this;

        bundle = getIntent().getExtras();

        if(!new SessionManager(this).isLoggedIn()){
            mButtonSaveWord.setVisibility(View.GONE);
        }

        wordsFromTables = bundle.getStringArray(WORDS_ARRAY_KEY);
        typesFromTables = bundle.getStringArray(TYPES_ARRAY_KEY);

        defineLocales(bundle.getStringArray(LANGUAGES_ARRAY_KEY));

        mImageButton1WordScreen.setOnClickListener(listenerWordVoice1);
        mImageButton2WordScreen.setOnClickListener(listenerWordVoice2);

        mWord1WordScreen.setText(wordsFromTables[0]);
        mWord2WordScreen.setText(wordsFromTables[1]);

        mType1WordScreen.setText(typesFromTables[0]);
        mType2WordScreen.setText(typesFromTables[1]);
    }

    private void defineLocales(String[] languages){

        if(languages[0].toLowerCase().equals("english")){
            languageNew = Locale.ENGLISH;
        }else{
            if(languages[0].toLowerCase().equals("spanish")){
                languageNew = new Locale("es", "ES");
            }else{
                if(languages[0].toLowerCase().equals("german")){
                    languageNew = Locale.GERMAN;
                }
            }
        }

        if(languages[1].toLowerCase().equals("english")){
            languageUser = Locale.ENGLISH;
        }else{
            if(languages[1].toLowerCase().equals("spanish")){
                languageUser = new Locale("es", "ES");
            }else{
                if(languages[1].toLowerCase().equals("german")){
                    languageUser = Locale.GERMAN;
                }
            }
        }
    }

    private View.OnClickListener listenerWordVoice1 = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            textToSpeech = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
                @Override
                public void onInit(int status) {
                    talkSpeech(status, languageNew, wordsFromTables[0]);
                }
            });
        }
    };

    private View.OnClickListener listenerWordVoice2 = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            textToSpeech = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
                @Override
                public void onInit(int status) {
                    talkSpeech(status, languageUser, wordsFromTables[1]);
                }
            });
        }
    };

    private void talkSpeech(int status, Locale language, String speech){
        if (status == TextToSpeech.SUCCESS) {
            textToSpeech.setLanguage(language);
            textToSpeech.speak(speech, TextToSpeech.QUEUE_FLUSH, null);
        }
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
}