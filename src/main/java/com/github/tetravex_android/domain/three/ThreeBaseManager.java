/*
 * ThreeBaseManager.java
 *
 * GENERATED FILE - DO NOT EDIT
 * 
 */



package com.github.tetravex_android.domain.three;

import org.dbtools.android.domain.AndroidBaseManager;
import com.github.tetravex_android.domain.DatabaseManager;
import android.database.sqlite.SQLiteDatabase;


@SuppressWarnings("all")
public abstract class ThreeBaseManager extends AndroidBaseManager<Three> {

    @javax.inject.Inject
     DatabaseManager databaseManager;

    public ThreeBaseManager() {
    }

    public String getDatabaseName() {
        return Three.DATABASE;
    }

    public Three newRecord() {
        return new Three();
    }

    public String getTableName() {
        return Three.TABLE;
    }

    public String[] getAllKeys() {
        return Three.ALL_KEYS;
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
        return Three.PRIMARY_KEY_COLUMN;
    }

    public String getDropSql() {
        return Three.DROP_TABLE;
    }

    public String getCreateSql() {
        return Three.CREATE_TABLE;
    }


}