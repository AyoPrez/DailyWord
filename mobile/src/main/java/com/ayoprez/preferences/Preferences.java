package com.ayoprez.preferences;

import android.app.Activity;
import android.app.Fragment;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceFragment;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.text.Html;
import android.text.SpannableString;
import android.text.method.LinkMovementMethod;
import android.text.util.Linkify;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.ayoprez.deilylang.R;
import com.crashlytics.android.Crashlytics;
import com.crashlytics.android.answers.CustomEvent;

import java.util.Locale;

/**
 * Created by AyoPrez on 03/08/15.
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
        public void onCreate(final Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.preferences_layout);

            Preference buttonAbout = (Preference) findPreference("about");
            buttonAbout.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
                @Override
                public boolean onPreferenceClick(Preference preference) {
                    showAboutDialog();
                    return true;
                }
            });

            Preference buttonFeedback = (Preference) findPreference("feedback");
            buttonFeedback.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
                @Override
                public boolean onPreferenceClick(Preference preference) {
                    sendFeedback();
                    return true;
                }
            });

            Preference buttonLegal = (Preference) findPreference("legal");
            buttonLegal.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
                @Override
                public boolean onPreferenceClick(Preference preference) {
                    Crashlytics.getInstance().answers.logCustom(new CustomEvent("Preferences").putCustomAttribute("pref", "Legal"));
                    getFragmentManager().beginTransaction().replace(
                            android.R.id.content, new LegalFragment()).commit();
                    return true;
                }
            });

            Preference buttonContact = (Preference) findPreference("contact");
            buttonContact.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
                @Override
                public boolean onPreferenceClick(Preference preference) {
                    //TODO Make a contact form here
                    showContactWebView();
                    return true;
                }
            });

            Preference buttonMoreApps = (Preference) findPreference("more");
            buttonMoreApps.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener(){
                @Override
                public boolean onPreferenceClick(Preference preference) {
                    Crashlytics.getInstance().answers.logCustom(new CustomEvent("Preferences").putCustomAttribute("pref", "MoreApps"));
                    showMoreAppsDialog();
                    return true;
                }
            });

            Preference buttonRateApp = (Preference) findPreference("rate");
            buttonRateApp.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener(){
                @Override
                public boolean onPreferenceClick(Preference preference) {
                    openMarket(getString(R.string.marketId));
                    return true;
                }
            });

        }

        private void showContactWebView() {
            Intent i = new Intent(getActivity(), ContactWebview.class);
            startActivity(i);
        }

        private void openMarket(String id){
            Intent intent;

            intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse("market://details?id="+id));
            //Try Google play
            if (!MyStartActivity(intent)) {
                //Market (Google play) app seems not installed, let's try to open a webbrowser
                intent.setData(Uri.parse("https://play.google.com/store/apps/details?id="+id));
                if (!MyStartActivity(intent)) {
                    //Well if this also fails, we have run out of options, inform the user.
                    Snackbar.make(getView(), getString(R.string.rateError), Snackbar.LENGTH_LONG).show();
                }
            }
        }

        private void openTestMarket(String id){
//                https://play.google.com/apps/testing/com.ayoprez.speechhelpcards?hl=de
            Intent i = new Intent(getActivity(), TestMarketWebView.class);
            i.putExtra("id", id);
            startActivity(i);
        }

        private boolean MyStartActivity(Intent aIntent) {
            try{
                startActivity(aIntent);
                return true;
            } catch (ActivityNotFoundException e) {
                return false;
            }
        }

        private void showAboutDialog() {
            final SpannableString m = new SpannableString(
                    getString(R.string.info_message) + " " +
                            Html.fromHtml(" <a href=\"http://www.ayoprez.com\">www.ayoprez.com</a>."));
            Linkify.addLinks(m, Linkify.WEB_URLS);

            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity())
                    .setTitle(getString(R.string.info))
                    .setMessage(m)
                    .setView(R.layout.preferences_madein_germany_layout);

            AlertDialog alertDialog = builder.create();
            alertDialog.show();
            ((TextView) alertDialog.findViewById(android.R.id.message)).setMovementMethod(LinkMovementMethod.getInstance());
        }

        public void sendFeedback(){
            Intent i = new Intent(Intent.ACTION_SEND);
            i.setType("message/rfc822");
            i.putExtra(Intent.EXTRA_EMAIL, new String[]{"info@ayoprez.com"});
            i.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.feedback_mail_subject));
            i.putExtra(Intent.EXTRA_TEXT   , "");
            try {
                startActivity(Intent.createChooser(i, getString(R.string.feedback_mail_dialog_title)));
            } catch (android.content.ActivityNotFoundException ex) {
                Snackbar.make(getView(), getString(R.string.feedback_mail_error), Snackbar.LENGTH_SHORT).show();
            }
        }

        public void showMoreAppsDialog(){

            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity())
                    .setTitle(getString(R.string.more_of_Ayoprez))
                    .setView(R.layout.preferences_moreapps_layout);

            AlertDialog alertDialog = builder.create();
            alertDialog.show();
            ((ImageButton) alertDialog.findViewById(R.id.button_SHC)).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    openTestMarket(getString(R.string.SHCId));
                }
            });

            ((ImageButton) alertDialog.findViewById(R.id.button_DeilyQuote)).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    openTestMarket(getString(R.string.QuoteId));
                }
            });
        }
    }

    public static class LegalFragment extends Fragment {
        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            return inflater.inflate(R.layout.preferences_legal_layout, container, false);
        }
    }
}