package deilyword;

import android.database.sqlite.SQLiteDatabase;

import java.util.Map;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.AbstractDaoSession;
import de.greenrobot.dao.identityscope.IdentityScopeType;
import de.greenrobot.dao.internal.DaoConfig;

import deilyword.UserMoments;

import deilyword.UserMomentsDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see de.greenrobot.dao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig userMomentsDaoConfig;

    private final UserMomentsDao userMomentsDao;

    public DaoSession(SQLiteDatabase db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        userMomentsDaoConfig = daoConfigMap.get(UserMomentsDao.class).clone();
        userMomentsDaoConfig.initIdentityScope(type);

        userMomentsDao = new UserMomentsDao(userMomentsDaoConfig, this);

        registerDao(UserMoments.class, userMomentsDao);
    }
    
    public void clear() {
        userMomentsDaoConfig.getIdentityScope().clear();
    }

    public UserMomentsDao getUserMomentsDao() {
        return userMomentsDao;
    }

}
