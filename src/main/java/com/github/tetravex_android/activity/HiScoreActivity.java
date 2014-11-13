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

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.github.tetravex_android.fragment.PageFragmentFactory;
import com.github.tetravex_android.R;
import com.github.tetravex_android.TetravexApp;
import com.github.tetravex_android.domain.five.FiveManager;
import com.github.tetravex_android.domain.four.FourManager;
import com.github.tetravex_android.domain.three.ThreeManager;
import com.github.tetravex_android.domain.two.TwoManager;

import javax.inject.Inject;

/**
 * Displays high scores that have been achieved
 */
public class HiScoreActivity extends FragmentActivity {

    /**
     * The number of pages to show in How To activity
     */
    private static final int NUM_PAGES = 4;
    // Database table managers
    @Inject
    TwoManager twoManager;
    @Inject
    ThreeManager threeManager;
    @Inject
    FourManager fourManager;
    @Inject
    FiveManager fiveManager;

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

        setContentView(R.layout.activity_hi_score);
        TetravexApp.injectActivity(this);

        // Instantiate a ViewPager and a PagerAdapter.
        mPager = (ViewPager) findViewById(R.id.hi_score_pager);
        mPagerAdapter = new HiScorePagerAdapter(getSupportFragmentManager());
        mPager.setAdapter(mPagerAdapter);

        mPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            public void onPageSelected(int position) {
                setIndicatorDots(position);
            }
        });

        // TODO: Set the starting page to show scores for the current board size setting
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.hi_score, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_reset_scores) {
            // reset the scores database
            DialogInterface.OnClickListener dialogClickListener =
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            switch (which) {
                                case DialogInterface.BUTTON_POSITIVE:
                                    // reset the scores database
                                    deleteAllScores();
                                    reloadFragments();
                                    break;

                                case DialogInterface.BUTTON_NEGATIVE: // fall-through
                                default:
                                    break;
                            }
                        }
                    };

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage(getResources().getString(R.string.dialog_reset_scores_prompt));
            builder.setPositiveButton(getResources().getString(R.string.dialog_ok),
                    dialogClickListener);
            builder.setNegativeButton(getResources().getString(R.string.dialog_cancel),
                    dialogClickListener);
            builder.show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * Sets the page indicator dots at the bottom of the screen to indicate which page is displayed
     *
     * @param pageIndex the index of the page currently displayed
     */
    private void setIndicatorDots(int pageIndex) {
        // set the indicator dot for the correct page
        LinearLayout dotGroup = (LinearLayout) findViewById(R.id.hi_score_dot_ll);
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
     * Delete all the high scores from the database.
     */
    private void deleteAllScores() {
        twoManager.deleteAll();
        threeManager.deleteAll();
        fourManager.deleteAll();
        fiveManager.deleteAll();

        Toast.makeText(getApplicationContext(),
                getResources().getString(R.string.scores_deleted),
                Toast.LENGTH_SHORT).show();
    }

    /**
     * Force the current activity to reload all Fragment objects in the pager adapter.
     */
    private void reloadFragments() {
        mPagerAdapter.notifyDataSetChanged();
    }

    /**
     * This adapter class accesses the PageFragments in order
     */
    private class HiScorePagerAdapter extends FragmentStatePagerAdapter {

        public HiScorePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            PageFragmentFactory pageFactory = PageFragmentFactory.getInstance();
            return pageFactory.getHiScoreFragment(position);
        }

        @Override
        public int getCount() {
            return NUM_PAGES;
        }

        @Override
        public int getItemPosition(Object object) {
            // forces reloading of all fragments when notifyDataSetChanged() is called
            return POSITION_NONE;
        }
    }
}
