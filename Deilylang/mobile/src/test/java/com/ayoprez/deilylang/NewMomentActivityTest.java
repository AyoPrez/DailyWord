package com.ayoprez.deilylang;

import com.ayoprez.newMoment.NewMomentActivity;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;

import static org.junit.Assert.assertNotNull;

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


}