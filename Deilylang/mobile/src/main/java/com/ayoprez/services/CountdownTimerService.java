package com.ayoprez.services;

import android.app.Service;
import android.content.Intent;
import android.os.CountDownTimer;
import android.os.IBinder;
import android.util.Log;

public class CountdownTimerService extends Service {

    public static final String TIMER_HOUR = "myHour";
    public static final String TIMER_MINUTE = "myMinute";
    public static final String TIMER_SECONDS = "mySecond";

    private String responseMinute = "";
    private String responseHour = "";
    private String responseSeconds = "";

    @Override
    public void onCreate() {
        super.onCreate();
        countdownTimer();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    private void calculateTime(long millisUntilFinished) {

        responseHour = String.format("%02d", millisUntilFinished / 3600000) + ":";
        long restOfHour = millisUntilFinished % 3600000;

        responseMinute = String.format("%02d", restOfHour / 60000) + ":";

        long restOfMinute = restOfHour % 60000;

        responseSeconds = String.format("%02d", restOfMinute / 1000);
    }

    private void sendIntentBroadcast() {
        Intent broadcastIntent = new Intent("com.AyoPrez.intent.action.PROCESS_RESPONSE");
        broadcastIntent.putExtra(TIMER_HOUR, responseHour);
        broadcastIntent.putExtra(TIMER_MINUTE, responseMinute);
        broadcastIntent.putExtra(TIMER_SECONDS, responseSeconds);

        sendBroadcast(broadcastIntent);
    }

    private void launchNotification() {
        Intent service = new Intent(this, NotificationService.class);

        startService(service);
    }

    public CountDownTimer countdownTimer() {
        return new CountDownTimer(30000, 1000) {

            public void onTick(long millisUntilFinished) {
                calculateTime(millisUntilFinished);
                Log.i("ResponseSeconds", responseSeconds);
                sendIntentBroadcast();
            }

            public void onFinish() {
                Log.e("Countdown finished", "Finish");
                launchNotification();
            }
        }.start();

    }
}