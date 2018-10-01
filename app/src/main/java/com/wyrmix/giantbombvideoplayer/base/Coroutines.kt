package com.wyrmix.giantbombvideoplayer.base

import inkapplicaitons.android.logger.Logger
import kotlinx.coroutines.experimental.CoroutineExceptionHandler
import kotlinx.coroutines.experimental.newSingleThreadContext
import kotlin.coroutines.experimental.AbstractCoroutineContextElement
import kotlin.coroutines.experimental.CoroutineContext

class UncaughtCoroutineExceptionHandler(private val logger: Logger) : CoroutineExceptionHandler, AbstractCoroutineContextElement(CoroutineExceptionHandler.Key) {
    override fun handleException(context: CoroutineContext, exception: Throwable) {
        logger.error(exception, "Exception [$exception] handled by $this")
    }
}

fun singleThread(name: String, logger: Logger): CoroutineContext {
    return newSingleThreadContext(name).plus(UncaughtCoroutineExceptionHandler(logger))
}
