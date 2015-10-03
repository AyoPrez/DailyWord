package com.ayoprez.notification;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import com.ayoprez.login.SessionManager;

import deilyquote.UserMoments;

/**
 * Created by Ayoze on 29/12/14.
 */
public class StartAndCancelAlarmManager extends TimeCalculator{

    private PendingIntent pendingIntent;
    private AlarmManager alarmManager;
    private SessionManager sessionManager;

    public StartAndCancelAlarmManager(Context context, UserMoments userMoments){
        this.sessionManager = new SessionManager(context);

        int requestId = (int) (userMoments.getId() - 0);
        pendingIntent = PendingIntent.getBroadcast(context, requestId, startIntent(context, userMoments), PendingIntent.FLAG_UPDATE_CURRENT);
        alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
    }

    public Intent startIntent(Context context, UserMoments userMoments){
        Intent alarmIntent = new Intent(context, AlarmReceiver.class);
        alarmIntent.putExtra("personality", userMoments.getPersonality());
        alarmIntent.putExtra("language", userMoments.getLanguage());
        if(sessionManager.getUserDetails().get("id") != null){
            alarmIntent.putExtra("id_u", sessionManager.getUserDetails().get("id"));
        }else{
            alarmIntent.putExtra("id_U", 0);
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