package com.wyrmix.giantbombvideoplayer.video.details

import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import com.wyrmix.giantbombvideoplayer.auth.API_KEY
import com.wyrmix.giantbombvideoplayer.video.models.Video

class VideoDetailsViewModel(val video: Video, private val prefs: SharedPreferences): ViewModel() {
    private var currentUrl: String? = ""

    fun getVideoUrl(): String {
        val key = prefs.getString(API_KEY, "")
        currentUrl = video.hdUrl + "?api_key=$key"
        return currentUrl ?: ""
    }
}
