package com.ayoprez.deilylang;

import android.app.AlertDialog;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.shadows.ShadowAlertDialog;

import java.util.ArrayList;

import deilyword.UserMoments;

import static android.view.HapticFeedbackConstants.LONG_PRESS;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
/**
 * Created by Ayoze on 16/01/15.
 */
@RunWith(CustomRobolectric.class)
public class ListviewTest extends Robolectric {

    private MainActivity activity;
    private ListView reviewList;
    private ReviewAdapter listAdapter;
    private View itemView;
    private int itemSelected = 1;
    private Long id1 = (long)01;
    private Long id2 = (long)02;

    public ArrayList<UserMoments> dataFromDatabaseExample() {
        ArrayList<UserMoments> dataFromDatabase = new ArrayList<>();

        dataFromDatabase.add(new UserMoments(id1, "Basic", "English", "1930", "spa"));
        dataFromDatabase.add(new UserMoments(id2, "Basic", "English", "1730", "spa"));

        return dataFromDatabase;
    }

    @Before
    public void setUp() throws Exception {

        activity = Robolectric.buildActivity(MainActivity.class).create().get();

        reviewList = (ListView) activity.findViewById(R.id.reviewList);
        listAdapter = new ReviewAdapter(activity, dataFromDatabaseExample());
        reviewList.setAdapter(listAdapter);

        itemView = reviewList.getAdapter().getView(itemSelected, null, reviewList);

        reviewList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                activity.showAlertDialogToDeleteItem(activity, position);
                return true;
            }
        });

    }

    @Test
    public void showListNotNullTest() throws Exception {
        assertNotNull(reviewList);
    }

    @Test
    public void checkAdapterGetItemId() {
        assertEquals(0, listAdapter.getItemId(0));
    }

    @Test
    public void checkAdapterGetItemIsNotNull() {
        assertNotNull(listAdapter.getItem(0));
    }

    //This mean that the list is full with 2 elements
    @Test
    public void checkListItemCount() {
        ListAdapter adapter = reviewList.getAdapter();
        assertEquals(2, adapter.getCount());
    }

    @Test
    public void selectedItem() {
        assertNotNull(itemView);
    }

    @Test
    public void testCorrectCreationAlertDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setTitle("title").setMessage("message");
        AlertDialog alert = builder.create();

        ShadowAlertDialog shadowAlertDialog = shadowOf(alert);
        assertNotNull(shadowAlertDialog);
    }

    public boolean makeItemLongClick(){
        AdapterView.OnItemLongClickListener listener = reviewList.getOnItemLongClickListener();

        ListAdapter adapter = reviewList.getAdapter();
        View itemView = adapter.getView(itemSelected, null, reviewList);
        listener.onItemLongClick(reviewList, itemView, itemSelected, adapter.getItemId(itemSelected));
        assertTrue(reviewList.performHapticFeedback(LONG_PRESS));
        return reviewList.performHapticFeedback(LONG_PRESS);
    }

    @Test
    public void testLongClickOnListElement() {
        ListView listView = (ListView) activity.getListView();
        assertTrue(Robolectric.shadowOf(listView).performHapticFeedback(LONG_PRESS));
        //assertTrue(makeItemLongClick());
    }

    @Test
    public void clickingPositiveButtonDismissesDialog() throws Exception {
        //https://github.com/upsight/playhaven-robolectric/blob/master/src/
        // test/java/com/xtremelabs/robolectric/shadows/AlertDialogTest.java

        AlertDialog alertDialog = new AlertDialog.Builder(activity)
                .setPositiveButton("Positive", null).create();
        alertDialog.show();

        assertTrue(alertDialog.isShowing());
        alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).performClick();
        assertFalse(alertDialog.isShowing());
    }
}