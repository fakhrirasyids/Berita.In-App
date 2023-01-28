package com.fakhrirasyids.core.utils

import com.fakhrirasyids.core.domain.model.News

object DataDummy {
    fun generateDummyLatestNewsList(): List<News> {
        val newNewsList = mutableListOf<News>()

        for (i in 0..10) {
            val news = News(
                title = "title",
                urlToImage = "image_url",
                url = "url",
                description = "desc",
                publishedAt = "time",
                newsType = "newsType",
                isLatest = true,
                isFavorite = false
            )
            newNewsList.add(news)
        }

        return newNewsList
    }

    fun generateDummyCategoryNewsList(): List<News> {
        val newNewsList = mutableListOf<News>()

        for (i in 0..5) {
            val news = News(
                title = "title",
                urlToImage = "image_url",
                url = "url",
                description = "desc",
                publishedAt = "time",
                newsType = "newsType",
                isFavorite = false
            )
            newNewsList.add(news)
        }

        return newNewsList
    }
}