package com.ayoprez.database;

import java.util.List;

import de.greenrobot.dao.query.QueryBuilder;
import de.greenrobot.dao.query.WhereCondition;
import deilyword.UserMoments;
import deilyword.UserMomentsDao;

/**
 * Created by Ayoze on 29/01/15.
 */
public class UserMomentsRepository {

    //TODO Dependencies
    //new CreateDatabase

    public static void insertOrUpdate(UserMoments userMoments) {
        getUserMomentsDao().insertOrReplace(userMoments);
    }

    public static long getIdFromData(UserMoments userMoments){
        QueryBuilder qb = getUserMomentsDao().queryBuilder();
        qb.where(new WhereCondition.StringCondition("Time = '"+ userMoments.getTime() +"' " +
                "AND Level = '"+ userMoments.getLevel() +"' " +
                "AND Language = '"+ userMoments.getLanguage() +"' "));

        List<UserMoments> idList = qb.list();
        return idList.get(0).getId();
    }

    public long getLastId(){
        List<UserMoments> userMomentsList = getUserMomentsDao().loadAll();

        if(userMomentsList.size() == 0){
            return 0;
        }else{
            long lastId = getUserMomentsDao().loadAll().get(getRowsCount() - 1).getId();
            return lastId + 1;
        }
    }

    public int getRowsCount(){
        return getUserMomentsDao().loadAll().size();
    }

    public static void clearMoments() {
        getUserMomentsDao().deleteAll();
    }

    public static void deleteMomentWithId(long id) {
        getUserMomentsDao().delete(getMomentForId(id));
    }

    public static List<UserMoments> getAllMoments() {
        return getUserMomentsDao().loadAll();
    }

    public static UserMoments getMomentForId(long id) {
        return getUserMomentsDao().load(id);
    }

    private static UserMomentsDao getUserMomentsDao() {
        return CreateDatabase.getDaoSession().getUserMomentsDao();
    }
}