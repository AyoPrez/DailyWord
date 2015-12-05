package com.ayoprez.notification;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.ayoprez.database.UserMomentsRepository;

import java.util.List;
import java.util.Locale;

import com.ayoprez.deilylang.ErrorHandler;
import com.ayoprez.deilylang.Translations;
import com.ayoprez.login.SessionManager;
import deilyword.UserMoments;

public class DeviceBootReceiver extends BroadcastReceiver {
    private static final String TAG = DeviceBootReceiver.class.getSimpleName();

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals("android.intent.action.BOOT_COMPLETED")) {
           reSchedule(context);
        }else{
            Log.e("", "It is not a reboot intent");
            ErrorHandler.getInstance().Error(TAG, "It is not a reboot intent");
        }
    }

    //TODO It doesn't work well. Review in the future

    public void reSchedule(Context context){
        List<UserMoments> dataFromDatabase = new UserMomentsRepository().getAllMoments(context);

        for(UserMoments userMoments : dataFromDatabase){
            try {
                new StartAndCancelAlarmManager(context, userMoments);
            }catch(Exception e){
                ErrorHandler.getInstance().Error(TAG, e.toString());
            }
        }
    }
}