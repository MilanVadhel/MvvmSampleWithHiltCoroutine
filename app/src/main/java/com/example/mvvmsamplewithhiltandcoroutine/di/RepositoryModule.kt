package com.example.mvvmsamplewithhiltandcoroutine.di

import com.example.mvvmsamplewithhiltandcoroutine.network.CharacterRepository
import com.example.mvvmsamplewithhiltandcoroutine.network.CharacterRepositoryImpl
import com.example.mvvmsamplewithhiltandcoroutine.network.VideoRepository
import com.example.mvvmsamplewithhiltandcoroutine.network.VideoRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindCharacterRepository(characterRepositoryImpl: CharacterRepositoryImpl) : CharacterRepository

    @Binds
    @Singleton
    abstract fun bindVideoRepository(videoRepositoryImpl: VideoRepositoryImpl) : VideoRepository

}