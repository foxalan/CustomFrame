package com.example.alan.customframe.delegate.web;

import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.alan.customframe.delegate.web.chromeclient.WebChromeClientImpl;
import com.example.alan.customframe.delegate.web.client.WebViewClientImpl;

/**
 * Function :
 * Modify Date : 2018/1/26
 *
 * @Author : Alan
 * Issue : TODO
 * Whether Solve :
 */

public class WebDelegateImpl extends WebDelegate{




    @Override
    public WebView initWebView(WebView webView) {
        return new WebViewInitializer().createWebView(webView);
    }

    @Override
    public WebViewClient initWebViewClient() {
        return new WebViewClientImpl();
    }

    @Override
    public WebChromeClient initWebChromeClient() {
        return new WebChromeClientImpl();
    }

    @Override
    public Object getLayout() {
        return getWebView();
    }

    @Override
    public void onBindView() {

    }

    @Override
    public IWebViewInitializer setInitializer() {
        return this;
    }
}
