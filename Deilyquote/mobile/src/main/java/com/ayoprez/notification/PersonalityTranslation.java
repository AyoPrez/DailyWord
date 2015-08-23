package com.ayoprez.notification;

/**
 * Created by AyoPrez on 22/08/15.
 */
public class PersonalityTranslation {

    public String translatePersonality(String personality) {
        //Spanish, German, Italian, French
        if (personality.equals("Estudiante") || personality.equals("Student") || personality.equals("Studente") || personality.equals("Étudiant")) {
            return "student";
        } else {
            if (personality.equals("Amante de la vida") || personality.equals("Leben Liebhaber") || personality.equals("Amante della vita") || personality.equals("Amoureux de la vie")) {
                return "life lover";
            } else {
                if (personality.equals("Comediante/a") || personality.equals("Komiker") || personality.equals("Comico") || personality.equals("Comédien/ne")) {
                    return "comedian";
                } else {
                    if (personality.equals("Justiciero/a") || personality.equals("Rechtschaffen") || personality.equals("Giusto") || personality.equals("Vertueux")) {
                        return "righteous";
                    } else {
                        if (personality.equals("Padre/Madre") || personality.equals("Vater/Mutter") || personality.equals("Padre/Madre") || personality.equals("Père/Mère")) {
                            return "parent";
                        } else {
                            if (personality.equals("Espiritual") || personality.equals("Spiritual") || personality.equals("Spirituale") || personality.equals("Spirituel")) {
                                return "spiritual";
                            } else {
                                if (personality.equals("Solitario/a") || personality.equals("Einzelgänger/in") || personality.equals("Solitario") || personality.equals("Solitaire")) {
                                    return "lonely";
                                } else {
                                    if (personality.equals("Enamorado/a") || personality.equals("Verliebt") || personality.equals("Innamorato") || personality.equals("Amoureux")) {
                                        return "in love";
                                    } else {
                                        if (personality.equals("Emprendedor/a") || personality.equals("Unternehmer") || personality.equals("Imprenditore") || personality.equals("Entrepreneur")) {
                                            return "entrepreneur";
                                        }else{
                                            return "all";
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}