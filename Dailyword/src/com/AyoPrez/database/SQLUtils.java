package com.AyoPrez.database;

import android.content.Context;
import android.database.Cursor;
import android.database.CursorIndexOutOfBoundsException;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class SQLUtils {

	private Context ctx;
	
	public SQLUtils(Context context){
		this.ctx = context;
	}
	
	public int getRowsCount() {
		int cnt = 0;
		
		//Abrimos la base de datos en modo escritura
	    SQLiteHelper dbh = new SQLiteHelper(ctx, "DBDailyWord", null, 1);
	
	    SQLiteDatabase db = dbh.getWritableDatabase();
	
	    if(db != null){
	    	try{
	    		Cursor c = db.rawQuery("SELECT * FROM User_Data", null);
	    		cnt = c.getCount();
	    		c.close();
	    	}catch(SQLException e){
	    		//Avisar que ocurrió un error
	    		Log.e("Error SQL", e.getMessage());
	    	}catch(CursorIndexOutOfBoundsException ce){
	    		Log.e("Cursor Error", ce.getMessage());
	    	}
	        db.close();
	    }else{
	    	Log.i("DBError", "Didn't open the Database"); 
	    }
	    return cnt;
	}

	public String LastId(){
		
		int max = 0;
		
		//Abrimos la base de datos en modo escritura
	    SQLiteHelper dbh = new SQLiteHelper(ctx, "DBDailyWord", null, 1);
	
	    SQLiteDatabase db = dbh.getWritableDatabase();
	
	    if(db != null){
	    	try{
	    		Cursor c = db.rawQuery("SELECT Id FROM User_Data order by Id DESC limit 1", null);
	    		if (c != null && c.moveToFirst()) {
	    		    max = c.getInt(0);
	    		}
	    		c.close();    		
	    	}catch(SQLException e){
	    		//Avisar que ocurrió un error
	    		Log.e("Error SQL", e.getMessage());
	    	}catch(CursorIndexOutOfBoundsException ce){
	    		Log.e("Cursor Error", ce.getMessage());
	    	}
	        db.close();
	    }else{
	    	Log.i("DBError", "Didn't open the Database"); 
	    }
	    return String.valueOf(max + 1);
	}
	
}