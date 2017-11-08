package com.kyleriedemann.giantbombvideoplayer.Base.DependencyInjection

import android.content.SharedPreferences
import com.danikula.videocache.HttpProxyCacheServer
import com.facebook.stetho.okhttp3.StethoInterceptor
import com.google.gson.Gson
import com.kyleriedemann.giantbombvideoplayer.Base.DependencyInjection.Context.BASE_URL
import com.kyleriedemann.giantbombvideoplayer.Base.DependencyInjection.Context.DISK_CACHE_SIZE
import com.kyleriedemann.giantbombvideoplayer.GiantbombApp
import com.kyleriedemann.giantbombvideoplayer.Network.GiantbombApiClient
import inkapplicaitons.android.logger.CompositeLogger
import inkapplicaitons.android.logger.ConsoleLogger
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.module.AndroidModule
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File

/**
 * Koin main module
 */
class GiantbombAppModule : AndroidModule() {
    override fun context() = applicationContext {
        context(Context.Singleton) {
            provide { CompositeLogger(listOf(ConsoleLogger())) }
            provide { applicationContext.getSharedPreferences("GiantbombApp", android.content.Context.MODE_PRIVATE) }
            provide {
                val httpClient = OkHttpClient.Builder()

                val interceptor = HttpLoggingInterceptor()
                interceptor.level = HttpLoggingInterceptor.Level.BODY

                httpClient.addInterceptor(interceptor)
                httpClient.addInterceptor(StethoInterceptor())

                val cacheDir = File(applicationContext.cacheDir, "http")
                val cache = Cache(cacheDir, DISK_CACHE_SIZE.toLong())
                httpClient.cache(cache)

                httpClient.build()
            }
            provide {
                Retrofit.Builder()
                        .baseUrl(BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create(Gson()))
                        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                        .client(get())
                        .build()
            }
            provide { get<Retrofit>().create(GiantbombApiClient::class.java) }

        }

        context(Context.Authentication) {

        }

        context(Context.List) {

        }

        context(Context.Playback) {
            provide { HttpProxyCacheServer.Builder(context)
                    .maxCacheSize((1024 * 1024 * 1024).toLong()) // 1 Gb for cache
                    .build()
            }
        }
    }
}

/**
 * Module constants
 */
object Context {
    val Singleton = "Singleton"
    val Authentication = "Authentication"
    val List = "List"
    val Playback = "Playback"

    val BASE_URL = "http://www.giantbomb.com/api/"
    val DISK_CACHE_SIZE = 50 * 1024 * 1024 // 50MB
}