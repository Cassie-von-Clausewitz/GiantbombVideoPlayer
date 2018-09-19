package com.kyleriedemann.giantbombvideoplayer.video.network

import android.content.SharedPreferences
import com.kyleriedemann.giantbombvideoplayer.base.singleThread
import com.kyleriedemann.giantbombvideoplayer.video.models.Result
import com.kyleriedemann.giantbombvideoplayer.video.models.VideoDao
import inkapplicaitons.android.logger.Logger
import kotlinx.coroutines.experimental.Deferred
import kotlinx.coroutines.experimental.launch

class ApiRepository(private val giantbombApiClient: GiantbombApiClient, private val sharedPreferences: SharedPreferences, private val videoDao: VideoDao, private val logger: Logger) {
    fun getVideos(): Deferred<Result> {
        launch(singleThread("handler1", logger)) {
            giantbombApiClient.getVideos(sharedPreferences.getString("API_KEY", "No Saved API Key"), "json")

            val videos = giantbombApiClient.getVideos(sharedPreferences.getString("API_KEY", "No Saved API Key"), "json").await()
            videoDao.insertVideo(*videos.results.toTypedArray())
        }

        return giantbombApiClient.getVideos(sharedPreferences.getString("API_KEY", "No Saved API Key"), "json")
    }
}