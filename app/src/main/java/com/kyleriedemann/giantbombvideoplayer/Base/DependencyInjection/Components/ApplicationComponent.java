package com.kyleriedemann.giantbombvideoplayer.Base.DependencyInjection.Components;

import com.kyleriedemann.giantbombvideoplayer.Base.DependencyInjection.Modules.DebugModule;
import com.kyleriedemann.giantbombvideoplayer.Base.DependencyInjection.Modules.AndroidActivityModule;
import com.kyleriedemann.giantbombvideoplayer.Base.DependencyInjection.Modules.AndroidApplicationModule;
import com.kyleriedemann.giantbombvideoplayer.Base.DependencyInjection.Modules.GiantbombApplicationModule;
import com.kyleriedemann.giantbombvideoplayer.Base.DependencyInjection.Modules.NetworkModule;
import com.kyleriedemann.giantbombvideoplayer.GiantbombApp;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(
        modules = {
                AndroidApplicationModule.class,
                GiantbombApplicationModule.class,
                DebugModule.class,
                NetworkModule.class
        }
)
public interface ApplicationComponent {
    void inject(GiantbombApp target);

    ActivityComponent newActivityComponent(AndroidActivityModule module);
}
