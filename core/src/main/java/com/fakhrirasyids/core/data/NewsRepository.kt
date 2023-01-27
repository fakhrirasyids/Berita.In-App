package com.fakhrirasyids.core.data

import com.fakhrirasyids.core.data.local.LocalDataSource
import com.fakhrirasyids.core.data.remote.RemoteDataSource
import com.fakhrirasyids.core.data.remote.network.ApiResponse
import com.fakhrirasyids.core.data.remote.response.ArticlesItem
import com.fakhrirasyids.core.domain.model.News
import com.fakhrirasyids.core.domain.repository.INewsRepository
import com.fakhrirasyids.core.utils.AppExecutors
import com.fakhrirasyids.core.utils.Constants.BUSINESS_CATEGORY
import com.fakhrirasyids.core.utils.Constants.COUNTRY_ID
import com.fakhrirasyids.core.utils.Constants.ENTERTAINMENT_CATEGORY
import com.fakhrirasyids.core.utils.Constants.HEALTH_CATEGORY
import com.fakhrirasyids.core.utils.Constants.LATEST_NEWS
import com.fakhrirasyids.core.utils.Constants.SCIENCE_CATEGORY
import com.fakhrirasyids.core.utils.Constants.SEARCHED_NEWS
import com.fakhrirasyids.core.utils.Constants.SPORTS_CATEGORY
import com.fakhrirasyids.core.utils.Constants.TECHNOLOGY_CATEGORY
import com.fakhrirasyids.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class NewsRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : INewsRepository {

    override fun getLatestNews(): Flow<Resource<List<News>>> =
        object : NetworkBoundResource<List<News>, List<ArticlesItem>>() {
            override fun loadFromDB(): Flow<List<News>> {
                return localDataSource.getLatestNews().map {
                    DataMapper.mapEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: List<News>?): Boolean =
                data == null || data.isEmpty()

            override suspend fun createCall(): Flow<ApiResponse<List<ArticlesItem>>> =
                remoteDataSource.getLatestNews(COUNTRY_ID)

            override suspend fun saveCallResult(data: List<ArticlesItem>) {
                val newsList = DataMapper.mapResponsesToEntities(data, LATEST_NEWS)
                localDataSource.insertNews(newsList)
            }
        }.asFlow()

    override fun getBusinessNews(): Flow<Resource<List<News>>> =
        object : NetworkBoundResource<List<News>, List<ArticlesItem>>() {
            override fun loadFromDB(): Flow<List<News>> {
                return localDataSource.getNewsByCategory(BUSINESS_CATEGORY).map {
                    DataMapper.mapEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: List<News>?): Boolean =
                data == null || data.isEmpty()

            override suspend fun createCall(): Flow<ApiResponse<List<ArticlesItem>>> =
                remoteDataSource.getLatestCategoryNews(COUNTRY_ID, BUSINESS_CATEGORY)

            override suspend fun saveCallResult(data: List<ArticlesItem>) {
                val newsList = DataMapper.mapResponsesToEntities(data, BUSINESS_CATEGORY)
                localDataSource.insertNews(newsList)
            }
        }.asFlow()

    override fun getEntertainmentNews(): Flow<Resource<List<News>>> =
        object : NetworkBoundResource<List<News>, List<ArticlesItem>>() {
            override fun loadFromDB(): Flow<List<News>> {
                return localDataSource.getNewsByCategory(ENTERTAINMENT_CATEGORY).map {
                    DataMapper.mapEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: List<News>?): Boolean =
                data == null || data.isEmpty()

            override suspend fun createCall(): Flow<ApiResponse<List<ArticlesItem>>> =
                remoteDataSource.getLatestCategoryNews(COUNTRY_ID, ENTERTAINMENT_CATEGORY)

            override suspend fun saveCallResult(data: List<ArticlesItem>) {
                val newsList = DataMapper.mapResponsesToEntities(data, ENTERTAINMENT_CATEGORY)
                localDataSource.insertNews(newsList)
            }
        }.asFlow()

    override fun getHealthNews(): Flow<Resource<List<News>>> =
        object : NetworkBoundResource<List<News>, List<ArticlesItem>>() {
            override fun loadFromDB(): Flow<List<News>> {
                return localDataSource.getNewsByCategory(HEALTH_CATEGORY).map {
                    DataMapper.mapEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: List<News>?): Boolean =
                data == null || data.isEmpty()

            override suspend fun createCall(): Flow<ApiResponse<List<ArticlesItem>>> =
                remoteDataSource.getLatestCategoryNews(COUNTRY_ID, HEALTH_CATEGORY)

            override suspend fun saveCallResult(data: List<ArticlesItem>) {
                val newsList = DataMapper.mapResponsesToEntities(data, HEALTH_CATEGORY)
                localDataSource.insertNews(newsList)
            }
        }.asFlow()

    override fun getScienceNews(): Flow<Resource<List<News>>> =
        object : NetworkBoundResource<List<News>, List<ArticlesItem>>() {
            override fun loadFromDB(): Flow<List<News>> {
                return localDataSource.getNewsByCategory(SCIENCE_CATEGORY).map {
                    DataMapper.mapEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: List<News>?): Boolean =
                data == null || data.isEmpty()

            override suspend fun createCall(): Flow<ApiResponse<List<ArticlesItem>>> =
                remoteDataSource.getLatestCategoryNews(COUNTRY_ID, SCIENCE_CATEGORY)

            override suspend fun saveCallResult(data: List<ArticlesItem>) {
                val newsList = DataMapper.mapResponsesToEntities(data, SCIENCE_CATEGORY)
                localDataSource.insertNews(newsList)
            }
        }.asFlow()

    override fun getSportsNews(): Flow<Resource<List<News>>> =
        object : NetworkBoundResource<List<News>, List<ArticlesItem>>() {
            override fun loadFromDB(): Flow<List<News>> {
                return localDataSource.getNewsByCategory(SPORTS_CATEGORY).map {
                    DataMapper.mapEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: List<News>?): Boolean =
                data == null || data.isEmpty()

            override suspend fun createCall(): Flow<ApiResponse<List<ArticlesItem>>> =
                remoteDataSource.getLatestCategoryNews(COUNTRY_ID, SPORTS_CATEGORY)

            override suspend fun saveCallResult(data: List<ArticlesItem>) {
                val newsList = DataMapper.mapResponsesToEntities(data, SPORTS_CATEGORY)
                localDataSource.insertNews(newsList)
            }
        }.asFlow()

    override fun getTechnologyNews(): Flow<Resource<List<News>>> =
        object : NetworkBoundResource<List<News>, List<ArticlesItem>>() {
            override fun loadFromDB(): Flow<List<News>> {
                return localDataSource.getNewsByCategory(TECHNOLOGY_CATEGORY).map {
                    DataMapper.mapEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: List<News>?): Boolean =
                data == null || data.isEmpty()

            override suspend fun createCall(): Flow<ApiResponse<List<ArticlesItem>>> =
                remoteDataSource.getLatestCategoryNews(COUNTRY_ID, TECHNOLOGY_CATEGORY)

            override suspend fun saveCallResult(data: List<ArticlesItem>) {
                val newsList = DataMapper.mapResponsesToEntities(data, TECHNOLOGY_CATEGORY)
                localDataSource.insertNews(newsList)
            }
        }.asFlow()

    override fun searchNews(query: String): Flow<Resource<List<News>>> =
        object : NetworkBoundResource<List<News>, List<ArticlesItem>>() {
            override fun loadFromDB(): Flow<List<News>> {
                return localDataSource.getNewsByCategory(SEARCHED_NEWS).map {
                    DataMapper.mapEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: List<News>?): Boolean = true

            override suspend fun createCall(): Flow<ApiResponse<List<ArticlesItem>>> =
                remoteDataSource.getNewsBySearch(query)

            override suspend fun saveCallResult(data: List<ArticlesItem>) {
                val newsList = DataMapper.mapResponsesToEntities(data, SEARCHED_NEWS)
                localDataSource.insertNews(newsList)
            }
        }.asFlow()

    override fun getFavoriteNews(): Flow<List<News>> {
        return localDataSource.getFavoriteNews().map {
            DataMapper.mapEntitiesToDomain(it)
        }
    }

    override fun setFavoriteNews(news: News, state: Boolean) {
        val newsEntity = DataMapper.mapDomainToEntity(news)
        appExecutors.diskIO().execute { localDataSource.setFavoriteNews(newsEntity, state) }
    }
}
