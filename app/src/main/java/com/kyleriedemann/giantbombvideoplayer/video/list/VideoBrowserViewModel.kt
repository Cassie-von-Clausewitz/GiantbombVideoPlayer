package com.kyleriedemann.giantbombvideoplayer.video.list

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import com.kyleriedemann.giantbombvideoplayer.video.models.Result
import com.kyleriedemann.giantbombvideoplayer.video.network.ApiRepository
import kotlinx.coroutines.experimental.Deferred

class VideoBrowserViewModel(private val apiRepository: ApiRepository, val app: Application): AndroidViewModel(app) {
    fun getVideos(): Deferred<Result> {
        return apiRepository.getVideos()
    }
}