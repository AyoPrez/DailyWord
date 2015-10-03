package com.ayoprez.notification;

/**
 * Created by AyoPrez on 21/08/15.
 */
public class LanguageTranslation {

    public String translateLanguage(String language){
        String finalLanguage = "";

        if(checkEnglishLanguage(language)){
            finalLanguage = language;
        }else{
            if(translateSpanishLanguage(language).equals("")){
                if(translateGermanLanguage(language).equals("")){
                    if(translateEnglishLanguage(language).equals("")){
                        if(translateFrenchLanguage(language).equals("")){
                            if(!translateItalianLanguage(language).equals("")){
                                finalLanguage = translateItalianLanguage(language);
                            }
                        }else{
                            finalLanguage = translateFrenchLanguage(language);
                        }
                    }else{
                        finalLanguage = translateEnglishLanguage(language);
                    }
                }else{
                    finalLanguage = translateGermanLanguage(language);
                }
            }else{
                finalLanguage = translateSpanishLanguage(language);
            }
        }

        return finalLanguage;
    }

    private boolean checkEnglishLanguage(String language){
        boolean checked = false;
        switch (language.toLowerCase()){
            case "english":
                checked = true;
                break;
            case "spanish":
                checked = true;
                break;
            case "italian":
                checked = true;
                break;
            case "french":
                checked = true;
                break;
            case "german":
                checked = true;
                break;
            default:
                checked = false;
        }
        return checked;
    }

    private String translateSpanishLanguage(String language){
        String finalLanguage = "";
        switch (language.toLowerCase()){
            case "español":
                finalLanguage = "spanish";
                break;
            case "spanisch":
                finalLanguage = "spanish";
                break;
            case "spagnolo":
                finalLanguage = "spanish";
                break;
            case "espagnol":
                finalLanguage = "spanish";
                break;
        }
        return finalLanguage;
    }

    private String translateEnglishLanguage(String language){
        String finalLanguage = "";
        switch (language.toLowerCase()){
            case "inglés":
                finalLanguage = "english";
                break;
            case "englisch":
                finalLanguage = "english";
                break;
            case "inglese":
                finalLanguage = "english";
                break;
            case "anglais":
                finalLanguage = "english";
                break;
        }
        return finalLanguage;
    }

    private String translateGermanLanguage(String language){
        String finalLanguage = "";
        switch (language.toLowerCase()){
            case "alemán":
                finalLanguage = "german";
                break;
            case "deutsch":
                finalLanguage = "german";
                break;
            case "tedesco":
                finalLanguage = "german";
                break;
            case "allemand":
                finalLanguage = "german";
                break;
        }
        return finalLanguage;
    }

    private String translateItalianLanguage(String language){
        String finalLanguage = "";
        switch (language.toLowerCase()){
            case "italiano":
                finalLanguage = "italian";
                break;
            case "italienisch":
                finalLanguage = "italian";
                break;
            case "italien":
                finalLanguage = "italian";
                break;
        }
        return finalLanguage;
    }

    private String translateFrenchLanguage(String language){
        String finalLanguage = "";
        switch (language.toLowerCase()){
            case "francés":
                finalLanguage = "french";
                break;
            case "französisch":
                finalLanguage = "french";
                break;
            case "francese":
                finalLanguage = "french";
                break;
            case "français":
                finalLanguage = "french";
                break;
        }
        return finalLanguage;
    }
}