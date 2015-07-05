package com.ayoprez.wordscreen;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.ayoprez.deilylang.R;
import com.ayoprez.deilylang.Utils;
import com.ayoprez.login.SessionManager;
import com.ayoprez.notification.StartAndCancelAlarmManager;

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

    @OnClick(R.id.button_WordScreen_) void buttonDone(){
        Integer wordId = bundle.getInt(WORDS_ID_KEY);
        String languageNew = "english";
        String languageDevice = Locale.getDefault().getDisplayLanguage();
        String level = bundle.getString("level");

        //Poner que se cierre si se ha guardado la palabra
    }

    @OnClick(R.id.button_WordScreen_2) void buttonRemindMeLater(){
        if(new StartAndCancelAlarmManager(mContext, null).startAlarmManager20MinutesLater()){
            new Utils().Create_Dialog(mContext, mContext.getString(R.string.successSavingMoment),
                    mContext.getString(R.string.buttonAcceptDialog),
                    mContext.getString(R.string.successSavingDialogTitle));
        }else{
            new Utils().Create_Dialog(mContext, mContext.getString(R.string.errorSavingMoment),
                    mContext.getString(R.string.buttonAcceptDialog),
                    mContext.getString(R.string.errorSavingDialogTitle));
        }
    }

    @OnClick(R.id.button_report_WordScreen) void buttonReport(){

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word_screen);
        ButterKnife.inject(this);

        this.mContext = this;

        bundle = getIntent().getExtras();

        if(!new SessionManager(this).isLoggedIn()){
            mButtonSaveWord.setVisibility(View.GONE);
        }

        wordsFromTables = bundle.getStringArray(WORDS_ARRAY_KEY);
        typesFromTables = bundle.getStringArray(TYPES_ARRAY_KEY);

        mImageButton1WordScreen.setOnClickListener(listenerWordVoice1);
        mImageButton2WordScreen.setOnClickListener(listenerWordVoice2);

        mWord1WordScreen.setText(wordsFromTables[0]);
        mWord2WordScreen.setText(wordsFromTables[1]);

        mType1WordScreen.setText(typesFromTables[0]);
        mType2WordScreen.setText(typesFromTables[1]);
    }

    private View.OnClickListener listenerWordVoice1 = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            textToSpeech = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
                @Override
                public void onInit(int status) {
                    if (status == TextToSpeech.SUCCESS) {
                        textToSpeech.setLanguage(new Locale(Locale.getDefault().getDisplayLanguage()));
                        textToSpeech.speak(wordsFromTables[0], TextToSpeech.QUEUE_FLUSH, null);
                    }
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
                    Locale languageNew = null;
                    if(Locale.getDefault().getDisplayLanguage() != Locale.UK.getDisplayLanguage()){
                        languageNew = Locale.UK;
                    }
                    if (status == TextToSpeech.SUCCESS) {
                        textToSpeech.setLanguage(languageNew);
                        textToSpeech.speak(wordsFromTables[1], TextToSpeech.QUEUE_FLUSH, null);
                    }
                }
            });
        }
    };

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
}