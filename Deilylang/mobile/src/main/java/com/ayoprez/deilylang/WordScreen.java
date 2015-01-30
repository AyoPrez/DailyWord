package com.ayoprez.deilylang;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.Locale;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class WordScreen extends Activity {

    private Context mContext;
    private TextToSpeech textToSpeech;
    private String[] wordsFromTables;

    @InjectView(R.id.imageButton_WordScreen_1)
    ImageButton mImageButton1WordScreen;
    @InjectView(R.id.textView_WordScreen_1)
    TextView mWord1WordScreen;
    @InjectView(R.id.imageButton_WordScreen_2)
    ImageButton mImageButton2WordScreen;
    @InjectView(R.id.textView_WordScreen_2)
    TextView mWord2WordScreen;
    @InjectView(R.id.button_WordScreen_)
    Button mButton1WordScreen;
    @InjectView(R.id.button_WordScreen_2)
    Button mButton2WordScreen;

    @OnClick(R.id.button_WordScreen_) void listenerWordScreenButtonDone(){
//        if (new SQLMethods(mContext).ChangeDBConfValue("1", "English")) {
//            Toast.makeText(mContext, "Changed succesfully", Toast.LENGTH_LONG).show();
//        } else {
//            Toast.makeText(mContext, "Fail", Toast.LENGTH_LONG).show();
//        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word_screen);
        ButterKnife.inject(this);

        this.mContext = this;

        Bundle bundle = getIntent().getExtras();

        wordsFromTables = bundle.getStringArray("words");

        mImageButton1WordScreen.setOnClickListener(listenerWordScreenImageButton1);
        mImageButton2WordScreen.setOnClickListener(listenerWordScreenImageButton2);

        mWord1WordScreen.setText(wordsFromTables[0]);
        mWord2WordScreen.setText(wordsFromTables[1]);

//        mButton1WordScreen.setOnClickListener(listenerWordScreenButtonDone);
//        mButton2WordScreen.setOnClickListener(listenerWordScreenButtonMoreMinutes);
    }

//    private View.OnClickListener listenerWordScreenButtonDone = new View.OnClickListener() {
//
//        @Override
//        public void onClick(View v) {
//            if (new SQLMethods(mContext).ChangeDBConfValue("1", "English")) {
//                Toast.makeText(mContext, "Changed succesfully", Toast.LENGTH_LONG).show();
//            } else {
//                Toast.makeText(mContext, "Fail", Toast.LENGTH_LONG).show();
//            }
//        }
//    };

    private View.OnClickListener listenerWordScreenButtonMoreMinutes = new View.OnClickListener() {

        @Override
        public void onClick(View v) {

        }
    };

    private View.OnClickListener listenerWordScreenImageButton1 = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            textToSpeech = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
                @Override
                public void onInit(int status) {
                    if (status != TextToSpeech.ERROR) {
                        textToSpeech.setLanguage(new Locale("es"));
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
                    if (status != TextToSpeech.ERROR) {
                        textToSpeech.setLanguage(Locale.UK);
                        textToSpeech.speak(wordsFromTables[1], TextToSpeech.QUEUE_FLUSH, null);
                    }
                }
            });
        }
    };
}