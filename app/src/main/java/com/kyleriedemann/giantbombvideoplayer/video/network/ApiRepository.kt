package com.kyleriedemann.giantbombvideoplayer.video.network

import android.content.SharedPreferences
import com.appmattus.layercache.Cache
import com.appmattus.layercache.fromRetrofit
import com.kyleriedemann.giantbombvideoplayer.video.models.Result
import com.kyleriedemann.giantbombvideoplayer.video.models.VideoDao
import com.kyleriedemann.giantbombvideoplayer.base.singleThread
import inkapplicaitons.android.logger.Logger
import kotlinx.coroutines.experimental.Deferred
import kotlinx.coroutines.experimental.launch

class ApiRepository(private val giantbombApiClient: GiantbombApiClient, private val sharedPreferences: SharedPreferences, private val videoDao: VideoDao, private val logger: Logger) {
    fun getVideos(): Deferred<Result> {
        launch(singleThread("handler1", logger)) {
            giantbombApiClient.getVideos(sharedPreferences.getString("API_KEY", "No Saved API Key"), "json")

            val cache = Cache.fromRetrofit<Int, Result> { offset -> giantbombApiClient.getVideosCall(sharedPreferences.getString("API_KEY", "No Saved API Key"), "json", offset) }

            logger.info("Cache [$cache]")

            val videos = giantbombApiClient.getVideos(sharedPreferences.getString("API_KEY", "No Saved API Key"), "json").await()
            videoDao.insertVideo(*videos.results.toTypedArray())
        }

        return giantbombApiClient.getVideos(sharedPreferences.getString("API_KEY", "No Saved API Key"), "json")
    }
}