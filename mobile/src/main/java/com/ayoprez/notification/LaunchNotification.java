package com.ayoprez.notification;

import android.app.Application;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;

import android.util.Log;
import com.ayoprez.deilylang.ErrorHandler;
import com.ayoprez.deilylang.R;
import com.ayoprez.deilylang.WordFromDatabase;
import com.ayoprez.wordscreen.WordScreen;
import com.crashlytics.android.Crashlytics;

import java.util.Random;

import de.greenrobot.event.EventBus;
import retrofit.RetrofitError;

/**
 * Created by AyoPrez on 12/04/15.
 */
public class LaunchNotification extends Application{
    private static final String TAG = LaunchNotification.class.getSimpleName();

    private Context context;

    public LaunchNotification(Context context){
        this.context = context;
        EventBus.getDefault().register(this);
    }

    public void launchNotification(Context context, WordFromDatabase words) throws Exception{
        NotificationManager nManager = (NotificationManager)
                context.getSystemService(Context.NOTIFICATION_SERVICE);

        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(
                context)
                .setColor(context.getResources().getColor(R.color.ColorPrimary))
                .setSmallIcon(
                    Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP ?
                        R.drawable.deilylang_lollipop_notification_icon : R.drawable.deilylang_notification_icon)
                .setContentTitle(context.getString(R.string.app_name))
                .setContentText(words.getWord()[0] + " -> " + words.getWord()[1]);

        int requestId = getRandomId();
        PendingIntent contentIntent = PendingIntent.getActivity(context, requestId, getTargetIntent(words),
                PendingIntent.FLAG_ONE_SHOT);

        notificationBuilder.setContentIntent(contentIntent);
        notificationBuilder.setAutoCancel(true);

        nManager.notify(requestId, notificationBuilder.build());
    }

    private Intent getTargetIntent(WordFromDatabase words){
        Intent targetIntent = new Intent(context, WordScreen.class);

        targetIntent.putExtras(setBundleData(words));
        return targetIntent;
    }

    private Bundle setBundleData(WordFromDatabase words){
        Bundle bundle = new Bundle();

        bundle.putInt("wordId", words.getId());
        bundle.putStringArray("words", words.getWord());
        bundle.putStringArray("types", words.getType());
        bundle.putString("level", words.getLevel());
        bundle.putStringArray("languages", words.getLanguages());
        return bundle;
    }

    public int getRandomId(){
        return new Random().nextInt(10000);
    }

    public void onEvent(WordFromDatabase words){
        try {
            launchNotification(context, words);
        }catch (Exception e){
            ErrorHandler.getInstance().Error(TAG, e.toString());
            ErrorHandler.getInstance().informUser(context, context.getString(R.string.errorDefault));
        }finally{
            unregisterEvent();
        }

    }

    private void unregisterEvent(){
        EventBus.getDefault().unregister(this);
    }

    public void onEvent(RetrofitError error){
        ErrorHandler.getInstance().informUser(context, context.getString(R.string.dgts__network_error));
    }

}