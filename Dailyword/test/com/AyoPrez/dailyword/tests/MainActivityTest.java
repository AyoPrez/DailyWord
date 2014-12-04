package com.AyoPrez.dailyword.tests;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.shadows.ShadowAlertDialog;
import org.robolectric.shadows.ShadowDialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.widget.Button;
import android.widget.TextView;

import com.AyoPrez.dailyword.MainActivity;
import com.AyoPrez.dailyword.R;

@RunWith(RobolectricTestRunner.class)
public class MainActivityTest extends Robolectric{

	private MainActivity activity;
	private Button pressMeButton;
	private TextView languageButton;

	@Before 
	public void setUp() throws Exception{ 
		activity = Robolectric.buildActivity(MainActivity.class).create().get();
		
		languageButton = (TextView) activity.findViewById(R.id.tv_appLanguage);
		pressMeButton = (Button) activity.findViewById(R.id.b_main);
	} 

	@Test
	public void shouldStartDialogWhenButtonIsClicked(){ 
		pressMeButton.performClick();

		AlertDialog alert = ShadowAlertDialog.getLatestAlertDialog();
		ShadowAlertDialog sAlert = shadowOf(alert);

		assertThat(sAlert.getTitle().toString(), equalTo(activity.getString(R.string.dialog_title)));
	} 
	
	@Test
	public void shouldOpenADialog(){
		languageButton.performClick();
		
		Dialog languages = ShadowDialog.getLatestDialog();
		ShadowDialog languageDialog = shadowOf(languages);
				
		assertThat(languageDialog.getTitle().toString(), equalTo(activity.getString(R.string.button_language)));
	}
	
	
}