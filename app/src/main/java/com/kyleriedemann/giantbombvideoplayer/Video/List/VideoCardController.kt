package com.kyleriedemann.giantbombvideoplayer.Video.List

import com.airbnb.epoxy.EpoxyController
import com.kyleriedemann.giantbombvideoplayer.Video.Models.Result

class VideoCardController : EpoxyController() {
    private var results: Result? = null

    fun setPredictions(results: Result?) {
        this.results = results
        requestModelBuild()
    }

    override fun buildModels() {
        results?.results?.forEach {
            VideoCardModel_()
                    .video(it)
                    .addTo(this)
        }
    }
}