package com.kyleriedemann.giantbombvideoplayer.Base;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;

/**
 * Sets up networking code for the application and libraries.
 *
 * THIS IS THE RELEASE FILE. DO NOT INCLUDE DEBUGGING TOOLS HERE.
 */
@Module
public class NetworkModule {
    @Provides
    @Singleton
    OkHttpClient httpClient() {
        return new OkHttpClient.Builder().build();
    }
}
