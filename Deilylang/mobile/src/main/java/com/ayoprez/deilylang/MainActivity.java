package com.ayoprez.deilylang;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.ayoprez.database.SQLMethods;
import com.ayoprez.database.SQLUtils;
import com.ayoprez.newMoment.NewMomentActivity;

import java.util.ArrayList;

public class MainActivity extends Activity {

    private Button reviewMomentsButton;
    private Context mContext;

    private PendingIntent pendingIntent;
    private ListView reviewList;
    private ReviewAdapter reviewAdapter;
    private ArrayList<UserData> String_Review;

    private SQLMethods sqlMethods;
    private SQLUtils sqlUtils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        mContext = this;
        this.reviewMomentsButton = (Button) findViewById(R.id.b_main);
        this.String_Review = new ArrayList<UserData>();
        this.sqlMethods = new SQLMethods(this);
        this.sqlUtils = new SQLUtils(this);

        /* Retrieve a PendingIntent that will perform a broadcast */
        Intent alarmIntent = new Intent(MainActivity.this, AlarmReceiver.class);
        pendingIntent = PendingIntent.getBroadcast(MainActivity.this, 0, alarmIntent, 0);

        //startAlarmManager();

        showReviewList();
        momentsButton();
    }

    private void momentsButton() {
        reviewMomentsButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent newMomentIntent = new Intent(mContext, NewMomentActivity.class);
                startActivity(newMomentIntent);
            }

        });
    }

    public void startAlarmManager() {
        AlarmManager manager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        int interval = 30000;

        manager.setInexactRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), interval, pendingIntent);
        Toast.makeText(this, "Alarm Set", Toast.LENGTH_SHORT).show();
    }

    public void cancelAlarmManager() {
        AlarmManager manager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        manager.cancel(pendingIntent);
        Toast.makeText(this, "Alarm Canceled", Toast.LENGTH_SHORT).show();
    }

    private void showReviewList(){
        reviewList = (ListView) findViewById(R.id.reviewList);

        String[][] DB_Data = sqlMethods.Recover_Data_Review();

        UserData ss = null;

        for(int h = 0; h < sqlUtils.getRowsCount(); h++){
            ss = new UserData(DB_Data[h][0], DB_Data[h][3], DB_Data[h][2], DB_Data[h][1]);
            String_Review.add(ss);
        }

        reviewAdapter = new ReviewAdapter(this, String_Review);

        reviewList.setAdapter(reviewAdapter);
    }

}