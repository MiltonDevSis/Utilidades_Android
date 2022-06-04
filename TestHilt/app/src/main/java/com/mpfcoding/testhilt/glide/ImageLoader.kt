package com.mpfcoding.testhilt.glide

import android.widget.ImageView

interface ImageLoader {

    fun load(imageView: ImageView, imageUrl: String)
}