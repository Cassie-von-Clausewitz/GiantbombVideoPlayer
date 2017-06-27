package com.kyleriedemann.giantbombvideoplayer.Base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.kyleriedemann.giantbombvideoplayer.Base.ActionBar.ActionBarController;
import com.kyleriedemann.giantbombvideoplayer.Base.DependencyInjection.Components.ActivityComponent;
import com.kyleriedemann.giantbombvideoplayer.Base.DependencyInjection.Modules.AndroidActivityModule;
import com.kyleriedemann.giantbombvideoplayer.Base.DependencyInjection.Components.ApplicationComponent;
import com.kyleriedemann.giantbombvideoplayer.Base.DependencyInjection.DaggerAwareActivity;
import com.kyleriedemann.giantbombvideoplayer.GiantbombApp;
import com.kyleriedemann.giantbombvideoplayer.R;

import javax.inject.Inject;

import butterknife.ButterKnife;
import icepick.Icepick;
import inkapplicaitons.android.logger.Logger;
import inkapplications.android.layoutinjector.LayoutInjector;
import inkapplications.guava.Stopwatch;
import io.reactivex.disposables.CompositeDisposable;

/**
 * Boilerplate activity pre-configured to run framework utilities.
 *
 * This class should not be used for re-usable logic. Not all activities must
 * extend this class, it is simply for convenience.
 */
abstract public class BaseActivity extends AppCompatActivity implements DaggerAwareActivity {

    @Inject
    Logger logger;

    @Inject
    ActionBarController actionBarController;

    private CompositeDisposable disposables;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Stopwatch createTimer = Stopwatch.createStarted();
        this.initializeInjections();
        this.logger.trace("Activity Lifecycle: %s.onCreate()", this.getClass().getSimpleName());
        LayoutInjector.injectContentView(this);
        ButterKnife.bind(this);
        Icepick.restoreInstanceState(this, savedInstanceState);

        // Find the toolbar view inside the activity layout, if it exists and is named appropriately
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        // If we found the toolbar layout, set it as the action bar for this activity.
        if (toolbar != null) {
            // Sets the Toolbar to act as the ActionBar for this Activity window.
            setSupportActionBar(toolbar);
            actionBarController.supplyActionBar(getSupportActionBar());
        }

        this.actionBarController.initialize(this);

        createTimer.stop();
        this.logger.debug("BaseActivity onCreate took %s", createTimer);
    }

    @Override
    protected void onStart() {
        super.onStart();
        this.logger.trace("Activity Lifecycle: %s.onStart()", this.getClass().getSimpleName());

        disposables = new CompositeDisposable();
    }

    @Override
    protected void onResume() {
        super.onResume();
        this.logger.info("Viewing: %s", this.getClass().getSimpleName());
        this.logger.trace("Activity Lifecycle: %s.onResume()", this.getClass().getSimpleName());
    }
    @Override
    protected void onPause() {
        super.onPause();
        this.logger.trace("Activity Lifecycle: %s.onPause()", this.getClass().getSimpleName());
    }

    @Override
    protected void onStop() {
        super.onStop();
        this.logger.trace("Activity Lifecycle: %s.onStop()", this.getClass().getSimpleName());

        disposables.dispose();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        this.logger.trace("Activity Lifecycle: %s.onSaveInstanceState()", this.getClass().getSimpleName());
        Icepick.saveInstanceState(this, outState);
    }

    /**
     * Default menu actions.
     *
     * Set the default behavior of the home button to close the activity (back button).
     * This can be handled different per-activity, but this seems like a
     * reasonable default for most activities.
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * Inject this class and invoke the child injector.
     */
    private void initializeInjections() {
        GiantbombApp application = (GiantbombApp) this.getApplication();
        ApplicationComponent applicationComponent = application.getApplicationComponent();
        ActivityComponent activityComponent = applicationComponent.newActivityComponent(new AndroidActivityModule(this));

        activityComponent.inject(this);
        this.injectSelf(activityComponent);
    }

    /** Unexpected error with the user subscription. */
    private void onAuthError(Throwable error) {
        this.logger.error(error, "Unknown Error observing user");
    }
}