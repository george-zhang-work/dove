package com.dove.reader.formatter;

import com.dove.reader.provider.BookModel;
import com.dove.reader.provider.ReaderContract.FormatterType;
import com.dove.reader.provider.book.Book;

/**
 * Created by george on 4/23/14.
 */
public abstract class Formatter {

    private final String mFileType;

    protected Formatter(String fileType) {
        mFileType = fileType;
    }

    public abstract FormatterType getType();

    public abstract void format(BookModel bookModel) throws FormatterException;

    public abstract void parseMetaInfo(Book book) throws FormatterException;

    public abstract void parseManifest(Book book) throws FormatterException;
}
