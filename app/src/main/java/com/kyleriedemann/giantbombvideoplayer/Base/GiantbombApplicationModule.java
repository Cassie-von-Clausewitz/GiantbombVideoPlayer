package com.kyleriedemann.giantbombvideoplayer.Base;

import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.inkapplications.android.applicationlifecycle.ApplicationCallbacks;
import com.inkapplications.android.applicationlifecycle.ApplicationLifecycleSubscriber;

import java.util.Collections;
import java.util.List;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import inkapplicaitons.android.logger.CompositeLogger;
import inkapplicaitons.android.logger.EmptyLogger;
import inkapplicaitons.android.logger.Logger;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

@Module
public class GiantbombApplicationModule {
    @Provides
    @Singleton
    ApplicationCallbacks applicationCallbacks(
            FrescoInitializer fresco,
            JodaInitializer joda
    ) {
        ApplicationLifecycleSubscriber[] callbacks = { fresco, joda };

        return new ApplicationCallbacks(callbacks);
    }

    @Provides
    @Singleton
    Logger applicationLogger(@Debug Logger debugLogger) {
        List<Logger> loggers = Collections.singletonList(debugLogger);

        return new CompositeLogger(loggers);
    }
}
