
package com.dove.alarm;

import android.net.Uri;
import android.provider.BaseColumns;

public final class AlarmContract {

    public static final String AUTHORITY = "com.dove.alarm";

    /**
     * Alarms start with an invalid id when it hasn't been saved to the
     * database.
     */
    public static final long INVALID_ID = -1;

    public static class AlarmType {
        public static final int ALARM_CLOCK = 0X01;
        public static final int ALARM_UPDATE = 0X02;
    }

    protected interface AlarmColumns extends BaseColumns {
        /**
         * The content:// style URL for this table
         */
        public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/alarm");

        /**
         * Alarm type defined in {@link AlarmType}
         * <p>
         * Type: INTEGER
         * </p>
         */
        public static final String TYPE = "type";

        /**
         * Hour in 24-hour localtime 0 - 23.
         * <P>
         * Type: INTEGER
         * </P>
         */
        public static final String HOUR = "hour";

        /**
         * Minutes in localtime 0 - 59
         * <P>
         * Type: INTEGER
         * </P>
         */
        public static final String MINUTES = "minutes";

        /**
         * Days of week coded as integer
         * <P>
         * Type: INTEGER
         * </P>
         */
        public static final String DAYS = "daysofweek";

        /**
         * Alarm time in UTC milliseconds from the epoch.
         * <P>
         * Type: INTEGER
         * </P>
         */
        public static final String ALARM_TIME = "alarmtime";

        /**
         * True if alarm is active
         * <P>
         * Type: BOOLEAN
         * </P>
         */
        public static final String ENABLED = "enabled";

        /**
         * True if alarm should vibrate
         * <P>
         * Type: BOOLEAN
         * </P>
         */
        public static final String VIBRATE = "vibrate";

        /**
         * Message to show when alarm triggers Note: not currently used
         * <P>
         * Type: STRING
         * </P>
         */
        public static final String MESSAGE = "message";

        /**
         * Audio alert to play when alarm triggers
         * <P>
         * Type: STRING
         * </P>
         */
        public static final String ALERT = "alert";

        /**
         * The default sort order for this table
         */
        public static final String DEFAULT_SORT_ORDER = HOUR + ", " + MINUTES + " ASC";

        // Used when filtering enabled alarms.
        public static final String WHERE_ENABLED = ENABLED + "=1";

        static final String[] ALARM_QUERY_COLUMNS = {
                _ID, HOUR, MINUTES, DAYS, ALARM_TIME, ENABLED, VIBRATE, MESSAGE, ALERT
        };
    }

}
