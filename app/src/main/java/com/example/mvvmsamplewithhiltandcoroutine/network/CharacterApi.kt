package com.example.mvvmsamplewithhiltandcoroutine.network

import com.example.mvvmsamplewithhiltandcoroutine.model.CharacterResponse
import com.example.mvvmsamplewithhiltandcoroutine.network.ApiConstants.CHARACTER_LIST
import retrofit2.http.GET
import retrofit2.http.Query

interface CharacterApi {

    @GET(CHARACTER_LIST)
    suspend fun getCharacterList(@Query("page") pageNumber : Int) : CharacterResponse
}