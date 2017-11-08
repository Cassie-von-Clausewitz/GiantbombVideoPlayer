package com.kyleriedemann.giantbombvideoplayer.VideoList

import android.content.SharedPreferences
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.kyleriedemann.giantbombvideoplayer.Models.Result
import com.kyleriedemann.giantbombvideoplayer.Network.GiantbombApiClient
import com.kyleriedemann.giantbombvideoplayer.R
import inkapplicaitons.android.logger.Logger
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import org.koin.android.ext.android.inject

class VideoListActivity : AppCompatActivity() {

    val apiClient: GiantbombApiClient by inject()
    val prefs: SharedPreferences by inject()
    val logger: Logger by inject()

    internal var disposables = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.video_list_layout)

        val apiKey = prefs.getString("API_KEY", "No Saved API Key")

        disposables.add(apiClient.getVideos(apiKey, "json")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ this.onDataReady(it) }, { this.onDataError(it) }))
    }

    fun onDataReady(data: Result) {

    }

    fun onDataError(e: Throwable) {
        logger.error(e.message, e)

        if (e.cause != null) onDataError(e)
    }
}
