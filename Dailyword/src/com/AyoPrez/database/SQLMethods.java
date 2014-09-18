package com.AyoPrez.database;

import android.content.Context;
import android.database.Cursor;
import android.database.CursorIndexOutOfBoundsException;
import android.database.SQLException;
import android.database.sqlite.SQLiteConstraintException;
import android.util.Log;
import android.widget.Toast;

public class SQLMethods extends OpenAndCloseDatabaseMethods{

	private Context ctx;
	//private Utils UTILS;
	private SQLUtils SQLUTILS;
	
	public SQLMethods(Context context){
		this.ctx = context;
		//this.UTILS = new Utils(context);
		this.SQLUTILS = new SQLUtils(context);
	}

	public void createDatabase(){
		try{
			openDatabaseInWritableMode(ctx, "DBDailyWord");

			if(isOpenInWriteableMode()){

				new IncludeWordsToDatabase(getDatabaseInWritableMode());

				closeDatabase();
			}else{
				Log.i("DBError", "Didn't open the Database"); 
			}
		}catch(SQLiteConstraintException e){
			Log.e("SQLError", e.getMessage());
		}    
	}

	public void deleteDatabaseRow(String level, String time, String language){

		openDatabaseInWritableMode(ctx, "DBDailyWord");

		if(isOpenInWriteableMode()){
			try{
				getDatabaseInWritableMode().execSQL("DELETE FROM User_Data WHERE Time = '"+ time +"' AND Level = '"+ level +"' AND Language = '"+ language +"' ");
				//UTILS.Create_Dialog_DontFinishActivity("El momento se eliminó satisfactoriamente", "Aceptar", "Eliminar momento");
			}catch(SQLException e){
				Log.e("Error SQL", e.getMessage());
			}
			closeDatabase();
		}else{
			Log.e("DBError", "Didn't open the Database"); 
		}

	}

	public void setMomentDataIntoDatabase(String Id, String AppLanguage, String Language, String Level, String Time){

		openDatabaseInWritableMode(ctx, "DBDailyWord");

		if(isOpenInWriteableMode()){
			try{
				getDatabaseInWritableMode().execSQL("INSERT INTO User_Data (Id, AppLanguage, Language, Level, Time) VALUES ('" + Id + "', '" + AppLanguage + "', '" + Language + "" +
						"', '" + Level + "', '" + Time + "')");

				//UTILS.Create_Dialog("El momento ha sido guardado", "Aceptar", "Momento guardado");
			}catch(SQLException e){
				Log.e("Error SQL", e.getMessage());
			}
			closeDatabase();
		}else{
			Log.e("DBError", "Didn't open the Database"); 
		}

	}

	public String[][] getMomentsDataIntoDatabase(){
		String[][] DB_Data = new String[20][4];

		openDatabaseInWritableMode(ctx, "DBDailyWord");

		if(isOpenInWriteableMode()){
			try{
				Cursor c = getDatabaseInWritableMode().rawQuery("SELECT Id, Time, Language, Level FROM User_Data", null);
				if(c.moveToFirst()){
					for(int j = 0; j < SQLUTILS.getRowsCount(); j++){
						for(int i = 0; i < 4; i++){
							DB_Data[j][i] = c.getString(i);
						}
						c.moveToNext();
					}
				}else{
					Toast.makeText(ctx, "No hay registros", Toast.LENGTH_LONG).show();
				}
			}catch(SQLException e){
				Log.e("Error SQL", e.getMessage());
			}catch(CursorIndexOutOfBoundsException ce){
				Log.e("Cursor Error", ce.getMessage());
			}
			closeDatabase();
		}else{
			Log.i("DBError", "Didn't open the Database"); 
		}

		return DB_Data;
	}

	public void changeConfValueInDatabase(String id, String Language){
		try{
			openDatabaseInWritableMode(ctx, "DBDailyWord");

			if(isOpenInWriteableMode()){

				getDatabaseInWritableMode().execSQL("UPDATE '"+Language+"' SET Conf = 1 WHERE Id LIKE '"+ id +"'");

				closeDatabase();
			}else{
				Log.i("DBError", "Didn't open the Database"); 
			}
		}catch(SQLiteConstraintException e){
			Log.e("SQLError", e.getMessage());
		} 
	}

}