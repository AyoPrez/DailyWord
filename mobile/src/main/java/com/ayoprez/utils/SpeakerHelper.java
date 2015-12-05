package com.ayoprez.utils;

import android.content.Context;
import android.os.Build;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import de.greenrobot.event.EventBus;

import java.util.Locale;

/**
 * Created by AyoPrez on 23/08/15.
 */
public class SpeakerHelper implements TextToSpeech.OnInitListener{
    private static final String LOG_TAG = SpeakerHelper.class.getSimpleName();

    private TextToSpeech tts;
    private Locale local;
    private boolean ready = false;
    private boolean allowed = false;

    public SpeakerHelper(Context context){
        this.tts = new TextToSpeech(context, this);
        this.local = Locale.ENGLISH;
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
            EventBus.getDefault().post(ready);
            Log.d(LOG_TAG, "Success ready: " + ready);

        }else{
            ready = false;
            EventBus.getDefault().post(ready);
            Log.d(LOG_TAG, "Not success ready: " + ready);
        }
    }

    public void speak(final Locale language, final String text){
        Log.d(LOG_TAG, "Ready: " + ready);
        if(ready && allowed) {
            if(Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
                tts.setLanguage(language);
                tts.speak(text, TextToSpeech.QUEUE_FLUSH, null);
            }else{
                tts.setLanguage(language);
                tts.speak(text, TextToSpeech.QUEUE_FLUSH, null, null);
            }
        }
    }

    public void destroy(){
        tts.shutdown();
    }
}