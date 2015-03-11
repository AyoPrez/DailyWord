package com.ayoprez.deilylang;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.ayoprez.newMoment.NewMomentActivity;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.annotation.Config;
import org.robolectric.util.ActivityController;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
@RunWith(CustomRobolectric.class)
public class MainActivityTest extends Robolectric {

    private MainActivity activity;
    private Button pressMeButton;
    private ListView reviewList;

    @Before
    public void setUp() throws Exception {

        activity = Robolectric.buildActivity(MainActivity.class).create().get();
        pressMeButton = (Button) activity.findViewById(R.id.b_main);

        reviewList = (ListView) activity.findViewById(R.id.reviewList);

        reviewList.setAdapter(new ReviewAdapter(activity, activity.getDataFromDatabaseToListView(activity)));
    }

    @Test
    public void testLayoutOnCreate() {
        assertEquals(R.id.mainActivityLayout, shadowOf(activity).getContentView().getId());
    }

    @Test
    public void checkActivityIsNotNull() throws Exception{
        assertNotNull(activity);
    }

    @Test
    public void checkButtonIsNotNull() throws Exception{
        assertNotNull(pressMeButton);
    }

    @Test
    public void checkTextInButton() throws Exception{
        assertEquals(pressMeButton.getText().toString(), activity.getString(R.string.new_moment));
    }

    @Test
    @Config(qualifiers = "es")
    public void testTextFromButtonSpanish() throws Exception {
        String appNameStr = activity.getResources().getString(R.string.new_moment);
        assertEquals(appNameStr, "Nuevo momento");
    }

    @Test
    @Config(qualifiers = "en")
    public void testTextFromButtonEnglish() throws Exception {
        String appNameStr = activity.getResources().getString(R.string.new_moment);
        assertEquals(appNameStr, "New Moment");
    }

    @Test
    @Config(qualifiers = "de")
    public void testTextFromButtonGerman() throws Exception {
        String appNameStr = activity.getResources().getString(R.string.new_moment);
        assertEquals(appNameStr, "Neue Moment");
    }

    @Test
    public void shouldStartActivityWhenButtonIsClicked() throws Exception{
        pressMeButton.performClick();
        Intent intent = Robolectric.shadowOf(activity).getNextStartedActivity();
        assertEquals(NewMomentActivity.class.getCanonicalName(), intent.getComponent().getClassName());
    }

    @Test
    public void testRestoreActivity() throws Exception {
        Bundle savedInstanceState = new Bundle();
        ListAdapter adapter = reviewList.getAdapter();

        assertNotNull(adapter);

        ActivityController activityController = new ActivityController(activity);
        activityController.saveInstanceState(savedInstanceState).pause().stop().destroy();


        Activity restoredActivity = Robolectric.buildActivity(MainActivity.class).create(savedInstanceState)
                .start().restoreInstanceState(savedInstanceState).resume().visible().get();

        assertNotNull(restoredActivity);
    }
}