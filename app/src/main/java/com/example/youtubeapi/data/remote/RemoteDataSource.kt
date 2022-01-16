package com.example.youtubeapi.data.remote

import com.example.youtubeapi.BuildConfig
import com.example.youtubeapi.core.BaseDataSource
import com.example.youtubeapi.utils.`object`.Constant
import org.koin.dsl.module

val remoteDataSource = module {
    factory { RemoteDataSource(get()) }
}

class RemoteDataSource(private val youtubeApi: YoutubeApi) : BaseDataSource() {

    suspend fun getPlaylist() = getResult {
        youtubeApi.getPlaylists(Constant.PART, Constant.CHANNEL_ID, BuildConfig.API_KEY, 20)
    }

    suspend fun getPlaylistItem(videoPlaylistId: String) = getResult {
        youtubeApi.getPlaylistItems(Constant.PART, videoPlaylistId, BuildConfig.API_KEY, 20)
    }
}