package com.example.mvvmsamplewithhiltandcoroutine.view.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvmsamplewithhiltandcoroutine.databinding.ItemCharacterBinding
import com.example.mvvmsamplewithhiltandcoroutine.databinding.ItemVideoBinding
import com.example.mvvmsamplewithhiltandcoroutine.model.Result
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.SimpleExoPlayer
import kotlin.coroutines.coroutineContext

class VideoListAdapter(context: Context) :
    RecyclerView.Adapter<VideoListAdapter.VideoViewHolder>() {

    private val itemList = ArrayList<String>()
    private var simpleExoPlayer: SimpleExoPlayer? = null
    private var mediaItem: MediaItem? = null

    init {
        simpleExoPlayer = SimpleExoPlayer.Builder(context).build()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoViewHolder {
        return VideoViewHolder(
            ItemVideoBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: VideoViewHolder, position: Int) {
        holder.bind(itemList[position])
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    fun submitList(itemList: ArrayList<String>) {
        this.itemList.apply {
            clear()
            addAll(itemList)
        }
        notifyDataSetChanged()
    }

    inner class VideoViewHolder(private val itemVideoBinding: ItemVideoBinding) :
        RecyclerView.ViewHolder(itemVideoBinding.root) {
        fun bind(videoUrl: String) {
            mediaItem = MediaItem.fromUri(videoUrl)
            itemVideoBinding.pvVideo.player = simpleExoPlayer
            simpleExoPlayer?.setMediaItem(mediaItem!!)
            simpleExoPlayer?.playWhenReady = true
            simpleExoPlayer?.prepare()
        }
    }
}