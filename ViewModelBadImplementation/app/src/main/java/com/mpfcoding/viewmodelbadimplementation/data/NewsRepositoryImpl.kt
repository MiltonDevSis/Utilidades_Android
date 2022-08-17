package com.mpfcoding.viewmodelbadimplementation.data

import com.mpfcoding.viewmodelbadimplementation.db.NewsDao
import com.mpfcoding.viewmodelbadimplementation.db.NewsEntity
import com.mpfcoding.viewmodelbadimplementation.db.toDomain
import com.mpfcoding.viewmodelbadimplementation.network.ApiService
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

class NewsRepositoryImpl (
    private val dispatcher: CoroutineDispatcher,
    private val newsDao: NewsDao,
    private val apiService: ApiService
): NewsRepository {

    override val allNews = newsDao.getAll().map { newsEntityList ->
        newsEntityList.map {
            it.toDomain()
        }
    }

    override suspend fun getAndStoreNews() {
        val newEntityList = apiService.getNews().news.map { title ->
            NewsEntity(title = title)
        }

        saveNews(newEntityList)
    }

    override suspend fun saveNews(newsList: List<NewsEntity>) {
        withContext(dispatcher) {
            newsDao.clearAll()
            newsDao.insertAll(newsList)
        }
    }
}