package com.kyleriedemann.giantbombvideoplayer;

import android.app.Application;

import com.danikula.videocache.HttpProxyCacheServer;
import com.facebook.stetho.Stetho;

import timber.log.Timber;

/**
 * Created by kyle on 3/15/16.
 */
public class GiantbombApp extends Application {

    private static GiantbombApp giantbombApp;
    private HttpProxyCacheServer proxy;

    @Override
    public void onCreate() {
        super.onCreate();

        Stetho.initializeWithDefaults(this);
        Timber.plant(new Timber.DebugTree());

        giantbombApp = this;
    }

    public HttpProxyCacheServer getProxy() {
        if (proxy == null) proxy = newProxy();

        return this.proxy;
    }

    private HttpProxyCacheServer newProxy() {
        return new HttpProxyCacheServer.Builder(this)
                .maxCacheSize(1024 * 1024 * 1024) // 1 Gb for cache
                .build();
    }

    public static GiantbombApp instance(){
        return giantbombApp;
    }
}
