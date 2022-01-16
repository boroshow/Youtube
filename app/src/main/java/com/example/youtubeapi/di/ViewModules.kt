package com.example.youtubeapi.di

import com.example.youtubeapi.ui.playlists.PlaylistViewModel
import com.example.youtubeapi.ui.video.VideoViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

val viewModules: Module = module {
    viewModel { PlaylistViewModel(get()) }
    viewModel { VideoViewModel(get()) }
}