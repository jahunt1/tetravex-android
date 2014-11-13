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
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.github.tetravex_android.R;

/**
 * The PauseActivity is shown when the user pauses a game
 */
public class PauseActivity extends Activity {

    /** User-defined activity result for starting a new game. */
    public static final int RESULT_NEW_GAME   = 2;
    /** User-defined activity result for quitting the current game. */
    public static final int RESULT_QUIT_GAME   = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Full screen
        this.getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_pause);
    }

    /**
     * Event handler that is invoked when the resume game button is clicked
     * @param view the View that was clicked
     */
    public void resumeButtonClicked(View view) {
        finish();
    }

    /**
     * Event handler that is invoked when the new game button is clicked
     * @param view the View that was clicked
     */
    public void newGameButtonClicked(View view) {
        // confirm with dialog
        DialogInterface.OnClickListener dialogClickListener =
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int selection) {
                        switch (selection) {
                            case DialogInterface.BUTTON_POSITIVE:
                                Intent intent = getIntent();
                                setResult(RESULT_NEW_GAME, intent);
                                finish();
                                break;
                            case DialogInterface.BUTTON_NEGATIVE: // fall-through
                            default:
                                break;
                        }
                    }
                };

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(getResources().getString(R.string.dialog_quit_game_prompt));
        builder.setPositiveButton(getResources().getString(R.string.dialog_ok),
                dialogClickListener);
        builder.setNegativeButton(getResources().getString(R.string.dialog_cancel),
                dialogClickListener);
        builder.show();
    }

    /**
     * Event handler that is invoked when the quit game button is clicked
     * @param view the View that was clicked
     */
    public void quitGameButtonClicked(View view) {
        // confirm with dialog
        DialogInterface.OnClickListener dialogClickListener =
                new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case DialogInterface.BUTTON_POSITIVE:
                                Intent intent = getIntent();
                                setResult(RESULT_QUIT_GAME, intent);
                                finish();
                                break;
                            case DialogInterface.BUTTON_NEGATIVE: // fall-through
                            default:
                                break;
                        }
                    }
                };

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(getResources().getString(R.string.dialog_quit_game_prompt));
        builder.setPositiveButton(getResources().getString(R.string.dialog_ok),
                dialogClickListener);
        builder.setNegativeButton(getResources().getString(R.string.dialog_cancel),
                dialogClickListener);
        builder.show();
    }
}
