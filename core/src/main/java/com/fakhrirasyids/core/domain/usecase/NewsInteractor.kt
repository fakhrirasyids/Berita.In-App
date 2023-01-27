package com.fakhrirasyids.core.domain.usecase

import com.fakhrirasyids.core.domain.model.News
import com.fakhrirasyids.core.domain.repository.INewsRepository

class NewsInteractor(private val newsRepository: INewsRepository) : NewsUseCase {

    override fun getLatestNews() = newsRepository.getLatestNews()

    override fun getBusinessNews() = newsRepository.getBusinessNews()

    override fun getEntertainmentNews() = newsRepository.getEntertainmentNews()

    override fun getHealthNews() = newsRepository.getHealthNews()

    override fun getScienceNews() = newsRepository.getScienceNews()

    override fun getSportsNews() = newsRepository.getSportsNews()

    override fun getTechnologyNews() = newsRepository.getTechnologyNews()

    override fun searchNews(query: String) = newsRepository.searchNews(query)

    override fun getFavoriteNews() = newsRepository.getFavoriteNews()

    override fun setFavoriteNews(news: News, state: Boolean) =
        newsRepository.setFavoriteNews(news, state)
}