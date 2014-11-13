/*
 * Five.java
 *
 * Generated on: 11/11/2014 09:28:13
 */



package com.github.tetravex_android.domain.five;

import android.database.Cursor;
import android.content.ContentValues;


public class Five extends FiveBaseRecord {


    public Five(Cursor cursor) {
        setContent(cursor);
    }

    public Five(ContentValues values) {
        setContent(values);
    }

    public Five() {
    }


}