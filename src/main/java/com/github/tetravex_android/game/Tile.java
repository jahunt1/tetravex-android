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
 *
 * This file incorporates work covered by the following copyright and permission notice:
 *
 * Copyright (C) 2010-2013 Robert Ancell
 *
 * This program is free software: you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software
 * Foundation, either version 2 of the License, or (at your option) any later
 * version. See http://www.gnu.org/copyleft/gpl.html the full text of the
 * license.
 */

package com.github.tetravex_android.game;

/**
 * Represents a single tile in the Tetravex game.
 */
public class Tile
{
    /* Edge colors */
    private int mNorth;
    private int mWest;
    private int mEast;
    private int mSouth;

    /* Solution location */
    private int mX;
    private int mY;

    public Tile (int x, int y)
    {
        this.mX = x;
        this.mY = y;
    }

    public int getX() {
        return this.mX;
    }

    public int getY() {
        return this.mY;
    }

    public int getNorth() {
        return mNorth;
    }

    public void setNorth(int north) {
        this.mNorth = north;
    }

    public int getWest() {
        return mWest;
    }

    public void setWest(int mWest) {
        this.mWest = mWest;
    }

    public int getEast() {
        return mEast;
    }

    public void setEast(int mEast) {
        this.mEast = mEast;
    }

    public int getSouth() {
        return mSouth;
    }

    public void setSouth(int mSouth) {
        this.mSouth = mSouth;
    }
}
