package com.ayoprez.deilyquote;

import android.app.Fragment;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceFragment;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.text.Html;
import android.text.SpannableString;
import android.text.method.LinkMovementMethod;
import android.text.util.Linkify;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Locale;

/**
 * Created by AyoPrez on 31/05/15.
 */
public class Preferences extends PreferenceActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getFragmentManager().beginTransaction().replace(
                android.R.id.content, new MyPreferenceFragment()).commit();
    }

    public static class MyPreferenceFragment extends PreferenceFragment {
        @Override
        public void onCreate(final Bundle savedInstanceState){
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.layout.preferences_layout);

//            ListPreference languageList = (ListPreference)findPreference("language");
//
//
//            languageList.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
//                @Override
//                public boolean onPreferenceChange(Preference preference, Object newValue) {
//
//                    changeLanguage(newValue.toString());
//                    return false;
//                }
//            });

            Preference buttonAbout = (Preference)findPreference("about");
            buttonAbout.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
                @Override
                public boolean onPreferenceClick(Preference preference) {
                    showAboutDialog();
                    return true;
                }
            });

            Preference buttonLegal = (Preference)findPreference("legal");
            buttonLegal.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
                @Override
                public boolean onPreferenceClick(Preference preference) {
                    getFragmentManager().beginTransaction().replace(
                            android.R.id.content, new LegalFragment()).commit();
                    return true;
                }
            });

            Preference buttonContact = (Preference)findPreference("contact");
            buttonContact.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
                @Override
                public boolean onPreferenceClick(Preference preference) {
                    //TODO Make a contact form here
                    showContactDialog();
                    return true;
                }
            });

        }

        private void showAboutDialog(){
            final SpannableString m = new SpannableString(
                    getString(R.string.info_message) + " " +
                            Html.fromHtml(" <a href=\"http://www.ayoprez.com\">www.ayoprez.com</a>."));
            Linkify.addLinks(m, Linkify.WEB_URLS);

            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity())
                    .setTitle(getString(R.string.info))
                    .setMessage(m);

            AlertDialog alertDialog = builder.create();
            alertDialog.show();
            ((TextView) alertDialog.findViewById(android.R.id.message)).setMovementMethod(LinkMovementMethod.getInstance());
        }

        private void showContactDialog(){
            final SpannableString m = new SpannableString(
                    getString(R.string.contact_message) + " " +
                            Html.fromHtml(" <a href=\"http://www.ayoprez.com/es/contacto\">www.ayoprez.com</a>."));
            Linkify.addLinks(m, Linkify.WEB_URLS);

            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity())
                    .setTitle(getString(R.string.contact))
                    .setMessage(m);

            AlertDialog alertDialog = builder.create();
            alertDialog.show();
            ((TextView) alertDialog.findViewById(android.R.id.message)).setMovementMethod(LinkMovementMethod.getInstance());
        }

        private void changeLanguage(String language){

            switch (translateLanguage(language)){
                case "english":
                    saveLanguage("en");
                    break;

                case "german":
                    saveLanguage("de");
                    break;

                case "spanish":
                    saveLanguage("es");
                    break;

                default:
                    saveLanguage("en");
            }

        }

        @Override
        public void onConfigurationChanged(Configuration newConfig) {
            super.onConfigurationChanged(newConfig);
        }

        private String translateLanguage(String language){
            String lang = "";

            if(language.equals("Inglés") || language.equals("Englisch") || language.equals("English")){
                lang = "english";
            }else {
                if (language.equals("Español") || language.equals("Spanisch") || language.equals("Spanish")) {
                    lang = "spanish";
                }else {
                    if(language.equals("Alemán") || language.equals("Deutsch") || language.equals("German")){
                        lang = "german";
                    }
                }
            }

            return lang;
        }

        private void saveLanguage(String languageToLoad){
            Locale locale = new Locale(languageToLoad);
            Locale.setDefault(locale);
            Configuration config = new Configuration();
            config.locale = locale;
            getActivity().getBaseContext().getResources()
                    .updateConfiguration(config, getActivity().getBaseContext().getResources().getDisplayMetrics());

            SharedPreferences languagepref = getActivity().getSharedPreferences("language", MODE_PRIVATE);
            SharedPreferences.Editor editor = languagepref.edit();
            editor.putString("languageToLoad",languageToLoad );
            editor.commit();
        }
    }

    public static class ContactFragment extends Fragment{
        @Override
        public void onViewCreated(View view, Bundle savedInstanceState) {
            super.onViewCreated(view, savedInstanceState);

        }
    }

    public static class LegalFragment extends Fragment {
        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            return inflater.inflate(R.layout.legal_layout, container, false);
        }
    }

}