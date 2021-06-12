package com.example.mvvmsamplewithhiltandcoroutine.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CharacterResponse(
    val info: Info,
    val results: List<Result>
) : Parcelable