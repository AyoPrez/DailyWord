package com.ayoprez.deilylang;

import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;

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
        Utils.Create_Dialog_DoNotFinishActivity(context, message, context.getString(android.R.string.ok),
                context.getString(R.string.errorDefault), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
    }
}
