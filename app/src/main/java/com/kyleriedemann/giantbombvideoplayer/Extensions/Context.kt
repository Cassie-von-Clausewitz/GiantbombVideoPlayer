package com.kyleriedemann.giantbombvideoplayer.Extensions

import android.content.Context
import android.content.ContextWrapper
import android.app.Activity



/**
 * Context extension methods
 *
 * Created by kriedema on 6/27/17.
 */
fun Context.getActivity() : Activity? {
    var context = this
    while (context is ContextWrapper) {
        if (context is Activity) {
            return context
        }
        context = context.baseContext
    }
    return null
}