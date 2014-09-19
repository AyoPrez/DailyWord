package com.AyoPrez.database;

import java.util.ArrayList;

import android.content.Context;
import android.database.Cursor;
import android.database.CursorIndexOutOfBoundsException;
import android.database.SQLException;
import android.database.sqlite.SQLiteException;
import android.util.Log;
import android.widget.Toast;

import com.AyoPrez.dailyword.Moment;

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
			openDatabaseInWritableMode(ctx);

			if(isOpenInWriteableMode()){

				new IncludeWordsToDatabase(getDatabaseInWritableMode());

				closeDatabase();
			}else{
				Log.e("DBError", "Didn't open the Database"); 
			}
		}catch(SQLiteException e){
			Log.e("SQLError", e.getMessage());
		}    
	}

	public void deleteMomentFromUserDataTable(Moment moment){

		openDatabaseInWritableMode(ctx);

		if(isOpenInWriteableMode()){
			try{
				getDatabaseInWritableMode().execSQL(
						"DELETE FROM UserData " +
						"WHERE Time = '"+ moment.getTime() +"' " +
						"AND Level = '"+ moment.getLevel() +"' " +
						"AND Language = '"+ moment.getLanguage() +"' "
						);
				
				//UTILS.Create_Dialog_DontFinishActivity("El momento se eliminó satisfactoriamente", "Aceptar", "Eliminar momento");
			}catch(SQLException e){
				Log.e("Error SQL", e.getMessage());
			}
			closeDatabase();
		}else{
			Log.e("DBError", "Didn't open the Database"); 
		}

	}

	public void createMomentIntoUserDataTable(Moment moment){

		openDatabaseInWritableMode(ctx);

		if(isOpenInWriteableMode()){
			try{
				getDatabaseInWritableMode().execSQL(
						"INSERT INTO UserData (Id, AppLanguage, Language, Level, Time) " +
						"VALUES ('" + moment.getId() + "', '" + moment.getAppLanguage() + "', '" + moment.getLanguage() + "" +
						"', '" + moment.getLevel() + "', '" + moment.getTime() + "')"
						);

				//UTILS.Create_Dialog("El momento ha sido guardado", "Aceptar", "Momento guardado");
			}catch(SQLException e){
				Log.e("Error SQL", e.getMessage());
			}
			closeDatabase();
		}else{
			Log.e("DBError", "Didn't open the Database"); 
		}

	}

	public ArrayList<Moment> getMomentsDataFromUserDataTable(){

		ArrayList<Moment> arrayListOfMoments = new ArrayList<Moment>();
				
		openDatabaseInWritableMode(ctx);

		if(isOpenInWriteableMode()){
			try{
				Cursor c = getDatabaseInWritableMode().rawQuery("SELECT Id, Time, Language, Level FROM UserData", null);
				if(c.moveToFirst()){
					for(int j = 0; j < SQLUTILS.getDatabaseRowsCount(); j++){
						for(int i = 0; i < 4; i++){
							Moment momentToFill = new Moment();
							momentToFill.setId(c.getString(i));
							momentToFill.setLanguage(c.getString(i));
							momentToFill.setLevel(c.getString(i));
							momentToFill.setTime(c.getString(i));
							arrayListOfMoments.add(momentToFill);
						}
						c.moveToNext();
					}
				}else{
					Toast.makeText(ctx, "Table User_Data is empty", Toast.LENGTH_LONG).show();
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

		return arrayListOfMoments;
	}

	public void changeConfValueInDatabase(Moment moment){
		try{
			openDatabaseInWritableMode(ctx);

			if(isOpenInWriteableMode()){

				getDatabaseInWritableMode().execSQL(
						"UPDATE '"+moment.getLanguage()+"' " +
						"SET Conf = 1 " +
						"WHERE Id LIKE '"+ moment.getId() +"'"
						);

				closeDatabase();
			}else{
				Log.i("DBError", "Didn't open the Database"); 
			}
		}catch(SQLiteException e){
			Log.e("SQLError", e.getMessage());
		} 
	}

}