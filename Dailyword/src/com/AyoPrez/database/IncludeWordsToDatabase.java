package com.AyoPrez.database;

import android.database.sqlite.SQLiteDatabase;

public class IncludeWordsToDatabase {

	public IncludeWordsToDatabase(SQLiteDatabase database){

		SpanishWords(database);
		EnglishWords(database);
		GermanWords(database);
	}

	public void SpanishWords(SQLiteDatabase database){

		//Insertar un registro
		database.execSQL("INSERT INTO Spanish (Word, Level, Category, Id, Conf) VALUES ('Hola', 'Básico', 'Sustantivo', 01, 0)");
		database.execSQL("INSERT INTO Spanish (Word, Level, Category, Id, Conf) VALUES ('Bueno', 'Básico', 'Adjetivo', 02, 0)");
		database.execSQL("INSERT INTO Spanish (Word, Level, Category, Id, Conf) VALUES ('Malo', 'Básico', 'Adjetivo', 03, 0)");
	}

	public void EnglishWords(SQLiteDatabase database){

		//Insertar un registro
		database.execSQL("INSERT INTO English (Word, Level, Category, Id, Conf) VALUES ('Hello', 'Basic', 'Noun', 01, 0)");
		database.execSQL("INSERT INTO Spanish (Word, Level, Category, Id, Conf) VALUES ('Good', 'Basic', 'Adjective', 02, 0)");
		database.execSQL("INSERT INTO Spanish (Word, Level, Category, Id, Conf) VALUES ('Bad', 'Basic', 'Adjective', 03, 0)");
	}

	public void GermanWords(SQLiteDatabase database){

		//Insertar un registro
		database.execSQL("INSERT INTO German (Word, Level, Category, Id, Conf) VALUES ('Hallo', 'Basic', 'Nomen', 01, 0)");
		database.execSQL("INSERT INTO Spanish (Word, Level, Category, Id, Conf) VALUES ('Gut', 'Basic', 'Adjektiv', 02, 0)");
		database.execSQL("INSERT INTO Spanish (Word, Level, Category, Id, Conf) VALUES ('Schlecht', 'Basic', 'Adjektiv', 03, 0)");
	}	
}