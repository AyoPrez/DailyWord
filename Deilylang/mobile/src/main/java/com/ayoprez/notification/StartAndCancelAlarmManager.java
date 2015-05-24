package com.ayoprez.notification;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import java.util.Locale;
import java.util.Random;

import deilyword.UserMoments;

/**
 * Created by Ayoze on 29/12/14.
 */
public class StartAndCancelAlarmManager extends TimeCalculator{

    private PendingIntent pendingIntent;
    private AlarmManager alarmManager;

    public StartAndCancelAlarmManager(Context context, UserMoments userMoments){
        Intent alarmIntent = new Intent(context, AlarmReceiver.class);
        alarmIntent.putExtra("level", userMoments.getLevel());
        alarmIntent.putExtra("languageLearning", userMoments.getLanguage());
        alarmIntent.putExtra("languageDevice", Locale.getDefault().getDisplayLanguage());

        int requestId = (int) (userMoments.getId() - 1);
//        int requestId = getRandomId();
        pendingIntent = PendingIntent.getBroadcast(context, requestId, alarmIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
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

    public int getRandomId(){
        return new Random().nextInt(1000);
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