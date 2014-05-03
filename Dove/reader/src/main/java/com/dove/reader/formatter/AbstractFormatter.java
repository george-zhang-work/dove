package com.dove.reader.formatter;

import com.dove.reader.provider.BookModel;
import com.dove.reader.provider.ReaderContract;

/**
 * Created by george on 4/23/14.
 */
public abstract class AbstractFormatter extends Formatter {

    protected AbstractFormatter(String fileType) {
        super(fileType);
    }

    @Override
    public ReaderContract.FormatterType getType() {
        return ReaderContract.FormatterType.JAVA;
    }

    @Override
    public void format(BookModel bookModel) throws FormatterException {

    }
}
