package com.example.youtubeapi.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.example.youtubeapi.core.network.result.Resource
import com.example.youtubeapi.data.remote.RemoteDataSource
import com.example.youtubeapi.models.PlayList
import kotlinx.coroutines.Dispatchers

class Repository(private val dataSource: RemoteDataSource) {

    fun getPlaylist(): LiveData<Resource<PlayList>> = liveData(Dispatchers.IO) {
        emit(Resource.loading())
        val responce = dataSource.getPlaylist()
        emit(responce)
    }

    fun getPlaylistItem(videoPlaylistId: String): LiveData<Resource<PlayList>> =
        liveData(Dispatchers.IO) {
            emit(Resource.loading())
            val responce = dataSource.getPlaylistItem(videoPlaylistId)
            emit(responce)
        }
}