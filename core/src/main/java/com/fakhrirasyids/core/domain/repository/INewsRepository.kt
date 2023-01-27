package com.fakhrirasyids.core.domain.repository

import com.fakhrirasyids.core.data.Resource
import com.fakhrirasyids.core.domain.model.News
import kotlinx.coroutines.flow.Flow

interface INewsRepository {

    fun getLatestNews(): Flow<Resource<List<News>>>

    fun getBusinessNews(): Flow<Resource<List<News>>>

    fun getEntertainmentNews(): Flow<Resource<List<News>>>

    fun getHealthNews(): Flow<Resource<List<News>>>

    fun getScienceNews(): Flow<Resource<List<News>>>

    fun getSportsNews(): Flow<Resource<List<News>>>

    fun getTechnologyNews(): Flow<Resource<List<News>>>

    fun searchNews(query: String): Flow<Resource<List<News>>>

    fun getFavoriteNews(): Flow<List<News>>

    fun setFavoriteNews(news: News, state: Boolean)
}