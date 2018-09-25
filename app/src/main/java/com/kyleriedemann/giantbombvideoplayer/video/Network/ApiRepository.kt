package com.kyleriedemann.giantbombvideoplayer.video.network

import android.content.SharedPreferences
import com.kyleriedemann.giantbombvideoplayer.video.models.Result
import com.kyleriedemann.giantbombvideoplayer.video.models.Video
import com.kyleriedemann.giantbombvideoplayer.video.models.VideoDao
import kotlinx.coroutines.experimental.CoroutineScope
import kotlinx.coroutines.experimental.GlobalScope
import kotlinx.coroutines.experimental.channels.ReceiveChannel
import kotlinx.coroutines.experimental.channels.produce
import kotlinx.coroutines.experimental.selects.select
import timber.log.Timber
import kotlin.coroutines.experimental.CoroutineContext

class ApiRepository(private val giantbombApiClient: GiantbombApiClient, private val sharedPreferences: SharedPreferences, private val videoDao: VideoDao): CoroutineScope {
    suspend fun getVideos(): Result {
        val key = sharedPreferences.getString("API_KEY", "No Saved API Key")
        if (key.isNullOrEmpty()) throw IllegalStateException("no saved API key")

        val db = produce {
            send(videoDao.selectAll())
        }
        val network = produce {
            send(giantbombApiClient.getVideos(key, "json").await())
        }

        val videos = cachedDataOrNetworkFallback(db, network, videoDao)?: Result()
        Timber.i("video results [${videos.results.size}]")

        return videos
    }

    suspend inline fun cachedDataOrNetworkFallback(db: ReceiveChannel<List<Video>>, network: ReceiveChannel<Result>, videoDao: VideoDao): Result? {
        return select {
            db.onReceiveOrNull {
                Timber.i("onReceive from db [${it?.size}]")
                if (it == null) null
                else Result(results = it)
            }
            network.onReceiveOrNull {
                Timber.i("onReceive from network [${it?.results?.size}]")
                if (it != null) {
                    Timber.d("inserting network values into db")
                    videoDao.insertVideo(*it.results.toTypedArray())
                }

                it
            }
        }
    }

    override val coroutineContext: CoroutineContext
        get() = GlobalScope.coroutineContext
}
