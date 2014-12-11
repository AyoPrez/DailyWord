package com.ayoprez.services;

import android.app.IntentService;
import android.content.Intent;
import android.os.CountDownTimer;
import android.util.Log;

public class CountdownTimerService extends IntentService {

    public static final String TIMER_HOUR = "myHour";
    public static final String TIMER_MINUTE = "myMinute";
    public static final String TIMER_SECONDS = "mySecond";

    private String responseMinute = "";
    private String responseHour = "";
    private String responseSeconds = "";

    public CountdownTimerService() {
        super("DailyWordService");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i("·Service·", "Service created");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i("·Service·", "Service started");

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    private void calculateTime(long millisUntilFinished){

        responseHour = String.format("%02d", millisUntilFinished / 3600000) + ":";
        long restOfHour = millisUntilFinished % 3600000;

        responseMinute = String.format("%02d", restOfHour / 60000) + ":";

        long restOfMinute = restOfHour % 60000;

        responseSeconds = String.format("%02d", restOfMinute / 1000);
    }

    private void sendBroadcast(){
        Intent broadcastIntent = new Intent("com.AyoPrez.intent.action.PROCESS_RESPONSE");
        broadcastIntent.putExtra(TIMER_HOUR, responseHour);
        broadcastIntent.putExtra(TIMER_MINUTE, responseMinute);
        broadcastIntent.putExtra(TIMER_SECONDS, responseSeconds);

        sendBroadcast(broadcastIntent);
    }

    @Override
    protected void onHandleIntent(Intent intent) {

        new CountDownTimer(300000, 1000) {

            public void onTick(long millisUntilFinished) {
                Log.i("MillisToFinish", "" + millisUntilFinished);
                calculateTime(millisUntilFinished);
                sendBroadcast();
            }

            public void onFinish() {
                Log.e("Countdown finished", "Finish");
            }
        }.start();

    }
}