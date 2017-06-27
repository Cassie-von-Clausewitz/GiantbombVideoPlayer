package com.kyleriedemann.giantbombvideoplayer.VideoList

import android.content.Context
import android.content.Intent
import android.support.v7.widget.CardView
import android.util.AttributeSet
import android.view.View
import com.airbnb.epoxy.ModelProp
import com.airbnb.epoxy.ModelView
import com.kyleriedemann.giantbombvideoplayer.Base.Extensions.debug
import com.kyleriedemann.giantbombvideoplayer.Base.Extensions.getActivity
import com.kyleriedemann.giantbombvideoplayer.Models.Video
import com.kyleriedemann.giantbombvideoplayer.Network.Connectivity
import com.kyleriedemann.giantbombvideoplayer.R
import com.kyleriedemann.giantbombvideoplayer.VideoPlayer.VideoViewActivity
import kotlinx.android.synthetic.main.video_card.view.*

@ModelView(defaultLayout = R.layout.model_video_card)
class VideoCard : CardView {

    internal var video: Video? = null

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        inflateLayout(context, attrs)
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        inflateLayout(context, attrs)
    }

    fun inflateLayout(context: Context, attrs: AttributeSet?) {
        View.inflate(context, R.layout.video_card, this)
    }

    @ModelProp
    fun setVideo(video: Video?) {
        this.video = video

        video?.let {
            video_name.text = it.name

            var deckStr = it.deck
            if (deckStr.length > 100) {
                deckStr = deckStr.substring(0, 96) + "..."
            }
            deck.text = deckStr

            val url = it.videoImage.thumbImageUrl.replace(" ".toRegex(), "%20").replace(".png".toRegex(), ".jpg")
            context.getActivity()?.debug("Thumbnail Url %s", url)
            video_thumb.setImageURI(url)

            this.setOnClickListener {
                val i = Intent(context, VideoViewActivity::class.java)
                if (Connectivity.isConnectedFast(context)) {
                    i.putExtra("url", video.highUrl)
                } else {
                    i.putExtra("url", video.lowUrl)
                }

                context.startActivity(i)
            }
        }
    }
}
