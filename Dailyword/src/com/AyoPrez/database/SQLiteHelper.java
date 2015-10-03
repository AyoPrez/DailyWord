package com.AyoPrez.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLiteHelper extends SQLiteOpenHelper {

	private final static String DATABASE_NAME = "DBDailyWord";
	private final static int DATABASE_VERSION = 1;
	
	private String sqlCreateGerman = "CREATE TABLE German (" +
			"Word VARCHAR(30) NOT NULL, " +
			"Level VARCHAR(6) NOT NULL," +
			"Category VARCHAR(20) NOT NULL," +
			"Id DECIMAL(20) NOT NULL," +
			"Conf DECIMAL(1) NOT NULL" +
			")";
	private String sqlCreateEnglish = "CREATE TABLE English (" +
			"Word VARCHAR(30) NOT NULL, " +
			"Level VARCHAR(6) NOT NULL," +
			"Category VARCHAR(20) NOT NULL," +
			"Id DECIMAL(20) NOT NULL," +
			"Conf DECIMAL(1) NOT NULL" +
			")";
	private String sqlCreateSpanish = "CREATE TABLE Spanish (" +
			"Word VARCHAR(30) NOT NULL, " +
			"Level VARCHAR(6) NOT NULL," +
			"Category VARCHAR(20) NOT NULL," +
			"Id DECIMAL(20) NOT NULL," +
			"Conf DECIMAL(1) NOT NULL" +
			")";
	private String sqlCreateUserData = "CREATE TABLE UserMoments (" +
			"AppLanguage VARCHAR(15) NOT NULL, " +
			"Language VARCHAR(15) NOT NULL, " +
			"Level VARCHAR(15) NOT NULL, " +
			"Time VARCHAR(5) NOT NULL, " +
			"Id VARCHAR(4) NOT NULL," +
			"CONSTRAINT pk_user PRIMARY KEY(Id)" +
			");";
	
	public SQLiteHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	public SQLiteHelper(Context context, String name, int version){
		super(context, name, null, version);
	}
	
	@Override
	public void onCreate(SQLiteDatabase database) {
		database.execSQL(sqlCreateGerman);
		database.execSQL(sqlCreateSpanish);
		database.execSQL(sqlCreateEnglish);
		database.execSQL(sqlCreateUserData);
		database.execSQL("sqlCreateAvailableLanguages");
	}

	@Override
	public void onUpgrade(SQLiteDatabase database, int versionAnterior, int versionNueva) {

	}

}