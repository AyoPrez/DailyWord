package com.ayoprez.preferences;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;
import com.ayoprez.deilylang.AbstractBaseActivity;

import java.util.Locale;

/**
 * Created by AyoPrez on 5/07/15.
 */
public class ContactWebview extends AbstractBaseActivity {

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

        if(DeviceLanguageISO.equals("spa")){
            switchLanguages(mWebView, 1);
        }else{
            if(DeviceLanguageISO.equals("deu")){
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
                webView.loadUrl("http://ayoprez.com/es/contacto/");
                setContentView(mWebView);
                break;

            case 2:
                //German
                webView.loadUrl("http://ayoprez.com/de/kontakt/");
                setContentView(mWebView);
                break;

            default:
                webView.loadUrl("http://ayoprez.com/en/contact/");
                setContentView(mWebView);
        }
    }

}