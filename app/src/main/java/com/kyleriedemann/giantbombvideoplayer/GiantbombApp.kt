package com.kyleriedemann.giantbombvideoplayer

import android.app.Application
import com.facebook.stetho.Stetho
import com.kyleriedemann.giantbombvideoplayer.Base.DependencyInjection.GiantbombAppModule
import org.koin.android.ext.android.startAndroidContext

class GiantbombApp : Application() {
    override fun onCreate() {
        super.onCreate()

        Stetho.initializeWithDefaults(this)
        startAndroidContext(this, GiantbombAppModule())
    }
}
