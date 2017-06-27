package com.kyleriedemann.giantbombvideoplayer.Base.Extensions

import android.app.Activity
import android.content.Intent
import android.util.Log
import com.kyleriedemann.giantbombvideoplayer.Base.BaseActivity
import kotlin.reflect.KClass

val callStackPosition: Int = 2

/**
 * Start an Activity as a new task, clearing the current backstack.
 */
fun Activity.startNew(intent: Intent) {
    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
    startActivity(intent)
}

/**
 * Start an Activity as a new task, clearing the current backstack.
 */
fun Activity.startNew(activityClass: KClass<out Activity>) {
    val intent = Intent(this, activityClass.java)
    startNew(intent)
}

/**
 * Just start an activity based on a class. That's all I want to do, android.
 */
fun Activity.startActivity(activityClass: KClass<out Activity>) {
    val intent = Intent(this, activityClass.java)
    startActivity(intent)
}

fun Activity.trace(message: String) {
    if (this is BaseActivity) {
        this.logger.trace(message)
    } else {
        Log.v(Thread.currentThread().stackTrace[callStackPosition].className, message)
    }
}

fun Activity.trace(message: String, vararg args: Any) {
    if (this is BaseActivity) {
        this.logger.trace(message, args)
    } else {
        Log.v(Thread.currentThread().stackTrace[callStackPosition].className, String.format(message, args))
    }
}

fun Activity.trace(throwable: Throwable, message: String) {
    if (this is BaseActivity) {
        this.logger.trace(throwable, message)
    } else {
        Log.v(Thread.currentThread().stackTrace[callStackPosition].className, message, throwable)
    }
}

fun Activity.debug(message: String) {
    if (this is BaseActivity) {
        this.logger.debug(message)
    } else {
        Log.d(Thread.currentThread().stackTrace[callStackPosition].className, message)
    }
}

fun Activity.debug(message: String, vararg args: Any) {
    if (this is BaseActivity) {
        this.logger.debug(message, args)
    } else {
        Log.d(Thread.currentThread().stackTrace[callStackPosition].className, String.format(message, args))
    }
}

fun Activity.debug(throwable: Throwable, message: String) {
    if (this is BaseActivity) {
        this.logger.debug(throwable, message)
    } else {
        Log.d(Thread.currentThread().stackTrace[callStackPosition].className, message, throwable)
    }
}

fun Activity.info(message: String) {
    if (this is BaseActivity) {
        this.logger.info(message)
    } else {
        Log.i(Thread.currentThread().stackTrace[callStackPosition].className, message)
    }
}

fun Activity.info(message: String, vararg args: Any) {
    if (this is BaseActivity) {
        this.logger.info(message, args)
    } else {
        Log.i(Thread.currentThread().stackTrace[callStackPosition].className, String.format(message, args))
    }
}

fun Activity.info(throwable: Throwable, message: String) {
    if (this is BaseActivity) {
        this.logger.info(throwable, message)
    } else {
        Log.i(Thread.currentThread().stackTrace[callStackPosition].className, message, throwable)
    }
}

fun Activity.warn(message: String) {
    if (this is BaseActivity) {
        this.logger.warn(message)
    } else {
        Log.w(Thread.currentThread().stackTrace[callStackPosition].className, message)
    }
}

fun Activity.warn(message: String, vararg args: Any) {
    if (this is BaseActivity) {
        this.logger.warn(message, args)
    } else {
        Log.w(Thread.currentThread().stackTrace[callStackPosition].className, String.format(message, args))
    }
}

fun Activity.warn(throwable: Throwable, message: String) {
    if (this is BaseActivity) {
        this.logger.warn(throwable, message)
    } else {
        Log.w(Thread.currentThread().stackTrace[callStackPosition].className, message, throwable)
    }
}

fun Activity.error(message: String) {
    if (this is BaseActivity) {
        this.logger.error(message)
    } else {
        Log.e(Thread.currentThread().stackTrace[callStackPosition].className, message)
    }
}

fun Activity.error(message: String, vararg args: Any) {
    if (this is BaseActivity) {
        this.logger.error(message, args)
    } else {
        Log.e(Thread.currentThread().stackTrace[callStackPosition].className, String.format(message, args))
    }
}

fun Activity.error(throwable: Throwable, message: String) {
    if (this is BaseActivity) {
        this.logger.error(throwable, message)
    } else {
        Log.e(Thread.currentThread().stackTrace[callStackPosition].className, message, throwable)
    }
}
