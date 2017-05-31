package com.kyleriedemann.giantbombvideoplayer.Base.actionbar;

import android.support.annotation.StringRes;
import android.support.v4.app.FragmentActivity;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Defines a user readable title for a screen.
 *
 * When defined on an Activity or Fragment that is initialized with the
 * ActionBarController, this defines the title to use in the actionbar.
 *
 * @see ActionBarController#initialize(FragmentActivity)
 * @author Renee Vandervelde
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface DisplayTitle {
    @StringRes int value();
}
