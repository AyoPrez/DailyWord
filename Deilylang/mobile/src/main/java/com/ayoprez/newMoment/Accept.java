package com.ayoprez.newMoment;

import android.content.Context;
import android.widget.Toast;

import com.ayoprez.deilylang.R;
import com.ayoprez.notification.StartAndCancelAlarmManager;
import com.ayoprez.deilylang.Utils;

import com.ayoprez.database.SQLMethods;
import com.ayoprez.database.SQLUtils;

public class Accept {

	private Context ctx;
	private SQLMethods SQLMETHODS;
	private SQLUtils SQLUTILS;
	private Utils UTILS;
	
	public Accept(Context context){
		this.ctx = context;
		this.SQLMETHODS = new SQLMethods(context);
		this.SQLUTILS = new SQLUtils(ctx);
		this.UTILS = new Utils(context);
	}
	
	public void Accept_Dialog(String AppLanguage, String Language, String Level, String Time){
		
		if(!Language.equals(ctx.getString(R.string.button_language)) && !Level.equals(ctx.getString(R.string.button_level))
				&& !Time.equals(ctx.getString(R.string.button_time))){
			SQLMETHODS.Save_Data_DB(SQLUTILS.LastId(), AppLanguage, Language, Level, UTILS.TakeOutTimeDots(Time));
			
			if(new StartAndCancelAlarmManager(ctx).startAlarmManager(Time)){
                UTILS.Create_Dialog(ctx.getString(R.string.successSavingMoment),
                        ctx.getString(R.string.buttonAcceptDialog),
                        ctx.getString(R.string.successSavingDialogTitle));
            }else{
                UTILS.Create_Dialog(ctx.getString(R.string.errorSavingMoment),
                        ctx.getString(R.string.buttonAcceptDialog),
                        ctx.getString(R.string.errorSavingDialogTitle));
            }
		}else{
			Toast.makeText(ctx, ctx.getString(R.string.error_select), Toast.LENGTH_LONG).show();			
		}
	}
		
}