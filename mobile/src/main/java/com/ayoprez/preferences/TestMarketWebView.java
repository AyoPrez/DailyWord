package com.ayoprez.preferences;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;
import com.ayoprez.deilylang.AbstractBaseActivity;

/**
 * Created by ayo on 04.12.15.
 */
public class TestMarketWebView extends AbstractBaseActivity {

    private WebView mWebView;
    private String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mWebView  = new WebView(this);

        mWebView.getSettings().setJavaScriptEnabled(true); // enable javascript

        final Activity activity = this;

        Intent intent = getIntent();
        id = intent.getStringExtra("id");

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
                webView.loadUrl("https://play.google.com/apps/testing/" + id + "?hl=es");
                setContentView(mWebView);
                break;

            case 2:
                //German
                webView.loadUrl("https://play.google.com/apps/testing/" + id + "?hl=de");
                setContentView(mWebView);
                break;

            default:
                webView.loadUrl("https://play.google.com/apps/testing/" + id + "?hl=en");
                setContentView(mWebView);
        }
    }
}
