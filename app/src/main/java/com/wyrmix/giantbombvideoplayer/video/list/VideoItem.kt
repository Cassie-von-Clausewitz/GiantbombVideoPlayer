package com.wyrmix.giantbombvideoplayer.video.list

import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.wyrmix.giantbombvideoplayer.R
import com.wyrmix.giantbombvideoplayer.databinding.VideoCardBinding
import com.wyrmix.giantbombvideoplayer.video.models.Video
import com.xwray.groupie.databinding.BindableItem



class VideoItem(val video: Video): BindableItem<VideoCardBinding>() {
    override fun getLayout(): Int = R.layout.video_card

    override fun bind(binding: VideoCardBinding, position: Int) {
        binding.video = video
        val options = RequestOptions()
        options.centerCrop()

        Glide.with(binding.root.context)
                .load(video.videoImage.screenUrl)
                .apply(options)
                .into(binding.videoThumbnail)


        Glide.with(binding.root.context)
                .load(video)
    }
}