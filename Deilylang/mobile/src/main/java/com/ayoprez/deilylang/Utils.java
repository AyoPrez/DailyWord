package com.ayoprez.deilylang;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.util.Log;

public class Utils {

	private Context ctx;
	
	public Utils(Context context){
		this.ctx = context;
	}
	
	public void Create_Dialog(String message, String button_title, String dialog_title){
		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(ctx);
		alertDialogBuilder.setTitle(dialog_title);
		alertDialogBuilder.setMessage(message);
		alertDialogBuilder.setPositiveButton(button_title, new OnClickListener(){

			@Override
			public void onClick(DialogInterface dialog, int which) {
				Intent i = new Intent(ctx, MainActivity.class);
				ctx.startActivity(i);
				((Activity)ctx).finish();
			}
		});
						
		alertDialogBuilder.show();
	}
	
	public void Create_Dialog_DontFinishActivity(String message, String button_title, String dialog_title){
		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(ctx);
		alertDialogBuilder.setTitle(dialog_title);
		alertDialogBuilder.setMessage(message);
		alertDialogBuilder.setPositiveButton(button_title, null);
						
		alertDialogBuilder.show();
	}
	
	public String WithZero(int i){
		if(i < 10 && i >= 0){
			return 0+String.valueOf(i);
		}else{
			return String.valueOf(i);
		}
	}
	
	public String TakeOutTimeDots(String s){
		int n = s.indexOf(':');
		return s.substring(0, n) + s.substring(n + 1);
	}
	
	public int TakeHourFromTime(String time){
		String hour = time.substring(0, 2);
		Log.d("Hour", hour);
		return Integer.valueOf(hour);
	}
	
	public int TakeMinuteFromTime(String time){
		String minute = time.substring(3, 5);
		Log.d("Minute", minute);
		return Integer.valueOf(minute);
	}
	
}