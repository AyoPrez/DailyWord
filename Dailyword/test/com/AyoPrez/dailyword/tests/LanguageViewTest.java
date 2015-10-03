package com.AyoPrez.dailyword.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;

import com.AyoPrez.dailyword.MainActivity;
import com.AyoPrez.view.LanguageView;

@RunWith(RobolectricTestRunner.class)
public class LanguageViewTest {

	private MainActivity activity;
	
	@Before 
	public void setUp() throws Exception{ 
		activity = Robolectric.buildActivity(MainActivity.class).create().start().get();
	} 

	@Test
	public void activityIsNotNullTest(){
		assertNotNull(activity);
	}
	
	@Test
	public void languageViewIsNotNullTest(){
		assertNotNull(new LanguageView(activity));
	}
	
	@Test
	public void availableLanguagesTest(){
		String[] Languages = new LanguageView(activity).availableLanguages();

		assertEquals(Languages[0], "English");
	}
	
}