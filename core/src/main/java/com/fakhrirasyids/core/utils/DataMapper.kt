package com.fakhrirasyids.core.utils

import com.fakhrirasyids.core.data.local.entity.NewsEntity
import com.fakhrirasyids.core.data.remote.response.ArticlesItem
import com.fakhrirasyids.core.domain.model.News
import com.fakhrirasyids.core.utils.Constants.LATEST_NEWS
import com.fakhrirasyids.core.utils.Constants.SEARCHED_NEWS

object DataMapper {
    fun mapResponsesToEntities(newsList: List<ArticlesItem>, newsType: String): List<NewsEntity> {

        val newEmptyList: List<ArticlesItem>
        val newNewsList = ArrayList<NewsEntity>()

        if (newsType == LATEST_NEWS || newsType == SEARCHED_NEWS) {
            newEmptyList = if (newsList.size >= 10) {
                newsList.filterIndexed { index, _ ->
                    index < 10
                }
            } else {
                newsList
            }

            if (newsType == LATEST_NEWS) {
                newEmptyList.map {
                    val news = NewsEntity(
                        title = it.title!!,
                        urlToImage = it.urlToImage,
                        url = it.url,
                        description = it.description,
                        publishedAt = it.publishedAt,
                        newsType = newsType,
                        isLatest = true,
                        isFavorite = false
                    )
                    newNewsList.add(news)
                }
            } else {
                newEmptyList.map {
                    val news = NewsEntity(
                        title = it.title!!,
                        urlToImage = it.urlToImage,
                        url = it.url,
                        description = it.description,
                        publishedAt = it.publishedAt,
                        newsType = newsType,
                        isFavorite = false
                    )
                    newNewsList.add(news)
                }
            }

            return newNewsList
        } else {
            newEmptyList = if (newsList.size >= 5) {
                newsList.filterIndexed { index, _ ->
                    index < 5
                }
            } else {
                newsList
            }

            newEmptyList.map {
                val news = NewsEntity(
                    title = it.title!!,
                    urlToImage = it.urlToImage,
                    url = it.url,
                    description = it.description,
                    publishedAt = it.publishedAt,
                    newsType = newsType,
                    isFavorite = false
                )
                newNewsList.add(news)
            }
            return newNewsList
        }
    }

    fun mapEntitiesToDomain(newsList: List<NewsEntity>): List<News> =
        newsList.map {
            News(
                title = it.title,
                urlToImage = it.urlToImage,
                url = it.url,
                description = it.description,
                publishedAt = it.publishedAt,
                newsType = it.newsType,
                isLatest = it.isLatest,
                isFavorite = it.isFavorite
            )
        }

    fun mapDomainToEntity(news: News) = NewsEntity(
        title = news.title!!,
        urlToImage = news.urlToImage,
        url = news.url,
        description = news.description,
        publishedAt = news.publishedAt,
        newsType = news.newsType,
        isLatest = news.isLatest,
        isFavorite = news.isFavorite
    )
}