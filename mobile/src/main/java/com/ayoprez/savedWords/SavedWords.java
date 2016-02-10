package com.ayoprez.savedWords;

/**
 * Created by AyoPrez on 30/05/15.
 */
public class SavedWords {

    private int id_W;
    private String Word;
    private String Level;
    private String Type;
    private String Language;

    public SavedWords(){}

    public SavedWords(int Id, String Word, String Type, String Level, String Language){
        this.id_W = Id;
        this.Level = Level;
        this.Word = Word;
        this.Type = Type;
        this.Language = Language;
    }

    public String getLevel() {
        return Level;
    }

    public void setLevel(String level) {
        Level = level;
    }

    public String getWord() {
        return Word;
    }

    public void setWord(String word) {
        Word = word;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public String getLanguage() {
        return Language;
    }

    public void setLanguage(String language) {
        Language = language;
    }

    public int getId_W() {
        return id_W;
    }

    public void setId_W(int id_W) {
        this.id_W = id_W;
    }
}