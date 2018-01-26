package com.example.alan.customframe.delegate.web;

import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.alan.customframe.delegate.web.chromeclient.WebChromeClientImpl;
import com.example.alan.customframe.delegate.web.client.WebViewClientImpl;
import com.example.alan.customframe.delegate.web.route.RouteKeys;
import com.example.alan.customframe.delegate.web.route.Router;

/**
 * Function :
 * Modify Date : 2018/1/26
 *
 * @Author : Alan
 * Issue : TODO
 * Whether Solve :
 */

public class WebDelegateImpl extends WebDelegate{

    public static WebDelegateImpl create(String url) {
        final Bundle args = new Bundle();
        args.putString(RouteKeys.URL.name(), url);
        final WebDelegateImpl delegate = new WebDelegateImpl();
        delegate.setArguments(args);
        return delegate;
    }

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

        if (getUrl() != null) {
            //用原生的方式模拟Web跳转并进行页面加载
            Router.getInstance().loadPage(this, getUrl());
        }

    }

    @Override
    public IWebViewInitializer setInitializer() {
        return this;
    }
}
