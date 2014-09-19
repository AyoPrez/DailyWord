package com.example.dailyword.databaseTest;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import android.test.AndroidTestCase;
import android.test.RenamingDelegatingContext;

import com.AyoPrez.database.SQLiteHelper;

public class SQLiteHelperTest extends AndroidTestCase{

	private SQLiteHelper database;
		
	@Before
	public void setUp() throws Exception {
		RenamingDelegatingContext context = new RenamingDelegatingContext(getContext(), "test_");
		database = new SQLiteHelper(context);
	}

	@After
	public void tearDown() throws Exception {
		database.close(); 
        super.tearDown();
	}

	@Test
	public void test() {
	
	}

}