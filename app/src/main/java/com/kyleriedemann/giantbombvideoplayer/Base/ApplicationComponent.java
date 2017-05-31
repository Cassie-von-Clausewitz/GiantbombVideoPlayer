package com.kyleriedemann.giantbombvideoplayer.Base;

import com.kyleriedemann.giantbombvideoplayer.GiantbombApp;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(
        modules = {
                AndroidApplicationModule.class,
                GiantbombApplicationModule.class
        }
)
public interface ApplicationComponent {
    void inject(GiantbombApp target);

    ActivityComponent newActivityComponent(AndroidActivityModule module);
}
