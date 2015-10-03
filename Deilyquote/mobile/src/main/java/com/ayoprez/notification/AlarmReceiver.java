
package com.ayoprez.notification;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.ayoprez.restfulservice.QuoteGet;

public class AlarmReceiver extends BroadcastReceiver{

    public Context context;

    @Override
    public void onReceive(Context context, Intent intent) {
        this.context = context;
        try {
            getQuote(intent);
        } catch (Exception e) {
            Log.e("NotificationException", "Error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void getQuote(Intent intent)throws Exception{
        String personality = new PersonalityTranslation().translatePersonality(intent.getStringExtra("personality"));
        String language = new LanguageTranslation().translateLanguage(intent.getStringExtra("language"));
        int id_u = intent.getIntExtra("id_u", 0);
        if(!personality.equals("") || personality != null){
            if(personality.equals("all")) {
                new QuoteGet().getQuoteWithoutPersonality(context, language, id_u);
            }else {
                new QuoteGet().getQuoteWithPersonality(context, personality, language, id_u);
            }
        }
    }
}