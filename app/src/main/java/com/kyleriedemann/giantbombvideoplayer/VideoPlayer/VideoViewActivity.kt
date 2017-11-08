package com.kyleriedemann.giantbombvideoplayer.VideoPlayer

import android.media.MediaPlayer
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ProgressBar
import android.widget.VideoView

import com.danikula.videocache.HttpProxyCacheServer
import com.kyleriedemann.giantbombvideoplayer.GiantbombApp
import com.kyleriedemann.giantbombvideoplayer.R
import com.kyleriedemann.giantbombvideoplayer.Utils.PrefManager

import butterknife.BindView
import butterknife.ButterKnife


class VideoViewActivity : AppCompatActivity(), MediaPlayer.OnCompletionListener, MediaPlayer.OnPreparedListener, MediaPlayer.OnInfoListener {

    @BindView(R.id.video_view)
    internal var videoView: VideoView? = null

    @BindView(R.id.video_buffering_spinner)
    internal var bufferingSpinner: ProgressBar? = null

    internal var mediaController: CustomMediaController

    internal var currentUrl: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video_player)
        ButterKnife.bind(this)

        mediaController = CustomMediaController(this)
        mediaController.setAnchorView(videoView)
        videoView!!.setMediaController(mediaController)

        videoView!!.systemUiVisibility = (View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN)

        videoView!!.setOnCompletionListener(this)
        videoView!!.setOnPreparedListener(this)
        videoView!!.setOnInfoListener(this)

        val url = intent.extras!!.getString("url")

        currentUrl = url

        val proxy = GiantbombApp.Companion.instance().getProxy()
        val proxyUrl = proxy.getProxyUrl(url)
        videoView!!.setVideoPath(proxyUrl)
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

        val position = PrefManager.with(this).getInt(currentUrl, 0)
        if (videoView != null) videoView!!.seekTo(position)
    }

    override fun onPause() {
        super.onPause()

        PrefManager.with(this).save(currentUrl, videoView!!.currentPosition)
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
        PrefManager.with(this).remove(currentUrl)
    }

    override fun onPrepared(mp: MediaPlayer) {
        val position = PrefManager.with(this).getInt(currentUrl, 0)

        videoView!!.seekTo(position)
        videoView!!.start()
    }

    override fun onInfo(mp: MediaPlayer, what: Int, extra: Int): Boolean {
        if (MediaPlayer.MEDIA_INFO_VIDEO_RENDERING_START == what) {
            bufferingSpinner!!.visibility = View.GONE
        }
        if (MediaPlayer.MEDIA_INFO_BUFFERING_START == what) {
            bufferingSpinner!!.visibility = View.VISIBLE
        }
        if (MediaPlayer.MEDIA_INFO_BUFFERING_END == what) {
            bufferingSpinner!!.visibility = View.VISIBLE
        }
        return false
    }
}
