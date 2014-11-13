/*
 * FiveBaseManager.java
 *
 * GENERATED FILE - DO NOT EDIT
 * 
 */



package com.github.tetravex_android.domain.five;

import org.dbtools.android.domain.AndroidBaseManager;
import com.github.tetravex_android.domain.DatabaseManager;
import android.database.sqlite.SQLiteDatabase;


@SuppressWarnings("all")
public abstract class FiveBaseManager extends AndroidBaseManager<Five> {

    @javax.inject.Inject
     DatabaseManager databaseManager;

    public FiveBaseManager() {
    }

    public String getDatabaseName() {
        return Five.DATABASE;
    }

    public Five newRecord() {
        return new Five();
    }

    public String getTableName() {
        return Five.TABLE;
    }

    public String[] getAllKeys() {
        return Five.ALL_KEYS;
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
        return Five.PRIMARY_KEY_COLUMN;
    }

    public String getDropSql() {
        return Five.DROP_TABLE;
    }

    public String getCreateSql() {
        return Five.CREATE_TABLE;
    }


}