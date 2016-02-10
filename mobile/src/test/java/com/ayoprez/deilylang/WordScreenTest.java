package com.ayoprez.deilylang;

import android.app.Activity;
import android.widget.Button;
import android.widget.TextView;

import com.ayoprez.wordscreen.WordScreen;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by AyoPrez on 16/05/15.
 */
@RunWith(CustomRobolectric.class)
public class WordScreenTest {

    private Activity screenActivity;
    private ArrayList<WordFromDatabase> wordFromDatabaseArrayList;

    @Before
    public void setUp() throws Exception {
        screenActivity = Robolectric.buildActivity(WordScreen.class).create().resume().get();
        wordFromDatabaseArrayList = new MockDataBase().getListOfWords();
    }

    @Test
    public void checkActivityIsNotNull() throws Exception{
        assertNotNull(screenActivity);
    }

    @Test
    public void word1IsShowing(){
        TextView textViewWord1 = (TextView) screenActivity.findViewById(R.id.textView_WordScreen_1);
        String word1 = wordFromDatabaseArrayList.get(0).getWord()[0];
        textViewWord1.setText(word1);

        assertEquals(textViewWord1.getText().toString(), word1);
    }

    @Test
    public void word2IsShowing(){
        TextView textViewWord2 = (TextView) screenActivity.findViewById(R.id.textView_WordScreen_2);
        String word2 = wordFromDatabaseArrayList.get(0).getWord()[1];
        textViewWord2.setText(word2);

        assertEquals(textViewWord2.getText().toString(), word2);
    }

    @Test
    public void type1IsShowing(){
        TextView textViewType1 = (TextView) screenActivity.findViewById(R.id.textView_Type_1);
        String type1 = wordFromDatabaseArrayList.get(0).getType()[0];
        textViewType1.setText(type1);

        assertEquals(textViewType1.getText().toString(), type1);
    }

    @Test
    public void type2IsShowing(){
        TextView textViewType2 = (TextView) screenActivity.findViewById(R.id.textView_Type_2);
        String type2 = wordFromDatabaseArrayList.get(0).getType()[1];
        textViewType2.setText(type2);

        assertEquals(textViewType2.getText().toString(), type2);
    }

    @Test
    public void button1IsShowing(){
        Button button1 = (Button) screenActivity.findViewById(R.id.button_WordScreen_);
        assertNotNull(button1);
    }

    @Test
    public void button2IsShowing(){
        Button button2 = (Button) screenActivity.findViewById(R.id.button_WordScreen_2);
        assertNotNull(button2);
    }

}