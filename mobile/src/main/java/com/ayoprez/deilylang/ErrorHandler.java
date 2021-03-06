package com.ayoprez.deilylang;

import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;

import android.view.WindowManager;
import com.ayoprez.utils.Utils;
import com.crashlytics.android.Crashlytics;

/**
 * Created by AyoPrez on 10.10.15.
 */
public class ErrorHandler {

    public static ErrorHandler getInstance(){
        return new ErrorHandler();
    }

    public void Error(String where, String error){
        Log.e(where, error);

        Crashlytics.getInstance().core.getIdentifier();
        Crashlytics.getInstance().core.log(where + " - " + error);
    }

    public void informUser(Context context, String message){
        try {
            Utils.getInstance().Create_Dialog_DoNotFinishActivity(context, message, context.getString(android.R.string.ok),
                context.getString(R.string.errorSavingDialogTitle), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        }catch (WindowManager.BadTokenException e){
            e.getStackTrace();
        }
    }
}
