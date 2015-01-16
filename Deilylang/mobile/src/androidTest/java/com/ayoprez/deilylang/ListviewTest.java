package com.ayoprez.deilylang;

import android.widget.ListAdapter;
import android.widget.ListView;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
/**
 * Created by Ayoze on 16/01/15.
 */
@Config(emulateSdk = 18)
@RunWith(RobolectricTestRunner.class)
public class ListviewTest extends Robolectric {

    private MainActivity activity;
    private ListView reviewList;
    private ReviewAdapter listAdapter;


    public ArrayList<UserData> dataFromDatabaseExample(){
        ArrayList<UserData> dataFromDatabase = new ArrayList<>();

        dataFromDatabase.add(new UserData("01", "Basic", "English", "1930"));
        dataFromDatabase.add(new UserData("02", "Basic", "English", "1730"));

        return dataFromDatabase;
    }

    @Before
    public void setUp() throws Exception {

        activity = Robolectric.buildActivity(MainActivity.class).create().get();

        reviewList = (ListView) activity.findViewById(R.id.reviewList);
        listAdapter = new ReviewAdapter(activity, dataFromDatabaseExample());
        reviewList.setAdapter(listAdapter);
    }

    @Test
    public void showListNotNullTest() throws Exception{
        assertNotNull(reviewList);
    }

    @Test
    public void checkAdapterGetItemId(){
        assertEquals(0, listAdapter.getItemId(0));
    }

    @Test
    public void checkAdapterGetItemIsNotNull(){
        assertNotNull(listAdapter.getItem(0));
    }

    @Test
    public void checkListItemCount(){
        ListAdapter adapter = reviewList.getAdapter();
        assertEquals(2, adapter.getCount());
    }

    @Test
    public void listItemClickable(){
        assertTrue(reviewList.isClickable());
    }

//    @Test
//    public void testClickOnListElement() throws Exception{
//        ListAdapter adapter = reviewList.getAdapter();
//        View itemView = adapter.getView(0, null, reviewList);
//        assertTrue(reviewList.performItemClick(itemView, 0, adapter.getItemId(0)));
//    }
//
//    @Test
//    public void listItemLongClickable(){
//        assertTrue(reviewList.isLongClickable());
//    }
//
//    @Test
//    public void testLongClickOnListElement() throws Exception{
//        AdapterView.OnItemLongClickListener listener = reviewList.getOnItemLongClickListener();
//        ListAdapter adapter = reviewList.getAdapter();
//        View itemView = adapter.getView(0, null, reviewList);
//        listener.onItemLongClick(reviewList, itemView, 0, adapter.getItemId(0));
//        assertTrue(reviewList.performHapticFeedback(LONG_PRESS));
//    }
}