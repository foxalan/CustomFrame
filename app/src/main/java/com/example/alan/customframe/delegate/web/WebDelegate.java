package com.example.alan.customframe.delegate.web;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.webkit.WebView;

import com.example.alan.customframe.delegate.LatteDelegate;
import com.example.alan.customframe.latte.ConfigType;
import com.example.alan.customframe.latte.Latte;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

/**
 * Function :
 * Modify Date : 2018/1/26
 *
 * @Author : Alan
 * Issue : TODO
 * Whether Solve :
 */

public abstract class WebDelegate extends LatteDelegate implements IWebViewInitializer {


    private WebView mWebView = null;
    private final ReferenceQueue<WebView> WEB_VIEW_QUEUE = new ReferenceQueue<>();
    private boolean mIsWebViewAvailable;


    public abstract IWebViewInitializer setInitializer();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initWebView();
    }

    private void initWebView() {
        if (mWebView != null) {
            mWebView.removeAllViews();
            mWebView.destroy();
        } else {
            final IWebViewInitializer initializer = setInitializer();
            if (initializer != null) {
                final WeakReference<WebView> webViewWeakReference =
                        new WeakReference<>(new WebView(getContext()), WEB_VIEW_QUEUE);
                mWebView = webViewWeakReference.get();
                mWebView = initializer.initWebView(mWebView);
                mWebView.setWebViewClient(initializer.initWebViewClient());
                mWebView.setWebChromeClient(initializer.initWebChromeClient());
                final String name = Latte.getConfiguration(ConfigType.JAVASCRIPT_INTERFACE);
                //用于与网页
            //    mWebView.addJavascriptInterface(LatteWebInterface.create(this), name);
                mIsWebViewAvailable = true;
            } else {
                throw new NullPointerException("Initializer is null!");
            }
        }
    }

    @Override
    public void onBindView() {

    }
}
