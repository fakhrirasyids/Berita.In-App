package com.fakhrirasyids.core.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "news")
data class NewsEntity(
    @PrimaryKey
    @ColumnInfo(name = "title")
    var title: String,

    @ColumnInfo(name = "url")
    var url: String?,

    @ColumnInfo(name = "urlToImage")
    var urlToImage: String?,

    @ColumnInfo(name = "description")
    var description: String?,

    @ColumnInfo(name = "publishedAt")
    var publishedAt: String?,

    @ColumnInfo(name = "newsType")
    var newsType: String?,

    @ColumnInfo(name = "isLatest")
    var isLatest: Boolean = false,

    @ColumnInfo(name = "isFavorite")
    var isFavorite: Boolean = false
)
