package com.fakhrirasyids.core.data.remote.network

import com.fakhrirasyids.core.data.remote.response.NewsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("top-headlines")
    suspend fun getLatestNews(
        @Query("country") country: String
    ): NewsResponse

    @GET("top-headlines")
    suspend fun getLatestCategoryNews(
        @Query("country") country: String,
        @Query("category") category: String
    ): NewsResponse

    @GET("everything")
    suspend fun getNewsBySearch(
        @Query("q") news: String
    ): NewsResponse
}