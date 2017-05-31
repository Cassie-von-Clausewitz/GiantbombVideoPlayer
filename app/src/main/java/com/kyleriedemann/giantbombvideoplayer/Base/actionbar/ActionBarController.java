package com.kyleriedemann.giantbombvideoplayer.Base.actionbar;

import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.ActionBar;

import com.kyleriedemann.giantbombvideoplayer.Base.ActivityScope;

import javax.inject.Inject;

import inkapplicaitons.android.logger.Logger;

/**
 * Provides an API for interacting with the standard action bar.
 *
 * @author Renee Vandervelde
 */
@ActivityScope
public class ActionBarController {
    private final String NULL_MSG = "ActionBar was null.  Assuming this is intended to be an Activity with "
            + "no ActionBar.  Otherwise, you probably forgot to call supplyActionBar() after setting "
            + "toolBar as actionBar in onCreate().";

    final private Logger logger;
    private ActionBar actionBar;

    @Inject
    public ActionBarController(Logger logger) {
        this.logger = logger;
    }

    public void supplyActionBar(ActionBar actionBar) {
        this.actionBar = actionBar;
    }

    /**
     * Display a back arrow in the Activity's Action bar.
     */
    public void homeAsUp() {
        if (null == this.actionBar) {
            this.logger.info(NULL_MSG);
            return;
        }

        this.actionBar.setDisplayHomeAsUpEnabled(true);
    }

    /**
     * Set the title text displayed in the action bar.
     *
     * @param title The Resource ID of the text to be displayed.
     */
    public void setTitle(@StringRes int title) {
        if (null == this.actionBar) {
            this.logger.info(NULL_MSG);
            return;
        }

        this.actionBar.setTitle(title);
    }

    /**
     * Initialize the Actionbar for the current Activity based on Annotations.
     *
     * When the relevant annotations are present, this sets up:
     *  - Home-As-Up button
     *  - ActionBar Title
     *
     * @see DisplayTitle
     * @see HomeAsUp
     * @param activity The Activity that annotations should be parsed on.
     */
    public void initialize(FragmentActivity activity) {
        this.setHomeAsUpByAnnotation(activity);
        this.setTitleByAnnotation(activity);
    }

    /**
     * Initialize the Actionbar for the current Fragment based on Annotations.
     *
     * When the relevant annotations are present, this sets up:
     *  - Home-As-Up button
     *  - ActionBar Title
     *
     * @see DisplayTitle
     * @see HomeAsUp
     * @param fragment The Fragment that annotations should be parsed on.
     */
    public void initialize(Fragment fragment) {
        this.setHomeAsUpByAnnotation(fragment);
        this.setTitleByAnnotation(fragment);
    }

    /** Parse the DisplayTitle annotation, and set it to the action bar if present. */
    private void setTitleByAnnotation(Object target) {
        if (target.getClass().isAnnotationPresent(DisplayTitle.class)) {
            DisplayTitle titleAnnotation = target.getClass().getAnnotation(DisplayTitle.class);
            this.setTitle(titleAnnotation.value());
        }
    }

    /** Check for the HomeAsUp annotation, and set the button if present. */
    private void setHomeAsUpByAnnotation(Object target) {
        if (target.getClass().isAnnotationPresent(HomeAsUp.class)) {
            this.homeAsUp();
        }
    }
}
