package com.kyleriedemann.giantbombvideoplayer.Video;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.widget.MediaController;

/**
 * Created by kyle on 3/17/16.
 */
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
