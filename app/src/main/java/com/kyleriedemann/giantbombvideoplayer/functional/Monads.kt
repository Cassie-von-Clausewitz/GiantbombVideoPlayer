package com.kyleriedemann.giantbombvideoplayer.functional

fun half(a: Int) = when {
    a % 2 == 0 -> Option.Some(a / 2)
    else -> Option.None
}