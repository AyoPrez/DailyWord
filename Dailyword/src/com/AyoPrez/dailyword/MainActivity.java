package com.AyoPrez.dailyword;

import java.util.Locale;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.AyoPrez.view.LanguageView;

public class MainActivity extends Activity {

	private TextView appLanguage;
	private TextView timerHour;
	private TextView timerMinute;
	private TextView timerSeconds;
	private Button reviewMomentsButton;
	private Context mContext;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		
		mContext = this;
		this.appLanguage = (TextView) findViewById(R.id.tv_appLanguage);
		this.timerHour = (TextView) findViewById(R.id.tv_main_hour);
		this.timerMinute = (TextView) findViewById(R.id.tv_main_minute);
		this.timerSeconds = (TextView) findViewById(R.id.tv_main_seconds);
		this.reviewMomentsButton = (Button) findViewById(R.id.b_main);
		
		appLanguageControls();
		appTimerControls();
		momentsButton();
	}
	
	private void appLanguageControls(){
		
		appLanguage.setText(Locale.getDefault().getDisplayLanguage());
		
		appLanguage.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Show the list of languages available in the database
				new LanguageView(mContext);
			}
			
		});
	}
	
	private void appTimerControls(){
		
		timerHour.setText("13:");
		timerMinute.setText("00:");
		timerSeconds.setText("45");
		
		// TODO Create a thread that update every second the time to the next moment
	}
	
	private void momentsButton(){
		reviewMomentsButton.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Show current moments in database and the button to create a new one.  
				new AlertDialog.Builder(mContext).setTitle(R.string.dialog_title).show();
			}
			
		});
	}

}