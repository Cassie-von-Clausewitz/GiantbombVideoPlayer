package com.kyleriedemann.giantbombvideoplayer

import android.content.Intent
import butterknife.OnClick
import com.kyleriedemann.giantbombvideoplayer.Authentication.AuthenticationActivity
import com.kyleriedemann.giantbombvideoplayer.Base.ActionBar.DisplayTitle
import com.kyleriedemann.giantbombvideoplayer.Base.BaseActivity
import com.kyleriedemann.giantbombvideoplayer.Base.DependencyInjection.Components.ActivityComponent
import com.kyleriedemann.giantbombvideoplayer.Base.Extensions.startActivity
import inkapplicaitons.android.logger.Logger
import inkapplications.android.layoutinjector.Layout
import javax.inject.Inject

@DisplayTitle(R.string.app_name)
@Layout(R.layout.activity_main)
class MainActivity : BaseActivity() {

    @Inject
    lateinit var logger: Logger

    @OnClick(R.id.auth_button)
    fun startAuth() = startActivity(AuthenticationActivity::class)

    override fun injectSelf(component: ActivityComponent) = component.inject(this)
}
