package com.dove.common.web;

import android.content.Context;
import android.util.AttributeSet;
import android.webkit.GeolocationPermissions;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class DoveWebView extends WebView {

    public DoveWebView(Context context) {
        super(context);
    }

    public DoveWebView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public DoveWebView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    /**
     * WebViewClient handles all hyperlinks in the existing WebView.
     */
    protected class DoveWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            if (url.startsWith("tel:")) {
                return onTelUrlLoading(url);
            }
            if (url.startsWith("sms:")) {
                return onSmsUrlLoading(url);
            }
            return super.shouldOverrideUrlLoading(view, url);
        }
    }

    /**
     * WebChromeClient handles UI-related calls.
     */
    protected class DoveWebChromeClient extends WebChromeClient {
        @Override
        public void onGeolocationPermissionsShowPrompt(String origin, GeolocationPermissions.Callback callback) {
            // Always grant permission since the application itself requires location permission
            // and the user has therefor already grant it.
            callback.invoke(origin, true, false);
        }
    }

    protected boolean onSmsUrlLoading(String url) {
        return false;
    }

    protected boolean onTelUrlLoading(String url) {
        return false;
    }
}
