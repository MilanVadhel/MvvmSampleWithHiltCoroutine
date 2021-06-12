package com.example.mvvmsamplewithhiltandcoroutine.network

import com.example.mvvmsamplewithhiltandcoroutine.model.CharacterResponse
import javax.inject.Inject

class CharacterRepositoryImpl @Inject constructor(
    private val characterApi: CharacterApi
) : CharacterRepository {

    override suspend fun getCharactersList(pageNumber : Int) : CharacterResponse {
        return characterApi.getCharacterList(pageNumber = pageNumber)
    }
}