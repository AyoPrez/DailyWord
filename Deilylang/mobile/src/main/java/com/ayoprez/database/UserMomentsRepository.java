package com.ayoprez.database;

import android.content.Context;

import java.util.List;

import deilyword.UserMoments;
import deilyword.UserMomentsDao;


/**
 * Created by Ayoze on 29/01/15.
 */
public class UserMomentsRepository {

    public static void insertOrUpdate(Context context, UserMoments userMoments) {
        getUserMomentsDao(context).insertOrReplace(userMoments);
    }

    public static void clearMoments(Context context) {
        getUserMomentsDao(context).deleteAll();
    }

    public static void deleteMomentWithId(Context context, long id) {
        getUserMomentsDao(context).delete(getMomentForId(context, id));
    }

    public static List<UserMoments> getAllMoments(Context context) {
        return getUserMomentsDao(context).loadAll();
    }

    public static UserMoments getMomentForId(Context context, long id) {
        return getUserMomentsDao(context).load(id);
    }

    private static UserMomentsDao getUserMomentsDao(Context c) {
        return new CreateDatabase(c).getDaoSession().getUserMomentsDao();
    }
}