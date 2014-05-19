package com.dove.reader.ui.book;

import android.content.Context;
import android.os.Environment;
import android.util.AttributeSet;
import android.webkit.WebView;

import java.io.File;
import java.util.HashMap;

/**
 * Created by george on 5/13/14.
 */
public class BookView extends WebView {

    public BookView(Context context) {
        super(context);
        init();
    }

    public BookView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public BookView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init() {
        File dir = Environment.getExternalStorageDirectory();
//        loadUrl("http://wap.baidu.com");
        loadUrl("file:///android_asset/ebooks/book.epub/OPS/cover-page.xhtml");

        HashMap<String, String> map = new HashMap<>();
    }
}
