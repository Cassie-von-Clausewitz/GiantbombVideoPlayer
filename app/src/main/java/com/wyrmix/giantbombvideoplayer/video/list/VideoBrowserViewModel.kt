package com.wyrmix.giantbombvideoplayer.video.list

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.wyrmix.giantbombvideoplayer.video.models.VideoCategoryResult
import com.wyrmix.giantbombvideoplayer.video.models.VideoResult
import com.wyrmix.giantbombvideoplayer.video.models.VideoShowResult
import com.wyrmix.giantbombvideoplayer.video.network.ApiRepository

class VideoBrowserViewModel(private val apiRepository: ApiRepository, val app: Application): AndroidViewModel(app) {
    suspend fun getVideos(): VideoResult {
        return apiRepository.getVideos()
    }

    suspend fun getVideoShows(): VideoShowResult {
        return apiRepository.getVideoShows()
    }

    suspend fun getVideoCategories(): VideoCategoryResult {
        return apiRepository.getVideoCategories()
    }
}