package com.ayoprez.deilyquote;

/**
 * Created by AyoPrez on 10/05/15.
 */
public class Quote {


    private int Id_Q;
    private String Quote;
    private String Author;
    private String Personality;
    private String Personality2;
    private String Personality3;
    private String Language;

    public Quote(){}

    public Quote(int id_q, String quote, String author, String personality, String personality2,
                String personality3, String language){
        this.Id_Q = id_q;
        this.Quote = quote;
        this.Author = author;
        this.Personality = personality;
        this.Personality2 = personality2;
        this.Personality3 = personality3;
        this.Language = language;
    }

    public int getId_Q() {
        return Id_Q;
    }

    public void setId_Q(int id_Q) {
        Id_Q = id_Q;
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