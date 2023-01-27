package com.fakhrirasyids.beritain.ui.main.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.fakhrirasyids.core.data.Resource
import com.fakhrirasyids.core.domain.model.News
import com.fakhrirasyids.core.domain.usecase.NewsUseCase

class SearchViewModel(private val newsUseCase: NewsUseCase) : ViewModel() {
    val isLoading: MutableLiveData<Boolean> = MutableLiveData<Boolean>()
    val isError: MutableLiveData<Boolean> = MutableLiveData<Boolean>()

    var searchedNews: LiveData<Resource<List<News>>> = MutableLiveData()

    fun searchNews(query: String): LiveData<Resource<List<News>>> {
        searchedNews = newsUseCase.searchNews(query).asLiveData()
        return searchedNews
    }
}