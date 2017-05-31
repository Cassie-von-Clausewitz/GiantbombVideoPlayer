package com.kyleriedemann.giantbombvideoplayer.Base;

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
