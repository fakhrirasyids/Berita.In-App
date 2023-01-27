package com.fakhrirasyids.beritain.ui.main.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.fakhrirasyids.core.domain.usecase.NewsUseCase

class HomeViewModel(newsUseCase: NewsUseCase) : ViewModel() {
    val latestNews = newsUseCase.getLatestNews().asLiveData()
    val businessNews = newsUseCase.getBusinessNews().asLiveData()
    val entertainmentNews = newsUseCase.getEntertainmentNews().asLiveData()
    val healthNews = newsUseCase.getHealthNews().asLiveData()
    val scienceNews = newsUseCase.getScienceNews().asLiveData()
    val sportsNews = newsUseCase.getSportsNews().asLiveData()
    val technologyNews = newsUseCase.getTechnologyNews().asLiveData()
}