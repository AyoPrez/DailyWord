package com.AyoPrez.view;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TimePicker;

import com.AyoPrez.dailyword.R;
import com.AyoPrez.dailyword.Utils;

public class TimeView {
	
	private Dialog Dialog;
	private final TimePicker Timey_Wimey;
	private Utils UTILS;
	private String timeChoosed;
	
	public String getTimeChoosed() {
		return timeChoosed;
	}

	public void setTimeChoosed(String timeChoosed) {
		this.timeChoosed = timeChoosed;
	}

	public TimeView(Context context){	
		this.UTILS = new Utils(context);
		this.Dialog = new Dialog(context);
		this.Dialog.setContentView(R.layout.time_dialog);
		this.Dialog.setTitle(R.string.button_time);
		
		Timey_Wimey = (TimePicker) Dialog.findViewById(R.id.tP_time);
		Timey_Wimey.setIs24HourView(true);
				
		Button B_Time_Dialog = (Button) Dialog.findViewById(R.id.b_time_dialog);
		
		B_Time_Dialog.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				int currentHour = Timey_Wimey.getCurrentHour();
				int currentMinute = Timey_Wimey.getCurrentMinute();
							
				setTimeChoosed(UTILS.WithZero(currentHour)+":"+UTILS.WithZero(currentMinute));
				
				Dialog.dismiss();
			}
		});
		
		Dialog.show();
	}
}