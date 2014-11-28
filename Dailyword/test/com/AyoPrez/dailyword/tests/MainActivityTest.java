package com.AyoPrez.dailyword.tests;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import static org.hamcrest.CoreMatchers.equalTo; 
import com.AyoPrez.dailyword.MainActivity;
import com.AyoPrez.dailyword.R;

@RunWith(RobolectricTestRunner.class)
public class MainActivityTest {

	@Test
	public void shouldHaveHappySmiles() throws Exception {
		String hello = new MainActivity().getResources().getString(R.string.basic);
		assertThat(hello, equalTo("Básico"));
	} 

}
