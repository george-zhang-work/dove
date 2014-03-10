
package com.dove.reader.providers;

import android.database.Cursor;
import android.database.MatrixCursor;
import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;

import com.dove.reader.content.CursorCreator;
import com.dove.reader.log.LogTag;
import com.dove.reader.log.LogUtils;
import com.dove.reader.providers.UIProvider.AccountColumns;
import com.google.common.base.Objects;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Account implements Parcelable {
    private static final String LOG_TAG = LogTag.getLogTag();

    /**
     * Human readable account name.
     */
    private final String name;

    /**
     * Account type. MUST MATCH SYSTEM ACCOUNT MANAGER TYPE.
     */
    private final String type;

    /**
     * The real name associated with the account, e.g. "John Doe"
     */
    private final String readerName;

    /**
     * Account manager name. MUST MATCH SYSTEM ACCOUNT MANAGER NAME
     */
    private final String accountManagerName;

    /**
     * The version of the UI provider schema from which this account provider
     * will return results.
     */
    public final int providerVersion;

    /**
     * The uri to directly access the information for this account.
     */
    public final Uri uri;

    /**
     * The possible capabilities that this account supports.
     */
    public final int capabilities;

    /**
     * Mime-type defining this account.
     */
    public final String mimeType;

    /**
     * Settings object for account.
     */
    public final Settings settings = null;

    /**
     * Return a serialized String for this account.
     */
    public synchronized String serialize() {
        JSONObject json = new JSONObject();
        try {
            json.put(AccountColumns.NAME, name);
            json.put(AccountColumns.TYPE, type);
            json.put(AccountColumns.READER_NAME, readerName);
            json.put(AccountColumns.ACCOUNT_MANAGER_NAME, accountManagerName);
            json.put(AccountColumns.PROVIDER_VERSION, providerVersion);
            json.put(AccountColumns.URI, uri);
            json.put(AccountColumns.CAPABILITIES, capabilities);
            json.put(AccountColumns.MIME_TYPE, mimeType);
        } catch (JSONException e) {
            LogUtils.wtf(LOG_TAG, "Could not serialize account with name %s", name);
        }
        return json.toString();
    }

    /**
     * Create a new instance of an Account object using a serialized instance
     * created previously using {@link #serialize()}. This returns null if the
     * serialized instance was invalid or does not represent a valid account
     * object.
     * 
     * @param serializedAccount JSON encoded account object
     * @return Account object
     */
    public static Account newinstance(String serializedAccount) {
        // The heavy lifting is done by Account(name, type, serializedAccount).
        // This method is a wrapper to check for errors and exceptions and
        // return back a null in cases something breaks.
        JSONObject json;
        try {
            json = new JSONObject(serializedAccount);
            final String name = (String) json.get(UIProvider.AccountColumns.NAME);
            final String type = (String) json.get(UIProvider.AccountColumns.TYPE);
            return new Account(name, type, serializedAccount);
        } catch (JSONException e) {
            LogUtils.w(LOG_TAG, e, "Could not create an account from this input: \"%s\"",
                    serializedAccount);
            return null;
        }
    }

    /**
     * Construct a new Account instance from a previously serialized string.
     * This calls {@link android.accounts.Account#Account(String, String)} with
     * name and type given as the first two arguments.
     * <p>
     * This is private. Public uses should go through the safe
     * {@link #newinstance(String)} method.
     * </p>
     * 
     * @param acctName name of account in {@link android.accounts.Account}
     * @param acctType type of account in {@link android.accounts.Account}
     * @param jsonAccount string obtained from {@link #serialize()} on a valid
     *            account.
     * @throws JSONException
     */
    private Account(String acctName, String acctType, String jsonAccount) throws JSONException {
        name = acctName;
        type = acctType;
        final JSONObject json = new JSONObject(jsonAccount);
        readerName = json.optString(AccountColumns.READER_NAME);
        final String amName = json.optString(AccountColumns.ACCOUNT_MANAGER_NAME);
        // We need accountManagerName to be filled in, but we might be dealing
        // with an old cache entry which doesn't have it, so use the display
        // name instead in that case as a fallback
        if (TextUtils.isEmpty(amName)) {
            accountManagerName = name;
        } else {
            accountManagerName = amName;
        }
        providerVersion = json.getInt(AccountColumns.PROVIDER_VERSION);
        uri = Uri.parse(json.optString(AccountColumns.URI));
        capabilities = json.getInt(AccountColumns.CAPABILITIES);
        mimeType = json.optString(AccountColumns.MIME_TYPE);
    }

    public Account(Cursor cursor) {
        name = cursor.getString(cursor.getColumnIndex(AccountColumns.NAME));
        type = cursor.getString(cursor.getColumnIndex(AccountColumns.TYPE));
        readerName = cursor.getString(cursor.getColumnIndex(AccountColumns.READER_NAME));
        accountManagerName = cursor.getString(cursor
                .getColumnIndex(AccountColumns.ACCOUNT_MANAGER_NAME));

        final int capabilitiesColumnIndex = cursor.getColumnIndex(AccountColumns.CAPABILITIES);
        if (capabilitiesColumnIndex != -1) {
            capabilities = cursor.getInt(capabilitiesColumnIndex);
        } else {
            capabilities = 0;
        }

        providerVersion = cursor.getInt(cursor.getColumnIndex(AccountColumns.PROVIDER_VERSION));
        uri = Uri.parse(cursor.getString(cursor.getColumnIndex(AccountColumns.URI)));
        mimeType = cursor.getString(cursor.getColumnIndex(AccountColumns.MIME_TYPE));
    }

    public Account(Parcel in, ClassLoader loader) {
        name = in.readString();
        readerName = in.readString();
        type = in.readString();
        accountManagerName = in.readString();
        providerVersion = in.readInt();
        uri = in.readParcelable(null);
        capabilities = in.readInt();
        mimeType = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(readerName);
        dest.writeString(type);
        dest.writeString(accountManagerName);
        dest.writeInt(providerVersion);
        dest.writeParcelable(uri, 0);
        dest.writeInt(capabilities);
        dest.writeString(mimeType);
    }

    @Override
    public String toString() {
        return serialize();
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if ((o == null) || (o.getClass() != this.getClass())) {
            return false;
        }

        final Account other = (Account) o;
        return TextUtils.equals(name, other.name) && TextUtils.equals(readerName, other.readerName)
                && TextUtils.equals(accountManagerName, other.accountManagerName)
                && TextUtils.equals(type, other.type) && capabilities == other.capabilities
                && providerVersion == other.providerVersion && Objects.equal(uri, other.uri)
                && TextUtils.equals(mimeType, other.mimeType);

    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name, readerName, accountManagerName, type, capabilities,
                providerVersion, uri, mimeType);
    }

    /**
     * Returns whether two Accounts match, as determined by their base URIs.
     * <p>
     * For a deep object comparison, use {@link #equals(Object)}.
     */
    public boolean matches(Account other) {
        return other != null && Objects.equal(uri, other.uri);
    }

    public static final ClassLoaderCreator<Account> CREATOR = new ClassLoaderCreator<Account>() {
        @Override
        public Account createFromParcel(Parcel source, ClassLoader loader) {
            return new Account(source, loader);
        }

        @Override
        public Account createFromParcel(Parcel source) {
            return new Account(source, null);
        }

        @Override
        public Account[] newArray(int size) {
            return new Account[size];
        }
    };

    /**
     * Get Human readable account name.
     * 
     * @return The account display name.
     */
    public String getDisplayName() {
        return name;
    }

    /**
     * Returns the real name associated with the account, e.g. "John Doe" or
     * null if no such name has been configured
     * 
     * @return reader name
     */
    public String getReaderName() {
        return readerName;
    }

    /**
     * Creates a {@link Map} where the column name is the key and the value is
     * the value, which can be used for populating a {@link MatrixCursor}.
     */
    public Map<String, Object> getValueMap() {
        // ImmutableMap.Builder does not allow null values
        final Map<String, Object> map = new HashMap<String, Object>();
        map.put(AccountColumns._ID, 0);
        map.put(AccountColumns.NAME, name);
        map.put(AccountColumns.READER_NAME, readerName);
        map.put(AccountColumns.TYPE, type);
        map.put(AccountColumns.ACCOUNT_MANAGER_NAME, accountManagerName);
        map.put(AccountColumns.PROVIDER_VERSION, providerVersion);
        map.put(AccountColumns.URI, uri);
        map.put(AccountColumns.CAPABILITIES, capabilities);
        map.put(AccountColumns.MIME_TYPE, mimeType);

        return map;
    }

    /**
     * Public object that knows how to construct Accounts given Cursors.
     */
    public final static CursorCreator<Account> FACTORY = new CursorCreator<Account>() {
        @Override
        public Account createFromCursor(Cursor c) {
            return new Account(c);
        }

        @Override
        public String toString() {
            return "Account CursorCreator";
        }
    };
}
