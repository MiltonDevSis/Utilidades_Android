package com.mpfcoding.viewmodelbadimplementation.ui.main

import android.content.Context
import androidx.lifecycle.*
import com.mpfcoding.viewmodelbadimplementation.data.NewsRepository
import com.mpfcoding.viewmodelbadimplementation.db.NewsEntity
import com.mpfcoding.viewmodelbadimplementation.network.ApiService
import kotlinx.coroutines.launch

class MainViewModel(
    protected val context: Context,
    protected val newsRepository: NewsRepository,
    protected val apiService: ApiService
) : ViewModel() {

    val newsEvent: LiveData<List<NewsEntity>> =
        newsRepository.getNews(context).asLiveData()

    fun getNews() = viewModelScope.launch {
        val newsEntityList = apiService.getNews().news.map {
            NewsEntity(title = it)
        }

        newsRepository.saveNews(context, newsEntityList)
    }

    class MainViewModelFactory(
        private val context: Context,
        private val newsRepository: NewsRepository,
        private val apiService: ApiService
    ) : ViewModelProvider.Factory {

        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return modelClass.getConstructor(
                Context::class.java,
                NewsRepository::class.java,
                ApiService::class.java
            ).newInstance(context, newsRepository, apiService)
        }
    }
}