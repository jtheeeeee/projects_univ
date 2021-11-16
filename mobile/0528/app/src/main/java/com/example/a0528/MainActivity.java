package com.example.a0528;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends AppCompatActivity {
    WebView wv;
    private final Handler handeler = new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loadWeb("http://192.168.56.1:8080/c:/ws/s0528/hello.jsp");

    }

    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
        }
        if (newConfig.keyboardHidden == Configuration.KEYBOARDHIDDEN_NO) {
        }
    }

    public class AndroidHandler {
    }
    @SuppressLint("JavascriptInterface")
    @JavascriptInterface
    private void loadWeb(String url) {
        final Context myApp = this;
        wv = (WebView)findViewById(R.id.webview);
        wv.clearCache(true);
        wv.getSettings().setJavaScriptEnabled(true);
        wv.addJavascriptInterface(new AndroidHandler(), "hybrid");
        wv.getSettings().setDomStorageEnabled(true);
        wv.loadUrl(url);
        wv.setWebViewClient(new HelloWebViewClient());
        wv.setHorizontalScrollBarEnabled(false);
        wv.setVerticalScrollBarEnabled(false);
    }
    public class HelloWebViewClient extends WebViewClient {
        public boolean shouldOverrideUrlLoding(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }
}
