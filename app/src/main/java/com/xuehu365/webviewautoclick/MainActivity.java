package com.xuehu365.webviewautoclick;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends Activity {

    private static final String[] DOM_IDS = {"link"};// 需要操作的dom元素id

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        WebView webView = new WebView(this);

        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);

        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                Log.d("lyg", "shouldOverrideUrlLoading " + url);
                return super.shouldOverrideUrlLoading(view, url);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                Log.d("lyg", "onPageFinished " + url);
                view.loadUrl(getDomOperationStatements(DOM_IDS));
            }
        });
        webView.loadUrl("file:///android_asset/schame-test.html");
    }

    public static String getDomOperationStatements(String[] domIds) {
        StringBuilder builder = new StringBuilder();
        // add javascript prefix
        builder.append("javascript:(function() { ");
        for (String domId : domIds) {
            builder.append("var item = document.getElementById('").append(domId).append("');");
            builder.append("item.click();");
        }
        // add javascript suffix
        builder.append("})()");
        return builder.toString();
    }
}
