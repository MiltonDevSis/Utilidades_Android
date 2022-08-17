package com.mpfcoding.viewmodelbadimplementation.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.mpfcoding.viewmodelbadimplementation.data.NewsRepository
import com.mpfcoding.viewmodelbadimplementation.domain.News
import kotlinx.coroutines.launch

class MainViewModel (
    private val newsRepository: NewsRepository
) : ViewModel() {

    val newsEvent: LiveData<List<News>> =
        newsRepository.allNews.asLiveData(viewModelScope.coroutineContext)

    init {
        viewModelScope.launch {
            newsRepository.getAndStoreNews()
        }
    }

    class MainViewModelFactory(
        private val newsRepository: NewsRepository
    ) : ViewModelProvider.Factory {

        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return modelClass.getConstructor(
                NewsRepository::class.java
            ).newInstance(newsRepository)
        }
    }
}