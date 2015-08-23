package com.ayoprez.deilyquote;

import android.content.Context;
import android.speech.tts.TextToSpeech;

import java.util.Locale;

/**
 * Created by AyoPrez on 23/08/15.
 */
public class SpeakerHelper implements TextToSpeech.OnInitListener{

    private TextToSpeech tts;
    private Locale local;
    private boolean ready = false;
    private boolean allowed = false;

    public SpeakerHelper(Context context, Locale locale){
        this.tts = new TextToSpeech(context, this);
        this.local = locale;
    }

    public boolean isAllowed(){
        return allowed;
    }

    public void allow(boolean allowed){
        this.allowed = allowed;
    }

    @Override
    public void onInit(int status) {
        if(status == TextToSpeech.SUCCESS){
            tts.setLanguage(local);
            ready = true;
        }else{
            ready = false;
        }
    }

    public void speak(String text){
        if(ready && allowed) {
            tts.speak(text, TextToSpeech.QUEUE_FLUSH, null);
        }
    }

    public void destroy(){
        tts.shutdown();
    }
}