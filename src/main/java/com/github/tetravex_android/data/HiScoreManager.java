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

package com.github.tetravex_android.data;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.view.Gravity;
import android.widget.Toast;

import com.github.tetravex_android.R;
import com.github.tetravex_android.domain.five.Five;
import com.github.tetravex_android.domain.five.FiveManager;
import com.github.tetravex_android.domain.four.Four;
import com.github.tetravex_android.domain.four.FourManager;
import com.github.tetravex_android.domain.three.Three;
import com.github.tetravex_android.domain.three.ThreeManager;
import com.github.tetravex_android.domain.two.Two;
import com.github.tetravex_android.domain.two.TwoManager;
import com.github.tetravex_android.game.Score;

import org.dbtools.android.domain.AndroidBaseRecord;
import org.joda.time.DateTime;

import javax.inject.Inject;

/**
 * Updates the high scores database when a new high score is achieved
 */
public class HiScoreManager extends AsyncTask<Score, Void, Boolean> {

    private final String TAG = this.getClass().getSimpleName();

    // the Context of the calling class
    private Context mContext = null;

    // Database table managers
    @Inject
    TwoManager twoManager;
    @Inject
    ThreeManager threeManager;
    @Inject
    FourManager fourManager;
    @Inject
    FiveManager fiveManager;

    public Context getContext() {
        return mContext;
    }

    public void setContext(Context context) {
        mContext = context;
    }

    /**
     * Add a new score to the high scores database
     * @param boardSize the size of the game board in [2..5]
     * @param elapsed the time retrieved from the Chronometer view
     */
    public void addScore(int boardSize, String elapsed) {
        Score achievedScore = new Score(boardSize, elapsed, DateTime.now());

        // update the DB on a separate thread
        this.execute(achievedScore);
    }

    @Override
    protected Boolean doInBackground(Score... params) {
        // expect only a single Score in the list of params
        Score achievedScore = params[0];

        // TODO implement enforcement of 10 scores per board size
        // requires comparison of Chronometer text values
        boolean scoreAdded = true;

        AndroidBaseRecord newScore;
        switch (achievedScore.getSize()) {
            case 2:
                newScore = new Two();
                ((Two) newScore).setTime(achievedScore.getElapsedTime());
                ((Two) newScore).setScoreDate(achievedScore.getDate());
                twoManager.save((Two) newScore);
                break;
            case 3:
                newScore = new Three();
                ((Three) newScore).setTime(achievedScore.getElapsedTime());
                ((Three) newScore).setScoreDate(achievedScore.getDate());
                threeManager.save((Three) newScore);
                break;
            case 4:
                newScore = new Four();
                ((Four) newScore).setTime(achievedScore.getElapsedTime());
                ((Four) newScore).setScoreDate(achievedScore.getDate());
                fourManager.save((Four) newScore);
                break;
            case 5:
                newScore = new Five();
                ((Five) newScore).setTime(achievedScore.getElapsedTime());
                ((Five) newScore).setScoreDate(achievedScore.getDate());
                fiveManager.save((Five) newScore);
                break;
            default:
                // Unexpected case
                Log.wtf(TAG, "Invalid Board Size: " + String.valueOf(achievedScore.getSize()));
                break;
        }

        return scoreAdded;
    }

    @Override
    protected void onPostExecute(Boolean newHiScore) {
        // Show a Toast notification when the user achieves a new high score
        if(newHiScore) {
            String toastText = mContext.getResources().getString(
                    R.string.high_score_notification_text);
            Toast highScoreToast = Toast.makeText(mContext, toastText, Toast.LENGTH_SHORT);
            highScoreToast.setGravity(Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 20);
            highScoreToast.show();
        }
    }
}
