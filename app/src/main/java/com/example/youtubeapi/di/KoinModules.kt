package com.example.youtubeapi.di

import com.example.youtubeapi.core.network.networkModule
import com.example.youtubeapi.data.remote.remoteDataSource

val koinModule = listOf(
    repoModules,
    viewModules,
    networkModule,
    remoteDataSource,
)