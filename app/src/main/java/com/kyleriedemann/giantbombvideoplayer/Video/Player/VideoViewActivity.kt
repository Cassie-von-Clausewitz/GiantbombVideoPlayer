package com.kyleriedemann.giantbombvideoplayer.Video.Player

import android.content.SharedPreferences
import android.media.MediaPlayer
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.danikula.videocache.HttpProxyCacheServer
import com.kyleriedemann.giantbombvideoplayer.R
import kotlinx.android.synthetic.main.activity_video_player.*
import org.koin.android.ext.android.inject

class VideoViewActivity : AppCompatActivity(), MediaPlayer.OnCompletionListener, MediaPlayer.OnPreparedListener, MediaPlayer.OnInfoListener {

    val proxy: HttpProxyCacheServer by inject()
    val prefs: SharedPreferences by inject()

    internal lateinit var mediaController: CustomMediaController

    internal var currentUrl: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video_player)

        mediaController = CustomMediaController(this)
        mediaController.setAnchorView(video_view)
        video_view!!.setMediaController(mediaController)

        video_view!!.systemUiVisibility = (View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN)

        video_view!!.setOnCompletionListener(this)
        video_view!!.setOnPreparedListener(this)
        video_view!!.setOnInfoListener(this)

        val url = intent.extras!!.getString("url")

        currentUrl = url

        val proxyUrl = proxy.getProxyUrl(url)
        video_view!!.setVideoPath(proxyUrl)
    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        if (hasFocus) {
            window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                    or View.SYSTEM_UI_FLAG_FULLSCREEN
                    or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY)
        }
    }

    override fun onStop() {
        super.onStop()
    }

    override fun onRestart() {
        super.onRestart()

        val position = prefs.getInt(currentUrl, 0)
        if (video_view != null) video_view!!.seekTo(position)
    }

    override fun onPause() {
        super.onPause()

        prefs.edit().putInt(currentUrl, video_view!!.currentPosition).apply()
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_video_detail, menu)
        return true
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        val id = item.itemId


        return if (id == R.id.action_settings) {
            true
        } else super.onOptionsItemSelected(item)

    }

    override fun onCompletion(mp: MediaPlayer) {
        prefs.edit().remove(currentUrl).apply()
    }

    override fun onPrepared(mp: MediaPlayer) {
        val position = prefs.getInt(currentUrl, 0)

        video_view!!.seekTo(position)
        video_view!!.start()
    }

    override fun onInfo(mp: MediaPlayer, what: Int, extra: Int): Boolean {
        if (MediaPlayer.MEDIA_INFO_VIDEO_RENDERING_START == what) {
            video_buffering_spinner!!.visibility = View.GONE
        }
        if (MediaPlayer.MEDIA_INFO_BUFFERING_START == what) {
            video_buffering_spinner!!.visibility = View.VISIBLE
        }
        if (MediaPlayer.MEDIA_INFO_BUFFERING_END == what) {
            video_buffering_spinner!!.visibility = View.VISIBLE
        }
        return false
    }
}
