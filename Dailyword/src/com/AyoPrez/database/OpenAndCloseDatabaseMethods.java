package com.AyoPrez.database;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

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
	
	public void openDatabase(Context ctx, String databaseName) throws SQLException{
		try{
			database = new SQLiteHelper(ctx, databaseName, null, 1);
		}catch(SQLException e){
			
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
	
	public void openDatabaseInWritableMode(Context ctx, String databaseName) throws SQLException {
		if(isOpen()){
			openDatabase(ctx, databaseName);
			databaseInWritableMode = database.getWritableDatabase();		
		}else{
			databaseInWritableMode = database.getWritableDatabase();
		}
	}

	public void openDatabaseInReadableMode(Context ctx, String databaseName) throws SQLException {
		if(isOpen()){
			openDatabase(ctx, databaseName);
			databaseInReadableMode = database.getReadableDatabase();		
		}else{
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