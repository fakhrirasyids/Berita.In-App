package com.fakhrirasyids.beritain.ui.detail

import androidx.lifecycle.ViewModel
import com.fakhrirasyids.core.domain.model.News
import com.fakhrirasyids.core.domain.usecase.NewsUseCase

class DetailViewModel(private val newsUseCase: NewsUseCase) : ViewModel() {
    fun setFavoriteTourism(news: News, newStatus: Boolean) =
        newsUseCase.setFavoriteNews(news, newStatus)
}