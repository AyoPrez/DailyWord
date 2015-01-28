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

import com.ayoprez.database.SQLMethods;

import java.util.Locale;

public class WordScreen extends Activity {

    private Context mContext;
    private TextToSpeech textToSpeech;
    private String[] wordsFromTables;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word_screen);

        this.mContext = this;

        ImageButton imageButton1WordScreen = (ImageButton) findViewById(R.id.imageButton_WordScreen_1);
        ImageButton imageButton2WordScreen = (ImageButton) findViewById(R.id.imageButton_WordScreen_2);
        TextView word1WordScreen = (TextView) findViewById(R.id.textView_WordScreen_1);
        TextView word2WordScreen = (TextView) findViewById(R.id.textView_WordScreen_2);
        Button button1WordScreen = (Button) findViewById(R.id.button_WordScreen_);
        Button button2WordScreen = (Button) findViewById(R.id.button_WordScreen_2);

        wordsFromTables = new SQLMethods(mContext).getWordFromTables();

        imageButton1WordScreen.setOnClickListener(listenerWordScreenImageButton1);
        imageButton2WordScreen.setOnClickListener(listenerWordScreenImageButton2);

        word1WordScreen.setText(wordsFromTables[0]);
        word2WordScreen.setText(wordsFromTables[1]);

        button1WordScreen.setOnClickListener(listenerWordScreenButtonDone);
        button2WordScreen.setOnClickListener(listenerWordScreenButtonMoreMinutes);
    }

    private View.OnClickListener listenerWordScreenButtonDone = new View.OnClickListener(){

        @Override
        public void onClick(View v) {
            if(new SQLMethods(mContext).ChangeDBConfValue("1", "English")){
                Toast.makeText(mContext, "Changed succesfully", Toast.LENGTH_LONG).show();
            }else{
                Toast.makeText(mContext, "Fail", Toast.LENGTH_LONG).show();
            }
        }
    };

    private View.OnClickListener listenerWordScreenButtonMoreMinutes = new View.OnClickListener(){

        @Override
        public void onClick(View v) {

        }
    };

    private View.OnClickListener listenerWordScreenImageButton1 = new View.OnClickListener(){

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

    private View.OnClickListener listenerWordScreenImageButton2 = new View.OnClickListener(){

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
