/*
 * Two.java
 *
 * Created: 11/11/2014 09:28:12
 */



package com.github.tetravex_android.domain.two;

import android.database.Cursor;
import android.content.ContentValues;


public class Two extends TwoBaseRecord {


    public Two(Cursor cursor) {
        setContent(cursor);
    }

    public Two(ContentValues values) {
        setContent(values);
    }

    public Two() {
    }


}