package com.ayoprez.notification;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.ayoprez.deilylang.WordFromDatabase;

/**
 * Created by AyoPrez on 1/08/15.
 */
public class ShortTimeReceiver extends BroadcastReceiver {

    public Context context;

    @Override
    public void onReceive(Context context, Intent intent) {
        this.context = context;
        try {
            getWords(context, intent);
        } catch (Exception e) {
            Log.e("NotificationException", "Error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void getWords(Context context, Intent intent)throws Exception{
        String level = intent.getStringExtra("level");
        String[] words = intent.getStringArrayExtra("words");
        String[] types = intent.getStringArrayExtra("types");
        String languageLearning = intent.getStringExtra("languageLearning");
        String languageDevice = intent.getStringExtra("languageDevice");
        String[] languages = {languageLearning, languageDevice};
        int id = intent.getIntExtra("id", 0);

        WordFromDatabase wordFromDatabase = new WordFromDatabase(id, words, level, types, languages);

        new LaunchNotification(context).launchNotification(context, wordFromDatabase);
    }
}
