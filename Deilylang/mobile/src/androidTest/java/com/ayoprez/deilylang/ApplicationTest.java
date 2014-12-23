package com.ayoprez.deilylang;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.widget.Button;
import android.widget.TextView;

import com.ayoprez.services.CountdownTimerService;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowActivity;
import org.robolectric.shadows.ShadowAlertDialog;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
@Config(emulateSdk = 18)
@RunWith(RobolectricTestRunner.class)
public class ApplicationTest extends Robolectric {

    private MainActivity activity;
    private Button pressMeButton;
    private TextView Hours;
    private TextView Minutes;
    private TextView Seconds;

    @Before
    public void setUp() throws Exception {

        activity = Robolectric.buildActivity(MainActivity.class).create().get();

        //activity.sendCountdownService();

        pressMeButton = (Button) activity.findViewById(R.id.b_main);
        Hours = (TextView) activity.findViewById(R.id.tv_main_hour);
        Minutes = (TextView) activity.findViewById(R.id.tv_main_minute);
        Seconds = (TextView) activity.findViewById(R.id.tv_main_seconds);
    }

    @Test
    public void shouldStartDialogWhenButtonIsClicked() {
        pressMeButton.performClick();

        AlertDialog alert = ShadowAlertDialog.getLatestAlertDialog();
        ShadowAlertDialog sAlert = shadowOf(alert);

        assertThat(sAlert.getTitle().toString(), equalTo(activity.getString(R.string.dialog_title)));
    }

    @Test
    public void hoursIndicateANumber() {
        Hours.setText("00:");
        assertEquals(Hours.getText().toString(), "00:");
    }

    @Test
    public void minutesIndicateANumber() {
        Minutes.setText("30:");
        assertEquals(Minutes.getText().toString(), "30:");
    }

    @Test
    public void secondsIndicateANumber() {
        Seconds.setText("00");
        assertEquals(Seconds.getText().toString(), "00");
    }

    @Test
    public void testService(){
        Activity activity = new Activity();
        Intent intent = new Intent(activity, CountdownTimerService.class);
        activity.startService(intent);

        ShadowActivity shadowActivity = Robolectric.shadowOf(activity);
        Intent startedIntent = shadowActivity.getNextStartedService();
        assertNotNull(startedIntent);
    }

    @Test
    public void testServiceAfterDestroy(){
        Activity activity = new Activity();
        Intent intent = new Intent(activity, CountdownTimerService.class);
        activity.startService(intent);

        ShadowActivity shadowActivity = Robolectric.shadowOf(activity);
        shadowActivity.onDestroy();
        Intent startedIntent = shadowActivity.getNextStartedService();
        assertNotNull(startedIntent);
    }

}