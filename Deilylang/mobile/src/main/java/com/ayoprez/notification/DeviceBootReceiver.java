package com.ayoprez.notification;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.ayoprez.database.UserMomentsRepository;

import java.util.List;

import deilyword.UserMoments;

public class DeviceBootReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals("android.intent.action.BOOT_COMPLETED")) {

            Intent alarmIntent = new Intent(context, AlarmReceiver.class);

            PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, alarmIntent, 0);

            AlarmManager manager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);

            List<UserMoments> dataFromDatabase = new UserMomentsRepository().getAllMoments(context);
            int total = new UserMomentsRepository().getRowsCount(context);

            for(int i = 0; i < total; i++){
                try {
                    manager.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(),
                            System.currentTimeMillis() +
                                    new TimeCalculator().getTimeFromNowUntilUserChoiceTime(dataFromDatabase.get(i).getTime()),
                            pendingIntent);
                }catch(Exception e){

                }
            }

        }
    }
}
