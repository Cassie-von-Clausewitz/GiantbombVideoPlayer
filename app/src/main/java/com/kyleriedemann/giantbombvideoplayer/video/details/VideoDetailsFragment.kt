package com.kyleriedemann.giantbombvideoplayer.video.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.kyleriedemann.giantbombvideoplayer.databinding.FragmentVideoDetailsBinding
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

class VideoDetailsFragment: Fragment() {
    val videoDetailsViewModel by inject<VideoDetailsViewModel> { parametersOf(VideoDetailsFragmentArgs.fromBundle(arguments).video) }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = FragmentVideoDetailsBinding.inflate(inflater, container, false)
        binding.viewModel = videoDetailsViewModel
        Glide.with(context!!).load(videoDetailsViewModel.video.videoImage.screenUrl).into(binding.detailImage)
        return binding.root
    }
}