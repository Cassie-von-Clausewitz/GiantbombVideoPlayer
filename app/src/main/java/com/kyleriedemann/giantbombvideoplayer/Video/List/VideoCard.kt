package com.kyleriedemann.giantbombvideoplayer.video.list

import com.kyleriedemann.giantbombvideoplayer.R
import com.kyleriedemann.giantbombvideoplayer.video.models.Video
import com.xwray.groupie.kotlinandroidextensions.Item
import com.xwray.groupie.kotlinandroidextensions.ViewHolder

class VideoCard(val video: Video?): Item() {

    override fun getLayout() = R.layout.video_card

    override fun bind(viewHolder: ViewHolder, position: Int) {
//        viewHolder.video_title.text = video?.name
//        viewHolder.video_thumbnail.scaleType = ImageView.ScaleType.CENTER_INSIDE
//        viewHolder.video_thumbnail.setImageURI(Uri.parse(video?.videoImage?.iconUrl))
    }
}
