package com.ayoprez.deilylang;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by root on 29/12/14.
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

    public void startAlarmManager() {

        int interval = 30000;
        alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), interval, pendingIntent);
        Toast.makeText(context, "Alarm Set", Toast.LENGTH_SHORT).show();
    }

    public void cancelAlarmManager() {
        alarmManager.cancel(pendingIntent);
        Toast.makeText(context, "Alarm Canceled", Toast.LENGTH_SHORT).show();
    }

}