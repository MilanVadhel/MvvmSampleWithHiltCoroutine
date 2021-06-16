package com.example.mvvmsamplewithhiltandcoroutine.view

import android.media.MediaDataSource
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.media2.exoplayer.external.ExoPlayerFactory
import androidx.media2.exoplayer.external.source.ExtractorMediaSource
import com.example.mvvmsamplewithhiltandcoroutine.databinding.FragmentVideoListBinding
import com.example.mvvmsamplewithhiltandcoroutine.extension.hide
import com.example.mvvmsamplewithhiltandcoroutine.extension.show
import com.example.mvvmsamplewithhiltandcoroutine.view.common.UiState
import com.example.mvvmsamplewithhiltandcoroutine.viewmodel.VideoViewModel
import com.google.android.exoplayer2.MediaItem
import dagger.hilt.android.AndroidEntryPoint


import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.extractor.ExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.MediaSourceFactory
import com.google.android.exoplayer2.trackselection.*
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.android.exoplayer2.upstream.*
import com.google.android.exoplayer2.util.Util


@AndroidEntryPoint
class VideoListFragment : Fragment() {

    private val TAG = "VideoListFragment"
    private var _binding: FragmentVideoListBinding? = null
    private val binding get() = _binding!!
    private val viewModel: VideoViewModel by viewModels()

    private var simpleExoPlayer: SimpleExoPlayer? = null
    private var mediaItem: MediaItem? = null

    /* private val videoListAdapter by lazy {
         VideoListAdapter(requireContext())
     }*/

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentVideoListBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //initRecyclerView()
        loadData()
        initLiveDataObserver()
    }

    private fun initRecyclerView() {
        //binding.rvVideos.adapter = videoListAdapter
    }

    private fun loadData() {
        viewModel.getVideoList()
    }

    private fun initLiveDataObserver() {
        viewModel.videoListLiveEvent.observe(viewLifecycleOwner, {
            when (it) {
                is UiState.Error -> {
                    Log.e(TAG, "Error ->  ", it.throwable)
                    binding.pbData.hide()
                }
                is UiState.Loading -> {
                    binding.pbData.show()
                }
                is UiState.Success -> {
                    // videoListAdapter.submitList(it.data)
                    binding.pbData.hide()
                }
            }
        })
    }

    override fun onStart() {
        super.onStart()
        initPlayer()
    }

    @RequiresApi(Build.VERSION_CODES.M)
    fun initPlayer() {
        /*if (simpleExoPlayer == null) {
            simpleExoPlayer = SimpleExoPlayer.Builder(requireContext()).build()
            binding.pvVideo.player = simpleExoPlayer
            simpleExoPlayer?.setMediaItem(MediaItem.fromUri("http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4"))
            simpleExoPlayer?.playWhenReady = true
            simpleExoPlayer?.prepare()
            simpleExoPlayer?.play()
        }*/

        val bandwidthMeter: BandwidthMeter = DefaultBandwidthMeter()
        val videoTrackSelectionFactory: ExoTrackSelection.Factory = ExoTrackSelection.Factory(bandwidthMeter)
        val trackSelector: TrackSelector = DefaultTrackSelector(videoTrackSelectionFactory)

//Initialize the player

//Initialize the player
        simpleExoPlayer = SimpleExoPlayer.Builder(requireContext()).build()

//Initialize simpleExoPlayerView

//Initialize simpleExoPlayerView

        binding.pvVideo.player = simpleExoPlayer

// Produces DataSource instances through which media data is loaded.

// Produces DataSource instances through which media data is loaded.
        val dataSourceFactory: DataSource.Factory =
            DefaultDataSourceFactory(requireContext(), Util.getUserAgent(requireContext(), "CloudinaryExoplayer"))

// Produces Extractor instances for parsing the media data.

// Produces Extractor instances for parsing the media data.
        val extractorsFactory: ExtractorsFactory = DefaultExtractorsFactory()

// This is the MediaSource representing the media to be played.

// This is the MediaSource representing the media to be played.
        val videoUri: Uri = Uri.parse("any Cloudinary URL")
        val videoSource: MediaDataSource = MediaDataSource(
            videoUri,
            dataSourceFactory, extractorsFactory, null, null
        )

// Prepare the player with the source.

// Prepare the player with the source.
        simpleExoPlayer?.prepare(videoSource)

    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume: ${simpleExoPlayer?.isPlaying}")
    }
    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}