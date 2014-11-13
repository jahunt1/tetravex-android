/*
 * Four.java
 *
 * Created: 11/11/2014 09:28:13
 */



package com.github.tetravex_android.domain.four;

import android.database.Cursor;
import android.content.ContentValues;


public class Four extends FourBaseRecord {


    public Four(Cursor cursor) {
        setContent(cursor);
    }

    public Four(ContentValues values) {
        setContent(values);
    }

    public Four() {
    }


}