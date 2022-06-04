package com.mpfcoding.testhilt.di

import com.mpfcoding.testhilt.glide.GlideImageLoaderImpl
import com.mpfcoding.testhilt.glide.ImageLoader
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface ImageModule {

    // qual imageLoader eu quero injetar?  --> GlideImageLoaderImpl
    // para qual interface? --> ImageLoader

    @Binds
    fun bindImageLoader(imageLoader: GlideImageLoaderImpl): ImageLoader
}