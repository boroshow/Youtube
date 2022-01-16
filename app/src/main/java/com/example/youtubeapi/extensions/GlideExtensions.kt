package com.example.youtubeapi.extensions

import android.widget.ImageView
import com.bumptech.glide.Glide

fun ImageView.glide(uri: String){
    Glide.with(this).load(uri).into(this)
}