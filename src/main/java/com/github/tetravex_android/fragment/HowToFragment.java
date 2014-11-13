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
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.tetravex_android.R;
import com.github.tetravex_android.TetravexApp;

/**
 * Presents the view associated with a page of instructions for gameplay
 */
public class HowToFragment extends Fragment {

    private final String TAG = "HowToFragment";
    private String mInstructionText;
    private int mPageNumber;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_howto_page, container, false);

        TextView instructionTv = (TextView) rootView.findViewById(R.id.instruction_text);
        instructionTv.setText(mInstructionText);

        ImageView imageView = (ImageView) rootView.findViewById(R.id.how_to_image);
        Drawable imgDrawable = getHowToDrawable();
        imageView.setImageDrawable(imgDrawable);

        return rootView;
    }

    /**
     * Set the instruction text that will be displayed once the Fragment's layout is inflated
     *
     * @param instructionText the text to set
     */
    public void setInstructionText(String instructionText) {
        this.mInstructionText = instructionText;
    }

    /**
     * Set the page number for the instruction fragment
     *
     * @param pageNumber the page number to set
     */
    public void setPageNumber(int pageNumber) {
        mPageNumber = pageNumber;
    }

    /**
     * Retrieves the drawable image to be used for this HowToFragment
     *
     * @return the appropriate drawable for the page to display
     */
    private Drawable getHowToDrawable() {
        Drawable drawable;

        Resources resources = TetravexApp.getApplication(this).getResources();
        switch (mPageNumber) {
            case 1:
                drawable = resources.getDrawable(R.drawable.solved3x3);
                break;
            case 2:
                drawable = resources.getDrawable(R.drawable.drag_highlighted);
                break;
            case 3:
                drawable = resources.getDrawable(R.drawable.victory_condition);
                break;
            default:
                drawable = null;
                Log.e(TAG, "Bad page number: " + String.valueOf(mPageNumber));
                break;
        }

        return drawable;
    }
}
