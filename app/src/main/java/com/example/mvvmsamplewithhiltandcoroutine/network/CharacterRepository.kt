package com.example.mvvmsamplewithhiltandcoroutine.network

import com.example.mvvmsamplewithhiltandcoroutine.model.CharacterResponse

interface CharacterRepository {
    suspend fun getCharactersList(pageNumber : Int) : CharacterResponse
}