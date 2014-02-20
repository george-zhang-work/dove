
package com.dove.reader.providers;

import android.provider.BaseColumns;

public class UIProvider {

    public static final class CursorStatus {
        /**
         * The cursor is actively loading more data.
         */
        public static final int LOADING = 1 << 0;

        /*
         * The cursor is currently not loading more data, but more data may be
         * avaiable.
         */
        public static final int LOADED = 1 << 1;

        /**
         * The cursor finishes loading, and there will be no more data.
         */
        public static final int COMPLETE = 1 << 2;

        /**
         * An error occured while loading data.
         */
        public static final int ERROR = 1 << 3;
    }

    public static final class CursorExtraKeys {
        /**
         * This integer column contains the staus of the message cursor. The
         * value will be one defined in {@link CursorStatus}.
         */
        public static final String EXTRA_STATUS = "cursor_status";

        /**
         * Used for finding the cause of an error.
         */
        public static final String EXTRA_ERROR = "cursor_error";

        /**
         * This integer column contains the total message count for this folder.
         */
        public static final String EXTRA_TOTAL_COUNT = "cursor_total_count";
    }

    public static final class AccountCapabilities {
    }

    public static final class AccountColumns implements BaseColumns {
        /**
         * This string column contains the human visible name for the account.
         */
        public static final String NAME = "name";

        /**
         * This string column contains the real name associated with the
         * account, e.g. "George Zhang".
         */
        public static final String READER_NAME = "readerName";

        /**
         * This string column contains the account manager name of this account.
         */
        public static final String ACCOUNT_MANAGER_NAME = "accountManagerName";

        /**
         * This integer contains the type of the account: Google versus non
         * google. This is not returned by the UIProvider,rather this is a
         * notion in the system.
         */
        public static final String TYPE = "type";

        /**
         * This integer column returns the version of the UI provider schema
         * from which this account provider will return results.
         */
        public static final String PROVIDER_VERSION = "providerVersion";

        /**
         * This string column contains the uri to directly access the
         * information for this account.
         */
        public static final String URI = "accountUri";

        /**
         * This integer column contains a bit field of the possible capabilities
         * that this account supports.
         */
        public static final String CAPABILITIES = "capabilities";

        /**
         * Mime-type defining this account.
         */
        public static final String MIME_TYPE = "mimeType";
    }

    public static final class AccountType {
    }

    public static final class FolderCapabilities {
    }

    public static final class FolderColumns {
    }

    public static final class FolderType {
    }
}
