package com.ayoprez.deilylang;

import java.util.ArrayList;

/**
 * Created by AyoPrez on 16/05/15.
 */
public class MockDataBase {
    private final ArrayList<WordFromDatabase> words;

    public MockDataBase() {
        words = new ArrayList<>();

        addWord(1, joinWords("Hola", "Hello"), "Basic", 0, joinWords("Interjección", "Interjection"));
        addWord(2, joinWords("Bueno", "Good"), "Basic", 0, joinWords("Adjetivo", "Adjective"));
        addWord(3, joinWords("Malo", "Bad"), "Basic", 0, joinWords("Adjetivo", "Adjective"));
        addWord(4, joinWords("Mesa", "Table"), "Basic", 0, joinWords("Sustantivo", "Noun"));
        addWord(5, joinWords("Silla", "Chair"), "Basic", 0, joinWords("Sustantivo", "Noun"));
    }

    public ArrayList<WordFromDatabase> getListOfWords(){
        return words;
    }

    public String[] joinWords(String word1, String word2){
        String[] words = {word1, word2};
        return  words;
    }

    public void addWord(int Id, String[] Word, String Level, int Conf, String[] Type) {
        words.add(new WordFromDatabase(Id, Word, Level, Type));
    }
}