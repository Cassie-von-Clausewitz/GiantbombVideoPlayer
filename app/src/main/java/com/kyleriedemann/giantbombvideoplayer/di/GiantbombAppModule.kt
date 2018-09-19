package com.kyleriedemann.giantbombvideoplayer.di

import android.app.Activity
import androidx.room.Room
import com.danikula.videocache.HttpProxyCacheServer
import com.facebook.stetho.okhttp3.StethoInterceptor
import com.google.gson.Gson
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.experimental.CoroutineCallAdapterFactory
import com.kyleriedemann.giantbombvideoplayer.auth.AuthenticationViewModel
import com.kyleriedemann.giantbombvideoplayer.di.Context.BASE_URL
import com.kyleriedemann.giantbombvideoplayer.di.Context.DISK_CACHE_SIZE
import com.kyleriedemann.giantbombvideoplayer.video.details.VideoDetailsViewModel
import com.kyleriedemann.giantbombvideoplayer.video.list.VideoBrowserViewModel
import com.kyleriedemann.giantbombvideoplayer.video.models.AppDatabase
import com.kyleriedemann.giantbombvideoplayer.video.models.Video
import com.kyleriedemann.giantbombvideoplayer.video.network.ApiRepository
import com.kyleriedemann.giantbombvideoplayer.video.network.GiantbombApiClient
import com.kyleriedemann.giantbombvideoplayer.video.player.VideoPlayerViewModel
import inkapplicaitons.android.logger.CompositeLogger
import inkapplicaitons.android.logger.ConsoleLogger
import inkapplicaitons.android.logger.Logger
import io.palaima.debugdrawer.DebugDrawer
import io.palaima.debugdrawer.commons.BuildModule
import io.palaima.debugdrawer.commons.DeviceModule
import io.palaima.debugdrawer.commons.NetworkModule
import io.palaima.debugdrawer.commons.SettingsModule
import io.palaima.debugdrawer.logs.LogsModule
import io.palaima.debugdrawer.network.quality.NetworkQualityModule
import io.palaima.debugdrawer.okhttp3.OkHttp3Module
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File

/**
 * Koin main module
 */
val appModule = module {
        module("app") {
            single { CompositeLogger(listOf(ConsoleLogger("GiantbombApp"))) } bind Logger::class
            single { get<android.content.Context>().getSharedPreferences("GiantbombApp", android.content.Context.MODE_PRIVATE) }
            single {
                val httpClient = OkHttpClient.Builder()

                val interceptor = HttpLoggingInterceptor()
                interceptor.level = HttpLoggingInterceptor.Level.BODY

                httpClient.addInterceptor(interceptor)
                httpClient.addInterceptor(StethoInterceptor())

                val cacheDir = File((get() as android.content.Context).cacheDir, "http")
                val cache = Cache(cacheDir, DISK_CACHE_SIZE.toLong())
                httpClient.cache(cache)

                httpClient.build()
            }
            single {
                Retrofit.Builder()
                        .baseUrl(BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create(Gson()))
                        .addCallAdapterFactory(CoroutineCallAdapterFactory())
                        .client(get())
                        .build()
            }
            single { get<Retrofit>().create(GiantbombApiClient::class.java) } bind GiantbombApiClient::class
            single { Room.databaseBuilder(get(), AppDatabase::class.java, "LaBombaGigante").fallbackToDestructiveMigration().build() }
            single { get<AppDatabase>().videoDao() }

            module("auth") {
                viewModel { AuthenticationViewModel(get(), get(), get(), get()) }
            }

            module("browse") {
                single { ApiRepository(get(), get(), get(), get()) }
                viewModel { VideoBrowserViewModel(get(), get()) }
            }

            module("details") {
                viewModel { (video: Video) -> VideoDetailsViewModel(video) }
            }

            module("playback") {
                factory { HttpProxyCacheServer.Builder((get() as android.content.Context))
                        .maxCacheSize((1024 * 1024 * 1024).toLong()) // 1 Gb for cache
                        .build()
                }

                viewModel { (video: Video) -> VideoPlayerViewModel(video, get()) }
            }

            module("debug") {
                single { (activity: Activity) ->
                    DebugDrawer.Builder(activity)
                            .modules(
                                    DeviceModule(),
                                    BuildModule(),
                                    NetworkModule(),
                                    SettingsModule(),
                                    OkHttp3Module(get()),
                                    NetworkQualityModule(androidContext()),
                                    LogsModule()
                            ).build()
                }
            }
        }
}

/**
 * Module constants
 */
object Context {
    const val BASE_URL = "https://www.giantbomb.com/api/"
    const val DISK_CACHE_SIZE = 50 * 1024 * 1024 // 50MB
}