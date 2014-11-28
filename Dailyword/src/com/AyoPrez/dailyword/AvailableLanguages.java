package com.AyoPrez.dailyword;

import android.content.Context;
import android.util.Log;

public class AvailableLanguages {
	
	private Context ctx;
	
	public AvailableLanguages(Context context){
		this.ctx = context;
		Log.i("AL", ""+ctx);
	}
	
	String[] Languages = {ctx.getString(R.string.English), ctx.getString(R.string.Spanish), ctx.getString(R.string.German)};

	public String[] getLanguages() {
		return Languages;
	}
	
}