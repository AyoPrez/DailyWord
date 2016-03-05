package com.ayoprez.deilylang;

import java.util.Locale;

/**
 * Created by AyoPrez on 24.10.15.
 */
public class DetectDeviceLanguage {

    public static String getISO3Language(){
        return Locale.getDefault().getISO3Language();
    }

    public static Locale getLocale(String language){
        if(language.length() == 3){
            return getLocaleFromISO3(language);
        }else{
            return getLocaleFromString(language);
        }
    }

    public static Locale getLocaleFromString(String language){

        switch (language.toLowerCase()) {
            case "spanish":
                return new Locale("es");
            case "german":
                return new Locale("de");
            case "italian":
                return new Locale("it");
            case "french":
                return new Locale("fr");
            case "english":
                return new Locale("en");
            default:
                return new Locale("en");
        }
    }

    public static Locale getLocaleFromISO3(String ISO3){
        switch (ISO3.toLowerCase()){
            case "spa":
                return new Locale("es");
            case "deu":
                return new Locale("de");
            case "ita":
                return new Locale("it");
            case "fra":
                return new Locale("fr");
            case "eng":
                return new Locale("en");
            default:
                return new Locale("en");
        }
    }

    public static String getISO3FromString(String language){
        switch (language){
            case "spanish":
                return "spa";
            case "español":
                return "spa";
            case "english":
                return "eng";
            case "inglés":
                return "eng";
            default:
                return null;
        }
    }
}