package com.example.mvvmsamplewithhiltandcoroutine.model.video

data class Video(
    val description: String,
    val sources: List<String>,
    val subtitle: String,
    val thumb: String,
    val title: String
)