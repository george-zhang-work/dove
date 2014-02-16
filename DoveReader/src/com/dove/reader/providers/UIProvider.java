
package com.dove.reader.providers;

import android.provider.BaseColumns;

public class UIProvider {

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

}
