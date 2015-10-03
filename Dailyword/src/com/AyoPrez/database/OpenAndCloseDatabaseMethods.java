package com.AyoPrez.database;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.util.Log;

public class OpenAndCloseDatabaseMethods {

	private SQLiteHelper database;
	private SQLiteDatabase databaseInWritableMode;
	private SQLiteDatabase databaseInReadableMode;
				
	public SQLiteHelper getDatabase() {
		return database;
	}

	public void setDatabase(SQLiteHelper database) {
		this.database = database;
	}

	public SQLiteDatabase getDatabaseInWritableMode() {
		return databaseInWritableMode;
	}

	public void setDatabaseInWritableMode(SQLiteDatabase databaseInWritableMode) {
		this.databaseInWritableMode = databaseInWritableMode;
	}

	public SQLiteDatabase getDatabaseInReadableMode() {
		return databaseInReadableMode;
	}

	public void setDatabaseInReadableMode(SQLiteDatabase databaseInReadableMode) {
		this.databaseInReadableMode = databaseInReadableMode;
	}
	
	public void openDatabase(Context ctx) throws SQLException, SQLiteException{
		try{
			database = new SQLiteHelper(ctx);
		}catch(SQLException e){
			Log.e("SQLException", "There is an error en OpenAndCloseDatabaseMethods in line 42");
		}
	}
	
	public boolean isOpen(){
		if(database != null){
			return true;
		}else{
			return false;
		}
	}
	
	public boolean isOpenInWriteableMode(){
		if(databaseInWritableMode != null){
			return true;
		}else{
			return false;
		}
	}
	
	public boolean isOpenInReadableMode(){
		if(databaseInReadableMode != null){
			return true;
		}else{
			return false;
		}
	}
	
	public void openDatabaseInWritableMode(Context ctx) throws SQLException, SQLiteException {
		if(isOpen()){
			databaseInWritableMode = database.getWritableDatabase();		
		}else{
			openDatabase(ctx);
			databaseInWritableMode = database.getWritableDatabase();
		}
	}

	public void openDatabaseInReadableMode(Context ctx) throws SQLException, SQLiteException {
		if(isOpen()){
			databaseInReadableMode = database.getReadableDatabase();		
		}else{
			openDatabase(ctx);
			databaseInReadableMode = database.getReadableDatabase();
		}
	}
	
	public void closeDatabase() throws SQLException{
		if(isOpen()){
			if(isOpenInWriteableMode()){
				databaseInWritableMode.close();
			}else{
				database.close();
			}
		}
	}
	
}