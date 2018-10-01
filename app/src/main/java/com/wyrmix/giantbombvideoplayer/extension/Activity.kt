package com.wyrmix.giantbombvideoplayer.extension

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.ui.NavigationUI
import kotlin.reflect.KClass

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

fun AppCompatActivity.navigateUp(navigationController: NavController): Boolean {
    return NavigationUI.navigateUp(null, navigationController)
}