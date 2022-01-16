package com.example.youtubeapi.ui.video

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.youtubeapi.App
import com.example.youtubeapi.core.network.result.Resource
import com.example.youtubeapi.core.ui.BaseViewModel
import com.example.youtubeapi.models.PlayList
import com.example.youtubeapi.repository.Repository

class VideoViewModel(private val repository: Repository) : BaseViewModel() {

    var loading = MutableLiveData<Boolean>()

    fun getPlaylistsVideo(playlistId: String): LiveData<Resource<PlayList>> {
        return repository.getPlaylistItem(playlistId)
    }
}