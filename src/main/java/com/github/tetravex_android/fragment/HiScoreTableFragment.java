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

import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.github.tetravex_android.Constants;
import com.github.tetravex_android.R;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Presents the view corresponding with a table from the database
 */
public class HiScoreTableFragment extends Fragment {

    private String mLabel;
    private Cursor mScoreCursor;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_score_table, container, false);

        // Set the label for the size of the board
        TextView tableSize = (TextView) rootView.findViewById(R.id.hi_score_board_size);
        tableSize.setText(mLabel);

        TableLayout table = (TableLayout) rootView.findViewById(R.id.hi_score_table_layout);

        // fill the table from the database cursor
        if (mScoreCursor != null) {
            // move cursor to first row, will return false if cursor is empty
            if (mScoreCursor.moveToFirst()) {
                do {
                    // set time from the database entry, format text
                    TextView timeTv = new TextView(rootView.getContext());
                    timeTv.setText(mScoreCursor.getString(Constants.DB_TIME_COL_IDX));
                    timeTv.setTextAppearance(rootView.getContext(), R.style.score_table_entry);

                    // set date from the database entry, format text
                    TextView dateTv = new TextView(rootView.getContext());
                    String unixTimeStr = mScoreCursor.getString(Constants.DB_DATE_COL_IDX);
                    long unixSeconds = Long.valueOf(unixTimeStr);
                    Date date = new Date(unixSeconds);
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    String formattedDate = sdf.format(date);
                    dateTv.setText(formattedDate);
                    dateTv.setTextAppearance(rootView.getContext(), R.style.score_table_entry);

                    // add the Views from the database entry to the TableLayout
                    TableRow row = new TableRow(rootView.getContext());
                    row.addView(timeTv);
                    row.addView(dateTv);
                    table.addView(row);

                    // add row separator
                    View spacerView = new View(rootView.getContext());
                    TableLayout.LayoutParams params = new TableLayout.LayoutParams();
                    params.width = TableLayout.LayoutParams.MATCH_PARENT;
                    params.height = 1;
                    spacerView.setLayoutParams(params);
                    spacerView.setBackgroundColor(getResources().getColor(R.color.tile_bkgnd_gray));
                    table.addView(spacerView);
                    // move to next row
                } while (mScoreCursor.moveToNext());
            }
        }

        return rootView;
    }

    /**
     * Accessor for the table indicator String
     *
     * @return the name of the table
     */
    public String getLabel() {
        return mLabel;
    }

    /**
     * Set the title of the table that will be displayed once the Fragment's layout is inflated
     *
     * @param tableIndicator the text to set
     */
    public void setLabel(String tableIndicator) {
        this.mLabel = tableIndicator;
    }

    /**
     * Set the cursor that will be used to iterate over the items in the table
     *
     * @param cursor the cursor to be set
     */
    public void setCursor(Cursor cursor) {
        this.mScoreCursor = cursor;
    }
}
