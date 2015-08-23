package com.ayoprez.notification;

import android.app.Application;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.ayoprez.deilyquote.QuoteScreen;
import com.ayoprez.deilyquote.R;

import de.greenrobot.event.EventBus;
import deilyquote.UserQuotes;

/**
 * Created by AyoPrez on 12/04/15.
 */
public class LaunchNotification extends Application{

    private Context context;

    public LaunchNotification(Context context){
        this.context = context;
        EventBus.getDefault().register(this);
    }

    public void launchNotification(Context context, UserQuotes quote) throws Exception{
        NotificationManager nManager = (NotificationManager)
                context.getSystemService(Context.NOTIFICATION_SERVICE);

        Log.e("Notification", "Notification up");

        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(context)
                .setSmallIcon(R.drawable.deilyquote_notification_icon)
                .setContentTitle(context.getString(R.string.app_name))
                .setContentText(context.getString(R.string.notification_text));

        Intent targetIntent = new Intent(context, QuoteScreen.class);

        Bundle bundle = new Bundle();
        bundle.putInt("quoteId", safeLongToInt(quote.getId()));
        bundle.putString("quote", quote.getQuote());
        bundle.putString("author", quote.getAuthor());
        bundle.putString("language", quote.getLanguage());
        targetIntent.putExtras(bundle);

        PendingIntent contentIntent = PendingIntent.getActivity(context, 0, targetIntent,
                PendingIntent.FLAG_UPDATE_CURRENT);

        notificationBuilder.setContentIntent(contentIntent);
        notificationBuilder.setAutoCancel(true);

        nManager.notify(123, notificationBuilder.build());
    }

    public void onEvent(UserQuotes quote){
        try {
            launchNotification(context, quote);
            EventBus.getDefault().unregister(this);
        }catch (Exception e){
            Log.e("NotificationException", "Error: " + e.getMessage());
            //Crashlytics
        }
    }

    public static int safeLongToInt(long number) {
        if (number < Integer.MIN_VALUE || number > Integer.MAX_VALUE) {
            throw new IllegalArgumentException
                    (number + " cannot be cast to int without changing its value.");
        }
        return (int) number;
    }
}