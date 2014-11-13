/*
 * Three.java
 *
 * Created: 11/11/2014 09:28:13
 */



package com.github.tetravex_android.domain.three;

import android.database.Cursor;
import android.content.ContentValues;


public class Three extends ThreeBaseRecord {


    public Three(Cursor cursor) {
        setContent(cursor);
    }

    public Three(ContentValues values) {
        setContent(values);
    }

    public Three() {
    }


}