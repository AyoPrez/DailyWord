package com.ayoprez.unittests;

import com.ayoprez.deilylang.MockDataBase;
import com.ayoprez.deilylang.WordFromDatabase;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

/**
 * Created by AyoPrez on 16/05/15.
 */
public class ReceiveTheWordsTest {

    private ArrayList<WordFromDatabase> wordFromDatabases;

    @Before
    public void setUp(){
        wordFromDatabases = new MockDataBase().getListOfWords();
    }

    @Test
    public void getWords(){
        //0 -> Hola/Hello
        String[] words = wordFromDatabases.get(0).getWord();

        String[] wordTest = {"Hola", "Hello"};

        Assert.assertArrayEquals(words, wordTest);
    }

    @Test
    public void getTypes(){
        String[] types = wordFromDatabases.get(0).getType();

        String[] typesTest = {"Interjección", "Interjection"};

        Assert.assertArrayEquals(types, typesTest);
    }

    @Test
    public void getIds(){
        Assert.assertEquals(1, wordFromDatabases.get(0).getId());
    }

    @Test
    public void getLevel(){
        Assert.assertEquals("Basic", wordFromDatabases.get(0).getLevel());
    }
}