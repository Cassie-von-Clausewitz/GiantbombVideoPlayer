package com.wyrmix.giantbombvideoplayer.video.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.wyrmix.giantbombvideoplayer.databinding.FragmentVideoListBinding
import com.wyrmix.giantbombvideoplayer.extension.GroupieAdapter
import com.wyrmix.giantbombvideoplayer.video.models.toVideoItem
import com.xwray.groupie.Section
import kotlinx.coroutines.experimental.Dispatchers
import kotlinx.coroutines.experimental.GlobalScope
import kotlinx.coroutines.experimental.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.Koin.Companion.logger

class VideoListFragment: Fragment() {
    private val viewModel by viewModel<VideoBrowserViewModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = FragmentVideoListBinding.inflate(inflater, container, false)

        val groupAdapter = GroupieAdapter()
        groupAdapter.setOnItemClickListener { item, view ->
            run {
                val video = (item as VideoItem).video
                val action = VideoListFragmentDirections.actionVideoListFragmentToVideoDetailsFragment(video)
                view.findNavController().navigate(action)
//                val i = Intent(this, VideoViewActivity::class.java)
//                if (Connectivity.isConnectedFast(context!!)) {
//                    i.putExtra("url", video.video?.highUrl)
//                } else {
//                    i.putExtra("url", video.video?.lowUrl)
//                }
//
//                startActivity(i)
            }
        }

        binding.recyclerView.adapter = groupAdapter
        binding.recyclerView.layoutManager = GridLayoutManager(context, groupAdapter.spanCount)

        GlobalScope.launch(Dispatchers.Unconfined) {
            val videos = viewModel.getVideos()
            logger.info("Videos [${videos.results.size}]")

            val shows = viewModel.getVideoShows()
            logger.info("Shows [${shows.results}]")

            val categories = viewModel.getVideoCategories()
            logger.info("Categories [${categories.results}]")

            val section = Section()
            section.addAll(videos.results.map { it.toVideoItem() })
            groupAdapter.add(section)

            logger.info("Added ${section.itemCount} to adapter")
        }

        return binding.root
    }
}

/*
private val viewModel: VideoBrowserViewModel by viewModel()
    private val logger: Logger by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video_list)

        val groupAdapter = GroupieAdapter()
        groupAdapter.setOnItemClickListener { item, view ->
            run {
                val video = item as VideoCard

                val i = Intent(this, VideoViewActivity::class.java)
                if (Connectivity.isConnectedFast(this)) {
                    i.putExtra("url", video.video?.highUrl)
                } else {
                    i.putExtra("url", video.video?.lowUrl)
                }

                startActivity(i)
            }
        }

        recyclerView.adapter = groupAdapter
        recyclerView.layoutManager = GridLayoutManager(this, groupAdapter.spanCount)

        launch(UI) {
            val videos = viewModel.getVideos().await()
            logger.info("Videos [$videos]")

            val section = Section()
            section.addAll(videos.results.map { it.toVideoItem() })
            groupAdapter.add(section)

            logger.info("Added ${section.itemCount} to adapter")
        }
    }
 */