package com.ayoprez.newMoment;

import android.content.Context;
import android.widget.Toast;

import com.ayoprez.database.UserMomentsRepository;
import com.ayoprez.deilylang.R;
import com.ayoprez.deilylang.Utils;
import com.ayoprez.notification.StartAndCancelAlarmManager;

import deilyword.UserMoments;

public class Accept {

	private Context context;
	private Utils UTILS;
	
	public Accept(Context context){
		this.context = context;
		this.UTILS = new Utils();
	}
	
	public void Accept_Dialog(String AppLanguage, String Language, final String Level, String Time){
		UserMomentsRepository userMomentsRepository = new UserMomentsRepository();

		if(!Language.equals(context.getString(R.string.button_language)) && !Level.equals(context.getString(R.string.button_level))
				&& !Time.equals(context.getString(R.string.button_time))){
            UserMoments userMoments = new UserMoments(
                    userMomentsRepository.getLastId(context),
                    Language,
                    Level,
                    UTILS.TakeOutTimeDots(Time),
                    AppLanguage);

            userMomentsRepository.insertOrUpdate(context, userMoments);

			if(new StartAndCancelAlarmManager(context, userMoments).startAlarmManager(Time)){
                UTILS.Create_Dialog(context, context.getString(R.string.successSavingMoment),
                        context.getString(R.string.buttonAcceptDialog),
                        context.getString(R.string.successSavingDialogTitle));
            }else{
                UTILS.Create_Dialog(context, context.getString(R.string.errorSavingMoment),
                        context.getString(R.string.buttonAcceptDialog),
                        context.getString(R.string.errorSavingDialogTitle));
            }
		}else{
			Toast.makeText(context, context.getString(R.string.error_select), Toast.LENGTH_LONG).show();
		}
	}
		
}