package com.kyleriedemann.giantbombvideoplayer.video.list

import com.bumptech.glide.Glide
import com.kyleriedemann.giantbombvideoplayer.R
import com.kyleriedemann.giantbombvideoplayer.databinding.VideoCardBinding
import com.kyleriedemann.giantbombvideoplayer.video.models.Video
import com.xwray.groupie.databinding.BindableItem

class VideoItem(val video: Video): BindableItem<VideoCardBinding>() {
    override fun getLayout(): Int = R.layout.video_card

    override fun bind(binding: VideoCardBinding, position: Int) {
        binding.video = video
        Glide.with(binding.root.context).load(video.videoImage.thumbImageUrl).into(binding.videoThumbnail)
    }
}