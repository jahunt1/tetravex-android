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

package com.github.tetravex_android;

import android.app.Application;
import android.content.res.Resources;

import com.github.tetravex_android.activity.PuzzleActivity;
import com.github.tetravex_android.activity.HiScoreActivity;
import com.github.tetravex_android.activity.SettingsActivity;
import com.github.tetravex_android.data.HiScoreManager;
import com.github.tetravex_android.fragment.PageFragmentFactory;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by john on 10/27/14.
 */
@Module(
        injects = {
                TetravexApp.class,
                PuzzleActivity.class,
                HiScoreManager.class,
                HiScoreActivity.class,
                PageFragmentFactory.class,
                SettingsActivity.class
        },
        library = true,
        complete = false
)
public class TetravexModule {

    private final TetravexApp application;

    public TetravexModule(TetravexApp application) {
        this.application = application;
    }

    @Provides
    @Singleton
    public Application provideApplication() {
        return application;
    }

    @Provides
    public Resources provideResources() {
        return application.getResources();
    }
}
