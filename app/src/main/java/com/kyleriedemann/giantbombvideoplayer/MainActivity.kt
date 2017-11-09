package com.kyleriedemann.giantbombvideoplayer

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.kyleriedemann.giantbombvideoplayer.Authentication.AuthenticationActivity
import com.kyleriedemann.giantbombvideoplayer.Extensions.startActivity
import com.kyleriedemann.giantbombvideoplayer.Onboarding.OnboardingActivity
import com.kyleriedemann.giantbombvideoplayer.Video.List.VideoListActivity
import kotlinx.android.synthetic.main.activity_main.*

/**
 * garbage activity to launch into different parts of the app during development
 */
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        onboarding_button.setOnClickListener {
            startActivity(OnboardingActivity::class)
        }

        auth_button.setOnClickListener {
            startActivity(AuthenticationActivity::class)
        }

        video_button.setOnClickListener {
            startActivity(VideoListActivity::class)
        }
    }
}
