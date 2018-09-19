package com.kyleriedemann.giantbombvideoplayer.video.player

import android.app.Activity
import android.content.Context
import android.util.AttributeSet
import android.view.KeyEvent
import android.widget.MediaController

class CustomMediaController : MediaController {
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {}

    constructor(context: Context, useFastForward: Boolean) : super(context, useFastForward) {}

    constructor(context: Context) : super(context) {}

    override fun dispatchKeyEvent(event: KeyEvent): Boolean {
        if (event.keyCode == KeyEvent.KEYCODE_BACK)
            (context as Activity).finish()

        return super.dispatchKeyEvent(event)
    }
}
