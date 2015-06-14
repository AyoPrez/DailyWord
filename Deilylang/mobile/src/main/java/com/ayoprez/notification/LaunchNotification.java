package com.ayoprez.notification;

import android.app.Application;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.ayoprez.deilylang.R;
import com.ayoprez.deilylang.WordFromDatabase;
import com.ayoprez.wordscreen.WordScreen;

import java.util.Random;

import de.greenrobot.event.EventBus;
import retrofit.RetrofitError;

/**
 * Created by AyoPrez on 12/04/15.
 */
public class LaunchNotification extends Application{

    private Context context;

    public LaunchNotification(Context context){
        this.context = context;
        EventBus.getDefault().register(this);
    }

    private void launchNotification(Context context, WordFromDatabase words) throws Exception{
        NotificationManager nManager = (NotificationManager)
                context.getSystemService(Context.NOTIFICATION_SERVICE);

        Log.e("Notification", "Notification up");

        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(
                context)
                .setSmallIcon(R.drawable.deilylang_notification_icon)
                .setContentTitle(context.getString(R.string.app_name))
                .setContentText(words.getWord()[0] + " -> " + words.getWord()[1]);

        Intent targetIntent = new Intent(context, WordScreen.class);

        Bundle bundle = new Bundle();

        bundle.putInt("wordId", words.getId());
        bundle.putStringArray("words", words.getWord());
        bundle.putStringArray("types", words.getType());
        bundle.putString("level", words.getLevel());

        targetIntent.putExtras(bundle);

//Cambiar número pending
        PendingIntent contentIntent = PendingIntent.getActivity(context, getRandomId(), targetIntent,
                PendingIntent.FLAG_UPDATE_CURRENT);

        notificationBuilder.setContentIntent(contentIntent);
        notificationBuilder.setAutoCancel(true);

        nManager.notify(123, notificationBuilder.build());
    }

    public int getRandomId(){
        return new Random().nextInt(1000);
    }

    public void onEvent(WordFromDatabase words){
        try {
            launchNotification(context, words);
        }catch (Exception e){
            Log.e("NotificationException", "Error: " + e.getMessage() + ", Trace: " +
                    Log.getStackTraceString(e) + ",  " +
                    e.getStackTrace().toString() + "Error: " + e);
            //Crashlytics
        }
    }

    public void onEvent(RetrofitError error){ //Cambiar de sitio
        Log.e("TODO", "Aquí irá el comportamiento a hacer cuando no haya internet");
    }

}