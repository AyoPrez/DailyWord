package com.ayoprez.savedWords;

/**
 * Created by AyoPrez on 30/05/15.
 */
public class SavedWords {

    private String Word;
    private String Level;

    public SavedWords(){}

    public SavedWords(String Word, String Level){
        this.Level = Level;
        this.Word = Word;
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

}