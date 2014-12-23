package com.ayoprez.deilylang;

import android.app.AlertDialog;
import android.widget.Button;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowAlertDialog;

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

    @Before
    public void setUp() throws Exception {

        activity = Robolectric.buildActivity(MainActivity.class).create().get();

        pressMeButton = (Button) activity.findViewById(R.id.b_main);
    }

    @Test
    public void shouldStartDialogWhenButtonIsClicked() {
        pressMeButton.performClick();

        AlertDialog alert = ShadowAlertDialog.getLatestAlertDialog();
        ShadowAlertDialog sAlert = shadowOf(alert);

        assertThat(sAlert.getTitle().toString(), equalTo(activity.getString(R.string.dialog_title)));
    }
}