package com.wyrmix.giantbombvideoplayer.extension

import androidx.lifecycle.LiveData
import com.wyrmix.giantbombvideoplayer.functional.Option
import kotlinx.coroutines.experimental.CompletableDeferred
import kotlinx.coroutines.experimental.Deferred

fun <T> LiveData<T>.toJob(): Deferred<Option<T>> {
    val deferred = CompletableDeferred<Option<T>>()

    deferred.invokeOnCompletion {
        if (deferred.isCancelled) {
        }
    }

    if (value != null) {
        deferred.complete(Option.Some(value!!))
    } else {
        deferred.complete(Option.None)
    }

    return deferred
}