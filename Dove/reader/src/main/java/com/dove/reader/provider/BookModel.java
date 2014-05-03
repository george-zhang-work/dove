package com.dove.reader.provider;

import android.accounts.AccountAuthenticatorActivity;
import android.accounts.AccountManager;
import android.content.AbstractThreadedSyncAdapter;
import android.content.SyncAdapterType;
import android.support.v4.app.FragmentPagerAdapter;

import com.dove.reader.formatter.Formatter;
import com.dove.reader.formatter.FormatterException;
import com.dove.reader.provider.book.Book;

public class BookModel {

    /**
     * Create a new instance of a BookModel with the given book.
     *
     * @param book TODO add the book illustration
     * @return A new BookModel instance.
     * @throws FormatterException If there is a failure in instantiating the given BookModel class.  T
     *                            his is a runtime exception; it is not normally expected to happen.
     */
    public static BookModel instantiate(Book book) {
//        final BookModel model;
//        final Formatter formatter = book.getFormatter();
//        switch (formatter.getType()) {
//            case JAVA:
//                model = new ConcreteBookModel(book);
//                break;
//
//            default:
//                throw new FormatterException("Unknown Formatter type " + book.getFormatter()
//                    + " for book " + book.toString(), new IllegalArgumentException());
//        }
//        formatter.format(model);
//
//        SyncAdapterType s;
//        AccountManager accountManager = AccountManager.get(null);
//        AccountAuthenticatorActivity accountAuthenticatorActivity;
//        AbstractThreadedSyncAdapter ss;
//        FragmentPagerAdapter fragmentPagerAdapter;
//        return model;
        return null;
    }

    protected final Book mBook;

    protected BookModel(Book book) {
        mBook = book;
    }

}
