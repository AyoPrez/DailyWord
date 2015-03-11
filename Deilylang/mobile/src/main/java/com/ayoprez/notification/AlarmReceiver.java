
package com.ayoprez.notification;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.ayoprez.deilylang.R;
import com.ayoprez.deilylang.WordScreen;

public class AlarmReceiver extends BroadcastReceiver {

    String[] words = {"Hola", "Hello"};

    @Override
    public void onReceive(Context context, Intent intent) {
        try {
            launchNotification(context, words);
        } catch (Exception e) {
            Log.e("NotificationException", e.getMessage());
            e.printStackTrace();
        }
    }

    private void launchNotification(Context context, String[] words) throws Exception{
        NotificationManager nManager = (NotificationManager)
                context.getSystemService(Context.NOTIFICATION_SERVICE);

        Log.e("Notification", "Notification up");

        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(
                context)
                .setSmallIcon(R.drawable.deilylang_notification_icon)
                .setContentTitle(context.getString(R.string.app_name))
                .setContentText(words[0] + " -> " + words[1]);

        Intent targetIntent = new Intent(context, WordScreen.class);

        Bundle bundle = new Bundle();
        bundle.putStringArray("words", words);
        targetIntent.putExtras(bundle);

        PendingIntent contentIntent = PendingIntent.getActivity(context, 0, targetIntent,
                PendingIntent.FLAG_UPDATE_CURRENT);

        notificationBuilder.setContentIntent(contentIntent);
        notificationBuilder.setAutoCancel(true);

        nManager.notify(123, notificationBuilder.build());
    }
}