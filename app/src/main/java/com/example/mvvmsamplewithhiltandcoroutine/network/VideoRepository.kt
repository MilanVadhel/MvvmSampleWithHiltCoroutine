package com.example.mvvmsamplewithhiltandcoroutine.network

interface VideoRepository {
    suspend fun getVideosList() : ArrayList<String>
}