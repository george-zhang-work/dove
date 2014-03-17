
package com.dove.alarm;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class AlarmDatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABAE_NAME = "alarms.db";
    private static final int DATABASE_VERSION = 1;

    private static final String ALARMS_TABLE_NAME = "alarms";

    private static final String DEFAULT_ALARM_1 = "";
    private static final String DEFAULT_ALARM_2 = "";
    private static final String DEFAULT_ALARM_3 = "";

    public AlarmDatabaseHelper(Context context, CursorFactory factory) {
        super(context, DATABAE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create the tables.
        createAlarmsTable(db);

        // Insert default alarms.
        final String cs = ", "; // command and space.
        final String insertMe = new StringBuilder("INSERT INTO ").append(ALARMS_TABLE_NAME)
                .append(" (").append(AlarmContract.AlarmColumns.HOUR).append(cs)
                .append(AlarmContract.AlarmColumns.MINUTES).append(cs)
                .append(AlarmContract.AlarmColumns.DAYS_OF_WEEK).append(cs)
                .append(AlarmContract.AlarmColumns.ENABLED).append(cs)
                .append(AlarmContract.AlarmColumns.VIBRATE).append(cs)
                .append(AlarmContract.AlarmColumns.ALERT).append(cs).append(") VALUES ").toString();
        db.execSQL(insertMe + DEFAULT_ALARM_1);
        db.execSQL(insertMe + DEFAULT_ALARM_2);
        db.execSQL(insertMe + DEFAULT_ALARM_3);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABL IF EXISTS " + ALARMS_TABLE_NAME);
        onCreate(db);
    }

    private static void createAlarmsTable(SQLiteDatabase db) {
        final StringBuilder sb = new StringBuilder("CREATE TABLE ").append(ALARMS_TABLE_NAME)
                .append(" (").append(AlarmContract.AlarmColumns._ID).append(" INTEGER PRIMARY KEY, ")
                .append(AlarmContract.AlarmColumns.HOUR).append(" INTEGER NOT NULL, ")
                .append(AlarmContract.AlarmColumns.MINUTES).append(" INTEGER NOT NULL, ")
                .append(AlarmContract.AlarmColumns.DAYS_OF_WEEK).append(" INTEGER NOT NULL, ")
                .append(AlarmContract.AlarmColumns.ENABLED).append(" INTEGER NOT NULL, ")
                .append(AlarmContract.AlarmColumns.VIBRATE).append(" INTEGER NOT NULL, ")
                .append(AlarmContract.AlarmColumns.ALERT).append(" INTEGER NOT NULL, ");
        db.execSQL(sb.toString());
    }
}
