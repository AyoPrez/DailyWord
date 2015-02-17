package com.ayoprez.database;

import android.content.Context;

import java.util.List;

import deilyword.English;
import deilyword.EnglishDao;

/**
 * Created by Ayoze on 29/01/15.
 */
public class EnglishRepository {

    public static void insertOrUpdate(Context context, English english) {
        getEnglishDao(context).insertOrReplace(english);
    }

    public static List<English> getAllEnglishWords(Context context) {
        return getEnglishDao(context).loadAll();
    }

    public static English getEnglishWordForId(Context context, long id) {
        return getEnglishDao(context).load(id);
    }

    private static EnglishDao getEnglishDao(Context c) {
        return new CreateDatabase(c).getDaoSession().getEnglishDao();
    }
}