package com.wyrmix.giantbombvideoplayer.video.player

import android.annotation.SuppressLint
import android.app.Activity
import android.app.PictureInPictureParams
import android.content.pm.PackageManager
import android.content.res.Configuration
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.support.v4.media.session.MediaSessionCompat
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.google.android.exoplayer2.*
import com.google.android.exoplayer2.ext.mediasession.MediaSessionConnector
import com.google.android.exoplayer2.source.ExtractorMediaSource
import com.google.android.exoplayer2.source.TrackGroupArray
import com.google.android.exoplayer2.source.hls.HlsMediaSource
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector
import com.google.android.exoplayer2.trackselection.TrackSelectionArray
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import com.google.android.exoplayer2.util.Util
import com.wyrmix.giantbombvideoplayer.R
import kotlinx.android.synthetic.main.activity_video.*

class VideoActivity : AppCompatActivity() {
    companion object {
        @JvmField
        val ARG_VIDEO_URL = "VideoActivity.URL"
        val ARG_VIDEO_POSITION = "VideoActivity.POSITION"
    }

    var isInPipMode: Boolean = false
    lateinit var mUrl: String
    lateinit var player: SimpleExoPlayer
    private var videoPosition: Long = 0L
    var isPIPModeeEnabled: Boolean = true //Has the user disabled PIP mode in AppOpps?

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video)

        if (intent.extras == null || !intent.hasExtra(ARG_VIDEO_URL)) {
            finish()
        }

        mUrl = intent.getStringExtra(ARG_VIDEO_URL)

        savedInstanceState?.let { videoPosition = savedInstanceState.getLong(ARG_VIDEO_POSITION) }
    }

    override fun onStart() {
        super.onStart()

        player = ExoPlayerFactory.newSimpleInstance(this, DefaultTrackSelector())

        playerView.player = player

        val dataSourceFactory = DefaultDataSourceFactory(this, Util.getUserAgent(this, applicationInfo.loadLabel(packageManager).toString()))

        @SuppressLint("SwitchIntDef")
        when (Util.inferContentType(Uri.parse(mUrl))) {
            C.TYPE_HLS -> {
                val mediaSource = HlsMediaSource.Factory(dataSourceFactory).createMediaSource(Uri.parse(mUrl))
                player.prepare(mediaSource)
            }
            C.TYPE_OTHER -> {
                val mediaSource = ExtractorMediaSource.Factory(dataSourceFactory).createMediaSource(Uri.parse(mUrl))
                player.prepare(mediaSource)
            }
            else -> {
                //This is to catch SmoothStreaming and DASH types which are not supported currently
                setResult(Activity.RESULT_CANCELED)
                finish()
            }
        }

        var returnResultOnce = true

        player.addListener(object : Player.EventListener{
            override fun onTimelineChanged(timeline: Timeline?, manifest: Any?, reason: Int) {}

            override fun onShuffleModeEnabledChanged(shuffleModeEnabled: Boolean) {}

            override fun onRepeatModeChanged(repeatMode: Int) {}

            override fun onPositionDiscontinuity(reason: Int) {}

            override fun onLoadingChanged(isLoading: Boolean) {}

            override fun onTracksChanged(trackGroups: TrackGroupArray?, trackSelections: TrackSelectionArray?) {}

            override fun onPlaybackParametersChanged(playbackParameters: PlaybackParameters?) {}

            override fun onPlayerError(error: ExoPlaybackException?) {
                setResult(Activity.RESULT_CANCELED)
                finishAndRemoveTask()
            }

            override fun onPlayerStateChanged(playWhenReady: Boolean, playbackState: Int) {
                if(playbackState == Player.STATE_READY && returnResultOnce){
                    setResult(Activity.RESULT_OK)
                    returnResultOnce = false
                }
            }

            override fun onSeekProcessed() {}
        })
        player.playWhenReady = true

        //Use Media Session Connector from the EXT library to enable MediaSession Controls in PIP.
        val mediaSession = MediaSessionCompat(this, packageName)
        val mediaSessionConnector = MediaSessionConnector(mediaSession)
        mediaSessionConnector.setPlayer(player, null)
        mediaSession.isActive = true
    }

    override fun onPause() {
        videoPosition = player.currentPosition
        super.onPause()
    }

    override fun onResume() {
        super.onResume()
        if(videoPosition > 0L && !isInPipMode){
            player.seekTo(videoPosition)
        }
        //Makes sure that the media controls pop up on resuming and when going between PIP and non-PIP states.
        playerView.useController = true
    }

    override fun onStop() {
        super.onStop()
        playerView.player = null
        player.release()
        //PIPmode activity.finish() does not remove the activity from the recents stack.
        //Only finishAndRemoveTask does this.
        if ((Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)
                && packageManager.hasSystemFeature(PackageManager.FEATURE_PICTURE_IN_PICTURE)) {
            finishAndRemoveTask()
        }
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState.apply {
            this?.putLong(ARG_VIDEO_POSITION, player.currentPosition)
        })
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        super.onRestoreInstanceState(savedInstanceState)
        videoPosition = savedInstanceState!!.getLong(ARG_VIDEO_POSITION)
    }

    override fun onBackPressed(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.N
                && packageManager.hasSystemFeature(PackageManager.FEATURE_PICTURE_IN_PICTURE)
                && isPIPModeeEnabled) {
            enterPIPMode()
        } else {
            super.onBackPressed()
        }
    }

    override fun onPictureInPictureModeChanged(isInPictureInPictureMode: Boolean, newConfig: Configuration?) {
        if(newConfig !=null){
            videoPosition = player.currentPosition
            isInPipMode = !isInPictureInPictureMode
        }
        super.onPictureInPictureModeChanged(isInPictureInPictureMode, newConfig)
    }

    //Called when the user touches the Home or Recents button to leave the app.
    override fun onUserLeaveHint() {
        super.onUserLeaveHint()
        enterPIPMode()
    }

    @Suppress("DEPRECATION")
    fun enterPIPMode(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N
                && packageManager.hasSystemFeature(PackageManager.FEATURE_PICTURE_IN_PICTURE)) {
            videoPosition = player.currentPosition
            playerView.useController = false
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                val params = PictureInPictureParams.Builder()
                this.enterPictureInPictureMode(params.build())
            } else {
                this.enterPictureInPictureMode()
            }
            /* We need to check this because the system permission check is publically hidden for integers for non-manufacturer-built apps
               https://github.com/aosp-mirror/platform_frameworks_base/blob/studio-3.1.2/core/java/android/app/AppOpsManager.java#L1640

               ********* If we didn't have that problem *********
                val appOpsManager = getSystemService(Context.APP_OPS_SERVICE) as AppOpsManager
                if(appOpsManager.checkOpNoThrow(AppOpManager.OP_PICTURE_IN_PICTURE, packageManager.getApplicationInfo(packageName, PackageManager.GET_META_DATA).uid, packageName) == AppOpsManager.MODE_ALLOWED)

                30MS window in even a restricted memory device (756mb+) is more than enough time to check, but also not have the system complain about holding an action hostage.
             */
            Handler().postDelayed({checkPIPPermission()}, 30)
        }
    }

    @RequiresApi(Build.VERSION_CODES.N)
    fun checkPIPPermission(){
        isPIPModeeEnabled = isInPictureInPictureMode
        if(!isInPictureInPictureMode){
            onBackPressed()
        }
    }
}