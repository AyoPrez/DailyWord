package com.ayoprez.newMoment;

import android.content.Context;
import android.widget.Toast;

import com.ayoprez.database.UserMomentsRepository;
import com.ayoprez.deilylang.R;
import com.ayoprez.utils.Utils;
import com.ayoprez.notification.StartAndCancelAlarmManager;

import deilyword.UserMoments;

public class Accept {

	private Context context;

    //TODO Dependencies
    //-new UserMoments
    //-new StartAndCancelAlarmManager


	public Accept(Context context){
		this.context = context;
	}
	
	public void Accept_Dialog(String Language, final String Level, String Time){
		UserMomentsRepository userMomentsRepository = new UserMomentsRepository();

		if(!Language.equals(context.getString(R.string.button_language)) && !Level.equals(context.getString(R.string.button_level))
				&& !Time.equals(context.getString(R.string.button_time))){
            UserMoments userMoments = new UserMoments(
                    userMomentsRepository.getLastId(context),
                    Language,
                    Level,
                    Utils.getInstance().TakeOutTimeDots(Time));

            UserMomentsRepository.insertOrUpdate(context, userMoments);

			if(new StartAndCancelAlarmManager(context, userMoments).startAlarmManager(Time)){
                Utils.getInstance().Create_Dialog(context, context.getString(R.string.successSavingMoment),
                        context.getString(R.string.buttonAcceptDialog),
                        context.getString(R.string.successSavingDialogTitle));
            }else{
                Utils.getInstance().Create_Dialog(context, context.getString(R.string.errorSavingMoment),
                        context.getString(R.string.buttonAcceptDialog),
                        context.getString(R.string.errorSavingDialogTitle));
            }
		}else{
			Toast.makeText(context, context.getString(R.string.error_select), Toast.LENGTH_LONG).show();
		}
	}
		
}