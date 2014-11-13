/*
 * FourBaseManager.java
 *
 * GENERATED FILE - DO NOT EDIT
 * 
 */



package com.github.tetravex_android.domain.four;

import org.dbtools.android.domain.AndroidBaseManager;
import com.github.tetravex_android.domain.DatabaseManager;
import android.database.sqlite.SQLiteDatabase;


@SuppressWarnings("all")
public abstract class FourBaseManager extends AndroidBaseManager<Four> {

    @javax.inject.Inject
     DatabaseManager databaseManager;

    public FourBaseManager() {
    }

    public String getDatabaseName() {
        return Four.DATABASE;
    }

    public Four newRecord() {
        return new Four();
    }

    public String getTableName() {
        return Four.TABLE;
    }

    public String[] getAllKeys() {
        return Four.ALL_KEYS;
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
        return Four.PRIMARY_KEY_COLUMN;
    }

    public String getDropSql() {
        return Four.DROP_TABLE;
    }

    public String getCreateSql() {
        return Four.CREATE_TABLE;
    }


}