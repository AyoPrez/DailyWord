package com.ayoprez.deilylang;

import android.app.Dialog;
import android.widget.Button;

import com.ayoprez.newMoment.NewMomentActivity;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.shadows.ShadowDialog;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by Ayoze on 16/01/15.
 */
@RunWith(CustomRobolectric.class)
public class NewMomentActivityTest extends Robolectric {

    private NewMomentActivity momentActivity;

    @Before
    public void setUp() throws Exception {
        momentActivity = Robolectric.buildActivity(NewMomentActivity.class).create().get();
    }

    @Test
    public void checkActivityIsNotNull() throws Exception{
        assertNotNull(momentActivity);
    }

    @Test
    public void testButtons(){
        Button languageButton = (Button) momentActivity.findViewById(R.id.b_language);
        assertEquals(languageButton.getText().toString() , momentActivity.getApplication().getString(R.string.button_language));
        assertTrue(languageButton.performClick());

        Button levelButton = (Button) momentActivity.findViewById(R.id.b_level);
        assertEquals(levelButton.getText().toString() , momentActivity.getApplication().getString(R.string.button_level));
        assertTrue(levelButton.performClick());

        Button timeButton = (Button) momentActivity.findViewById(R.id.b_time);
        assertEquals(timeButton.getText().toString() , momentActivity.getApplication().getString(R.string.button_time));
        assertTrue(timeButton.performClick());

        Button acceptButton = (Button) momentActivity.findViewById(R.id.b_accept);
        assertEquals(acceptButton.getText().toString() , momentActivity.getApplication().getString(R.string.button_accept));
        assertTrue(acceptButton.performClick());
    }

    @Test
    public void testFirstButtonDialog(){
        Button languageButton = (Button) momentActivity.findViewById(R.id.b_language);
        languageButton.performClick();
        Dialog shadowDialog = ShadowDialog.getLatestDialog();
        assertNotNull(shadowDialog);
        ShadowDialog shadowDialog1 = shadowOf(shadowDialog);
        assertEquals(shadowDialog1.getTitle(), momentActivity.getString(R.string.button_language));
    }

    @Test
    public void testSecondButtonDialog(){
        Button levelButton = (Button) momentActivity.findViewById(R.id.b_level);
        levelButton.performClick();
        Dialog shadowDialog = ShadowDialog.getLatestDialog();
        assertNotNull(shadowDialog);
        ShadowDialog shadowDialog1 = shadowOf(shadowDialog);
        assertEquals(shadowDialog1.getTitle(), momentActivity.getString(R.string.button_level));
    }

    @Test
    public void testThirdButtonDialog(){
        Button timeButton = (Button) momentActivity.findViewById(R.id.b_time);
        timeButton.performClick();
        Dialog shadowDialog = ShadowDialog.getLatestDialog();
        assertNotNull(shadowDialog);
        ShadowDialog shadowDialog1 = shadowOf(shadowDialog);
        assertEquals(shadowDialog1.getTitle(), momentActivity.getString(R.string.button_time));
    }

//    @Test
//    public void testFourthButtonDialog(){
//        Button acceptButton = (Button) momentActivity.findViewById(R.id.b_accept);
//        acceptButton.performClick();
//        AlertDialog shadowAlertDialog = ShadowAlertDialog.getLatestAlertDialog();
//        assertNotNull(shadowAlertDialog);
//    }

}