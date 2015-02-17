package com.ayoprez.newMoment;

import android.content.Context;
import android.widget.Toast;

import com.ayoprez.database.UserMomentsRepository;
import com.ayoprez.deilylang.R;
import com.ayoprez.deilylang.Utils;
import com.ayoprez.notification.StartAndCancelAlarmManager;

import java.util.Locale;

import deilyword.UserMoments;

public class Accept {

	private Context ctx;
	private Utils UTILS;
	
	public Accept(Context context){
		this.ctx = context;
		this.UTILS = new Utils(context);
	}
	
	public void Accept_Dialog(String AppLanguage, String Language, String Level, String Time){
		UserMomentsRepository userMomentsRepository = new UserMomentsRepository();


		if(!Language.equals(ctx.getString(R.string.button_language)) && !Level.equals(ctx.getString(R.string.button_level))
				&& !Time.equals(ctx.getString(R.string.button_time))){
            UserMoments userMoments = new UserMoments(
                    userMomentsRepository.getLastId(ctx),
                    Language,
                    Level,
                    UTILS.TakeOutTimeDots(Time),
                    Locale.getDefault().getISO3Language());

            userMomentsRepository.insertOrUpdate(ctx, userMoments);
			
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