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

import android.util.Log;

import java.util.ArrayList;
import java.util.Random;

/**
 * Represents the data needed for a Tetravex puzzle
 */
public class Puzzle
{
    private final int RAND_COLOR_RANGE = 10;

    public enum PuzzleState {
        NEW,
        IN_PROGRESS,
        PAUSED,
        COMPLETED
    }

    private final int NORTH = 0;
    private final int EAST  = 1;
    private final int SOUTH = 2;
    private final int WEST  = 3;

    private int mSize;
    private Tile[][] mBoard;
    private boolean mColor = true;
    private PuzzleState state = PuzzleState.NEW;
    /* Random number generator */
    private final Random mRand;

    /**
     * Initializes a newly created Puzzle with the specified size
     * @param size the size of the board (example: 3 for a 3 x 3 board)
     */
    public Puzzle(int size)
    {
        mSize = size;
        mBoard = new Tile[size * 2][size];
        mRand = new Random();
        initializeBoard();
    }

    /**
     * Sets up the game board in preparation for a new game
     */
    private void initializeBoard() {
        for (int x = 0; x < mSize; x++) {
            for (int y = 0; y < mSize; y++) {
                mBoard[x][y] = new Tile(x, y);
            }
        }

        /* Pick random colours for edges */
        for (int x = 0; x < mSize; x++) {
            for (int y = 0; y <= mSize; y++) {
                // random int in [0,RAND_COLOR_RANGE)
                int n = mRand.nextInt(RAND_COLOR_RANGE);
                if (y - 1 >= 0)
                    mBoard[x][y - 1].setSouth(n);
                if (y < mSize)
                    mBoard[x][y].setNorth(n);
            }
        }
        for (int x = 0; x <= mSize; x++) {
            for (int y = 0; y < mSize; y++) {
                int n = mRand.nextInt(RAND_COLOR_RANGE);
                if (x - 1 >= 0)
                    mBoard[x - 1][y].setEast(n);
                if (x < mSize)
                    mBoard[x][y].setWest(n);
            }
        }

        /* Pick up the tiles... */
        ArrayList<Tile> tiles = new ArrayList<Tile>();
        for (int x = 0; x < mSize; x++) {
            for (int y = 0; y < mSize; y++) {
                tiles.add(mBoard[x][y]);
                mBoard[x][y] = null;
            }
        }

        /* ...and place then randomly on the right hand side */
        for (int x = 0; x < mSize; x++) {
            for (int y = 0; y < mSize; y++) {
                int n = mRand.nextInt(tiles.size());
                // get the nth tile from the list
                Tile tile = tiles.get(n);
                mBoard[x + mSize][y] = tile;
                tiles.remove (tile);
            }
        }
    }

    public int getSize() {
        return mSize;
    }

    public void setColor(boolean value) {
        mColor = value;
    }

    public boolean isColor() {
        return mColor;
    }

    public PuzzleState getState() {
        return state;
    }

    public void setState(PuzzleState state) {
        this.state = state;
    }

    /**
     * Retrieve the tile from the board based on the position in the GridView
     * @param xStartIdx determines if the tile was placed on the starting board or the target board
     * @param position the GridView position from which the tile was dragged
     * @return the Tile from the given board position
     */
    public Tile getTileByPosition(int xStartIdx, int position) {
        int posCursor = 0;
        boolean matchFound = false;
        Tile tile = null;
        // check bounds on position first
        if(position >= 0 || position < (mSize * mSize)) {
            for (int x = xStartIdx; x < mSize * 2; x++) {
                for (int y = 0; y < mSize; y++) {
                    if (posCursor == position) {
                        tile = mBoard[x][y];
                        matchFound = true;
                        break;
                    }
                    else {
                        posCursor++;
                    }
                }
                if(matchFound) {
                    break;
                }
            }
        }
        return tile;
    }

    /**
     * Set the tile on the board based on the position where it was dropped
     * @param xStartIdx determines if the tile was placed on the starting board or the target board
     * @param position the GridView position where the tile was dropped
     * @param tile the object containing the Tile data
     */
    public void setTile(int xStartIdx, int position, Tile tile) {
        int posCursor = 0;
        boolean tileSet = false;
        // check bounds on position first
        if(position >= 0 || position < (mSize * mSize)) {
            for (int x = xStartIdx; x < mSize * 2; x++) {
                for (int y = 0; y < mSize; y++) {
                    if (posCursor == position) {
                        mBoard[x][y] = tile;
                        tileSet = true;
                        break;
                    }
                    else {
                        posCursor++;
                    }
                }
                if(tileSet) {
                    break;
                }
            }
        }
    }

    /**
     * Determine if the puzzle has been solved.
     * @return {@code true} if all tiles are in their proper slots;
     * {@code false} otherwise.
     */
    public boolean isSolved() {
        for (int x = 0; x < mSize; x++) {
            for (int y = 0; y < mSize; y++) {
                Tile tile = mBoard[x][y];

                // Return as soon as we know the result
                if(y > 0) { // check neighbor to the west
                    if(!neighborIsPair(tile, mBoard[x][y-1], WEST)) {
                        return false;
                    }
                }
                if(x > 0) { // check neighbor to the north
                    if(!neighborIsPair(tile, mBoard[x-1][y], NORTH)) {
                        return false;
                    }
                }
                if(y < mSize - 1) { // check neighbor to the east
                    if(!neighborIsPair(tile, mBoard[x][y+1], EAST)) {
                        return false;
                    }
                }
                if(x < mSize - 1) { // check neighbor to the south
                    if(!neighborIsPair(tile, mBoard[x+1][y], SOUTH)) {
                        return false;
                    }
                }
            }
        }

        // we made it through the comparisons without any failures -> the puzzle is solved
        return true;
    }

    /**
     * Check if two tiles have a matching adjacent side
     * @param tile the Tile to check
     * @param neighbor the tile's neighbor used for comparison
     * @param side the side of the Tile to check
     * @return {@code true} if the Tiles match on the given side; {@code false} otherwise
     */
    private boolean neighborIsPair(Tile tile, Tile neighbor, int side) {
        // initial guard clause
        if(tile == null || neighbor == null) {
            return false;
        }
        boolean pair = false;
        switch (side) {
            case NORTH:
                if(tile.getNorth() == neighbor.getSouth()) {
                    pair = true;
                }
                break;
            case EAST:
                if(tile.getEast() == neighbor.getWest()) {
                    pair = true;
                }
                break;
            case SOUTH:
                if(tile.getSouth() == neighbor.getNorth()) {
                    pair = true;
                }
                break;
            case WEST:
                if(tile.getWest() == neighbor.getEast()) {
                    pair = true;
                }
                break;
            default:
                StringBuilder sb = new StringBuilder();
                sb.append("Invalid side given for comparison: ");
                sb.append(side);
                Log.e("TetravexAndroid", sb.toString());
                break;
        }
        return pair;
    }
}
