/*
 * Copyright (C) 2014 John Hunt <john.alma.hunt@gmail.com>
 *
 * This file is part of Tetravex Android.
 *
 * Tetravex Android is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Tetravex Android is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Tetravex Android.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.github.tetravex_android.game;

import com.github.tetravex_android.Constants;

import org.joda.time.DateTime;

/**
 * Represents information about a score that may be entered in the database
 */
public class Score {

    private int mSize = Constants.DEFAULT_BOARD_SIZE;
    private String mElapsedTime = null;
    private DateTime mDate = null;

    /**
     * Initializes a newly created Score object
     * @param boardSize the size of the board
     * @param elapsedTime String representation of the time retrieved from the Chronometer view
     * @param date the date the score was achieved
     */
    public Score(int boardSize, String elapsedTime, DateTime date) {
        mSize = boardSize;
        mElapsedTime = elapsedTime;
        mDate = date;
    }

    public int getSize() {
        return mSize;
    }

    public void setSize(int size) {
        mSize = size;
    }

    public String getElapsedTime() {
        return mElapsedTime;
    }

    public void setElapsedTime(String elapsedTime) {
        mElapsedTime = elapsedTime;
    }

    public DateTime getDate() {
        return mDate;
    }

    public void setDate(DateTime date) {
        mDate = date;
    }
}
