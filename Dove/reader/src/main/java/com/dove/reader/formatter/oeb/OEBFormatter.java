package com.dove.reader.formatter.oeb;

import com.dove.reader.formatter.AbstractFormatter;
import com.dove.reader.formatter.FormatterException;
import com.dove.reader.provider.book.Book;

/**
 * Created by george on 4/23/14.
 */
public class OEBFormatter extends AbstractFormatter {

    protected OEBFormatter(String fileType) {
        super("ePub");
    }

    @Override
    public void parseMetaInfo(Book book) throws FormatterException {
    }

    @Override
    public void parseManifest(Book book) throws FormatterException {

    }
}
