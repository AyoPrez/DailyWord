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
        int wordId = 90;

        try {
            new LaunchNotification(context).launchNotification(context, new WordFromDatabase(wordId, words, "basic", types, languages));
        } catch (Exception e) {
            Log.e("DeilyLangError", "NotificationButton  " + e);
            e.printStackTrace();
        }
    }


}
