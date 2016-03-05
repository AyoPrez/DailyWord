package com.ayoprez.notification;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import com.ayoprez.deilylang.WordFromDatabase;
import com.ayoprez.login.SessionManager;

/**
 * Created by AyoPrez on 1/08/15.
 */
public class ShortTimeStartAndCancel extends TimeCalculator{

    private PendingIntent pendingIntent;
    private AlarmManager alarmManager;

    //TODO Dependencies
    //-new SessionManager

    public ShortTimeStartAndCancel(Context context, WordFromDatabase wordFromDatabase){
        Intent alarmIntent = startIntent(context, wordFromDatabase);

        int requestId = wordFromDatabase.getId();
        pendingIntent = PendingIntent.getBroadcast(context, requestId, alarmIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
    }

    private Intent startIntent(Context context, WordFromDatabase wordFromDatabase){
        Intent alarmIntent = new Intent(context, ShortTimeReceiver.class);
        alarmIntent.putExtra("types", wordFromDatabase.getType());
        alarmIntent.putExtra("words", wordFromDatabase.getWord());
        alarmIntent.putExtra("level", wordFromDatabase.getLevel());
        alarmIntent.putExtra("languageLearning", wordFromDatabase.getLanguages()[0]);
        alarmIntent.putExtra("languageDevice", wordFromDatabase.getLanguages()[1]);

        if(new SessionManager(context).getUserDetails().get("id") != null){
            alarmIntent.putExtra("id", Integer.valueOf(new SessionManager(context).getUserDetails().get("id")));
        }else{
            alarmIntent.putExtra("id", 0);
        }
        return alarmIntent;
    }

    public boolean startAlarmManager20MinutesLater() {
        try {
            alarmManager.set(AlarmManager.RTC_WAKEUP,
                    getMillisFromNowTo20MinutesLater(),
                    pendingIntent);
        }catch(Exception e){
            return false;
        }
        return true;
    }

    public void cancelAlarmManager() throws Exception{
        pendingIntent.cancel();
        alarmManager.cancel(pendingIntent);
    }
}