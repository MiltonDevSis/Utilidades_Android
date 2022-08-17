package com.mpfcoding.viewmodelbadimplementation.data

import com.mpfcoding.viewmodelbadimplementation.db.NewsEntity
import com.mpfcoding.viewmodelbadimplementation.domain.News
import kotlinx.coroutines.flow.Flow

interface NewsRepository {

    val allNews: Flow<List<News>>

    suspend fun getAndStoreNews()

    suspend fun saveNews(newsList: List<NewsEntity>)
}