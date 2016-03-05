
package com.ayoprez.notification;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.ayoprez.deilylang.ErrorHandler;
import com.ayoprez.deilylang.Translations;
import com.ayoprez.restfulservice.GetWords;

public class AlarmReceiver extends BroadcastReceiver{

    private static final String LOG_TAG = AlarmReceiver.class.getSimpleName();

    public Context context;

    //TODO Dependencies
    //-new GetWords

    @Override
    public void onReceive(Context context, Intent intent) {
        this.context = context;
        try {
            getWords(context, intent);
        } catch (Exception e) {
            ErrorHandler.getInstance().Error(LOG_TAG, e.toString());
        }
    }

    private void getWords(Context context, Intent intent)throws Exception{
        String level = Translations.translateLevel(intent.getStringExtra("level"));
        String languageLearning = intent.getStringExtra("languageLearning");

        //TODO Revisar porque traduzco el lenguaje del dispositivo
        String languageDevice = Translations.translateLanguageFromSpanishToEnglish(intent.getStringExtra("languageDevice"));

        int id = intent.getIntExtra("id", 0);

        if(languageLearning.equals("eng")){
            new GetWords(context).sendEnglishWordRequest(level, id, languageDevice);
        }else if(languageLearning.equals("spa")){
                new GetWords(context).sendSpanishWordRequest(level, id, languageDevice);
        }
    }
}