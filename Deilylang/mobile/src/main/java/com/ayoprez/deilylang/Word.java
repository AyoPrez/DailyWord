package com.ayoprez.deilylang;

/**
 * Created by AyoPrez on 12/04/15.
 */
public class Word {

    private int Id;
    private String Word;
    private String Level;
    private String Type;

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getWord() {
        return Word;
    }

    public void setWord(String word) {
        this.Word = word;
    }

    public String getLevel() {
        return Level;
    }

    public void setLevel(String level) {
        Level = level;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }
}