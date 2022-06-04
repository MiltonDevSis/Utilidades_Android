package com.mpfcoding.testhilt.glide

import android.widget.ImageView
import com.bumptech.glide.Glide
import javax.inject.Inject

class GlideImageLoaderImpl @Inject constructor() : ImageLoader {

    override fun load(imageView: ImageView, imageUrl: String) {
        Glide.with(imageView.rootView)
            .load(imageUrl)
            .into(imageView)
    }
}