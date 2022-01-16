package com.example.youtubeapi.ui.playlists

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.youtubeapi.App
import com.example.youtubeapi.core.network.result.Resource
import com.example.youtubeapi.core.ui.BaseViewModel
import com.example.youtubeapi.models.PlayList
import com.example.youtubeapi.repository.Repository

class PlaylistViewModel(private val repository: Repository) : BaseViewModel() {

    var loading = MutableLiveData<Boolean>()

    fun getPlaylist(): LiveData<Resource<PlayList>> {
        return repository.getPlaylist()
    }
}