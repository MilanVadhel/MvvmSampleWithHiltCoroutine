package com.example.mvvmsamplewithhiltandcoroutine.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mvvmsamplewithhiltandcoroutine.model.CharacterResponse
import com.example.mvvmsamplewithhiltandcoroutine.network.CharacterRepository
import com.example.mvvmsamplewithhiltandcoroutine.view.common.SingleLiveEvent
import com.example.mvvmsamplewithhiltandcoroutine.view.common.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class CharacterViewModel @Inject constructor(
    private val characterRepository: CharacterRepository
) : ViewModel() {

    private val TAG = "CharacterViewModel"
    private var isFirstTimeCalled = false
    private val _characterListLiveEvent = SingleLiveEvent<UiState<CharacterResponse>>()
    val characterListLiveEvent: LiveData<UiState<CharacterResponse>> get() = _characterListLiveEvent

    fun getCharacterList() {
        Log.d(TAG, "isFirstTimeCalled -> $isFirstTimeCalled")
        if (!isFirstTimeCalled) {
            _characterListLiveEvent.value = UiState.Loading()
            viewModelScope.launch {
                try {
                    _characterListLiveEvent.value =
                        UiState.Success(characterRepository.getCharactersList(1))
                } catch (e: Exception) {
                    _characterListLiveEvent.value = UiState.Error(e)
                }
            }
            isFirstTimeCalled = true
        }
    }
}