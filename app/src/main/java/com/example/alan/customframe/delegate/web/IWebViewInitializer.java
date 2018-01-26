package com.example.alan.customframe.delegate.web;

import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Function :
 * Modify Date : 2018/1/26
 *
 * @Author : Alan
 * Issue : TODO
 * Whether Solve :
 */

public interface IWebViewInitializer {

    WebView initWebView(WebView webView);

    WebViewClient initWebViewClient();

    WebChromeClient initWebChromeClient();
}
