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

package com.github.tetravex_android.fragment;

import android.content.res.Resources;
import android.database.Cursor;
import android.support.v4.app.Fragment;
import android.util.Log;

import com.github.tetravex_android.R;
import com.github.tetravex_android.domain.five.FiveManager;
import com.github.tetravex_android.domain.four.FourManager;
import com.github.tetravex_android.domain.three.ThreeManager;
import com.github.tetravex_android.domain.two.Two;
import com.github.tetravex_android.domain.two.TwoManager;

import javax.inject.Inject;

/**
 * Constructs Fragment objects for use in the HowToActivity and HiScoreActivity
 */
public class PageFragmentFactory {

    // make this class a singleton
    private static PageFragmentFactory instance;

    @Inject
    Resources resources;

    // Database table managers, used for high score table
    @Inject
    TwoManager twoManager;
    @Inject
    ThreeManager threeManager;
    @Inject
    FourManager fourManager;
    @Inject
    FiveManager fiveManager;

    private final static String TAG = PageFragmentFactory.class.getSimpleName();

    /**
     * Access the singleton instance of the PageFragmentFactory class
     *
     * @return the singleton instance
     */
    public static PageFragmentFactory getInstance() {
        if (instance == null) {
            instance = new PageFragmentFactory();
        }

        return instance;
    }

    /**
     * Get a Fragment with a page of instructions
     *
     * @param pageIndex the index of the page to retrieve
     * @return the appropriate Fragment
     */
    public Fragment getInstructionFragment(int pageIndex) {
        HowToFragment fragment = new HowToFragment();
        fragment.setPageNumber(pageIndex + 1);
        String instructionText;

        switch (pageIndex) {
            case 0:
                instructionText = resources.getString(R.string.how_to_text_1);
                fragment.setInstructionText(instructionText);
                break;
            case 1:
                instructionText = resources.getString(R.string.how_to_text_2);
                fragment.setInstructionText(instructionText);
                break;
            case 2:
                instructionText = resources.getString(R.string.how_to_text_3);
                fragment.setInstructionText(instructionText);
                break;
            default:
                Log.e(TAG, "Bad position for how to: " + String.valueOf(pageIndex));
                break;
        }

        return fragment;
    }

    /**
     * Construct a fragment with a table of high scores based on the page number
     *
     * @param pageIndex the index of the page that will show high scores
     * @return the Fragment to display
     */
    public Fragment getHiScoreFragment(int pageIndex) {
        HiScoreTableFragment hiScoreFragment = new HiScoreTableFragment();
        Cursor cursor;
        // declared here since findCursorBySelection doesn't seem to like the plain null parameter
        String[] nullStrArray = null;

        switch (pageIndex) {
            case 0:
                hiScoreFragment.setLabel(resources.getString(R.string.two_x_two));
                // fill Table with rows from Two database
                // find all, order by TIME column
                cursor = twoManager.findCursorBySelection(null, nullStrArray, Two.C_TIME);
                hiScoreFragment.setCursor(cursor);
                break;
            case 1:
                hiScoreFragment.setLabel(resources.getString(R.string.three_x_three));
                // fill Table with rows from Three database
                // find all, order by TIME column
                cursor = threeManager.findCursorBySelection(null, nullStrArray, Two.C_TIME);
                hiScoreFragment.setCursor(cursor);
                break;
            case 2:
                hiScoreFragment.setLabel(resources.getString(R.string.four_x_four));
                // fill Table with rows from Four database
                // find all, order by TIME column
                cursor = fourManager.findCursorBySelection(null, nullStrArray, Two.C_TIME);
                hiScoreFragment.setCursor(cursor);
                break;
            case 3:
                hiScoreFragment.setLabel(resources.getString(R.string.five_x_five));
                // fill Table with rows from Five database
                // find all, order by TIME column
                cursor = fiveManager.findCursorBySelection(null, nullStrArray, Two.C_TIME);
                hiScoreFragment.setCursor(cursor);
                break;
            default:
                Log.e(TAG, "Bad position for high score: " + String.valueOf(pageIndex));
                break;
        }

        return hiScoreFragment;
    }
}
