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

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.github.tetravex_android.R;

/**
 * The main activity for the Tetravex Android app.
 * Shows several navigation buttons to the user.
 */
public class DashboardActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Full screen
        this.getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_dashboard);
    }

    public void newGameButtonClicked(View view) {
        Intent intent = new Intent(this, PuzzleActivity.class);
        startActivity(intent);
    }

    public void scoresButtonClicked(View view) {
        Intent intent = new Intent(this, HiScoreActivity.class);
        startActivity(intent);
    }

    public void howToButtonClicked(View view) {
        Intent intent = new Intent(this, HowToActivity.class);
        startActivity(intent);
    }

    public void settingsButtonClicked(View view) {
        Intent intent = new Intent(this, SettingsActivity.class);
        startActivity(intent);
    }
}
