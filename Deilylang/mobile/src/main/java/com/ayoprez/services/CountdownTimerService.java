package com.ayoprez.services;

import android.app.IntentService;
import android.content.Intent;
import android.os.CountDownTimer;
import android.util.Log;

import com.ayoprez.deilylang.MainActivity;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p/>
 * helper methods.
 */
public class CountdownTimerService extends IntentService {

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
    public void onLowMemory() {
        super.onLowMemory();

    }

//    public CountDownTimer countdownControl(long startTime){
//        return new CountDownTimer(startTime, 1000) {
//
//            public void onTick(long millisUntilFinished) {
//                responseHour = String.format("%02d", millisUntilFinished / 3600000) + ":";
//                long restOfHour = millisUntilFinished % 3600000;
//
//                responseMinute = String.format("%02d", restOfHour / 60000) + ":";
//
//                long restOfMinute = restOfHour % 60000;
//
//                responseSeconds = String.format("%02d", restOfMinute / 1000);
//                notify();
//            }
//
//            public void onFinish() {
//                Log.e("Countdown finished", "Finish");
//            }
//        }.start();
//    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }


    public static final String TIMER_HOUR = "myHour";
    public static final String TIMER_MINUTE = "myMinute";
    public static final String TIMER_SECONDS = "mySecond";

    private String responseMinute = "";
    private String responseHour = "";
    private String responseSeconds = "";

    @Override
    protected void onHandleIntent(Intent intent) {

        //		countdownControl(300000);

        new CountDownTimer(300000, 1000) {

            public void onTick(long millisUntilFinished) {

                Intent broadcastIntent = new Intent(MainActivity.CountdownTimerRequestReceiver.PROCESS_RESPONSE);

                responseHour = String.format("%02d", millisUntilFinished / 3600000) + ":";
                long restOfHour = millisUntilFinished % 3600000;

                broadcastIntent.putExtra(TIMER_HOUR, responseHour);

                responseMinute = String.format("%02d", restOfHour / 60000) + ":";

                long restOfMinute = restOfHour % 60000;

                broadcastIntent.putExtra(TIMER_MINUTE, responseMinute);

                responseSeconds = String.format("%02d", restOfMinute / 1000);

                broadcastIntent.putExtra(TIMER_SECONDS, responseSeconds);

                sendBroadcast(broadcastIntent);
            }

            public void onFinish() {
                Log.e("Countdown finished", "Finish");
            }
        }.start();

    }
}