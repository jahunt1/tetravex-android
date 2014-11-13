/*
 * TwoBaseManager.java
 *
 * GENERATED FILE - DO NOT EDIT
 * 
 */



package com.github.tetravex_android.domain.two;

import org.dbtools.android.domain.AndroidBaseManager;
import com.github.tetravex_android.domain.DatabaseManager;
import android.database.sqlite.SQLiteDatabase;


@SuppressWarnings("all")
public abstract class TwoBaseManager extends AndroidBaseManager<Two> {

    @javax.inject.Inject
     DatabaseManager databaseManager;

    public TwoBaseManager() {
    }

    public String getDatabaseName() {
        return Two.DATABASE;
    }

    public Two newRecord() {
        return new Two();
    }

    public String getTableName() {
        return Two.TABLE;
    }

    public String[] getAllKeys() {
        return Two.ALL_KEYS;
    }

    public SQLiteDatabase getReadableDatabase(String databaseName) {
        return databaseManager.getReadableDatabase(databaseName);
    }

    public SQLiteDatabase getReadableDatabase() {
        return databaseManager.getReadableDatabase(getDatabaseName());
    }

    public SQLiteDatabase getWritableDatabase(String databaseName) {
        return databaseManager.getWritableDatabase(databaseName);
    }

    public SQLiteDatabase getWritableDatabase() {
        return databaseManager.getWritableDatabase(getDatabaseName());
    }

    public String getPrimaryKey() {
        return Two.PRIMARY_KEY_COLUMN;
    }

    public String getDropSql() {
        return Two.DROP_TABLE;
    }

    public String getCreateSql() {
        return Two.CREATE_TABLE;
    }


}