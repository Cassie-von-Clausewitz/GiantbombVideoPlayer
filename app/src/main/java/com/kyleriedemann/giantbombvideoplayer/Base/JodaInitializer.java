package com.kyleriedemann.giantbombvideoplayer.Base;

import android.app.Application;

import com.inkapplications.android.applicationlifecycle.Initializer;

import net.danlew.android.joda.JodaTimeAndroid;

import javax.inject.Inject;

public class JodaInitializer extends Initializer {
    @Inject
    JodaInitializer() {}

    @Override
    public void onCreate(Application application) {
        JodaTimeAndroid.init(application);
    }
}
