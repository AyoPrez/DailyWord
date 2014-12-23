package com.ayoprez.database;

import android.database.sqlite.SQLiteDatabase;

public class IncludeWordsToDatabase {
	
	public IncludeWordsToDatabase(SQLiteDatabase db){
	
		SpanishWords(db);
		EnglishWords(db);
		GermanWords(db);
	}
		
	public void SpanishWords(SQLiteDatabase db){
		
		//Insertar un registro
		db.execSQL("INSERT INTO Spanish (Word, Level, Category, Id, Conf) VALUES ('Hola', 'Básico', 'Sustantivo', 01, 0)");
		db.execSQL("INSERT INTO Spanish (Word, Level, Category, Id, Conf) VALUES ('Bueno', 'Básico', 'Adjetivo', 02, 0)");
		db.execSQL("INSERT INTO Spanish (Word, Level, Category, Id, Conf) VALUES ('Malo', 'Básico', 'Adjetivo', 03, 0)");
	}
	
	public void EnglishWords(SQLiteDatabase db){
		
		//Insertar un registro
		db.execSQL("INSERT INTO English (Word, Level, Category, Id, Conf) VALUES ('Hello', 'Basic', 'Noun', 01, 0)");
		db.execSQL("INSERT INTO Spanish (Word, Level, Category, Id, Conf) VALUES ('Good', 'Basic', 'Adjective', 02, 0)");
		db.execSQL("INSERT INTO Spanish (Word, Level, Category, Id, Conf) VALUES ('Bad', 'Basic', 'Adjective', 03, 0)");
	}

	public void GermanWords(SQLiteDatabase db){
		
		//Insertar un registro
		db.execSQL("INSERT INTO German (Word, Level, Category, Id, Conf) VALUES ('Hallo', 'Basic', 'Nomen', 01, 0)");
		db.execSQL("INSERT INTO Spanish (Word, Level, Category, Id, Conf) VALUES ('Gut', 'Basic', 'Adjektiv', 02, 0)");
		db.execSQL("INSERT INTO Spanish (Word, Level, Category, Id, Conf) VALUES ('Schlecht', 'Basic', 'Adjektiv', 03, 0)");
	}
}
