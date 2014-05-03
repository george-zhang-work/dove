package com.dove.reader.formatter;

import android.util.AndroidRuntimeException;

public class FormatterException extends AndroidRuntimeException {
    public FormatterException(String name, Throwable cause) {
        super(name, cause);
    }
}
