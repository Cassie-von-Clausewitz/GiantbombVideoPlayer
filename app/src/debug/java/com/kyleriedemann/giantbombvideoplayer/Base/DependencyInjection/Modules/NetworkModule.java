package com.kyleriedemann.giantbombvideoplayer.Base.DependencyInjection.Modules;

import com.facebook.stetho.okhttp3.StethoInterceptor;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * Sets up networking code for the application and libraries.
 * <p>
 * This is the DEBUG file, it is safe to include debugging tools here. Update
 * the respective RELEASE file accordingly.
 */
@Module
public class NetworkModule {
    @Provides
    @Singleton
    OkHttpClient httpClient() {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        return new OkHttpClient.Builder()
                .addNetworkInterceptor(new StethoInterceptor())
                .addNetworkInterceptor(httpLoggingInterceptor)
                .build();
    }
}
