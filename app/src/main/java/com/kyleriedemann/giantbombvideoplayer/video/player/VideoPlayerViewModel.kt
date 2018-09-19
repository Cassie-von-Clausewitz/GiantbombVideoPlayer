package com.kyleriedemann.giantbombvideoplayer.video.player

import android.content.SharedPreferences
import android.media.MediaPlayer
import androidx.lifecycle.ViewModel
import com.kyleriedemann.giantbombvideoplayer.auth.API_KEY
import com.kyleriedemann.giantbombvideoplayer.video.models.Video

typealias completion = MediaPlayer.OnCompletionListener
typealias prepared = MediaPlayer.OnPreparedListener

class VideoPlayerViewModel(val video: Video, val prefs: SharedPreferences): ViewModel(), completion, prepared {
    private var currentUrl: String? = ""

    fun getVideoUrl(): String {
        val key = prefs.getString(API_KEY, "")
        currentUrl = video.hdUrl + "?api_key=$key"
        return currentUrl ?: ""
    }

    override fun onCompletion(mp: MediaPlayer) {
        prefs.edit().remove(currentUrl).apply()
    }

    override fun onPrepared(mp: MediaPlayer) {
        val position = prefs.getInt(currentUrl, 0)

        mp.seekTo(position)
        mp.start()
    }
}