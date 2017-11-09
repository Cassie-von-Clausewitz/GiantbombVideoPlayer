package com.kyleriedemann.giantbombvideoplayer.Video.List

import android.content.Context
import android.content.Intent
import android.support.v7.widget.CardView
import android.util.AttributeSet
import android.view.View
import com.airbnb.epoxy.ModelProp
import com.airbnb.epoxy.ModelView
import com.kyleriedemann.giantbombvideoplayer.Video.Models.Video
import com.kyleriedemann.giantbombvideoplayer.Video.Network.Connectivity
import com.kyleriedemann.giantbombvideoplayer.R
import com.kyleriedemann.giantbombvideoplayer.Video.Player.VideoViewActivity
import kotlinx.android.synthetic.main.video_card.view.*

@ModelView(autoLayout = ModelView.Size.MATCH_WIDTH_WRAP_HEIGHT)
class VideoCard(context: Context, attrs: AttributeSet?) : CardView(context, attrs) {

    internal var video: Video? = null

    init {
        View.inflate(context, R.layout.video_card, this)
    }

    @ModelProp
    fun setVideo(video: Video?) {
        this.video = video

        video?.let {
            video_title.text = it.name

//            var deckStr = it.deck
//            if (deckStr.length > 100) {
//                deckStr = deckStr.substring(0, 96) + "..."
//            }
//            deck.text = deckStr

            val url = it.videoImage.thumbImageUrl.replace(" ".toRegex(), "%20").replace(".png".toRegex(), ".jpg")
            video_thumbnail.setImageURI(url)

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

    init {
        View.inflate(context, R.layout.video_card, this)
    }
}
