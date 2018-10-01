package com.wyrmix.giantbombvideoplayer.video.player

import android.media.MediaPlayer
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.danikula.videocache.HttpProxyCacheServer
import com.wyrmix.giantbombvideoplayer.databinding.FragmentVideoPlayerBinding
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

class VideoViewFragment: Fragment() {
    val viewModel by inject<VideoPlayerViewModel> { parametersOf(VideoViewFragmentArgs.fromBundle(arguments).video) }

    val proxy: HttpProxyCacheServer by inject()

    internal lateinit var mediaController: CustomMediaController

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = FragmentVideoPlayerBinding.inflate(inflater, container, false)

        mediaController = CustomMediaController(inflater.context)
        mediaController.setAnchorView(binding.videoView)
        binding.videoView.setMediaController(mediaController)

        binding.videoView.systemUiVisibility = (View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN)

        binding.videoView.setOnInfoListener { mediaPlayer, what, extra ->
            if (MediaPlayer.MEDIA_INFO_VIDEO_RENDERING_START == what) {
                binding.videoBufferingSpinner.visibility = View.GONE
            }
            if (MediaPlayer.MEDIA_INFO_BUFFERING_START == what) {
                binding.videoBufferingSpinner.visibility = View.VISIBLE
            }
            if (MediaPlayer.MEDIA_INFO_BUFFERING_END == what) {
                binding.videoBufferingSpinner.visibility = View.VISIBLE
            }
            false
        }

        val proxyUrl = proxy.getProxyUrl(viewModel.getVideoUrl())
        binding.videoView.setVideoPath(proxyUrl)

        return binding.root
    }
}

/*
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
 */