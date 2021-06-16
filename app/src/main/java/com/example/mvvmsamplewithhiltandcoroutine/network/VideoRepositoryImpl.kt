package com.example.mvvmsamplewithhiltandcoroutine.network

import com.example.mvvmsamplewithhiltandcoroutine.model.CharacterResponse
import com.example.mvvmsamplewithhiltandcoroutine.utils.VideoDataUtils
import javax.inject.Inject

class VideoRepositoryImpl @Inject constructor(
) : VideoRepository {

    override suspend fun getVideosList(): ArrayList<String> {
        return VideoDataUtils.getVideos()
    }
}