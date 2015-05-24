package com.ayoprez.deilylang;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.ayoprez.notification.StartAndCancelAlarmManager;

import java.util.Locale;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class WordScreen extends Activity {

    private Context mContext;
    private TextToSpeech textToSpeech;
    private String[] wordsFromTables;
    private String[] typesFromTables;

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

    @OnClick(R.id.button_WordScreen_) void listenerWordScreenButtonDone(){
//        if (new SQLMethods(mContext).ChangeDBConfValue("1", "English")) {
//            Toast.makeText(mContext, "Changed succesfully", Toast.LENGTH_LONG).show();
//        } else {
//            Toast.makeText(mContext, "Fail", Toast.LENGTH_LONG).show();
//        }
        Toast.makeText(this, "Que no, pringao", Toast.LENGTH_LONG).show();
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word_screen);
        ButterKnife.inject(this);

        this.mContext = this;

        Bundle bundle = getIntent().getExtras();

        wordsFromTables = bundle.getStringArray("words");
        typesFromTables = bundle.getStringArray("types");

        mImageButton1WordScreen.setOnClickListener(listenerWordScreenImageButton1);
        mImageButton2WordScreen.setOnClickListener(listenerWordScreenImageButton2);

        mWord1WordScreen.setText(wordsFromTables[0]);
        mWord2WordScreen.setText(wordsFromTables[1]);

        mType1WordScreen.setText(typesFromTables[0]);
        mType2WordScreen.setText(typesFromTables[1]);
    }

    private View.OnClickListener listenerWordScreenImageButton1 = new View.OnClickListener() {

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

    private View.OnClickListener listenerWordScreenImageButton2 = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            textToSpeech = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
                @Override
                public void onInit(int status) {
                    Locale languageNew;
                    if(Locale.getDefault().getDisplayLanguage() == "english"){ //Cambiar esto. Echar una pensada
                        languageNew = new Locale("es");
                    }else{
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
}