package com.example.alan.customframe.delegate.web.client;

import android.graphics.Bitmap;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.alan.customframe.delegate.web.IPageLoadListener;

/**
 * Function :
 * Modify Date : 2018/1/26
 * @Author : Alan
 * Issue : TODO
 * Whether Solve :
 */

public class WebViewClientImpl extends WebViewClient {

    private IPageLoadListener pageLoadListener;


    public static WebViewClientImpl create() {
        return new WebViewClientImpl();
    }

    public void setPageLoadListener(IPageLoadListener pageLoadListener) {
        this.pageLoadListener = pageLoadListener;
    }

    @Override
    public void onPageStarted(WebView view, String url, Bitmap favicon) {
        super.onPageStarted(view, url, favicon);
        if (pageLoadListener != null) {
            pageLoadListener.onStart();
        }
    }

    @Override
    public void onPageFinished(WebView view, String url) {
        super.onPageFinished(view, url);
        if (pageLoadListener != null) {
            pageLoadListener.onFinish();
        }
    }
}
