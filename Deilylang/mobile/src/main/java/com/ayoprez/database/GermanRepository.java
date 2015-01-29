package com.ayoprez.database;

import android.content.Context;

import java.util.List;

import deilyword.German;
import deilyword.GermanDao;

/**
 * Created by Ayoze on 29/01/15.
 */
public class GermanRepository {

    public static void insertOrUpdate(Context context, German german) {
        getGermanDao(context).insertOrReplace(german);
    }

    public static List<German> getAllGermanWords(Context context) {
        return getGermanDao(context).loadAll();
    }

    public static German getGermanWordForId(Context context, long id) {
        return getGermanDao(context).load(id);
    }

    private static GermanDao getGermanDao(Context c) {
        return new CreateDatabase(c).getDaoSession().getGermanDao();
    }
}