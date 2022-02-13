package com.example.digitifymovieapp.utils

import android.util.Log
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("load")
fun loadImage(view: ImageView, url: String?) {

    Log.d("ImageUrl", "view"+ view)
    Log.d("ImageUrl", "url"+ url)

    url?.let {
        Glide.with(view).load("https://image.tmdb.org/t/p/w500${it}").into(view)
    }


}