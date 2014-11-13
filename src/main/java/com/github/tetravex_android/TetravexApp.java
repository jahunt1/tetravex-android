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

import android.app.Activity;
import android.app.Application;
import android.content.Context;

import com.github.tetravex_android.fragment.PageFragmentFactory;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import dagger.ObjectGraph;

/**
 * Custom application class
 */
public class TetravexApp extends Application {

    private ObjectGraph mObjectGraph;

    @Inject
    public TetravexApp() {
    }

    @Override
    public void onCreate() {
        super.onCreate();

        mObjectGraph = ObjectGraph.create(getModules().toArray());
        mObjectGraph.inject(this);
        mObjectGraph.inject(PageFragmentFactory.getInstance());
    }

    protected List<Object> getModules() {
        List<Object> modules = new ArrayList<>();
        modules.add(new TetravexModule(this));
        return modules;
    }

    public void inject(Object object) {
        mObjectGraph.inject(object);
    }

    public static void injectActivity(Activity activity) {
        ((TetravexApp)activity.getApplication()).inject(activity);
    }

    public static void injectFragment(android.support.v4.app.Fragment fragment) {
        getApplication(fragment).inject(fragment);
    }

    public static TetravexApp getApplication(android.support.v4.app.Fragment fragment) {
        return (TetravexApp) fragment.getActivity().getApplication();
    }

    public static TetravexApp getApplication(Activity activity) {
        return (TetravexApp) activity.getApplication();
    }

    public static TetravexApp get(Context context) {
        return (TetravexApp) context.getApplicationContext();
    }
}
