package com.ayoprez.newMoment;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TimePicker;

import com.ayoprez.deilylang.R;
import com.ayoprez.utils.Utils;

import java.util.Calendar;

public class Moment {

	private Context ctx;
	private Dialog Dialog;
	private final TimePicker Timey_Wimey;
	private Utils UTILS;

	//TODO Dependencies
	//-new Utils

	public Moment(Context context){	
		this.ctx = context;
		this.UTILS = new Utils();
		
		this.Dialog = new Dialog(context);
		this.Dialog.setContentView(R.layout.dialog_time);
		this.Dialog.setTitle(R.string.button_time);
		
		Timey_Wimey = (TimePicker) Dialog.findViewById(R.id.tP_time);
		Timey_Wimey.setIs24HourView(true);
		Timey_Wimey.setCurrentHour(Calendar.getInstance().get(Calendar.HOUR_OF_DAY));

		Button B_Time_Dialog = (Button) Dialog.findViewById(R.id.b_time_dialog);
		B_Time_Dialog.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				int H = Timey_Wimey.getCurrentHour();
				int M = Timey_Wimey.getCurrentMinute();
							
				((NewMomentActivity)ctx).Time_Text(UTILS.WithZero(H)+":"+UTILS.WithZero(M));
				
				Dialog.dismiss();
			}
			
		});
		
		Dialog.show();
	}
}