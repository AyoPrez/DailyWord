package com.ayoprez.services;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;

import com.ayoprez.deilylang.MainActivity;
import com.ayoprez.deilylang.R;

public class NotificationService extends Service {

    public NotificationService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(final Intent intent, int flags, int startId) {

        createNotification();

        this.stopSelf();
        return super.onStartCommand(intent, flags, startId);
    }

    private void createNotification(){
        NotificationManager nManager = (NotificationManager)
                getSystemService(Context.NOTIFICATION_SERVICE);

        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(
                getBaseContext())
                .setSmallIcon(R.drawable.icon)
                .setContentTitle("Finish")
                .setContentText("Countdown finished");

        Intent targetIntent = new Intent(getBaseContext(), MainActivity.class);

        PendingIntent contentIntent = PendingIntent.getActivity(getBaseContext(), 0, targetIntent,
                PendingIntent.FLAG_UPDATE_CURRENT);

        notificationBuilder.setContentIntent(contentIntent);
        notificationBuilder.setAutoCancel(true);

        nManager.notify(123, notificationBuilder.build());
    }

}