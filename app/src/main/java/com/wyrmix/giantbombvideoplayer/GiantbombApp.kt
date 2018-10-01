package com.wyrmix.giantbombvideoplayer

import android.app.Application
import com.facebook.stetho.Stetho
import com.wyrmix.giantbombvideoplayer.di.appModule
import org.koin.android.ext.android.startKoin
import timber.log.Timber

class GiantbombApp : Application() {
    override fun onCreate() {
        super.onCreate()

        Stetho.initializeWithDefaults(this)
        startKoin(this, listOf(appModule))

        // todo inject this in debug module and run callback to plant in debug
        Timber.plant(Timber.DebugTree())
    }
}
