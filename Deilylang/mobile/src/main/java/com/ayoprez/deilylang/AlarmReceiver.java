package com.ayoprez.deilylang;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.ayoprez.services.NotificationService;

public class AlarmReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        launchNotification(context);
    }

    private void launchNotification(Context context) {
        Intent service = new Intent(context, NotificationService.class);
        context.startService(service);
    }
}
