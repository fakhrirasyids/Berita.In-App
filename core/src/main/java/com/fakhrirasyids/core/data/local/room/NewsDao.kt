package com.fakhrirasyids.core.data.local.room

import androidx.room.*
import com.fakhrirasyids.core.data.local.entity.NewsEntity
import com.fakhrirasyids.core.utils.Constants.SEARCHED_NEWS
import kotlinx.coroutines.flow.Flow


@Dao
interface NewsDao {

    @Query("SELECT * FROM news WHERE newsType =:newsType")
    fun getAllNews(newsType: String): Flow<List<NewsEntity>>

    @Query("SELECT * FROM news WHERE isLatest = 1")
    fun getLatestNews(): Flow<List<NewsEntity>>

    @Query("SELECT * FROM news WHERE isFavorite = 1")
    fun getFavoriteNews(): Flow<List<NewsEntity>>

    @Query("DELETE FROM news WHERE newsType = 'searched'")
    fun refreshSearchedNews()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(news: List<NewsEntity>)

    @Transaction
    suspend fun insertNews(news: List<NewsEntity>) {
        if (news[0].newsType == SEARCHED_NEWS) {
            refreshSearchedNews()
        }
        insert(news)
    }

    @Update
    fun updateFavoriteNews(news: NewsEntity)
}