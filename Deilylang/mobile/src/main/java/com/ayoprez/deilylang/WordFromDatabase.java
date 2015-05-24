package com.ayoprez.deilylang;

/**
 * Created by AyoPrez on 16/05/15.
 */
public class WordFromDatabase {

    private int Id;
    private String[] Word;
    private String Level;
    private String[] Type;

    public WordFromDatabase(){}

    public WordFromDatabase(int Id, String[] Word, String Level, String[] Type){
        this.Id = Id;
        this.Word = Word;
        this.Level = Level;
        this.Type = Type;
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
}
