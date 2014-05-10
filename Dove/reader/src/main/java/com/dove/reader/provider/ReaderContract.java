package com.dove.reader.provider;

import android.net.Uri;
import android.provider.BaseColumns;

public final class ReaderContract {
    /**
     * The authority for the reader provider.
     */
    public static final String AUTHORITY = "com.dove.reader";

    /**
     * A content:// style uri to the authority for the contacts provider
     */
    public static final Uri AUTHORITY_URI = Uri.parse("content://" + AUTHORITY);

    public interface ElementColumns {
        public static final String ID = "id";
    }

    public interface TextElementColumns extends ElementColumns {
        public static final String TEXT = "text";
    }

    public interface SimpleTextElementColumns extends TextElementColumns {

    }

    public interface AccountsColumns {
        /**
         * This string column contains the human visible name for the account.
         */
        public static final String DISPLAY_NAME = "display_name";

        /**
         * This integer column contains the type of the account.
         */
        public static final String TYPE = "type";
    }

    public static final class Accounts implements BaseColumns, AccountsColumns {
        /**
         * This utility class cannot be instantiated.
         */
        private Accounts() {
        }

        /**
         * The content:// style URI for this table
         */
        public static final Uri CONTENT_URI = Uri.withAppendedPath(AUTHORITY_URI, "accounts");

        /**
         * The MIME type of {@link #CONTENT_URI} providing a directory of account.
         */
        public static final String CONTENT_TYPE = "vnd.android.cursor.dir/account";

        /**
         * The MIME type of a {@link #CONTENT_URI} subdirectory of a single account.
         */
        public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/account";
    }

    public interface ContentsColumns {
        public static final String ICON = "icon";
        public static final String NUMBER = "number";
        public static final String TEXT = "text";
        public static final String INDEXED = "indexed";
    }

    public static class Contents implements BaseColumns, ContentsColumns {
        private Contents() {
        }

        /**
         * The content:// style URI for this table
         */
        public static final Uri CONTENT_URI = Uri.withAppendedPath(AUTHORITY_URI, "books/contents");

        /**
         * The MIME type of {@link #CONTENT_URI} providing a directory of contents.
         */
        public static final String CONTENT_TYPE = "vnd.android.cursor.dir/content";

        /**
         * The MIME type of a {@link #CONTENT_URI} subdirectory of a single content.
         */
        public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/content";

    }

    public interface BookColumns {
        /**
         * This String column contains the encoding type of the book.
         */
        public static final String ENCODING = "encoding";

        /**
         * This String column contains the book's standard language shortcut, as "Chinese".
         */
        public static final String LANGUAGE = "language";
    }

    public interface AuthorColumns {
        /**
         * This string column contains the human visible name for the author.
         */
        public static final String DISPLAY_NAME = "display_name";
    }

    public static class Books implements BaseColumns, BookColumns {
        /**
         * This utility class cannot be instantiated.
         */
        private Books() {
        }

        /**
         * The content:// style URI for this table
         */
        public static final Uri CONTENT_URI = Uri.withAppendedPath(AUTHORITY_URI, "books");

        /**
         * The MIME type of {@link #CONTENT_URI} providing a directory of books.
         */
        public static final String CONTENT_TYPE = "vnd.android.cursor.dir/book";

        /**
         * The MIME type of a {@link #CONTENT_URI} subdirectory of a single book.
         */
        public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/book";
    }

    public interface DrawerItemTypes {
        public static final int COUNT = 0x01;
        public static final int CONTENT = 0x00;
    }

    public enum FormatterType {
        JAVA(true);

        public final boolean mBuiltIn;

        FormatterType(boolean builtIn) {
            mBuiltIn = builtIn;
        }
    }
}
