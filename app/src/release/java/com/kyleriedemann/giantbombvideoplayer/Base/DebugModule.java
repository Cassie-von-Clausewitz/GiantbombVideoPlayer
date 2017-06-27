package com.kyleriedemann.giantbombvideoplayer.Base;

import com.inkapplications.android.applicationlifecycle.ApplicationCallbacks;
import dagger.Module;
import dagger.Provides;
import inkapplicaitons.android.logger.EmptyLogger;
import inkapplicaitons.android.logger.Logger;

import javax.inject.Singleton;

/**
 * Application dependencies necessary for Debugging, only when the app is debuggable.
 *
 * THIS IS THE NO-OP RELEASE FILE. DO NOT ADD REAL DEPENDENCIES HERE.
 */
@Module
public class DebugModule {

    @Provides
    @Debug
    @Singleton
    ApplicationCallbacks applicationCallbacks() {
        return new ApplicationCallbacks(/* THIS SPACE INTENTIONALLY LEFT BLANK */);
    }

    @Provides
    @Debug
    @Singleton
    Logger debugLogger() {
        return new EmptyLogger();
    }
}
