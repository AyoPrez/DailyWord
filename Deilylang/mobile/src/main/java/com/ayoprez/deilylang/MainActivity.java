package com.ayoprez.deilylang;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.ayoprez.services.CountdownTimerService;

public class MainActivity extends Activity {

    public TextView timerHour;
    public TextView timerMinute;
    public TextView timerSeconds;
    private Button reviewMomentsButton;
    private Context mContext;
    private CountdownTimerRequestReceiver countdownService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        mContext = this;
        this.timerHour = (TextView) findViewById(R.id.tv_main_hour);
        this.timerMinute = (TextView) findViewById(R.id.tv_main_minute);
        this.timerSeconds = (TextView) findViewById(R.id.tv_main_seconds);
        this.reviewMomentsButton = (Button) findViewById(R.id.b_main);
        this.countdownService = new CountdownTimerRequestReceiver();

        IntentFilter filter = new IntentFilter(CountdownTimerRequestReceiver.PROCESS_RESPONSE);
        filter.addCategory(Intent.CATEGORY_DEFAULT);

        registerReceiver(countdownService, filter);

        appTimerControls();
        momentsButton();
    }

    private void appTimerControls(){

        Intent intent = new Intent(this, CountdownTimerService.class);
        startService(intent);

    }

    private void momentsButton(){
        reviewMomentsButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {

                new AlertDialog.Builder(mContext).setTitle(R.string.dialog_title).show();
            }

        });
    }


    public class CountdownTimerRequestReceiver extends BroadcastReceiver {

        public static final String PROCESS_RESPONSE = "com.AyoPrez.intent.action.PROCESS_RESPONSE";

        @Override
        public void onReceive(Context context, Intent intent) {
            String responseHour = intent.getStringExtra(CountdownTimerService.TIMER_HOUR);
            String responseMinutes = intent.getStringExtra(CountdownTimerService.TIMER_MINUTE);
            String responseSeconds = intent.getStringExtra(CountdownTimerService.TIMER_SECONDS);

            Log.d("MainActivityResponse", "MARHour: " + responseHour);
            Log.d("MainActivityResponse", "MARMinute: " + responseMinutes);


            timerHour.setText(responseHour);
            timerMinute.setText(responseMinutes);
            timerSeconds.setText(responseSeconds);
        }
    }
}