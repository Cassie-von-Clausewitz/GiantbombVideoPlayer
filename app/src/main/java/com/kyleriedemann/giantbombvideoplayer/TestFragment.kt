package com.kyleriedemann.giantbombvideoplayer

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.kyleriedemann.giantbombvideoplayer.databinding.FragmentTestBinding

class TestFragment: Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = FragmentTestBinding.inflate(inflater, container, false)

        binding.authButton.setOnClickListener {  }
        binding.onboardingButton.setOnClickListener {  }
        binding.videoButton.setOnClickListener {  }
        return binding.root
    }
}