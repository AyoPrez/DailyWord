package com.ayoprez.deilylang;

/**
 * Created by AyoPrez on 25/08/15.
 */
public class Translations {


    public String translateLevel(String level){
        String translatedLevel = "";
        switch(level.toLowerCase()){
            case "básico":
                translatedLevel = "Basic";
                break;
            case "fácil":
                translatedLevel = "Easy";
                break;
            case "normal":
                translatedLevel = "Normal";
                break;
            case "difícil":
                translatedLevel = "Hard";
                break;
            default:
                translatedLevel = level;
        }

        return translatedLevel;
    }

    public String translateLanguage(String language){
        String translatedLanguage = "";
        switch(language.toLowerCase()){
            case "español":
                translatedLanguage = "Spanish";
                break;
            case "inglés":
                translatedLanguage = "English";
                break;
            default:
                translatedLanguage = language;
        }

        return translatedLanguage;
    }

    public String translateLanguageFromEnglishToSpanish(String language){
        String translatedLanguage = "";
        switch(language.toLowerCase()){
            case "spanish":
                translatedLanguage = "Español";
                break;
            case "english":
                translatedLanguage = "Inglés";
                break;
            default:
                translatedLanguage = language;
        }

        return translatedLanguage;
    }
}
