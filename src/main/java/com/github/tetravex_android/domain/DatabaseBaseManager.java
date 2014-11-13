package com.github.tetravex_android.domain;

import android.util.Log;
import org.dbtools.android.domain.AndroidDatabase;
import org.dbtools.android.domain.AndroidDatabaseManager;
import android.database.sqlite.SQLiteDatabase;
import org.dbtools.android.domain.AndroidBaseManager;

/**
 * DatabaseBaseManager.java
 *
 * GENERATED FILE - DO NOT EDIT
 */
@SuppressWarnings("all")
public abstract class DatabaseBaseManager extends AndroidDatabaseManager {

    public static final String MAIN_DATABASE_NAME = "main";

    public void createMainTables(@javax.annotation.Nonnull AndroidDatabase androidDatabase) {
        SQLiteDatabase database = androidDatabase.getSqLiteDatabase();
        database.beginTransaction();
        
        // Enum Tables
        
        // Tables
        AndroidBaseManager.createTable(database,
                com.github.tetravex_android.domain.two.Two.CREATE_TABLE);
        AndroidBaseManager.createTable(database,
                com.github.tetravex_android.domain.three.Three.CREATE_TABLE);
        AndroidBaseManager.createTable(database,
                com.github.tetravex_android.domain.four.Four.CREATE_TABLE);
        AndroidBaseManager.createTable(database,
                com.github.tetravex_android.domain.five.Five.CREATE_TABLE);

        database.setTransactionSuccessful();
        database.endTransaction();
    }

    public void onCreate(@javax.annotation.Nonnull AndroidDatabase androidDatabase) {
        Log.i(TAG, "Creating database: " + androidDatabase.getName());
        if (androidDatabase.getName().equals(MAIN_DATABASE_NAME)) {
            createMainTables(androidDatabase);
        }
    }

    public void onCreateViews(@javax.annotation.Nonnull AndroidDatabase androidDatabase) {
        Log.i(TAG, "Creating database views: " + androidDatabase.getName());
    }

    public void onDropViews(@javax.annotation.Nonnull AndroidDatabase androidDatabase) {
        Log.i(TAG, "Dropping database views: " + androidDatabase.getName());
    }
}