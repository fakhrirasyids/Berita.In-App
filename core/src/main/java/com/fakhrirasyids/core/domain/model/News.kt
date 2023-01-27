package com.fakhrirasyids.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class News(
    val title: String?,
    val url: String?,
    val urlToImage: String?,
    val description: String?,
    val publishedAt: String?,
    val newsType: String?,
    val isLatest: Boolean = false,
    var isFavorite: Boolean = false
) : Parcelable