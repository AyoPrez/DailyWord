package com.ayoprez.notification;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.ayoprez.database.UserMomentsRepository;

import java.util.List;

import deilyquote.UserMoments;

public class DeviceBootReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals("android.intent.action.BOOT_COMPLETED")) {
           reSchedule(context);
        }else{
            Log.e("", "It is not a reboot intent");
        }
    }

    public void reSchedule(Context context){
        Intent alarmIntent = new Intent(context, AlarmReceiver.class);
        AlarmManager manager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);

        List<UserMoments> dataFromDatabase = new UserMomentsRepository().getAllMoments(context);
        int total = new UserMomentsRepository().getRowsCount(context);

        String time;
        PendingIntent pendingIntent;

        for(int i = 0; i < total; i++){
            try {
                time = dataFromDatabase.get(i).getTime();
                long id = dataFromDatabase.get(i).getId();
                pendingIntent = PendingIntent.getBroadcast(context, (int)id, alarmIntent, PendingIntent.FLAG_UPDATE_CURRENT);

                manager.setRepeating(
                        AlarmManager.RTC_WAKEUP,
                        System.currentTimeMillis() +
                                new TimeCalculator().getTimeFromNowUntilUserChoiceTime(time),
                        AlarmManager.INTERVAL_DAY,
                        pendingIntent);

                time = null;
                pendingIntent = null;
            }catch(Exception e){
                //Crashlytics
                Log.e("RescheduleException", "Error: " + e.getMessage());
            }
        }
    }
}