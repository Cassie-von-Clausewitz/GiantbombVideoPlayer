package com.kyleriedemann.giantbombvideoplayer.VideoPlayer;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.widget.MediaController;

public class CustomMediaController extends MediaController {
    public CustomMediaController(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomMediaController(Context context, boolean useFastForward) {
        super(context, useFastForward);
    }

    public CustomMediaController(Context context) {
        super(context);
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event)
    {
        if (event.getKeyCode() == KeyEvent.KEYCODE_BACK)
            ((Activity) getContext()).finish();

        return super.dispatchKeyEvent(event);
    }
}
