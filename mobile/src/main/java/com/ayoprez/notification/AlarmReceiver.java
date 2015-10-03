
package com.ayoprez.notification;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.ayoprez.deilylang.Translations;
import com.ayoprez.restfulservice.GetWords;
import com.crashlytics.android.Crashlytics;

public class AlarmReceiver extends BroadcastReceiver{

    public Context context;
    public Translations translations = new Translations();

    @Override
    public void onReceive(Context context, Intent intent) {
        this.context = context;
        try {
            getWords(context, intent);
        } catch (Exception e) {
            Crashlytics.getInstance().log("Error AlarmReceiver: " + e);
            e.printStackTrace();
        }
    }

    private void getWords(Context context, Intent intent)throws Exception{
        String level = translations.translateLevel(intent.getStringExtra("level"));
        String languageLearning = intent.getStringExtra("languageLearning");
        String languageDevice = translations.translateLanguage(intent.getStringExtra("languageDevice"));
        int id = intent.getIntExtra("id", 0);

        if(languageLearning.equals("eng")){
            new GetWords(context).sendEnglishWordRequest(level, id, languageDevice);
        }else{
            if(languageLearning.equals("spa")){
                new GetWords(context).sendSpanishWordRequest(level, id, languageDevice);
            }
        }
    }
}