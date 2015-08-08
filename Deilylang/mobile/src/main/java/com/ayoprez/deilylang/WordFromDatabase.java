package com.ayoprez.deilylang;

/**
 * Created by AyoPrez on 16/05/15.
 */
public class WordFromDatabase {

    private int Id;
    private String[] Word;
    private String Level;
    private String[] Type;
    private String[] Languages;

    public WordFromDatabase(){}

    public WordFromDatabase(int Id, String[] Word, String Level, String[] Type, String[] Languages){
        this.Id = Id;
        this.Word = Word;
        this.Level = Level;
        this.Type = Type;
        this.Languages = Languages;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String[] getWord() {
        return Word;
    }

    public void setWord(String[] word) {
        Word = word;
    }

    public String getLevel() {
        return Level;
    }

    public void setLevel(String level) {
        Level = level;
    }

    public String[] getType() {
        return Type;
    }

    public void setType(String[] type) {
        Type = type;
    }

    public String[] getLanguages() {
        return Languages;
    }

    public void setLanguages(String[] languages) {
        Languages = languages;
    }
}
