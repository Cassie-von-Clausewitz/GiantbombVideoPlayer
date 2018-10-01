package com.wyrmix.giantbombvideoplayer.video.details

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.wyrmix.giantbombvideoplayer.databinding.FragmentVideoDetailsBinding
import com.wyrmix.giantbombvideoplayer.video.player.VideoActivity
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

class VideoDetailsFragment: Fragment() {
    val videoDetailsViewModel by inject<VideoDetailsViewModel> { parametersOf(VideoDetailsFragmentArgs.fromBundle(arguments).video) }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = FragmentVideoDetailsBinding.inflate(inflater, container, false)
        binding.viewModel = videoDetailsViewModel
        Glide.with(context!!).load(videoDetailsViewModel.video.videoImage.screenUrl).into(binding.detailImage)

        binding.fab.setOnClickListener {
//            val action = VideoDetailsFragmentDirections.actionVideoDetailsFragmentToVideoViewFragment(videoDetailsViewModel.video)
//            it.findNavController().navigate(action)

//            val action = VideoDetailsFragmentDirections.actionVideoDetailsFragmentToMediaSessionPlaybackActivity(videoDetailsViewModel.video)
//            it.findNavController().navigate(action)

            val intent = Intent(context, VideoActivity::class.java)
            intent.putExtra(VideoActivity.ARG_VIDEO_URL, videoDetailsViewModel.getVideoUrl())
            startActivity(intent)
        }

        binding.appbar.setExpanded(false)

        return binding.root
    }
}