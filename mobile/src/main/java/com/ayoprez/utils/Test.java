package com.ayoprez.utils;

import android.content.Context;
import android.util.Log;

import com.ayoprez.deilylang.WordFromDatabase;
import com.ayoprez.notification.LaunchNotification;

/**
 * Created by AyoPrez on 10.10.15.
 */
public class Test {

    public static void testNotification(Context context){
        String[] words = {"Word", "Palabra"};
        String[] types = {"Noun", "Sustantivo"};
        String[] languages = {"English", "Spanish"};
        String[] articles = {"", "La"};
        int wordId = 90;

        try {
            new LaunchNotification(context).launchNotification(context, new WordFromDatabase(wordId, words, "basic", types, languages, articles));
        } catch (Exception e) {
            Log.e("DeilyLangError", "NotificationButton  " + e);
            e.printStackTrace();
        }
    }

    public static void testNotificationError(Context context){
        String[] words = {"Error", "Error"};
        String[] types = {"Noun", "Sustantivo"};
        String[] languages = {"English", "Spanish"};
        String[] article = {"", "El"};
        int wordId = 90;

        try {
            new LaunchNotification(context).launchNotification(context, new WordFromDatabase(wordId, words, "basic", types, languages, article));
        } catch (Exception e) {
            Log.e("DeilyLangError", "NotificationButton  " + e);
            e.printStackTrace();
        }
    }

}
