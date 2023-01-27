package com.fakhrirasyids.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.fakhrirasyids.core.domain.usecase.NewsUseCase

class FavoriteViewModel(newsUseCase: NewsUseCase) : ViewModel() {
    val favoriteTourism = newsUseCase.getFavoriteNews().asLiveData()
}