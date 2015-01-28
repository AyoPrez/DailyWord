package com.ayoprez.notification;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Created by Ayoze on 29/12/14.
 */
public class StartAndCancelAlarmManager {

    private PendingIntent pendingIntent;
    private Context context;
    private AlarmManager alarmManager;

    public StartAndCancelAlarmManager(Context context){
        this.context = context;
        Intent alarmIntent = new Intent(context, AlarmReceiver.class);
        pendingIntent = PendingIntent.getBroadcast(context, 0, alarmIntent, 0);

        alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
    }

    public boolean startAlarmManager(String time) {
        try {
            alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), getMillisFromStringHour(time), pendingIntent);
        }catch(Exception e){
            return false;
        }
        return true;
    }

    public void cancelAlarmManager() {
        alarmManager.cancel(pendingIntent);
    }

    private long getMillisFromStringHour(String time){
        SimpleDateFormat format = new SimpleDateFormat("HH:mm");

        long millis = 0;
        try {
            millis = format.parse(time).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return millis;
    }

}