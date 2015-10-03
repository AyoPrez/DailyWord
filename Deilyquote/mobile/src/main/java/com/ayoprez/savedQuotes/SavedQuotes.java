package com.ayoprez.savedQuotes;

/**
 * Created by AyoPrez on 30/05/15.
 */
public class SavedQuotes {

    private int id_Q;
    private String Quote;
    private String Author;
    private String Personality;
    private String Personality2;
    private String Personality3;
    private String Language;

    public SavedQuotes(){}

    public SavedQuotes(int Id, String Quote, String Author, String Personality, String Personality2, String Personality3, String Language){
        this.id_Q = Id;
        this.Quote = Quote;
        this.Author = Author;
        this.Personality = Personality;
        this.Personality2 = Personality2;
        this.Personality3 = Personality3;
        this.Language = Language;
    }

    public SavedQuotes(int Id, String Quote, String Author, String Language){
        this.id_Q = Id;
        this.Quote = Quote;
        this.Author = Author;
        this.Language = Language;
    }

    public int getId_Q() {
        return id_Q;
    }

    public void setId_Q(int id_Q) {
        this.id_Q = id_Q;
    }

    public String getQuote() {
        return Quote;
    }

    public void setQuote(String quote) {
        Quote = quote;
    }

    public String getAuthor() {
        return Author;
    }

    public void setAuthor(String author) {
        Author = author;
    }

    public String getPersonality() {
        return Personality;
    }

    public void setPersonality(String personality) {
        Personality = personality;
    }

    public String getPersonality2() {
        return Personality2;
    }

    public void setPersonality2(String personality2) {
        Personality2 = personality2;
    }

    public String getPersonality3() {
        return Personality3;
    }

    public void setPersonality3(String personality3) {
        Personality3 = personality3;
    }

    public String getLanguage() {
        return Language;
    }

    public void setLanguage(String language) {
        Language = language;
    }
}