package com.ayoprez.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
 
public class SQLiteHelper extends SQLiteOpenHelper {
 
    //Sentencia SQL para crear tabla
    String sqlCreateGerman = "CREATE TABLE German (" +
    		"Word VARCHAR(30) NOT NULL, " +
    		"Level VARCHAR(6) NOT NULL," +
    		"Category VARCHAR(20) NOT NULL," +
    		"Id DECIMAL(20) NOT NULL," +
    		"Conf DECIMAL(1) NOT NULL" +
    		")";
    
    String sqlCreateEnglish = "CREATE TABLE English (" +
    		"Word VARCHAR(30) NOT NULL, " +
    		"Level VARCHAR(6) NOT NULL," +
    		"Category VARCHAR(20) NOT NULL," +
    		"Id DECIMAL(20) NOT NULL," +
    		"Conf DECIMAL(1) NOT NULL" +
    		")";
    
    String sqlCreateSpanish = "CREATE TABLE Spanish (" +
    		"Word VARCHAR(30) NOT NULL, " +
    		"Level VARCHAR(6) NOT NULL," +
    		"Category VARCHAR(20) NOT NULL," +
    		"Id DECIMAL(20) NOT NULL," +
    		"Conf DECIMAL(1) NOT NULL" +
    		")";
    
    String User_Data = "CREATE TABLE User_Data ( " +
    		"AppLanguage VARCHAR(15) NOT NULL, " +
    		"Language VARCHAR(15) NOT NULL, " +
    		"Level VARCHAR(15) NOT NULL, " +
    		"Time VARCHAR(5) NOT NULL, " +
    		"Id VARCHAR(4) NOT NULL," +
    		"CONSTRAINT pk_user PRIMARY KEY(Id)" +
    		");";
    
 
    public SQLiteHelper(Context contexto, String nombre, CursorFactory factory, int version) {
        super(contexto, nombre, factory, version);
    }
 
    @Override
    public void onCreate(SQLiteDatabase db) {
        //Se ejecuta la sentencia SQL de creación de la tabla
        db.execSQL(sqlCreateGerman);
        db.execSQL(sqlCreateSpanish);
        db.execSQL(sqlCreateEnglish);
        db.execSQL(User_Data);
        
    }
 
    @Override
    public void onUpgrade(SQLiteDatabase db, int versionAnterior, int versionNueva) {
 
        //Para actualizar las tablas
    }
}