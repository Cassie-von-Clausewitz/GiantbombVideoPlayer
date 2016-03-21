package com.kyleriedemann.giantbombvideoplayer.VideoPlayer;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.VideoView;

import com.kyleriedemann.giantbombvideoplayer.R;
import com.kyleriedemann.giantbombvideoplayer.Utils.PrefManager;

import butterknife.Bind;
import butterknife.ButterKnife;


public class VideoViewActivity extends AppCompatActivity implements MediaPlayer.OnCompletionListener, MediaPlayer.OnPreparedListener, MediaPlayer.OnInfoListener {

    @Bind(R.id.video_view)
    VideoView videoView;

    @Bind(R.id.video_buffering_spinner)
    ProgressBar bufferingSpinner;

    CustomMediaController mediaController;

    String currentUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_player);
        ButterKnife.bind(this);

        mediaController = new CustomMediaController(this);
        mediaController.setAnchorView(videoView);
        videoView.setMediaController(mediaController);

        videoView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN );

        videoView.setOnCompletionListener(this);
        videoView.setOnPreparedListener(this);
        videoView.setOnInfoListener(this);

        String url = getIntent().getExtras().getString("url");

        currentUrl = url;

        Uri uri = Uri.parse(url);
        videoView.setVideoURI(uri);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            getWindow().getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onRestart() {
        super.onRestart();

        int position = PrefManager.with(this).getInt(currentUrl, 0);
        if (videoView != null) videoView.seekTo(position);
    }

    @Override
    protected void onPause() {
        super.onPause();

        PrefManager.with(this).save(currentUrl, videoView.getCurrentPosition());
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_video_detail, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCompletion(MediaPlayer mp) {
        PrefManager.with(this).remove(currentUrl);
    }

    @Override
    public void onPrepared(MediaPlayer mp) {
        int position = PrefManager.with(this).getInt(currentUrl, 0);
        videoView.seekTo(position);

        videoView.start();
    }

    @Override
    public boolean onInfo(MediaPlayer mp, int what, int extra) {
        if (MediaPlayer.MEDIA_INFO_VIDEO_RENDERING_START == what) {
            bufferingSpinner.setVisibility(View.GONE);
        }
        if (MediaPlayer.MEDIA_INFO_BUFFERING_START == what) {
            bufferingSpinner.setVisibility(View.VISIBLE);
        }
        if (MediaPlayer.MEDIA_INFO_BUFFERING_END == what) {
            bufferingSpinner.setVisibility(View.VISIBLE);
        }
        return false;
    }
}
