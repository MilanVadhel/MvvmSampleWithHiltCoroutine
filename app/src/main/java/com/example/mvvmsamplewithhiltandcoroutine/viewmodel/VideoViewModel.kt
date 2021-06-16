package com.example.mvvmsamplewithhiltandcoroutine.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mvvmsamplewithhiltandcoroutine.network.VideoRepository
import com.example.mvvmsamplewithhiltandcoroutine.view.common.SingleLiveEvent
import com.example.mvvmsamplewithhiltandcoroutine.view.common.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class VideoViewModel @Inject constructor(
    private val videoRepository: VideoRepository
) : ViewModel() {

    private val TAG = "VideoViewModel"
    private var isFirstTimeCalled = false
    private val _videoListLiveEvent = SingleLiveEvent<UiState<ArrayList<String>>>()
    val videoListLiveEvent: LiveData<UiState<ArrayList<String>>> get() = _videoListLiveEvent

    fun getVideoList() {
        Log.d(TAG, "isFirstTimeCalled -> $isFirstTimeCalled")
        if (!isFirstTimeCalled) {
            _videoListLiveEvent.value = UiState.Loading()
            viewModelScope.launch {
                try {
                    _videoListLiveEvent.value =
                        UiState.Success(videoRepository.getVideosList())
                } catch (e: Exception) {
                    _videoListLiveEvent.value = UiState.Error(e)
                }
            }
            isFirstTimeCalled = true
        }
    }
}