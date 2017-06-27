package com.kyleriedemann.giantbombvideoplayer.Base.ActionBar;

import android.support.v4.app.FragmentActivity;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Indicates that the ActionBar should display a back/up button.
 *
 * When defined on an Activity or Fragment that is initialized with
 * ActionBarController, this will cause the actionbar to display the back/up arrow.
 *
 * @see ActionBarController#initialize(FragmentActivity)
 * @author Renee Vandervelde
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface HomeAsUp {}
