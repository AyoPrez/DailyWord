package com.ayoprez.notification;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.ayoprez.deilylang.Utils;
import com.ayoprez.login.SessionManager;

import java.util.Locale;

import deilyword.UserMoments;

/**
 * Created by Ayoze on 29/12/14.
 */
public class StartAndCancelAlarmManager extends TimeCalculator{

    private PendingIntent pendingIntent;
    private AlarmManager alarmManager;
    private SessionManager sessionManager;
    private String id_U;

    public StartAndCancelAlarmManager(Context context, UserMoments userMoments){
        Intent alarmIntent = startIntent(context, userMoments);

        this.sessionManager = new SessionManager(context);
        id_U = sessionManager.getUserDetails().get("id");

        int requestId = (int) (userMoments.getId() - 0);
        pendingIntent = PendingIntent.getBroadcast(context, requestId, alarmIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
    }

    private Intent startIntent(Context context, UserMoments userMoments){
        Intent alarmIntent = new Intent(context, AlarmReceiver.class);
        alarmIntent.putExtra("level", userMoments.getLevel());
        alarmIntent.putExtra("languageLearning", new Utils().translateLanguagesToISO(userMoments.getLanguage()));
        alarmIntent.putExtra("languageDevice", Locale.getDefault().getDisplayLanguage());

        if(id_U != null){
            Log.e("DeilyLang", "(Should be 1)Id_U: " + id_U + ", e integer: " + Integer.valueOf(id_U));
            alarmIntent.putExtra("id", Integer.valueOf(id_U));
        }else{
            alarmIntent.putExtra("id", 0);
        }
        return alarmIntent;
    }

    public boolean startAlarmManager(String time) {
        try {
            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,
                    System.currentTimeMillis() + getTimeFromNowUntilUserChoiceTime(time),
                    AlarmManager.INTERVAL_DAY,
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