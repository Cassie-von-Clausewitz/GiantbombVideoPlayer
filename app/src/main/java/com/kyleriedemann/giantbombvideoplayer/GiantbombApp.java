package com.kyleriedemann.giantbombvideoplayer;

import android.app.Application;
import android.support.v7.app.AppCompatDelegate;

import com.danikula.videocache.HttpProxyCacheServer;
import com.facebook.stetho.Stetho;
import com.inkapplications.android.applicationlifecycle.ApplicationCallbacks;
import com.kyleriedemann.giantbombvideoplayer.Base.DependencyInjection.Components.DaggerApplicationComponent;
import com.kyleriedemann.giantbombvideoplayer.Base.DependencyInjection.Modules.AndroidApplicationModule;
import com.kyleriedemann.giantbombvideoplayer.Base.DependencyInjection.Components.ApplicationComponent;
import com.kyleriedemann.giantbombvideoplayer.Base.DependencyInjection.Debug;
import com.squareup.leakcanary.LeakCanary;

import javax.inject.Inject;

import inkapplicaitons.android.logger.Logger;
import io.reactivex.disposables.CompositeDisposable;

public class GiantbombApp extends Application {

    private static GiantbombApp giantbombApp;
    private HttpProxyCacheServer proxy;


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

    private ApplicationComponent applicationComponent;

    private CompositeDisposable compositeDisposable;

    @Inject
    ApplicationCallbacks applicationCallbacks;

    @Inject
    Logger logger;

    @Inject
    @Debug
    ApplicationCallbacks debugCallbacks;

    @Override
    public void onCreate() {
        super.onCreate();

        Stetho.initializeWithDefaults(this);

        giantbombApp = this;

        // enabling vector drawable compatibility
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);

        LeakCanary.isInAnalyzerProcess(this);
        this.initializeInjections();
        this.applicationCallbacks.onCreate(this);
        this.debugCallbacks.onCreate(this);

        compositeDisposable = new CompositeDisposable();
    }

    @Override
    public void onTerminate() {
        super.onTerminate();

        if (compositeDisposable != null && !compositeDisposable.isDisposed()) {
            compositeDisposable.dispose();
        }
    }

    /**
     * Initialize the shagred application component and use it to inject this class.
     */
    private void initializeInjections() {
        DaggerApplicationComponent.Builder builder = DaggerApplicationComponent.builder();
        builder.androidApplicationModule(new AndroidApplicationModule(this));

        this.applicationComponent = builder.build();
        this.applicationComponent.inject(this);
    }

    /**
     * Get the single-instance of the shared application component.
     *
     * @return An application component to be used to inject base-level classes.
     */
    public ApplicationComponent getApplicationComponent() {
        return this.applicationComponent;
    }
}
