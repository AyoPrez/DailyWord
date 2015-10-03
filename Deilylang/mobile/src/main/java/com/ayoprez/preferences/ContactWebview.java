package com.ayoprez.preferences;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import java.util.Locale;

/**
 * Created by AyoPrez on 5/07/15.
 */
public class ContactWebview extends Activity {

    private WebView mWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mWebView  = new WebView(this);

        mWebView.getSettings().setJavaScriptEnabled(true); // enable javascript

        final Activity activity = this;

        mWebView.setWebViewClient(new WebViewClient() {
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                Toast.makeText(activity, description, Toast.LENGTH_SHORT).show();
            }
        });


        String iso3Language = Locale.getDefault().getISO3Language();

        if(iso3Language.equals("spa")){
            switchLanguages(mWebView, 1);
        }else{
            if(iso3Language.equals("deu")){
                switchLanguages(mWebView, 2);
            }else{
                switchLanguages(mWebView, 3);
            }
        }
    }

    private void switchLanguages(WebView webView, int language){

        switch (language){
            case 1:
                //Spanish
                webView.loadUrl("http://m.ayoprez.com/es/contacto/");
                setContentView(mWebView);
                break;

            case 2:
                //German
                webView.loadUrl("http://m.ayoprez.com/de/kontakt/");
                setContentView(mWebView);
                break;

            default:
                webView.loadUrl("http://m.ayoprez.com/en/contact/");
                setContentView(mWebView);
        }
    }

}