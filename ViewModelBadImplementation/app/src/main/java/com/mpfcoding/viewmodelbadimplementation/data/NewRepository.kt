package com.mpfcoding.viewmodelbadimplementation.data

import android.content.Context
import com.mpfcoding.viewmodelbadimplementation.db.NewsDatabase
import com.mpfcoding.viewmodelbadimplementation.db.NewsEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class NewsRepository {

    suspend fun saveNews(context: Context, newsList: List<NewsEntity>) {
        val database = NewsDatabase.getDatabase(context)
        withContext(Dispatchers.IO) {
            database.newsDao().clearAll()
            database.newsDao().insertAll(newsList)
        }
    }

    fun getNews(context: Context): Flow<List<NewsEntity>> {
        val database = NewsDatabase.getDatabase(context)
        return database.newsDao().getAll()
    }
}