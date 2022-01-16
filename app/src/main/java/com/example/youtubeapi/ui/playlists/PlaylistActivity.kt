package com.example.youtubeapi.ui.playlists

import android.content.Intent
import android.view.LayoutInflater
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.youtubeapi.R
import com.example.youtubeapi.core.network.result.Status
import com.example.youtubeapi.core.ui.BaseActivity
import com.example.youtubeapi.databinding.ActivityPlaylistBinding
import com.example.youtubeapi.extensions.showToast
import com.example.youtubeapi.models.Items
import com.example.youtubeapi.ui.video.VideoActivity
import com.example.youtubeapi.utils.Connectivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class PlaylistActivity : BaseActivity<PlaylistViewModel, ActivityPlaylistBinding>() {

    private val adapter: PlaylistAdapter by lazy {
        PlaylistAdapter(list)
    }
    private var list = mutableListOf<Items>()
    override val viewModel: PlaylistViewModel by viewModel()

    override fun initView() {
        super.initView()
        checkInet()
    }

    override fun initListener() {
        super.initListener()
        binding.clInternet.btnAgain.setOnClickListener {
            checkInet()
        }
    }

    override fun initViewModel() {
        super.initViewModel()

        viewModel.loading.observe(this) {
            binding.progressBar.isVisible = it
        }

        viewModel.getPlaylist().observe(this) {
            when (it.status) {
                Status.LOADING -> viewModel.loading.postValue(true)
                Status.SUCCESS -> {
                    viewModel.loading.postValue(false)
                    initAdapter(it.data?.items as MutableList<Items>)
                    adapter.setOnClick(object : PlaylistAdapter.OnClick {
                        override fun onClicked(position: Items) {
                            Intent(this@PlaylistActivity, VideoActivity::class.java).apply {
                                putExtra("idKey", position.id)
                                putExtra("title", position.snippet.title)
                                putExtra("description", position.snippet.description)
                                startActivity(this)
                            }
                        }
                    })
                }
                Status.ERROR -> {
                    viewModel.loading.postValue(false)
                    showToast(getString(R.string.error))
                }
            }
        }
    }


    override fun inflateVB(inflater: LayoutInflater): ActivityPlaylistBinding {
        binding = ActivityPlaylistBinding.inflate(inflater)
        return binding
    }

    private fun initAdapter(list: MutableList<Items>) {
        this.list = list
        binding.rvPlaylists.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = this@PlaylistActivity.adapter
        }
    }

    private fun checkInet() {
        val connect = Connectivity(this)
        connect.observe(this, { isConnected ->
            if (isConnected) {
                viewModel.loading.observe(this) {
                    binding.progressBar.isVisible = it
                }
                binding.rvPlaylists.isVisible = true
                binding.clInternet.containerCl.isVisible = false
                viewModel.loading.postValue(false)
                viewModel.getPlaylist().observe(this) {
                    when (it.status) {
                        Status.LOADING -> viewModel.loading.postValue(true)
                        Status.SUCCESS -> {
                            viewModel.loading.postValue(false)
                            showToast(getString(R.string.connected))
                        }
                        Status.ERROR -> {
                            viewModel.loading.postValue(false)
                            binding.clInternet.containerCl.isVisible = true
                        }
                    }
                }
            } else {
                binding.rvPlaylists.isVisible = false
                binding.clInternet.containerCl.isVisible = true
                showToast(getString(R.string.disconnected))
            }
        })
    }
}
