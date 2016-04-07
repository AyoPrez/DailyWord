package com.ayoprez.database;

import android.content.Context;
import android.util.Log;

import java.util.List;

import de.greenrobot.dao.query.QueryBuilder;
import de.greenrobot.dao.query.WhereCondition;
import deilyword.UserMoments;
import deilyword.UserMomentsDao;

/**
 * Created by Ayoze on 29/01/15.
 */
public class UserMomentsRepository {
    private static final String TAG = UserMomentsRepository.class.getSimpleName();

    //TODO Dependencies
    //new CreateDatabase

    public static void insertOrUpdate(Context context, UserMoments userMoments) {
        getUserMomentsDao(context).insertOrReplace(userMoments);
    }

    public static long getIdFromData(Context context, UserMoments userMoments){
        QueryBuilder qb = getUserMomentsDao(context).queryBuilder();
        qb.where(new WhereCondition.StringCondition("Time = '"+ userMoments.getTime() +"' " +
                "AND Level = '"+ userMoments.getLevel() +"' " +
                "AND Language = '"+ userMoments.getLanguage() +"' "));

        List<UserMoments> idList = qb.list();
        return idList.get(0).getId();
    }

    public long getLastId(Context context){
        List<UserMoments> userMomentsList = getUserMomentsDao(context).loadAll();

        if(userMomentsList.size() == 0){
            return 0;
        }else{
            long lastId = getUserMomentsDao(context).loadAll().get(getRowsCount(context) - 1).getId();
            return lastId + 1;
        }
    }

    public int getRowsCount(Context context){
        return getUserMomentsDao(context).loadAll().size();
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

    private static UserMomentsDao getUserMomentsDao(Context context) {
        Log.i(TAG, "getDaoSession: " + CreateDatabase.getDaoSession());
        return new CreateDatabase(context).getDaoSession().getUserMomentsDao();
    }
}