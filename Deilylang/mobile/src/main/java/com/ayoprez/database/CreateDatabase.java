package com.ayoprez.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import deilyword.DaoMaster;
import deilyword.DaoSession;

/**
 * Created by Ayoze on 29/01/15.
 */
public class CreateDatabase{

    public DaoSession daoSession;

    public CreateDatabase(Context context){
        setupDatabase(context);
    }

    //Take a look at this
    private void setupDatabase(Context context) {
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(context, "deilylang", null);
        SQLiteDatabase db = helper.getWritableDatabase();
        DaoMaster daoMaster = new DaoMaster(db);
        daoSession = daoMaster.newSession();
    }

    public DaoSession getDaoSession() {
        return daoSession;
    }
}