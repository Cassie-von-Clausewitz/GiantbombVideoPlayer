package com.kyleriedemann.giantbombvideoplayer.VideoPlayer

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast

import com.kyleriedemann.giantbombvideoplayer.R

class WebViewActivity : Activity() {
    private var webview: WebView? = null
    private var url: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_webview)

        webview = findViewById<View>(R.id.webview) as WebView
        webview!!.webViewClient = WebViewClient()

        webview!!.settings.javaScriptEnabled = true
        webview!!.settings.javaScriptCanOpenWindowsAutomatically = true
        webview!!.settings.setSupportMultipleWindows(true)
        webview!!.settings.setSupportZoom(true)
        webview!!.settings.builtInZoomControls = true
        webview!!.isVerticalScrollBarEnabled = false
        webview!!.isHorizontalScrollBarEnabled = false
        webview!!.settings.allowFileAccess = true

        url = intent.extras!!.getString("url")

        //        webview.loadUrl(url);
        overrideUrlLoading(webview, url)
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

    fun overrideUrlLoading(view: WebView, url: String?) {
        if (url!!.endsWith(".mp4") || url.endsWith(".3gp")) {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.setDataAndType(Uri.parse(url), "video/*")
            view.context.startActivity(intent)
        } else {
            val toast = Toast.makeText(baseContext, "No Video Found", Toast.LENGTH_LONG)
            toast.show()
        }
    }
}
