package com.kyleriedemann.giantbombvideoplayer.Base.DependencyInjection.Components;

import com.kyleriedemann.giantbombvideoplayer.Base.BaseActivity;
import com.kyleriedemann.giantbombvideoplayer.Base.BaseFragment;
import com.kyleriedemann.giantbombvideoplayer.Base.DependencyInjection.ActivityScope;
import com.kyleriedemann.giantbombvideoplayer.Base.DependencyInjection.Modules.AndroidActivityModule;

import dagger.Subcomponent;

@ActivityScope
@Subcomponent(
        modules={
            AndroidActivityModule.class
        }
)
public interface ActivityComponent {
    /**
     * @deprecated This method is necessary to inject the base activity but
     *             can't be used to inject child activities. This is deprecated
     *             to indicate that you need to add a new method to this class
     *             to inject your activity!
     */
    @Deprecated
    void inject(BaseActivity target);

    /**
     * @deprecated This method is necessary to inject the base fragment but
     *             can't be used to inject child fragments. This is deprecated
     *             to indicate that you need to add a new method to this class
     *             to inject your fragment!
     */
    @Deprecated
    void inject(BaseFragment target);
}
