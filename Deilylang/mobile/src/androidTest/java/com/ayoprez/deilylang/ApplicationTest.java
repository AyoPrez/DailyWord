package com.ayoprez.deilylang;

import android.app.AlertDialog;
import android.widget.Button;
import android.widget.TextView;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.shadows.ShadowAlertDialog;

import static junit.framework.Assert.assertEquals;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */

@RunWith(RobolectricTestRunner.class)
public class ApplicationTest extends Robolectric {

    //private ShadowCountDownTimer shadowCountDownTimer;

    private MainActivity activity;
    private Button pressMeButton;
    private TextView Hours;
    private TextView Minutes;
    private TextView Seconds;

    //private long timeValue30Minutes = 1800000;

    @Before
    public void setUp() throws Exception{

        activity = Robolectric.buildActivity(MainActivity.class).create().get();

        //shadowCountDownTimer = shadowOf(activity.countdownControl(timeValue30Minutes));

//		if(!shadowCountDownTimer.hasStarted()){
//			shadowCountDownTimer.start();
//		}else{
//			shadowCountDownTimer.invokeTick(timeValue30Minutes);
//		}

        pressMeButton = (Button) activity.findViewById(R.id.b_main);
        Hours = (TextView) activity.findViewById(R.id.tv_main_hour);
        Minutes = (TextView) activity.findViewById(R.id.tv_main_minute);
        Seconds = (TextView) activity.findViewById(R.id.tv_main_seconds);
    }

    @Test
    public void shouldStartDialogWhenButtonIsClicked(){
        pressMeButton.performClick();

        AlertDialog alert = ShadowAlertDialog.getLatestAlertDialog();
        ShadowAlertDialog sAlert = shadowOf(alert);

        assertThat(sAlert.getTitle().toString(), equalTo(activity.getString(R.string.dialog_title)));
    }

    //	@Test
//	public void checkThatCountDownIntervalIsASecond(){
//		assertEquals(shadowCountDownTimer.getCountDownInterval(), 1000);
//	}
//
    @Test
    public void hoursIndicateANumber(){
        assertEquals(Hours.getText().toString(), "00:");
    }

    @Test
    public void minutesIndicateANumber(){
        assertEquals(Minutes.getText().toString(), "30:");
    }

    @Test
    public void secondsIndicateANumber(){
        assertEquals(Seconds.getText().toString(), "00");
    }
//
//	@Test
//	public void activityDestroyedTest(){
//		MainActivity activity2 = Robolectric.buildActivity(MainActivity.class).create().destroy().get();
//
//		assertTrue(activity2.isDestroyed());
//	}
//
//	@Test
//	public void ifCountDownCountAfterOnStop(){
//		ActivityController<MainActivity> controller = Robolectric.buildActivity(MainActivity.class).create().start();
//		MainActivity activity2 = controller.get();
//
//		//shadowCountDownTimer = shadowOf(((MainActivity) activity2).countdownControl(timeValue30Minutes));
//
//		assertTrue(shadowCountDownTimer.hasStarted());
//
//		activity2 = controller.destroy().get();
//
//		//shadowCountDownTimer = shadowOf(((MainActivity) activity2).countdownControl(timeValue30Minutes));
//
//		assertTrue(shadowCountDownTimer.hasStarted());
//	}

}