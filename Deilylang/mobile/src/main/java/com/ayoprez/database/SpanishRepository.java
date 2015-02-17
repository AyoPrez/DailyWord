package com.ayoprez.database;

import android.content.Context;

import java.util.List;

import deilyword.Spanish;
import deilyword.SpanishDao;

/**
 * Created by Ayoze on 29/01/15.
 */
public class SpanishRepository {

    public static void insertOrUpdate(Context context, Spanish spanish) {
        getSpanishDao(context).insertOrReplace(spanish);
    }

    public static List<Spanish> getAllSpanishWords(Context context) {
        return getSpanishDao(context).loadAll();
    }

    public static Spanish getSpanishWordForId(Context context, long id) {
        return getSpanishDao(context).load(id);
    }

    private static SpanishDao getSpanishDao(Context c) {
        return new CreateDatabase(c).getDaoSession().getSpanishDao();
    }
}