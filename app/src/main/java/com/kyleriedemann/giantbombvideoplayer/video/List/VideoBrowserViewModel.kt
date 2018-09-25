package com.kyleriedemann.giantbombvideoplayer.video.list

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.kyleriedemann.giantbombvideoplayer.video.models.Result
import com.kyleriedemann.giantbombvideoplayer.video.network.ApiRepository

class VideoBrowserViewModel(private val apiRepository: ApiRepository, val app: Application): AndroidViewModel(app) {
    suspend fun getVideos(): Result {
        return apiRepository.getVideos()
    }
}