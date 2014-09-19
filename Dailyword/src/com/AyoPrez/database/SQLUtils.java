package com.AyoPrez.database;

import android.content.Context;
import android.database.Cursor;
import android.database.CursorIndexOutOfBoundsException;
import android.database.SQLException;
import android.util.Log;

public class SQLUtils extends OpenAndCloseDatabaseMethods{

	private Context ctx;
	
	public SQLUtils(Context context){
		this.ctx = context;
	}
	
	public int getDatabaseRowsCount() {
		int cnt = 0;
		
		openDatabaseInWritableMode(ctx);
			
	    if(getDatabaseInWritableMode() != null){
	    	try{
	    		Cursor c = getDatabaseInWritableMode().rawQuery("SELECT * FROM User_Data", null);
	    		cnt = c.getCount();
	    		c.close();
	    	}catch(SQLException e){
	    		Log.e("Error SQL", e.getMessage());
	    	}catch(CursorIndexOutOfBoundsException ce){
	    		Log.e("Cursor Error", ce.getMessage());
	    	}
	        closeDatabase();
	    }else{
	    	Log.i("DBError", "Didn't open the Database"); 
	    }
	    return cnt;
	}

	public String LastId(){
		int max = 0;
		
		openDatabaseInWritableMode(ctx);
	
	    if(getDatabaseInWritableMode() != null){
	    	try{
	    		Cursor c = getDatabaseInWritableMode().rawQuery("SELECT Id FROM User_Data order by Id DESC limit 1", null);
	    		if (c != null && c.moveToFirst()) {
	    		    max = c.getInt(0);
	    		}
	    		c.close();    		
	    	}catch(SQLException e){
	    		Log.e("Error SQL", e.getMessage());
	    	}catch(CursorIndexOutOfBoundsException ce){
	    		Log.e("Cursor Error", ce.getMessage());
	    	}
	        closeDatabase();
	    }else{
	    	Log.i("DBError", "Didn't open the Database"); 
	    }
	    return String.valueOf(max + 1);
	}
	
}