package com.kyleriedemann.giantbombvideoplayer.VideoList

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager

import com.kyleriedemann.giantbombvideoplayer.Base.ActionBar.DisplayTitle
import com.kyleriedemann.giantbombvideoplayer.Base.ActionBar.HomeAsUp
import com.kyleriedemann.giantbombvideoplayer.Base.BaseActivity
import com.kyleriedemann.giantbombvideoplayer.Base.DependencyInjection.Components.ActivityComponent
import com.kyleriedemann.giantbombvideoplayer.GiantbombApp
import com.kyleriedemann.giantbombvideoplayer.Models.Result
import com.kyleriedemann.giantbombvideoplayer.Network.GiantbombApiClient
import com.kyleriedemann.giantbombvideoplayer.Network.ServiceGenerator
import com.kyleriedemann.giantbombvideoplayer.R
import com.kyleriedemann.giantbombvideoplayer.Utils.PrefManager

import javax.inject.Inject

import butterknife.ButterKnife
import inkapplicaitons.android.logger.Logger
import inkapplications.android.layoutinjector.Layout
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

import com.kyleriedemann.giantbombvideoplayer.R.id.videolist

@HomeAsUp
@DisplayTitle(R.string.title_video_list)
@Layout(R.layout.video_list_layout)
class VideoListActivity : BaseActivity() {

    @Inject internal lateinit var apiClient: GiantbombApiClient

    internal var disposables = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val apiKey = PrefManager.with(GiantbombApp.instance()).getString("API_KEY", "No Saved API Key")

        disposables.add(apiClient.getVideos(apiKey, "json")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ this.onDataReady(it) }, { this.onDataError(it) }))
    }

    fun onDataReady(data: Result) {

    }

    fun onDataError(e: Throwable) {
        logger.error(e.message, e)

        if (e.cause != null) onDataError(e)
    }

    override fun injectSelf(component: ActivityComponent) = component.inject(this)
}
