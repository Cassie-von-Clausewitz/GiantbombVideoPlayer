package com.kyleriedemann.giantbombvideoplayer;

import android.app.Application;

import com.facebook.stetho.Stetho;

import timber.log.Timber;

/**
 * Created by kyle on 3/15/16.
 */
public class GiantbombApp extends Application {

    static GiantbombApp giantbombApp;

    @Override
    public void onCreate() {
        super.onCreate();

        Stetho.initializeWithDefaults(this);
        Timber.plant(new Timber.DebugTree());

        giantbombApp = this;
    }

    public static GiantbombApp instance(){
        return giantbombApp;
    }
}
