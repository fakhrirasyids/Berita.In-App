package com.fakhrirasyids.core.data.local

import com.fakhrirasyids.core.data.local.entity.NewsEntity
import com.fakhrirasyids.core.data.local.room.NewsDao
import kotlinx.coroutines.flow.Flow

class LocalDataSource(private val newsDao: NewsDao) {

    fun getLatestNews(): Flow<List<NewsEntity>> = newsDao.getLatestNews()

    fun getNewsByCategory(newsType: String): Flow<List<NewsEntity>> = newsDao.getAllNews(newsType)

    fun getFavoriteNews(): Flow<List<NewsEntity>> = newsDao.getFavoriteNews()

    suspend fun insertNews(newsList: List<NewsEntity>) {
        for (news in newsList) {
            newsDao.insertNews(news)
        }
    }

    fun setFavoriteNews(news: NewsEntity, newState: Boolean) {
        news.isFavorite = newState
        newsDao.updateFavoriteNews(news)
    }
}