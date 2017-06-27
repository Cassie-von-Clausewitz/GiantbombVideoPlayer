package com.kyleriedemann.giantbombvideoplayer.Base.DependencyInjection.Modules;

import com.inkapplications.android.applicationlifecycle.ApplicationCallbacks;
import com.inkapplications.android.applicationlifecycle.ApplicationLifecycleSubscriber;
import com.kyleriedemann.giantbombvideoplayer.Base.DependencyInjection.Debug;
import com.kyleriedemann.giantbombvideoplayer.Base.DependencyInjection.Initializers.FrescoInitializer;
import com.kyleriedemann.giantbombvideoplayer.Base.DependencyInjection.Initializers.JodaInitializer;

import java.util.Collections;
import java.util.List;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import inkapplicaitons.android.logger.CompositeLogger;
import inkapplicaitons.android.logger.Logger;

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
