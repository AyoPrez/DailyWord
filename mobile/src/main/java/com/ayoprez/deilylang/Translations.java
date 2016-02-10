package com.ayoprez.deilylang;

/**
 * Created by AyoPrez on 25/08/15.
 */
public class Translations {

    public static String translateLevel(String level){
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

    public static String translateLanguageFromSpanishToEnglish(String language){
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

    public static String translateLanguageFromEnglishToSpanish(String language){
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

    /**
     * Change when more languages are included.
     * **/
    public static String translateLanguagesToISO(String language){

        String lang = language.toLowerCase();
        String ISO3 = null;

        String[] English = {"inglés", "english", "englisch"};
        String[] Spanish = {"español", "spanish", "spanisch"};
        String[] German = {"alemán", "german", "deutsch"};

        if(lang.equals(English[0]) || lang.equals(English[1]) || lang.equals(English[2])){
            ISO3 = "eng";
        }else{
            if(lang.equals(Spanish[0]) || lang.equals(Spanish[1]) || lang.equals(Spanish[2])){
                ISO3 = "spa";
            }else{
                if(lang.equals(German[0]) || lang.equals(German[1]) || lang.equals(German[2])){
                    ISO3 = "deu";
                }
            }
        }
        return ISO3;
    }
}