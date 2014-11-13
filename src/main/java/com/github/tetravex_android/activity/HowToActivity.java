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

package com.github.tetravex_android.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.github.tetravex_android.fragment.PageFragmentFactory;
import com.github.tetravex_android.R;

/**
 * Gives the user some instructions on how to play the game
 */
public class HowToActivity extends FragmentActivity {

    /**
     * The number of pages to show in How To activity
     */
    private static final int NUM_PAGES = 3;

    /**
     * The pager widget, which handles animation and allows swiping horizontally to access previous
     * and next instruction pages.
     */
    private ViewPager mPager;

    /**
     * The pager adapter, which provides the pages to the view pager widget.
     */
    private PagerAdapter mPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Full screen
        this.getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_how_to);

        // Instantiate a ViewPager and a PagerAdapter.
        mPager = (ViewPager) findViewById(R.id.how_to_pager);
        mPagerAdapter = new HowToPagerAdapter(getSupportFragmentManager());
        mPager.setAdapter(mPagerAdapter);

        mPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener(){
            public void onPageSelected(int position) {
                setIndicatorDots(position);
            }
        });
    }

    /**
     * Sets the page indicator dots at the bottom of the screen to indicate which page is displayed
     *
     * @param pageIndex the index of the page currently displayed
     */
    private void setIndicatorDots(int pageIndex) {
        // set the indicator dot for the correct page
        LinearLayout dotGroup = (LinearLayout) findViewById(R.id.how_to_pager_dots);
        for (int i = 0; i < dotGroup.getChildCount(); i++) {
            ImageView view = (ImageView) dotGroup.getChildAt(i);
            String tagString = (String) view.getTag();
            int tagId = Integer.valueOf(tagString);

            if (tagId == pageIndex) {
                view.setImageResource(R.drawable.pager_dot_on);
            } else {
                view.setImageResource(R.drawable.pager_dot_off);
            }
        }
    }

    /**
     * This adapter class accesses the PageFragments in order
     */
    private class HowToPagerAdapter extends FragmentStatePagerAdapter {

        public HowToPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            PageFragmentFactory pageFactory = PageFragmentFactory.getInstance();
            return pageFactory.getInstructionFragment(position);
        }

        @Override
        public int getCount() {
            return NUM_PAGES;
        }
    }
}
