package deilyword;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.Property;
import de.greenrobot.dao.internal.DaoConfig;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table USER_WORDS.
*/
public class UserWordsDao extends AbstractDao<UserWords, Long> {

    public static final String TABLENAME = "USER_WORDS";

    /**
     * Properties of entity UserWords.<br/>
     * Can be used for QueryBuilder and for referencing column names.
    */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property IdWord = new Property(1, Integer.class, "IdWord", false, "ID_WORD");
        public final static Property LanguageNew = new Property(2, String.class, "LanguageNew", false, "LANGUAGE_NEW");
        public final static Property LanguageDevice = new Property(3, String.class, "LanguageDevice", false, "LANGUAGE_DEVICE");
        public final static Property Level = new Property(4, String.class, "Level", false, "LEVEL");
    };


    public UserWordsDao(DaoConfig config) {
        super(config);
    }
    
    public UserWordsDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(SQLiteDatabase db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "'USER_WORDS' (" + //
                "'_id' INTEGER PRIMARY KEY ," + // 0: id
                "'ID_WORD' INTEGER," + // 1: IdWord
                "'LANGUAGE_NEW' TEXT," + // 2: LanguageNew
                "'LANGUAGE_DEVICE' TEXT," + // 3: LanguageDevice
                "'LEVEL' TEXT);"); // 4: Level
    }

    /** Drops the underlying database table. */
    public static void dropTable(SQLiteDatabase db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "'USER_WORDS'";
        db.execSQL(sql);
    }

    /** @inheritdoc */
    @Override
    protected void bindValues(SQLiteStatement stmt, UserWords entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        Integer IdWord = entity.getIdWord();
        if (IdWord != null) {
            stmt.bindLong(2, IdWord);
        }
 
        String LanguageNew = entity.getLanguageNew();
        if (LanguageNew != null) {
            stmt.bindString(3, LanguageNew);
        }
 
        String LanguageDevice = entity.getLanguageDevice();
        if (LanguageDevice != null) {
            stmt.bindString(4, LanguageDevice);
        }
 
        String Level = entity.getLevel();
        if (Level != null) {
            stmt.bindString(5, Level);
        }
    }

    /** @inheritdoc */
    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    /** @inheritdoc */
    @Override
    public UserWords readEntity(Cursor cursor, int offset) {
        UserWords entity = new UserWords( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getInt(offset + 1), // IdWord
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // LanguageNew
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // LanguageDevice
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4) // Level
        );
        return entity;
    }
     
    /** @inheritdoc */
    @Override
    public void readEntity(Cursor cursor, UserWords entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setIdWord(cursor.isNull(offset + 1) ? null : cursor.getInt(offset + 1));
        entity.setLanguageNew(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setLanguageDevice(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setLevel(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
     }
    
    /** @inheritdoc */
    @Override
    protected Long updateKeyAfterInsert(UserWords entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    /** @inheritdoc */
    @Override
    public Long getKey(UserWords entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    /** @inheritdoc */
    @Override    
    protected boolean isEntityUpdateable() {
        return true;
    }
    
}
