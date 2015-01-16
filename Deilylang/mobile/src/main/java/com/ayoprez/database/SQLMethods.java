package com.ayoprez.database;

import android.content.Context;
import android.database.Cursor;
import android.database.CursorIndexOutOfBoundsException;
import android.database.SQLException;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

import com.ayoprez.deilylang.Utils;

public class SQLMethods {

	private Context ctx;
	private Utils UTILS;
	private SQLUtils SQLUTILS;
	
	public SQLMethods(Context context){
		this.ctx = context;
		this.UTILS = new Utils(context);
		this.SQLUTILS = new SQLUtils(context);
	}
	
	public void Create_Data(){
		try{
			//Abrimos la base de datos en modo escritura
	        SQLiteHelper dbh = new SQLiteHelper(ctx, "DBDailyWord", null, 1);
	 
	        SQLiteDatabase db = dbh.getWritableDatabase();
	 
	        //Si hemos abierto correctamente la base de datos
	        if(db != null){
	        	        	
	        	//Completar con un Boolean que indique si se guardaron bien o no.
	        	new IncludeWordsToDatabase(db);
	 
	            //Cerramos la base de datos
	            db.close(); 
	        }else{
	        	Log.i("DBError", "Didn't open the Database");
	        	//Hacer aquí que avise por medio de un dialog o un Toast. 
	        }
		}catch(SQLiteConstraintException e){
			Log.e("SQLError", e.getMessage());
		}    
	}
	
	public boolean Delete_Database_Row(String level, String time, String language){
        SQLiteHelper dbh = new SQLiteHelper(ctx, "DBDailyWord", null, 1);
 
        SQLiteDatabase db = dbh.getWritableDatabase();
        
        if(db != null){
        	try{
        		db.execSQL("DELETE FROM User_Data WHERE Time = '"+ time +"' AND Level = '"+ level +"' AND Language = '"+ language +"' ");
        		UTILS.Create_Dialog_DontFinishActivity("El momento se eliminó satisfactoriamente", "Aceptar", "Eliminar momento");
        	}catch(SQLException e){
        		Log.e("Error SQL", e.getMessage());
        	}
            db.close();
        }else{
        	Log.e("DBError", "Didn't open the Database"); 
        }
        return true;
	}

	public void Save_Data_DB(String Id, String AppLanguage, String Language, String Level, String Time){

		//Abrimos la base de datos en modo escritura
        SQLiteHelper dbh = new SQLiteHelper(ctx, "DBDailyWord", null, 1);
 
        SQLiteDatabase db = dbh.getWritableDatabase();
        
        if(db != null){
        	try{
        		db.execSQL("INSERT INTO User_Data (Id, AppLanguage, Language, Level, Time) VALUES ('" + Id + "', '" + AppLanguage + "', '" + Language + "" +
        			"', '" + Level + "', '" + Time + "')");
        		
        		UTILS.Create_Dialog("El momento ha sido guardado", "Aceptar", "Momento guardado");
        	}catch(SQLException e){
        		Log.e("Error SQL", e.getMessage());
        	}
            db.close();
        }else{
        	Log.e("DBError", "Didn't open the Database"); 
        }

	}

	public String[][] Recover_Data_Review(){
		String[][] DB_Data = new String[20][4];
		
		//Abrimos la base de datos en modo escritura
	    SQLiteHelper dbh = new SQLiteHelper(ctx, "DBDailyWord", null, 1);
	
	    SQLiteDatabase db = dbh.getWritableDatabase();
	    if(db != null){
	    	try{
	    		Cursor c = db.rawQuery("SELECT Id, Time, Language, Level FROM User_Data", null);
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
	    		//Avisar que ocurrió un error
	    		Log.e("Error SQL", e.getMessage());
	    	}catch(CursorIndexOutOfBoundsException ce){
	    		Log.e("Cursor Error", ce.getMessage());
	    	}
	        db.close();
	    }else{
	    	Log.i("DBError", "Didn't open the Database"); 
	    }
	    
	    return DB_Data;
	}
	
	public void ChangeDBConfValue(String id, String Language){
		try{
			//Abrimos la base de datos en modo escritura
	        SQLiteHelper dbh = new SQLiteHelper(ctx, "DBDailyWord", null, 1);
	 
	        SQLiteDatabase db = dbh.getWritableDatabase();
	 
	        //Si hemos abierto correctamente la base de datos
	        if(db != null){
	        	        	
	        	db.execSQL("UPDATE '"+Language+"' SET Conf = 1 WHERE Id LIKE '"+ id +"'");
	 
	            //Cerramos la base de datos
	            db.close(); 
	        }else{
	        	Log.i("DBError", "Didn't open the Database");
	        	//Hacer aquí que avise por medio de un dialog o un Toast. 
	        }
		}catch(SQLiteConstraintException e){
			Log.e("SQLError", e.getMessage());
		} 
	}
}