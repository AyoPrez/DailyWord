package com.ayoprez.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import deilyword.DaoMaster;
import deilyword.DaoSession;

/**
 * Created by Ayoze on 29/01/15.
 */
public class CreateDatabase{

    public static DaoSession daoSession;

    public CreateDatabase(Context context){
        if(daoSession == null) {
            setupDatabase(context);
        }
    }

    private void setupDatabase(Context context) {
        Log.e("CreateDatabase", "I've created a database");
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(context, "deilylang", null);
        SQLiteDatabase db = helper.getWritableDatabase();
        DaoMaster daoMaster = new DaoMaster(db);
        daoSession = daoMaster.newSession();
    }

    public static DaoSession getDaoSession() {
        return daoSession;
    }
}