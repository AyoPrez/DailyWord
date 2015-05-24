
package com.ayoprez.notification;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.ayoprez.restfulservice.GetWords;

public class AlarmReceiver extends BroadcastReceiver{

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
        String languageLearning = intent.getStringExtra("languageLearning");
        String languageDevice = intent.getStringExtra("languageDevice");
        new GetWords(context).sendWordRequest(level, languageDevice, languageLearning);
    }
}