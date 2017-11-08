package com.kyleriedemann.giantbombvideoplayer

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.kyleriedemann.giantbombvideoplayer.Authentication.AuthenticationActivity
import com.kyleriedemann.giantbombvideoplayer.Base.Extensions.startActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        auth_button.setOnClickListener {
            startActivity(AuthenticationActivity::class)
        }
    }
}
