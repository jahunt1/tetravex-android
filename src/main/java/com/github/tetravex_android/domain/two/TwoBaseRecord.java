/*
 * TwoBaseRecord.java
 *
 * GENERATED FILE - DO NOT EDIT
 * CHECKSTYLE:OFF
 * 
 */



package com.github.tetravex_android.domain.two;

import org.dbtools.android.domain.AndroidBaseRecord;
import android.database.Cursor;
import android.content.ContentValues;


@SuppressWarnings("all")
public abstract class TwoBaseRecord extends AndroidBaseRecord {

    public static final String DATABASE = "main";
    public static final String TABLE = "TWO";
    public static final String FULL_TABLE = "main.TWO";
    public static final String PRIMARY_KEY_COLUMN = "_id";
    public static final String C_ID = "_id";
    public static final String FULL_C_ID = "TWO._id";
    private long id = 0;
    public static final String C_TIME = "TIME";
    public static final String FULL_C_TIME = "TWO.TIME";
    private String time = "";
    public static final String C_SCORE_DATE = "SCORE_DATE";
    public static final String FULL_C_SCORE_DATE = "TWO.SCORE_DATE";
    private org.joda.time.DateTime scoreDate = null;
    public static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS TWO (" + 
        "_id INTEGER PRIMARY KEY  AUTOINCREMENT," + 
        "TIME TEXT NOT NULL," + 
        "SCORE_DATE INTEGER" + 
        ");" + 
        "" + 
        "";
    public static final String DROP_TABLE = "DROP TABLE IF EXISTS TWO;";
     static final String[] ALL_KEYS = new String[] {
        C_ID,
        C_TIME,
        C_SCORE_DATE};

    public TwoBaseRecord() {
    }

    @Override
    public String getDatabaseName() {
        return DATABASE;
    }

    @Override
    public String getTableName() {
        return TABLE;
    }

    @Override
    public String getIdColumnName() {
        return C_ID;
    }

    @Override
    public long getPrimaryKeyId() {
        return id;
    }

    @Override
    public void setPrimaryKeyId(long id) {
        this.id = id;
    }

    public static long getId(Cursor cursor) {
        return cursor.getLong(cursor.getColumnIndexOrThrow(C_ID));
    }

    public static String getTime(Cursor cursor) {
        return cursor.getString(cursor.getColumnIndexOrThrow(C_TIME));
    }

    public static org.joda.time.DateTime getScoreDate(Cursor cursor) {
        return !cursor.isNull(cursor.getColumnIndexOrThrow(C_SCORE_DATE)) ? new org.joda.time.DateTime(cursor.getLong(cursor.getColumnIndexOrThrow(C_SCORE_DATE))) : null;
    }

    @Override
    public String[] getAllKeys() {
        return ALL_KEYS.clone();
    }

    @Override
    public ContentValues getContentValues() {
        ContentValues values = new ContentValues();
        values.put(C_TIME, time);
        values.put(C_SCORE_DATE, scoreDate != null ? scoreDate.getMillis() : null);
        return values;
    }

    @Override
    public Object[] getValues() {
        Object[] values = new Object[]{
            time,
            scoreDate != null ? scoreDate.getMillis() : null,
        };
        return values;
    }

    public void setContent(ContentValues values) {
        time = values.getAsString(C_TIME);
        scoreDate = new org.joda.time.DateTime(values.getAsLong(C_SCORE_DATE));
    }

    @Override
    public void setContent(Cursor cursor) {
        id = cursor.getLong(cursor.getColumnIndexOrThrow(C_ID));
        time = cursor.getString(cursor.getColumnIndexOrThrow(C_TIME));
        scoreDate = !cursor.isNull(cursor.getColumnIndexOrThrow(C_SCORE_DATE)) ? new org.joda.time.DateTime(cursor.getLong(cursor.getColumnIndexOrThrow(C_SCORE_DATE))) : null;
    }

    @Override
    public String toString() {
        String text = "\n";
        text += "id = "+ id +"\n";
        text += "time = "+ time +"\n";
        text += "scoreDate = "+ scoreDate +"\n";
        return text;
    }

    public boolean isNewRecord() {
        return getPrimaryKeyId() <= 0;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public org.joda.time.DateTime getScoreDate() {
        return scoreDate;
    }

    public void setScoreDate(org.joda.time.DateTime scoreDate) {
        this.scoreDate = scoreDate;
    }


}